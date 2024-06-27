package com.history.controller;

import com.history.service.GoodService;
import com.history.utils.ResultBean;
import com.history.vo.ClickGoodVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CSL
 * @since 2023-02-14
 */
@Api(tags = "GoodController")
@RestController
public class GoodController {

    @Autowired
    private GoodService goodService;


    @ApiOperation(value = "文章点赞操作")
    @PutMapping("/user/articleClickGoodOperation")
    public ResultBean articleClickGoodOperation(@RequestBody ClickGoodVO clickGoodVO){
        if (clickGoodVO.getUserId() != null){
            return goodService.articleClickGoodOperation(clickGoodVO);
        }
        return ResultBean.error("你还未登录，请登录后点赞！");

    }


    @ApiOperation(value = "评论点赞操作")
    @PutMapping("/user/commentClickGoodOperation")
    public ResultBean commentClickGoodOperation(@RequestBody ClickGoodVO clickGoodVO){
        if (clickGoodVO.getUserId() != null){
            return goodService.commentClickGoodOperation(clickGoodVO);
        }
        return ResultBean.error("你还未登录，请登录后点赞！");

    }


}
