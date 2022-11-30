package com.example.demo.controller;


import com.example.demo.jwt.JwtUtils;
import com.example.demo.model.User;
import com.example.demo.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@Api(tags = "登录接口")
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    JwtUtils jwtUtils;

    @ApiOperation(value = "登录")
    @PostMapping
    public Result login(@RequestBody User user){
        log.info(user.toString());
        return new Result(jwtUtils.getToken(user));
    }
}
