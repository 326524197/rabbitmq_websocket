package com.example.demo.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String msg="未知错误";
        if("Bad credentials".equals(exception.getMessage())){
            msg = "用户名或密码错误";
        }else if("User is disabled".equals(exception.getMessage())){
            msg = "该用户已被封禁";
        }
        out.write("{\"code\":333,\"msg\":\""+msg+"\"}");
        out.flush();
        out.close();
    }
}
