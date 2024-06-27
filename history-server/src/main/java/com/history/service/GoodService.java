package com.history.service;

import com.history.entity.Good;
import com.baomidou.mybatisplus.extension.service.IService;
import com.history.utils.ResultBean;
import com.history.vo.ClickGoodVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CSL
 * @since 2023-02-14
 */
public interface GoodService extends IService<Good> {

    /**
     * 通过用户id和文章id查询用户点赞信息
     * @param userId
     * @param article
     * @return
     */
    Good getGoodByUserIdAndArticleId(Long userId, Long article);

    /**
     * 通过用户id和评论id查询用户点赞信息
     * @param userId
     * @param commentId
     * @param articleId
     * @return
     */
    Good getGoodByUserIdAndCommentId(Long userId,Long articleId, Long commentId);

    /**
     * 文章点赞/取消赞
     * @param clickGoodVO
     * @return
     */
    ResultBean articleClickGoodOperation(ClickGoodVO clickGoodVO);

    /**
     * 评论点赞/取消赞
     * @param clickGoodVO
     * @return
     */
    ResultBean commentClickGoodOperation(ClickGoodVO clickGoodVO);
}
