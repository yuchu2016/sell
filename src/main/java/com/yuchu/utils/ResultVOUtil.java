package com.yuchu.utils;

import com.yuchu.VO.ResultVO;

/**
 * @Author: yuchu
 * @Description:
 * @Date: Create in 20:38  2017/8/27
 * @Modified By:
 */
public class ResultVOUtil {

    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }
    public static ResultVO error(Integer code,String message){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(message);
        resultVO.setData(null);
        return resultVO;
    }
}
