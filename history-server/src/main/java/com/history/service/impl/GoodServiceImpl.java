package com.history.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.history.entity.Good;
import com.history.mapper.GoodMapper;
import com.history.service.ArticleService;
import com.history.service.CommentService;
import com.history.service.GoodService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.history.utils.ResultBean;
import com.history.vo.ClickGoodVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CSL
 * @since 2023-02-14
 */
@Service
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements GoodService {

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;


    @Override
    public Good getGoodByUserIdAndArticleId(Long userId, Long article) {
        return goodMapper.selectOne(
                new LambdaQueryWrapper<Good>()
                        .eq(Good::getUserId,userId)
                        .eq(Good::getArticleId,article)
                        .isNull(Good::getCommentId)
        );
    }

    @Override
    public Good getGoodByUserIdAndCommentId(Long userId,Long articleId , Long commentId) {
        return goodMapper.selectOne(
                new LambdaQueryWrapper<Good>()
                        .eq(Good::getUserId, userId)
                        .eq(Good::getCommentId, commentId)
                        .eq(Good::getArticleId,articleId)
        );
    }

    @Override
    public ResultBean articleClickGoodOperation(ClickGoodVO clickGoodVO) {
        if (clickGoodVO.getOperation() == 1){
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createTime = format.format(date);

            Good good = new Good();
            good.setArticleId(clickGoodVO.getArticleId())
                    .setUserId(clickGoodVO.getUserId())
                    .setCreateTime(createTime);
            // 保存点赞信息
            goodMapper.insert(good);
            // 文章点赞数量加 1
            articleService.clickGoodPlusOne(clickGoodVO.getArticleId());


        } else if (clickGoodVO.getOperation() == -1) {
            // 删除点赞信息
            goodMapper.delete(
                    new LambdaQueryWrapper<Good>()
                            .eq(Good::getArticleId,clickGoodVO.getArticleId())
                            .eq(Good::getUserId, clickGoodVO.getUserId())
            );
            // 文章点赞数量减 1
            articleService.clickGoodMinusOne(clickGoodVO.getArticleId());


        }else {
            return ResultBean.error("操作码有无");
        }
        return ResultBean.error("操作成功！");
    }


    @Override
    public ResultBean commentClickGoodOperation(ClickGoodVO clickGoodVO) {
        if (clickGoodVO.getOperation() == 1){
            Date date = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createTime = format.format(date);

            Good good = new Good();
            good.setArticleId(clickGoodVO.getArticleId())
                    .setCommentId(clickGoodVO.getCommentId())
                    .setUserId(clickGoodVO.getUserId())
                    .setCreateTime(createTime);
            // 保存点赞信息
            goodMapper.insert(good);

            // 评论点赞数量加 1
            commentService.clickGoodPlusOne(clickGoodVO.getCommentId());


        } else if (clickGoodVO.getOperation() == -1) {
            // 删除点赞信息
            goodMapper.delete(
                    new LambdaQueryWrapper<Good>()
                            .eq(Good::getArticleId,clickGoodVO.getArticleId())
                            .eq(Good::getUserId, clickGoodVO.getUserId())
                            .eq(Good::getCommentId,clickGoodVO.getCommentId())
            );
            // 评论点赞数量减 1
            commentService.clickGoodMinusOne(clickGoodVO.getCommentId());


        }else {
            return ResultBean.error("操作码有无");
        }
        return ResultBean.error("操作成功！");
    }
}
