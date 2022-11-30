package com.example.demo.ExceptionClass;


import lombok.Data;

/**
 * @author TJL
 * @date 2022/7/7
 * 全局异常类
 */
@Data
public class RequestException extends Exception{

    private int code;

    public RequestException() {
        super();
    }
    public RequestException(String s) {
        super(s);
    }

    public RequestException(int code,String s) {
        super(s);
        this.code = code;
    }
}
