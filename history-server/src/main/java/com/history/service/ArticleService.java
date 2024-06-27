package com.history.service;

import com.github.pagehelper.PageInfo;
import com.history.dto.ArticleDTO;
import com.history.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.history.utils.ResultBean;
import com.history.vo.ArticleVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CSL
 * @since 2023-02-14
 */
public interface ArticleService extends IService<Article> {

    /**
     * 通过朝代ID获取文章数量
     * @param dynastyId
     * @return
     */
    List<Article> getArticlesByDynastyId(Integer dynastyId);


    /**
     * 获取首页热门历史知识
     * @param pageIndex
     * @return
     */
    PageInfo<Article> getHotArticlesPage(Integer pageIndex);

    /**
     * 通过文章id和用户id获取文章信息
     * @param userId
     * @param articleId
     * @return
     */
    ResultBean getArticleByUserIdAndArticleId(Long userId ,Long articleId);

    /**
     * 点赞数量加一
     * @param articleId
     */
    void clickGoodPlusOne(Long articleId);

    /**
     * 点赞数量减一
     * @param articleId
     */
    void clickGoodMinusOne(Long articleId);

    /**
     * 通过朝代id查询文章列表（分页）
     * @param dynastyId
     * @param pageIndex
     * @return
     */
    PageInfo<ArticleDTO> getArticlesByDynastyIdWithPage(Integer dynastyId , Integer pageIndex);

    /**
     * 发布文章，保存文章信息
     * @param articleVO
     * @return
     */
    ResultBean saveArticle(ArticleVO articleVO);

    /**
     * 通过用户id获取发布文章
     * @param userId
     * @return
     */
    ResultBean getArticlesByUserId(Long userId);

    /**
     * 通过文章id获取文章信息
     * @param articleId
     * @return
     */
    Article getArticleByArticleId(Long articleId);

    /**
     * 获取已经通过审核的文章
     * @return
     */
    ResultBean getAliveArticles();


    /**
     * 获取还未审核的文章
     * @return
     */
    ResultBean getNotAliveArticles();

    /**
     * 通过文章id删除文章信息
     * @param articleId
     * @return
     */
    ResultBean deleteArticleByArticleId(Long articleId);

    /**
     * 修改文章状态值 isAlive
     * @param articleId
     * @return
     */
    ResultBean auditArticleByArticleId(Long articleId);


    /**
     * 删除所有通过审核的文章
     */
    void handleBatchDeleteArticlesByAlive();

    /**
     * 批量审核通过文章
     */
    void batchAudit();

    /**
     * 通过文章id获取文章信息
     * @param articleId
     * @return
     */
    ResultBean getArticleByArticle(Long articleId);


    /**
     * 查询文章总数
     * @return
     */
    ResultBean getArticleCount();


    /**
     * 通过朝代id查询文章数量
     * @param dynastyId
     * @return
     */
    Long getArticleCountByDynastyId(Integer dynastyId);

    /**
     * 获取未通过审核的文章数量
     * @return
     */
    ResultBean getArticleCountWithNotPass();
}
