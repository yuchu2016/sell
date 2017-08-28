package com.yuchu.enums;

import lombok.Getter;

/**
 * @Author: yuchu
 * @Description: 返回前端的异常对象枚举
 * @Date: Create in 11:30  2017/8/28
 * @Modified By:
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),
    ;
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    ResultEnum(Integer code) {
        this.code = code;
    }
}
