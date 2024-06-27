package com.history.controller;

import com.history.entity.Background;
import com.history.service.BackgroundService;
import com.history.utils.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CSL
 * @since 2023-02-14
 */

@Api(tags = "BackgroundController")
@RestController
public class BackgroundController {


    @Autowired
    private BackgroundService backgroundService;


    /**
     * 背景图片的类型（0：首页顶部，1：首页时间线条，2：首页时空地图，3：朝代背景）
     * @param type
     * @return
     */
    @ApiOperation(value = "获取首页顶部背景图片")
    @GetMapping("/common/getBackground")
    public ResultBean getBackgroundImgByType(Integer type){
        Background background = backgroundService.getBackgroundImgByType(type);
        return ResultBean.success("获取成功",background);
    }




}
