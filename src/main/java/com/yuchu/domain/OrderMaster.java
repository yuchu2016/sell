package com.yuchu.domain;

import com.yuchu.enums.OrderStatus;
import com.yuchu.enums.PayStatus;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: yuchu
 * @Description: 订单主表
 * @Date: Create in 9:27  2017/8/28
 * @Modified By:
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    /*订单Id*/
    @Id
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
    private  Integer orderStatus = OrderStatus.NEW.getCode();

    /*支付状态，默认未支付*/
    private Integer payStatus = PayStatus.WAIT.getCode();

    /*创建时间 */
    private Date createTime;

    /*修改时间*/
    private Date updateTime;
}
