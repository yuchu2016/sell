package com.yuchu.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Author: yuchu
 * @Description:订单详情表
 * @Date: Create in 9:37  2017/8/28
 * @Modified By:
 */
@Entity
@Data
public class OrderDetail {

    @Id
    private String detailId;

    /*订单Id*/
    private String orderId;

    /*商品Id*/
    private String productId;

    /*商品名称*/
    private String productName;

    /*单价*/
    private BigDecimal productPrice;

    /*商品数量*/
    private Integer productQuantity;

    /*商品图标*/
    private String productIcon;
}
