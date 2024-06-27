package com.history.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.history.entity.Article;
import com.history.entity.Good;
import com.history.entity.User;
import com.history.mapper.ArticleMapper;
import com.history.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.history.service.GoodService;
import com.history.service.UserService;
import com.history.utils.ResultBean;
import com.history.dto.ArticleDTO;
import com.history.vo.ArticleVO;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {


    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodService goodService;


    @Override
    public List<Article> getArticlesByDynastyId(Integer dynastyId) {
        return articleMapper.selectList(
                new LambdaQueryWrapper<Article>()
                        .eq(Article::getArticleDynastyId, dynastyId)
                        .eq(Article::getIsAlive,0)
        );
    }

    @Override
    public PageInfo<Article> getHotArticlesPage(Integer pageIndex) {
        PageHelper.startPage(pageIndex,6);
        List<Article> articles = articleMapper.selectList(
                new LambdaQueryWrapper<Article>()
                        .eq(Article::getIsAlive,0)
                        .orderByDesc(Article::getGoodNum)
        );

        return new PageInfo<>(articles);
    }

    @Override
    public ResultBean getArticleByUserIdAndArticleId(Long userId ,Long articleId) {
        Article article = articleMapper.selectById(articleId);

        if (article != null){
            // 通过文章作者id查询作者信息
            User user = userService.getUserByUserId(article.getArticleAuthorId());

            // 通过当前用户id查询，该用户是否给文章点过赞
            Good good = goodService.getGoodByUserIdAndArticleId(userId, articleId);
            boolean hasClickGood = good != null;

            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setArticleId(article.getArticleId())
                    .setArticleTitle(article.getArticleTitle())
                    .setArticleCover(article.getArticleCover())
                    .setArticleSummary(article.getArticleSummary())
                    .setArticleAuthorId(article.getArticleAuthorId())
                    .setAuthorName(user.getNickname())
                    .setAuthorAvatar(user.getAvatar())
                    .setArticleContent(article.getArticleContent())
                    .setArticleContentHtml(article.getArticleContentHtml())
                    .setArticleDynastyId(article.getArticleDynastyId())
                    .setArticleEvent(article.getArticleEvent())
                    .setGoodNum(article.getGoodNum())
                    .setHasClickGood(hasClickGood)
                    .setCreateTime(article.getCreateTime());

            return ResultBean.success("成功获取文章！",articleDTO);
        }
        return ResultBean.error("文章不存在！");
    }

    @Override
    public void clickGoodPlusOne(Long articleId) {
        Long goodNum = articleMapper.selectById(articleId).getGoodNum();

        Article article = new Article();
        article.setArticleId(articleId)
                .setGoodNum(goodNum + 1);

        articleMapper.updateById(article);
    }

    @Override
    public void clickGoodMinusOne(Long articleId) {
        Long goodNum = articleMapper.selectById(articleId).getGoodNum();

        Article article = new Article();
        article.setArticleId(articleId)
                .setGoodNum(goodNum - 1);

        articleMapper.updateById(article);
    }

    @Override
    public PageInfo<ArticleDTO> getArticlesByDynastyIdWithPage(Integer dynastyId ,Integer pageIndex) {
        PageHelper.startPage(pageIndex,6);
        List<Article> articles = articleMapper.selectList(
                new LambdaQueryWrapper<Article>()
                        .eq(Article::getArticleDynastyId, dynastyId)
        );

        List<ArticleDTO> articleDTOS = new ArrayList<>();
        articles.forEach(article -> {
            // 通过用户id查询用户信息
            User user = userService.getUserByUserId(article.getArticleAuthorId());

            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setArticleId(article.getArticleId())
                    .setArticleTitle(article.getArticleTitle())
                    .setArticleCover(article.getArticleCover())
                    .setArticleAuthorId(article.getArticleAuthorId())
                    .setAuthorName(user.getNickname())
                    .setCreateTime(article.getCreateTime());
            articleDTOS.add(articleDTO);

        });

        return new PageInfo<>(articleDTOS);
    }

    @Override
    public ResultBean saveArticle(ArticleVO articleVO) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());

        Article article = new Article();
        article.setArticleTitle(articleVO.getArticleTitle())
                .setArticleCover(articleVO.getArticleCover())
                .setArticleDynastyId(articleVO.getArticleDynastyId())
                .setArticleLabelId(articleVO.getArticleLabelId())
                .setArticleEvent(articleVO.getArticleEvent())
                .setArticleAuthorId(articleVO.getArticleAuthorId())
                .setArticleSummary(articleVO.getArticleSummary())
                .setArticleContent(articleVO.getArticleContent())
                .setArticleContentHtml(articleVO.getArticleContentHtml())
                .setCreateTime(date);

        articleMapper.insert(article);
        return ResultBean.success("成功发布文章！");
    }

    @Override
    public ResultBean getArticlesByUserId(Long userId) {
        List<Article> articles = articleMapper.selectList(
                new LambdaQueryWrapper<Article>()
                        .eq(Article::getArticleAuthorId, userId)
        );

        return ResultBean.success("成功获取用户发布的文章！",articles);
    }

    @Override
    public Article getArticleByArticleId(Long articleId) {
        return articleMapper.selectById(articleId);
    }

    @Override
    public ResultBean getAliveArticles() {
        List<Article> articles = articleMapper.selectList(
                new LambdaQueryWrapper<Article>()
                        .eq(Article::getIsAlive, 0)
        );
        return ResultBean.success("查询成功！",articles);
    }

    @Override
    public ResultBean getNotAliveArticles() {
        List<Article> articles = articleMapper.selectList(
                new LambdaQueryWrapper<Article>()
                        .eq(Article::getIsAlive, 1)
        );
        return ResultBean.success("查询成功！",articles);
    }

    @Override
    public ResultBean deleteArticleByArticleId(Long articleId) {
        articleMapper.deleteById(articleId);
        return ResultBean.success("删除成功！");
    }

    @Override
    public ResultBean auditArticleByArticleId(Long articleId) {
        Article article = new Article();
        article.setArticleId(articleId)
                .setIsAlive(0);
        articleMapper.updateById(article);
        return ResultBean.success("修改成功！");
    }

    @Override
    public void handleBatchDeleteArticlesByAlive() {
        articleMapper.delete(
                new LambdaQueryWrapper<Article>()
                        .eq(Article::getIsAlive, 1)
        );
    }

    @Override
    public void batchAudit() {
        List<Article> articles = articleMapper.selectList(
                new LambdaQueryWrapper<Article>()
                        .eq(Article::getIsAlive, 0)
        );
        articles.forEach(article -> {
            Article newArticle = new Article();
            newArticle.setArticleId(article.getArticleId())
                    .setIsAlive(1);
            articleMapper.updateById(newArticle);
        });

    }

    @Override
    public ResultBean getArticleByArticle(Long articleId) {

        Article article = articleMapper.selectById(articleId);
        return ResultBean.success("成功查询文章！",article);
    }

    @Override
    public ResultBean getArticleCount() {

        Long count = articleMapper.selectCount(null);
        return ResultBean.success("获取成功！",count);

    }

    @Override
    public Long getArticleCountByDynastyId(Integer dynastyId) {
        return articleMapper.selectCount(
                new LambdaQueryWrapper<Article>()
                        .eq(Article::getArticleDynastyId, dynastyId)
        );
    }

    @Override
    public ResultBean getArticleCountWithNotPass() {

        Long count = articleMapper.selectCount(
                new LambdaQueryWrapper<Article>()
                        .eq(Article::getIsAlive, 1)
        );
        return ResultBean.success("获取成功！",count);
    }


}
