package com.yuchu.dao;

import com.yuchu.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: yuchu
 * @Description:
 * @Date: Create in 9:43  2017/8/28
 * @Modified By:
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail,String> {

    List<OrderDetail> findByOrderId(String orderId);
}
