package com.yuchu.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @Author: yuchu
 * @Description:
 * @Date: Create in 9:31  2017/8/28
 * @Modified By:
 */
@Getter
public enum OrderStatus {
    NEW(0,"新订单"),
    FINISH(1,"完成"),
    CANCEL(2,"已取消"),
    ;
    private Integer code;

    private String message;

    OrderStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
