package com.history.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT 登录授权过滤器
 */


public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 获取Header
        String authHeader = request.getHeader(tokenHeader);

        //存在token，但是不是tokenHead开头
        if(authHeader != null && authHeader.startsWith(tokenHead)){
            // 字段截取authToken
            String authToken = authHeader.substring(tokenHead.length());
            // 根据authToken获取 username
            String username = jwtTokenUtil.getUsernameFromToken(authToken);
            //token存在用户名，但是未登录
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                //登录
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // 验证token是否有效，如果有效，将他重新放到用户对象里。
                if(jwtTokenUtil.validateToken(authToken,userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken = new
                            UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                    // 重新设置到用户对象里
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));;
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

            }
        }
        filterChain.doFilter(request,response);
    }
}
