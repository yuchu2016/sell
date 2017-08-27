package com.yuchu.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @Author: yuchu
 * @Description:
 * @Date: Create in 16:22  2017/8/27
 * @Modified By:
 */
@Entity
@Data
public class ProductInfo {

    @Id
    private String productId;

    /*名字*/
    private String productName;

    /*商品价格*/
    private BigDecimal productPrice;

    /*商品库存*/
    private Integer productStock;

    /*商品描述*/
    private String productDescription;

    /*商品图标*/
    private String productIcon;

    /*商品状态*/
    private  Integer productStatus;

    /*类目编号*/
    private Integer categoryType;
}
