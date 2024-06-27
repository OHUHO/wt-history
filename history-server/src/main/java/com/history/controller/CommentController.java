package com.history.controller;

import com.alibaba.fastjson.JSONObject;
import com.history.service.CommentService;
import com.history.utils.ResultBean;
import com.history.vo.CommentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CSL
 * @since 2023-02-14
 */

@Api(tags = "CommentController")
@RestController
public class CommentController {


    @Autowired
    private CommentService commentService;


    @ApiOperation(value = "通过用户id和文章id获取评论")
    @GetMapping("/common/getCommentsByUserIdAndArticleId/{params}")
    public ResultBean getCommentsByUserIdAndArticleId(@PathVariable String params){

        if (StringUtils.isNoneBlank(params)){
            JSONObject jsonObject = JSONObject.parseObject(params);
            long userId = jsonObject.getLongValue("userId");
            long articleId = jsonObject.getLongValue("articleId");

            return commentService.getCommentsByUserIdAndArticleId(userId ,articleId);
        }
        return ResultBean.error("参数不能为空！");
    }


    @ApiOperation(value = "保存用户评论")
    @PutMapping("/user/comment")
    public ResultBean saveComment(@RequestBody CommentVO commentVO){
        System.out.println("commentVO = " + commentVO.getArticleId());
        System.out.println("commentVO = " + commentVO.getContent());
        System.out.println("commentVO = " + commentVO.getUserId());
        if (commentVO.getUserId() != null){
            if (StringUtils.isNoneBlank(commentVO.getContent())){
                return commentService.saveComment(commentVO);
            }
            return ResultBean.error("评论内容不能为空！");
        }
        return ResultBean.error("还未登录，请登录后评论！");
    }


    @ApiOperation(value = "通过用户id获取评论")
    @GetMapping("/uer/getCommentByUserId/{userId}")
    public ResultBean getCommentByUserId(@PathVariable Long userId){
        if (userId != null){
            return commentService.getCommentByUserId(userId);
        }
        return ResultBean.error("用户id不能为空！");
    }


    @ApiOperation(value = "通过文章id获取评论")
    @GetMapping("/admin/getCommentByArticleId/{articleId}")
    public ResultBean getCommentByArticleId(@PathVariable Long articleId){
        if (articleId != null){
            return commentService.getCommentByArticleId(articleId);
        }
        return ResultBean.error("获取失败！");
    }


    @ApiOperation(value = "通过评论id审核评论")
    @PutMapping("/admin/updateAliveByCommentId/{params}")
    public ResultBean updateAliveByCommentId(@PathVariable String params){
        if (StringUtils.isNoneBlank(params)){
            JSONObject jsonObject = JSONObject.parseObject(params);
            long commentId = jsonObject.getLongValue("commentId");
            int setAlive = jsonObject.getIntValue("setAlive");
            System.out.println("commentId = " + commentId);
            System.out.println("setAlive = " + setAlive);
            return commentService.updateAliveByCommentId(commentId, setAlive);
        }
        return ResultBean.error("参数不能为空！");
    }


    @ApiOperation(value = "通过评论id删除评论")
    @DeleteMapping("/admin/deleteCommentByCommentId/{commentId}")
    public ResultBean deleteCommentByCommentId(@PathVariable Long commentId){
        if (commentId != null){
            return commentService.deleteCommentByCommentId(commentId);
        }
        return ResultBean.error("参数不能为空！");
    }

    @ApiOperation(value = "获取未通过审核的比例")
    @GetMapping("/admin/getCommentPassRatio/all")
    public ResultBean getCommentPassRatio(){
        return commentService.getCommentPassRatio();
    }


}
