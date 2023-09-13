package com.example.demo.config;

import com.example.demo.ExceptionClass.RequestException;
import com.example.demo.utils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    // 处理 Exception 异常
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public Result exceptionHandler(RequestException e) {
        log.err('异常信息==================='+e.getMessage());
        return new Result(e.getCode(),e.getMessage());
    }
}
