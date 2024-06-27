package com.history.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.history.config.security.JwtTokenUtil;
import com.history.entity.User;
import com.history.mapper.UserMapper;
import com.history.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.history.utils.ResultBean;
import com.history.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CSL
 * @since 2023-02-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @Override
    public ResultBean register(String username, String password, HttpServletRequest request) {

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return ResultBean.error("用户名或密码不能为空");
        }
        // 判断用户是否存在
        User user = userMapper.selectOne(
                new LambdaQueryWrapper<User>()
                        .eq(User::getUsername, username)
        );
        if (user != null){
            return ResultBean.error("该用户已经存在，请登录！");
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = format.format(new Date());
        User newUser = new User();
        // 对密码进行加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        newUser.setUsername(username)
                .setPassword(bCryptPasswordEncoder.encode(password))
                // .setPassword(password)
                .setCreateTime(createTime)
                .setNickname("默认昵称");

        userMapper.insert(newUser);
        return ResultBean.success("注册成功！");
    }

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        if (StringUtils.isNoneBlank(username)){
            user = userMapper.selectOne(
                    new LambdaQueryWrapper<User>()
                            .eq(User::getUsername, username)
            );
        }
        return user;
    }

    @Override
    public ResultBean login(String username, String password, HttpServletRequest request) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (userDetails != null && passwordEncoder.matches(password,userDetails.getPassword())){

            //更新security 登录用户对象
            UsernamePasswordAuthenticationToken authenticationToken = new
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            // 生成token
            String token = jwtTokenUtil.generateToken(userDetails);
            HashMap<String, String> tokenMap = new HashMap<>();
            tokenMap.put("token",token);
            tokenMap.put("tokenHead",tokenHead);

            return ResultBean.success("登录成功！",tokenMap);

        }
        return ResultBean.error("用户名或密码不正确！");

    }

    @Override
    public User getUserByUserId(Long userId) {
        return userMapper.selectById(userId);
    }


    @Override
    public ResultBean updateUserInfo(UserVO userVO) {
        User user = new User();

        user.setUserId(userVO.getUserId())
                .setNickname(userVO.getNickname())
                .setAvatar(userVO.getAvatar());

        userMapper.updateById(user);
        return ResultBean.success("用户信息修改成功！");
    }

    @Override
    public PageInfo<User> getAllUserByPageAndSearch(Integer pageIndex, Integer pageSize, String searchInfo){

        PageHelper.startPage(pageIndex,pageSize);
        // List<User> users = userMapper.selectList(null);
        List<User> users = userMapper.selectList(
                new LambdaQueryWrapper<User>()
                        .like(User::getUsername,searchInfo)
                        .or()
                        .like(User::getNickname,searchInfo)
        );

        return new PageInfo<>(users);
    }

    @Override
    public ResultBean deleteUserByUserId(Long userId) {
        userMapper.deleteById(userId);
        return ResultBean.success("删除成功！");
    }

    @Override
    public ResultBean updateUserAliveByUserId(Long userId) {
        User user = userMapper.selectById(userId);

        User updateUser = new User();
        updateUser.setUserId(userId);
        if (user.getIsAlive() == 0){
            updateUser.setIsAlive(1);
        }else {
            updateUser.setIsAlive(0);
        }
        userMapper.updateById(updateUser);
        return ResultBean.success("成功修改用户状态！");
    }

    @Override
    public ResultBean batchDeleteUsersByUserIds(List<Long> userIds) {
        userMapper.deleteBatchIds(userIds);
        return ResultBean.success("批量删除用户成功！");
    }

    @Override
    public ResultBean getUserCount() {

        Long count = userMapper.selectCount(null);
        return ResultBean.success("获取成功！",count);
    }

}
