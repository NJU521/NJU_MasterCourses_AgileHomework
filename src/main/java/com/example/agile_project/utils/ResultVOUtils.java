package com.example.agile_project.utils;

import com.example.agile_project.resultvo.ResultVO;
import org.springframework.http.HttpStatus;

import java.util.Collection;

public class ResultVOUtils {

    public static ResultVO<Object> ok() {
        return ok(null);
    }

    // @SuppressWarnings注解主要用在取消一些编译器产生的警告对代码左侧行列的遮挡，有时候这会挡住我们断点调试时打的断点。
    public static ResultVO<Object> ok(Object object) {
        ResultVO<Object> resultVo = new ResultVO<>();
        resultVo.setData(object);
        resultVo.setMessage(null);
        resultVo.setSuccess(true);
        resultVo.setStatus(HttpStatus.OK.value());
        if (object instanceof Collection) {
            resultVo.setTotal(((Collection) object).size());
        } else {
            resultVo.setTotal(null);
        }
        return resultVo;
    }

    public static ResultVO<Object> ok(Object object, Number total) {
        ResultVO<Object> resultVo = ok(object);
        resultVo.setTotal(total);
        return resultVo;
    }

    public static ResultVO<Object> fail(String message) {
        return fail(message, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    public static ResultVO<Object> fail(String message, HttpStatus httpStatus) {
        return fail(message, httpStatus.value(), null);
    }

    public static ResultVO<Object> fail(String message, Integer httpStatus) {
        return fail(message, httpStatus, null);
    }

    public static ResultVO<Object> fail(String message, HttpStatus httpStatus, Object data) {
        return fail(message, httpStatus.value(), data);
    }

    public static ResultVO<Object> fail(String message, Integer httpStatus, Object data) {
        ResultVO<Object> resultVo = new ResultVO<>();
        resultVo.setData(data);
        resultVo.setSuccess(false);
        resultVo.setMessage(message);
        resultVo.setStatus(httpStatus);
        resultVo.setTotal(null);
        return resultVo;
    }

}