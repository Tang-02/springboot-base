package com.example.demo.config;


import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.demo.ExceptionClass.RequestException;
import com.example.demo.jwt.JwtUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author TJL
 * @date 2022/7/7
 */
public class JWTInterceptor implements HandlerInterceptor {


    JwtUtils jwtUtils = new JwtUtils();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        String token = request.getHeader("token");
        System.out.println("token"+token);
        if(Strings.isEmpty(token)){
            throw new RequestException(403,"token不能为空");
        }


        try {
            jwtUtils.verify(token);
        } catch (SignatureVerificationException e) {
            System.out.println("无效签名！ 错误 ->"+ e);
            return false;
        } catch (TokenExpiredException e) {
            System.out.println("token过期！ 错误 ->"+e);
            return false;
        } catch (AlgorithmMismatchException e) {
            System.out.println("token算法不一致！ 错误 ->"+e);
            return false;
        } catch (Exception e) {
            System.out.println("token无效！ 错误 ->"+e);
            return false;
        }

        return true;
    }
}
