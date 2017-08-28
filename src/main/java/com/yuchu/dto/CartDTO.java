package com.yuchu.dto;

import lombok.Data;

/**
 * @Author: yuchu
 * @Description: 购物车
 * @Date: Create in 14:14  2017/8/28
 * @Modified By:
 */
@Data
public class CartDTO {
    /*商品Id*/
    private String productId;
    /*商品数量*/
    private Integer productQuantity;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
