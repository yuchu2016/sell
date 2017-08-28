package com.yuchu.utils;

import java.util.Random;

/**
 * @Author: yuchu
 * @Description: 生成随机数
 * @Date: Create in 11:48  2017/8/28
 * @Modified By:
 */
public class KeyUtil {

    /**
     * 生成唯一的主键，格式：时间戳+随机数
     * @return
     */
    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
