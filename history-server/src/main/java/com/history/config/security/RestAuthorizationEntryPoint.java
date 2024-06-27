package com.history.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.history.utils.ResultBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 当未登录时或者token失效时访问接口，自定义的返回结果
 */
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        // 通过response设置编码格式
        response.setCharacterEncoding("UTF-8");
        // 设置ContentType
        response.setContentType("application/json");

        // 输出流
        PrintWriter out = response.getWriter();

        ResultBean bean = ResultBean.error("尚未登录，请登录！");
        bean.setCode(401);
        out.write(new ObjectMapper().writeValueAsString(bean));
        out.flush();
        out.close();
    }
}
