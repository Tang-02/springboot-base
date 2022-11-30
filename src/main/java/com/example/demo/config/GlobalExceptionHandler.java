package com.example.demo.config;

import com.example.demo.ExceptionClass.RequestException;
import com.example.demo.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 处理 Exception 异常
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public Result exceptionHandler(RequestException e) {
        return new Result(e.getCode(),e.getMessage());
    }
}
