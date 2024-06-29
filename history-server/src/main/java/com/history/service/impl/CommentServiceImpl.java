package com.history.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.history.dto.MyCommentDTO;
import com.history.entity.Article;
import com.history.entity.Comment;
import com.history.entity.Good;
import com.history.entity.User;
import com.history.mapper.CommentMapper;
import com.history.service.ArticleService;
import com.history.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.history.service.GoodService;
import com.history.service.UserService;
import com.history.utils.ResultBean;
import com.history.dto.CommentDTO;
import com.history.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodService goodService;


    @Autowired
    private ArticleService  articleService;


    @Override
    public ResultBean getCommentsByUserIdAndArticleId(Long userId, Long articleId) {
        List<Comment> comments = commentMapper.selectList(
                new LambdaQueryWrapper<Comment>()
                        .eq(Comment::getArticleId, articleId)
                        .eq(Comment::getIsAlive,0)
        );

        List<CommentDTO> commentDTOS = new ArrayList<>();
        comments.forEach(comment -> {
            // 通过用户id查询用户信息
            User user = userService.getUserByUserId(comment.getUserId());

            // 通过用户id和文章id和评论id查询用户是否点赞
            Good good = goodService.getGoodByUserIdAndCommentId(userId,articleId,comment.getCommentId());
            boolean hasClickGood = good != null;

            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setCommentId(comment.getCommentId())
                    .setUserId(comment.getUserId())
                    .setNickname(user.getNickname())
                    .setAvatar(user.getAvatar())
                    .setArticleId(comment.getArticleId())
                    .setContent(comment.getContent())
                    .setGoodNum(comment.getGoodNum())
                    .setHasClickGood(hasClickGood)
                    .setCreateTime(comment.getCreateTime());

            commentDTOS.add(commentDTO);
        });


        return ResultBean.success("获取评论成功！" ,commentDTOS);
    }

    @Override
    public ResultBean saveComment(CommentVO commentVO) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createTime = format.format(date);

        Comment comment = new Comment();
        comment.setUserId(commentVO.getUserId())
                .setArticleId(commentVO.getArticleId())
                .setContent(commentVO.getContent())
                .setCreateTime(createTime);

        commentMapper.insert(comment);
        return ResultBean.success("评论成功！");
    }

    @Override
    public void clickGoodPlusOne(Long commentId) {
        Long goodNum = commentMapper.selectById(commentId).getGoodNum();

        Comment comment = new Comment();
        comment.setCommentId(commentId)
                .setGoodNum(goodNum + 1);

        commentMapper.updateById(comment);
    }

    @Override
    public void clickGoodMinusOne(Long commentId) {
        Long goodNum = commentMapper.selectById(commentId).getGoodNum();

        Comment comment = new Comment();
        comment.setCommentId(commentId)
                .setGoodNum(goodNum - 1);

        commentMapper.updateById(comment);
    }

    @Override
    public ResultBean getCommentByUserId(Long userId) {
        List<Comment> comments = commentMapper.selectList(
                new LambdaQueryWrapper<Comment>()
                        .eq(Comment::getUserId, userId)
        );

        List<MyCommentDTO> myCommentDTOS = new ArrayList<>();
        comments.forEach(comment -> {
            // 通过文章id获取文章信息
            Article article = articleService.getArticleByArticleId(comment.getArticleId());

            MyCommentDTO myCommentDTO = new MyCommentDTO();
            myCommentDTO.setCommentId(comment.getCommentId())
                    .setUserId(comment.getUserId())
                    .setArticleId(comment.getArticleId())
                    .setArticleTitle(article.getArticleTitle())
                    .setArticleContent(comment.getContent())
                    .setCreateTime(comment.getCreateTime());

            myCommentDTOS.add(myCommentDTO);
        });

        return ResultBean.success("获取我的评论成功！",myCommentDTOS);
    }

    @Override
    public ResultBean getCommentByArticleId(Long articleId) {
        List<Comment> comments = commentMapper.selectList(
                new LambdaQueryWrapper<Comment>()
                        .eq(Comment::getArticleId, articleId)
                        // .eq(Comment::getIsAlive,0)
        );
        return ResultBean.success("获取评论成功！",comments);
    }

    @Override
    public ResultBean updateAliveByCommentId(long commentId, int setAlive) {

        Comment comment = new Comment();
        comment.setCommentId(commentId)
                .setIsAlive(setAlive);
        commentMapper.updateById(comment);

        return ResultBean.success("更新状态成功！");
    }

    @Override
    public ResultBean deleteCommentByCommentId(Long commentId) {

        commentMapper.deleteById(commentId);
        return ResultBean.success("删除成功！");
    }

    @Override
    public ResultBean getCommentPassRatio() {

        double passCount = commentMapper.selectCount(
                new LambdaQueryWrapper<Comment>()
                        .eq(Comment::getIsAlive, 0)
        );
        double count = commentMapper.selectCount(null);

        double ratio = (passCount / count) * 100;
        return ResultBean.success("获取成功！",ratio);
    }


}
