package com.history.controller;

import com.history.service.AdminService;
import com.history.utils.ResultBean;
import com.history.vo.AdminVO;
import com.history.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CSL
 * @since 2023-02-14
 */
@Api(tags = "AdminController")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;



    @ApiOperation(value = "管理员登录")
    @PostMapping("/login")
    public ResultBean login(@RequestBody AdminVO adminVO){
        if (adminVO != null){
            return adminService.login(adminVO.getUsername(),adminVO.getPassword());
        }
        return ResultBean.error("用户名或密码不能为空！");
    }

    @GetMapping("/common/test")
    public String test(){
        return "test";
    }


}
