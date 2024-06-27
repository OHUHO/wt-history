package com.history.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.history.entity.User;
import com.history.service.UserService;
import com.history.utils.ImageUtil;
import com.history.utils.ResultBean;
import com.history.vo.LoginVO;
import com.history.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.security.Principal;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CSL
 * @since 2023-02-14
 */
@Api(tags = "UserController")
@RestController
// @RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${storage.ip}")
    private String ip;

    @Value("${storage.port}")
    private String port;

    @Value("${storage.path}")
    private String basePath;


    @ApiOperation(value = "用户注册")
    @PostMapping("/common/register")
    public ResultBean register(@RequestBody LoginVO loginVO, HttpServletRequest request){
        System.out.println("loginVO = " + loginVO);
        if (loginVO != null){
            return userService.register(loginVO.getUsername(), loginVO.getPassword(), request);
        }
        return ResultBean.error("用户名或密码不能为空！");
    }


    @ApiOperation(value = "用户登录")
    @PostMapping("/common/login")
    public ResultBean login(@RequestBody LoginVO loginVO, HttpServletRequest request){
        if (loginVO != null){
            return userService.login(loginVO.getUsername(),loginVO.getPassword(),request);
        }
        return ResultBean.error("用户名或密码不能为空！");
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/common/logout")
    public ResultBean logout(){
        return ResultBean.success("已退出登录！");
    }


    @ApiOperation(value = "获取当前登录用户的信息")
    @GetMapping("/common/getUserInfo/null")
    public ResultBean getUserInfo(Principal principal){
        System.out.println("principal = " + principal);
        if (principal != null){
            String username = principal.getName();
            User user = userService.getUserByUsername(username);
            user.setPassword(null);
            return ResultBean.success("成功获取登录用户信息！",user);
        }
        return  ResultBean.error("获取当前登录用户失败！");
    }



    @ApiOperation(value = "保存用户头像并返回地址")
    @PostMapping("/user/updateAvatar")
    public String coverImg(@RequestParam("userId") Integer userId,
                           @RequestParam("image") String image) throws Exception {

        // 图片后缀
        String suffix="jpg";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());

        long time = System.currentTimeMillis();

        // 文件保存的路径
        String path = basePath + "user/" + "user-id-" + userId + "/info/" + date + "/avatar/" + time + "_." + suffix;

        // 同步到数据库中的路径(返回给前端的地址)
        String pathDB = "";

        File outFile = new File(path);
        if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
            // 创建文件夹
            outFile.getParentFile().mkdirs();
        }
        try {
            // 将前端传递的文件保存到本地服务器路径下
            ImageUtil.generateImage(image, path);


            pathDB = "http://" + ip + ":" + port + "/files/" + "user/" + "user-id-" + userId + "/info/" + date + "/avatar/" + time + "_." + suffix;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pathDB;

    }


    @ApiOperation(value = "修改用户信息")
    @PutMapping("/user/updateUserInfo")
    public ResultBean updateUserInfo(@RequestBody UserVO userVO){
        if (userVO != null){
            return userService.updateUserInfo(userVO);
        }
        return ResultBean.error("修改信息成功！");
    }


    @ApiOperation(value = "获取所有用户(搜索 + 分页)")
    @GetMapping("/admin/getAllUser/{params}")
    public ResultBean getAllUser(@PathVariable String params){
        if (StringUtils.isNoneBlank(params)){
            JSONObject jsonObject = JSONObject.parseObject(params);
            int pageIndex = jsonObject.getIntValue("pageIndex");
            int pageSize = jsonObject.getIntValue("pageSize");
            String searchInfo = jsonObject.getString("searchInfo");


            PageInfo<User> pageInfo = userService.getAllUserByPageAndSearch(pageIndex,pageSize,searchInfo);
            return ResultBean.success("成功获取！",pageInfo);
        }
        return ResultBean.error("参数不能为空");
    }


    @ApiOperation(value = "通过用户id删除用户")
    @DeleteMapping("/admin/deleteUserByUserId/{userId}")
    public ResultBean deleteUserByUserId(@PathVariable Long userId){
        if (userId != null){
            return userService.deleteUserByUserId(userId);
        }
        return ResultBean.error("用户id不能为空！");
    }


    @ApiOperation(value = "通过用户id修改用户alive值")
    @PutMapping("/admin/updateUserAliveByUserId/{userId}")
    public ResultBean updateUserAliveByUserId(@PathVariable Long userId){
        if (userId != null){
            return userService.updateUserAliveByUserId(userId);
        }
        return ResultBean.success("修改失败！");
    }


    @ApiOperation(value = "通过用户id批量删除用户")
    @DeleteMapping("/admin/batchDeleteUsersByUserIds/{userIds}")
    public ResultBean batchDeleteUsersByUserIds(@PathVariable String userIds){
        if (StringUtils.isNoneBlank(userIds)){

            List<String> list = Arrays.asList(userIds.split(","));

            List<Long> userIds1 = new ArrayList<>();
            list.forEach(userid->{
                userIds1.add(Long.valueOf(userid));
            });

            return userService.batchDeleteUsersByUserIds(userIds1);
        }
        return ResultBean.error("参数不能为空！");
    }


    @ApiOperation(value = "获取用户总数")
    @GetMapping("/admin/getUserCount/all")
    public ResultBean getUserCount(){
        return userService.getUserCount();
    }

}
