package com.yuchu.dao;

import com.yuchu.domain.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by dell on 2017/8/24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao dao;
    @Test
    public void FindOneTest(){
        ProductCategory productCategory =dao.findOne(1);
        System.out.println(productCategory);
    }
    @Test
    @Transactional  //测试完后数据回滚
    public void saveTest(){
        ProductCategory productCategory = dao.findOne(6);
        productCategory.setCategoryName("女生热搜");
        ProductCategory result = dao.save(productCategory);
        Assert.assertNotNull(result);
        //Assert.assertNotEquals(null,result);
    }
    @Test
    public void findByCategoryTypeTest(){
        List<Integer> categoryTypeList = Arrays.asList(2,3,4);
        List<ProductCategory> results = dao.findByCategoryTypeIn(categoryTypeList);
        Assert.assertNotEquals(0,results.size());
    }
}