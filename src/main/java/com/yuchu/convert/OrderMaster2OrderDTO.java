package com.yuchu.convert;

import com.yuchu.domain.OrderMaster;
import com.yuchu.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: yuchu
 * @Description:
 * @Date: Create in 17:43  2017/8/28
 * @Modified By:
 */
public class OrderMaster2OrderDTO {

    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return  orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e->convert(e)).collect(Collectors.toList());
    }

}
