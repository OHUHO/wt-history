package com.history.service;

import com.history.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.history.utils.ResultBean;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CSL
 * @since 2023-02-14
 */
public interface AdminService extends IService<Admin> {


    /**
     * 管理员登录
     * @param username
     * @param password
     * @return
     */
    ResultBean login(String username, String password);
}
