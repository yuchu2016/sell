package com.yuchu.exception;

import com.yuchu.enums.ResultEnum;

/**
 * @Author: yuchu
 * @Description:
 * @Date: Create in 11:29  2017/8/28
 * @Modified By:
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code , String message) {
        super(message);
        this.code = code;
    }
}
