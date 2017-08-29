package com.yuchu.controller;

import com.yuchu.VO.ResultVO;
import com.yuchu.convert.OrderForm2OrderDTO;
import com.yuchu.dto.OrderDTO;
import com.yuchu.enums.OrderStatus;
import com.yuchu.enums.ResultEnum;
import com.yuchu.exception.SellException;
import com.yuchu.form.OrderForm;
import com.yuchu.service.OrderService;
import com.yuchu.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yuchu
 * @Description:
 * @Date: Create in 8:53  2017/8/29
 * @Modified By:
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;
    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            log.error("[创建订单]参数不正确，orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTO.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("[创建订单]错误，购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);
        Map<String,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());
        return ResultVOUtil.success(map);

    }
    //订单列表

    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid")String openid,
                                         @RequestParam(value = "page",defaultValue = "0")Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size){
        if (StringUtils.isEmpty(openid)){
            log.error("[查询订单列表]openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid,request);
        return ResultVOUtil.success(orderDTOPage.getContent());


    }
    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId){
        //Todo 不安全的
        OrderDTO orderDTO = orderService.findOne(orderId);
        return  ResultVOUtil.success(orderDTO);
    }
    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancle(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId){

        //Todo 不安全的
        OrderDTO orderDTO = orderService.findOne(orderId);
        orderService.cancel(orderDTO);
        return ResultVOUtil.success();
    }
}
