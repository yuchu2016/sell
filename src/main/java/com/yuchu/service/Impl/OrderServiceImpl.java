package com.yuchu.service.Impl;

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
import org.hibernate.engine.internal.Collections;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderId(orderId);
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
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
