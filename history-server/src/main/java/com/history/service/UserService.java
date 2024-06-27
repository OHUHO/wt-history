package com.history.service;

import com.github.pagehelper.PageInfo;
import com.history.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.history.utils.ResultBean;
import com.history.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CSL
 * @since 2023-02-14
 */
public interface UserService extends IService<User> {


    /**
     * 用户注册
     * @param username
     * @param password
     * @param request
     * @return
     */
    ResultBean register(String username, String password, HttpServletRequest request);


    /**
     * 通过用户名或取用户信息
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 用户登录
     * @param username
     * @param password
     * @param request
     * @return
     */
    ResultBean login(String username, String password, HttpServletRequest request);

    /**
     * 通过用户Id查询用户信息
     * @param userId
     * @return
     */
    User getUserByUserId(Long userId);

    /**
     * 修改用户信息
     * @param userVO
     * @return
     */
    ResultBean updateUserInfo(UserVO userVO);

    /**
     * 获取所有用户 搜索 分页
     * @param pageIndex
     * @param pageSize
     * @param searchInfo
     * @return
     */
    PageInfo<User> getAllUserByPageAndSearch(Integer pageIndex, Integer pageSize, String searchInfo);

    /**
     * 同通过用户id删除用户
     * @param userId
     * @return
     */
    ResultBean deleteUserByUserId(Long userId);

    /**
     * 通过用户id修改用户 alive
     * @param userId
     * @return
     */
    ResultBean updateUserAliveByUserId(Long userId);

    /**
     * 通过用户id批量删除用户
     * @param userIds
     * @return
     */
    ResultBean batchDeleteUsersByUserIds(List<Long> userIds);


    /**
     * 获取用户总数
     * @return
     */
    ResultBean getUserCount();
}
