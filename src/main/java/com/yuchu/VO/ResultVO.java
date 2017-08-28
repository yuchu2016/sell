package com.yuchu.VO;

import lombok.Data;

/**
 * @Author: yuchu
 * @Description: http请求返回的最外层对象
 * @Date: Create in 19:10  2017/8/27
 * @Modified By:
 */
@Data
public class ResultVO<T> {

    /*错误码*/
    private Integer code;

    /*提示信息*/
    private String msg;

    /*返回对象*/
    private T data;
}
