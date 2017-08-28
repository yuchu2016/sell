package com.yuchu.dto;

import com.yuchu.domain.OrderDetail;
import com.yuchu.enums.OrderStatus;
import com.yuchu.enums.PayStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: yuchu
 * @Description:
 * @Date: Create in 11:13  2017/8/28
 * @Modified By:
 */
@Data
public class OrderDTO {

    /*订单Id*/
    private String orderId;

    /*买家名字*/
    private String buyerName;

    /*买家手机号码*/
    private String buyerPhone;

    /*买家地址*/
    private String buyerAddress;

    /*买家Openid*/
    private String buyerOpenid;

    /*订单总额*/
    private BigDecimal orderAmount;

    /*订单状态，默认新订单*/
    private  Integer orderStatus;

    /*支付状态，默认未支付*/
    private Integer payStatus;

    /*创建时间 */
    private Date createTime;

    /*修改时间*/
    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
