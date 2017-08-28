package com.yuchu.enums;

import lombok.Getter;

/**
 * @Author: yuchu
 * @Description:
 * @Date: Create in 9:34  2017/8/28
 * @Modified By:
 */
@Getter
public enum PayStatus {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),
    ;
    private Integer code;

    private String message;

    PayStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
