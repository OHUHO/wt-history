package com.history.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.history.config.security.JwtTokenUtil;
import com.history.entity.Admin;
import com.history.mapper.AdminMapper;
import com.history.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.history.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CSL
 * @since 2023-02-14
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenHead}")
    private String tokenHead;



    @Override
    public ResultBean login(String username, String password) {
        Admin admin = adminMapper.selectOne(
                new LambdaQueryWrapper<Admin>()
                        .eq(Admin::getUsername, username)
                        .eq(Admin::getPassword, password)
        );
        if (admin != null){
            admin.setPassword(null);
            return ResultBean.success("登录成功！",admin);
        }
        return ResultBean.error("用户名或密码不正确！");
    }
}
