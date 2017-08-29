package com.yuchu.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yuchu.domain.OrderDetail;
import com.yuchu.enums.OrderStatus;
import com.yuchu.enums.PayStatus;
import com.yuchu.utils.serializer.Date2LongSerializer;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /*修改时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
