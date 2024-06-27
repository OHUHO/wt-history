package com.history.service;

import com.history.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.history.utils.ResultBean;
import com.history.vo.CommentVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CSL
 * @since 2023-02-14
 */
public interface CommentService extends IService<Comment> {


    /**
     * 通过文章id获取评论信息
     * @param articleId
     * @param userId
     * @return
     */
    ResultBean getCommentsByUserIdAndArticleId(Long userId,Long articleId);

    /**
     * 保存用户评论
     * @param commentVO
     * @return
     */
    ResultBean saveComment(CommentVO commentVO);

    /**
     * 评论点赞数量加 1
     * @param commentId
     */
    void clickGoodPlusOne(Long commentId);

    /**
     * 评论点赞数量减 1
     * @param commentId
     */
    void clickGoodMinusOne(Long commentId);

    /**
     * 通过用户id获取评论
     * @param userId
     * @return
     */
    ResultBean getCommentByUserId(Long userId);

    /**
     * 通过文章id获取评论
     * @param articleId
     * @return
     */
    ResultBean getCommentByArticleId(Long articleId);


    /**
     * 通过评论id审核评论
     * @param commentId
     * @param setAlive
     * @return
     */
    ResultBean updateAliveByCommentId(long commentId, int setAlive);


    /**
     * 通过评论id删除评论
     * @param commentId
     * @return
     */
    ResultBean deleteCommentByCommentId(Long commentId);

    /**
     * 获取评论审核通过的比率
     * @return
     */
    ResultBean getCommentPassRatio();
}
