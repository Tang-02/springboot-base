package com.example.demo.controller;


import com.auth0.jwt.interfaces.Claim;
import com.example.demo.ExceptionClass.RequestException;
import com.example.demo.jwt.JwtUtils;
import com.example.demo.model.User;
import com.example.demo.utils.RedisUtil;
import com.example.demo.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


@Api(tags = "测试接口")
@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

    @Resource
    JwtUtils jwtUtils;

    @Resource
    RedisUtil redisUtil;

    @ApiOperation(value = "token测试生成")
    @GetMapping("/token")
    public Result test1() throws RequestException {
        User user = new User();
        user.setId(1l);
        user.setUsername("tjl");
        String token = jwtUtils.getToken(user);
        Map<String, Claim> ClaimMap = jwtUtils.parseJwt(token);
        log.info("token中的用户id"+ClaimMap.get("id").asInt());
        log.info("token中的用户名"+ClaimMap.get("username").asString());
        return new Result(token);
    }

    @PostMapping("/redis")
    @ApiOperation(value = "redis存储测试")
    public Result redis(@RequestBody User user){
        redisUtil.setString("user",user,60);
        log.info("redis保存的信息:===="+String.valueOf(redisUtil.getObject("user")));
        return new Result(200);
    }
}
