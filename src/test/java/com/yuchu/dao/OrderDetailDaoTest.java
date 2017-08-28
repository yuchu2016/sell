package com.yuchu.dao;

import com.yuchu.domain.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: yuchu
 * @Description:
 * @Date: Create in 10:18  2017/8/28
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailDaoTest {
    @Autowired
    private OrderDetailDao dao;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("12345678910");
        orderDetail.setOrderId("111111112");
        orderDetail.setProductIcon("http://xxxxx.jpg");
        orderDetail.setProductId("111111112");
        orderDetail.setProductName("皮蛋瘦肉粥");
        orderDetail.setProductPrice(new BigDecimal(6.4));
        orderDetail.setProductQuantity(2);
        OrderDetail result =dao.save(orderDetail);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByOrderId() throws Exception {
        List<OrderDetail> result=dao.findByOrderId("111111111");
        Assert.assertNotEquals(0,result.size());
    }

}