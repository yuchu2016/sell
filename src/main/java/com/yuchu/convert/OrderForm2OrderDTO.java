package com.yuchu.convert;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yuchu.domain.OrderDetail;
import com.yuchu.dto.OrderDTO;
import com.yuchu.enums.ResultEnum;
import com.yuchu.exception.SellException;
import com.yuchu.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yuchu
 * @Description:
 * @Date: Create in 9:15  2017/8/29
 * @Modified By:
 */
@Slf4j
public class OrderForm2OrderDTO {

    public static OrderDTO convert(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("[对象转换]错误，string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
