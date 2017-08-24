package com.yuchu;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by dell on 2017/8/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
//@Data
public class LoggerTest {


    @Test
    public void test1(){
        String name = "yuchu";
        String password = "123456";
        log.info("name:{},password:{}",name,password);
        log.info("info...");
        log.error("err...");
        log.debug("debug...");
    }
}
