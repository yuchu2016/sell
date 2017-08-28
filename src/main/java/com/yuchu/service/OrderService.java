package com.yuchu.service;

import com.yuchu.domain.OrderMaster;
import com.yuchu.dto.OrderDTO;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author: yuchu
 * @Description: 订单service
 * @Date: Create in 11:07  2017/8/28
 * @Modified By:
 */
public interface OrderService {

    /**
     * 创建订单
     */
    OrderDTO create(OrderDTO orderDTO);

    /**
     * 查询单个订单
     */
    OrderDTO findOne(String orderId);
    /**
     * 查询订单列表
     */
    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);
    /**
     * 取消订单
     */
    OrderDTO cancel(OrderDTO orderDTO);
    /**
     * 完结订单
     */
    OrderDTO finish(OrderDTO orderDTO);
    /**
     * 支付订单
     */
    OrderDTO paid(OrderDTO orderDTO);
}
