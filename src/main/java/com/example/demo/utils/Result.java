package com.example.demo.utils;


import lombok.Data;

/**
 * @author TJL
 * @date 2022/7/7
 * 统一返回类
 */

@Data
public class Result {

    private Object data;
    private int code = 200;
    private String msg = "操作成功!";

    public Result(Object data) {
        this.data = data;
    }

    public Result(int stateCode) {
        code = stateCode;
    }

    public Result(int stateCode, String msg) {
        code = stateCode;
        this.msg = msg;
    }

    public Result(int stateCode, String msg, Object data ) {
        this.data = data;
        code = stateCode;
        this.msg = msg;
    }



    public Result(Object data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public Result(Object data, int stateCode) {
        this.data = data;
        code = stateCode;
    }
}