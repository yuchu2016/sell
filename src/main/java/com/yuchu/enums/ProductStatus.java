package com.yuchu.enums;

import lombok.Getter;

/**
 * @Author: yuchu
 * @Description:商品状态
 * @Date: Create in 17:21  2017/8/27
 * @Modified By:
 */
@Getter
public enum ProductStatus {
    UP(0,"在架"),
    DOWM(1,"下架")
    ;

    private Integer code;

    private String message;

    ProductStatus(Integer code,String message) {
        this.code = code;
        this.message = message;
    }

}
