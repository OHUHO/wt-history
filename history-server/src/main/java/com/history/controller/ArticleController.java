package com.history.controller;


import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.history.dto.ArticleDTO;
import com.history.entity.Article;
import com.history.service.ArticleService;
import com.history.utils.ImageUtil;
import com.history.utils.JsonUtils;
import com.history.utils.ResultBean;
import com.history.vo.ArticleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CSL
 * @since 2023-02-14
 */
@Api(tags = "ArticleController")
@RestController
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    @Value("${storage.ip}")
    private String ip;

    @Value("${storage.port}")
    private String port;

    @Value("${storage.path}")
    private String basePath;


    @ApiOperation(value = "通过朝代ID获取文章列表")
    @GetMapping("/common/getArticlesByDynastyId/{dynastyId}")
    public ResultBean getArticlesByDynastyId(@PathVariable Integer dynastyId){
        if (dynastyId != null){
            List<Article> articles = articleService.getArticlesByDynastyId(dynastyId);
            return ResultBean.success("获取文章成功",articles);
        }
        return ResultBean.error("查询朝代Id不能为空！");

    }


    @ApiOperation(value = "获取首页热门历史知识文章列表（分页）")
    @GetMapping("/common/getHotArticles/{pageIndex}")
    public ResultBean getHotArticles(@PathVariable Integer pageIndex){
        if (pageIndex != null){

            PageInfo<Article> pageInfo = articleService.getHotArticlesPage(pageIndex);
            return ResultBean.success("成功获取热门历史！", pageInfo);
        }
        return ResultBean.error("传入的页码不能为空！");
    }

    @ApiOperation(value = "通过用户id和文章id获取文章信息")
    @GetMapping("/common/getArticleByUserIdAndArticleId/{params}")
    public ResultBean getArticleByUserIdAndArticleId(@PathVariable String params){

        if (StringUtils.isNoneBlank(params)){
            JSONObject jsonObject = JSONObject.parseObject(params);
            long userId = jsonObject.getLongValue("userId");
            long articleId = jsonObject.getLongValue("articleId");

            return articleService.getArticleByUserIdAndArticleId(userId, articleId);
        }


        return ResultBean.error("参数不能为空");
    }


    @ApiOperation(value = "通过朝代ID获取文章列表（分页）")
    @GetMapping("/common/getArticlesByDynastyId/page/{params}")
    public ResultBean getArticlesByDynastyIdWithPage(@PathVariable String params){
        if (StringUtils.isNoneBlank(params)){
            JSONObject jsonObject = JSONObject.parseObject(params);
            int dynastyId = jsonObject.getIntValue("dynastyId");
            int pageIndex = jsonObject.getIntValue("pageIndex");

            PageInfo<ArticleDTO> pageInfo = articleService.getArticlesByDynastyIdWithPage(dynastyId,pageIndex);
            return ResultBean.success("获取文章成功",pageInfo);
        }

        return ResultBean.error("查询参数不能为空！");

    }


    @ApiOperation(value = "保存文章的图片并返回地址给前端")
    @PostMapping("/user/article/articleImg")
    public String articleInImg(@RequestParam("userId") Integer userId,
                               @RequestParam("image") MultipartFile image) throws Exception{

        String suffix = image.getContentType();
        suffix = suffix.substring(suffix.indexOf("/") + 1);
        if ("jpeg".equals(suffix)) {
            suffix = "jpg";
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());

        Long time = System.currentTimeMillis();

        // 保存的位置
        // 文件保存的路径
        String path = basePath + "user/" + "user-id-" + userId + "/article/" + date + "/" + time + "_." + suffix;

        // 返回给前端的 url 路径
        String imageUrl = "";

        File outFile = new File(path);
        if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
            // 创建文件夹
            outFile.getParentFile().mkdirs();
        }
        try {
            // 将前端传递的文件保存到本地服务器路径下
            image.transferTo(new File(path));
            imageUrl = "http://" + ip + ":" + port + "/files/" + "user/" + "user-id-" + userId + "/article/" + date + "/" + time + "_." + suffix;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageUrl;
    }



    @ApiOperation(value = "保存文章封面并返回地址")
    @PostMapping("/user/article/coverImg")
    public String coverImg(@RequestParam("userId") Integer userId,
                           @RequestParam("image") String image) throws Exception {

        // 图片后缀
        String suffix="jpg";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());

        Long time = System.currentTimeMillis();

        // 文件保存的路径
        String path = basePath + "user/" + "user-id-" + userId + "/article/" + date + "/cover/" + time + "_." + suffix;

        // 同步到数据库中的路径(返回给前端的地址)
        String pathDB = "";

        File outFile = new File(path);
        if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
            // 创建文件夹
            outFile.getParentFile().mkdirs();
        }
        try {
            // 将前端传递的文件保存到本地服务器路径下
            ImageUtil.generateImage(image, path);


            pathDB = "http://" + ip + ":" + port + "/files/" + "user/" + "user-id-" + userId + "/article/" + date + "/cover/" + time + "_." + suffix;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pathDB;

    }


    @ApiOperation(value = "保存发布文章")
    @PostMapping("/user/saveArticle")
    public ResultBean saveArticle(@RequestBody ArticleVO articleVO){
        if (articleVO != null){
            return articleService.saveArticle(articleVO);
        }
        return ResultBean.error("发布文章不能为空！");
    }

    @ApiOperation(value = "通过用户Id获取用户发布的文章")
    @GetMapping("/user/getArticlesByUserId/{userId}")
    public ResultBean getArticlesByUserId(@PathVariable Long userId){
        if (userId != null){
            return articleService.getArticlesByUserId(userId);
        }
        return ResultBean.success("获取用户发布的文章失败！");
    }


    @ApiOperation(value = "查询所有的已审核的文章")
    @GetMapping("/admin/getAliveArticles/all")
    public ResultBean getAliveArticles(){
        return articleService.getAliveArticles();
    }

    @ApiOperation(value = "查询所有的未审核的文章")
    @GetMapping("/admin/getNotAliveArticles/all")
    public ResultBean getNotAliveArticles(){
        return articleService.getNotAliveArticles();
    }


    @ApiOperation(value = "通过文章id删除文章")
    @DeleteMapping("/admin/deleteArticleByArticleId/{articleId}")
    public ResultBean deleteArticleByArticleId(@PathVariable Long articleId){
        if (articleId != null){
            return articleService.deleteArticleByArticleId(articleId);
        }
        return ResultBean.error("文章id不能为空！");
    }


    @ApiOperation(value = "通过文章id修改文章状态")
    @PutMapping("/admin/auditArticleByArticleId/{articleId}")
    public ResultBean auditArticleByArticleId(@PathVariable Long articleId){
        if (articleId != null){
            return articleService.auditArticleByArticleId(articleId);
        }
        return ResultBean.error("文章id不能为空！");
    }


    @ApiOperation(value = "删除所有的已通过审核的文章")
    @DeleteMapping("/admin/handleBatchDeleteArticlesByAlive")
    public ResultBean batchDeleteArticlesByAlive(){
        articleService.handleBatchDeleteArticlesByAlive();
        return ResultBean.success("成功删除所有文章！");
    }


    @ApiOperation(value = "批量审核文章")
    @PutMapping("/admin/batchAudit")
    public ResultBean batchAudit(){
        articleService.batchAudit();
        return ResultBean.success("审核成功！");
    }


    @ApiOperation(value = "通过文章id获取文章信息")
    @GetMapping("/admin/getArticleByArticleId/{articleId}")
    public ResultBean getArticleByArticle(@PathVariable Long articleId){
        if (articleId != null){
            return articleService.getArticleByArticle(articleId);
        }
        return ResultBean.error("查询文章id不能为空！");
    }


    @ApiOperation(value = "获取文章总数")
    @GetMapping("/admin/getArticleCount/all")
    public ResultBean getArticleCount(){
        return articleService.getArticleCount();
    }


    @ApiOperation(value = "获取未通过审核的文章数量")
    @GetMapping("/admin/getArticleCountWithNotPass/all")
    public ResultBean getArticleCountWithNotPass(){
        return articleService.getArticleCountWithNotPass();
    }

}
