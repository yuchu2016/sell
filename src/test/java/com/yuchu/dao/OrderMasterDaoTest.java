package com.yuchu.dao;

import com.yuchu.domain.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @Author: yuchu
 * @Description:
 * @Date: Create in 9:45  2017/8/28
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterDaoTest {
    @Autowired
    private OrderMasterDao dao;

    private final  String OPENID="123123";
    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123457");
        orderMaster.setBuyerName("yuchu");
        orderMaster.setBuyerPhone("123456789123");
        orderMaster.setBuyerAddress("NIIT");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        OrderMaster result = dao.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest request = new PageRequest(0,3);
        Page<OrderMaster> result= dao.findByBuyerOpenid(OPENID,request);
        //System.out.println(result.getTotalElements());
        Assert.assertNotEquals(0,result.getTotalElements());
    }

}