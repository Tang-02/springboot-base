package com.example.demo.jwt;

import com.example.demo.ExceptionClass.RequestException;
import com.example.demo.model.User;
import com.alibaba.druid.util.StringUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Map;

@Component
public class JwtUtils {

    String key = "admin123456";

    /**
     * 获取token
     * @param u user
     * @return token
     */
    public  String getToken(User u) {
        Calendar instance = Calendar.getInstance();
        //默认令牌过期时间7天
        instance.add(Calendar.DATE, 7);
//        System.out.println("========================="+u);
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("id", u.getId())
                .withClaim("username", u.getUsername());

        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(key));
    }

    /**
     * 验证token合法性 成功返回token
     */
    public DecodedJWT verify(String token) throws RequestException {

        if(StringUtils.isEmpty(token)){
            throw new RequestException(401,"token不能为空");
        }

        JWTVerifier build = JWT.require(Algorithm.HMAC256(key)).build();
        return build.verify(token);
    }

    /**
     * 解析token 获取Claims
     *
     * @param token jwt生成的token
     * @return Claims
     */
    public Map<String, Claim> parseJwt(String token) throws RequestException {
        if(StringUtils.isEmpty(token)){
            throw new RequestException(401,"token不能为空");
        }

        JWTVerifier build = JWT.require(Algorithm.HMAC256(key)).build();
        return build.verify(token).getClaims();
    }
}
