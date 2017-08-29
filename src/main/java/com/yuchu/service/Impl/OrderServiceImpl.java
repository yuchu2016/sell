package com.yuchu.service.Impl;

import com.yuchu.convert.OrderMaster2OrderDTO;
import com.yuchu.dao.OrderDetailDao;
import com.yuchu.dao.OrderMasterDao;
import com.yuchu.domain.OrderDetail;
import com.yuchu.domain.OrderMaster;
import com.yuchu.domain.ProductInfo;
import com.yuchu.dto.CartDTO;
import com.yuchu.dto.OrderDTO;
import com.yuchu.enums.OrderStatus;
import com.yuchu.enums.PayStatus;
import com.yuchu.enums.ResultEnum;
import com.yuchu.exception.SellException;
import com.yuchu.service.OrderService;
import com.yuchu.service.ProductService;
import com.yuchu.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.internal.Collections;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: yuchu
 * @Description: 订单Service实现类
 * @Date: Create in 11:18  2017/8/28
 * @Modified By:
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService{
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailDao orderDetailDao;
    @Autowired
    private OrderMasterDao orderMasterDao;
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        /*订单的唯一编号*/
        String orderId = KeyUtil.getUniqueKey();
        /*订单总价*/
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        /*购物车列表*/
        //List<CartDTD> cartDTDList = new ArrayList<>();

        //1.查询商品的数量，价格等信息
        for (OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算订单总价
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailDao.save(orderDetail);
        }
        //3.写入数据库(order_master和order_detail)

        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatus.NEW.getCode());
        orderMaster.setPayStatus(PayStatus.WAIT.getCode());
        orderMasterDao.save(orderMaster);

        //4.扣库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e->new CartDTO(e.getProductId(),e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {

        OrderMaster orderMaster=orderMasterDao.findOne(orderId);
        if (orderMaster == null)
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList))
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage=orderMasterDao.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderDTO> orderDTOList= OrderMaster2OrderDTO.convert(orderMasterPage.getContent());
        Page<OrderDTO> orderDTOPage=new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //BeanUtils.copyProperties(orderDTO,orderMaster);
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatus.NEW.getCode())) {
            log.error("[取消订单]订单状态不正确，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatus.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if (updateResult == null){
            log.error("[取消订单]更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返还库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[取消订单]订单中无商品，orderDTD={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e->new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        //如果已支付，需要退款
        if (orderDTO.getPayStatus().equals(PayStatus.SUCCESS.getCode())){
            //TODO
        }

        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //1.判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatus.NEW.getCode())) {
            log.error("[完结订单]订单状态不正确，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //2.修改
        orderDTO.setOrderStatus(OrderStatus.FINISH.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if (updateResult == null){
            log.error("[完结订单]更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatus.NEW.getCode())) {
            log.error("[订单支付成功]订单状态不正确，orderId={},orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatus.WAIT.getCode())){
            log.error("[订单支付成功]订单状态不正确，orderDTD={},orderStatus={}", orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }

        //修改订单状态
        orderDTO.setPayStatus(PayStatus.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if (updateResult == null){
            log.error("[订单支付完成]更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
