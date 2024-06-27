package com.history.controller;

import com.history.entity.Dynasty;
import com.history.service.DynastyService;
import com.history.utils.ImageUtil;
import com.history.utils.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author CSL
 * @since 2023-02-17
 */
@Api(tags = "DynastyController")
@RestController
public class DynastyController {

    @Autowired
    private DynastyService dynastyService;


    @Value("${storage.ip}")
    private String ip;

    @Value("${storage.port}")
    private String port;

    @Value("${storage.path}")
    private String basePath;


    @ApiOperation(value = "获取所有的朝代")
    @GetMapping("/common/getDynasties/all")
    public ResultBean getDynasties(){
        List<Dynasty> dynasties = dynastyService.getDynasties();
        return ResultBean.success("成功获取所有朝代", dynasties);
    }


    @ApiOperation(value = "通过朝代ID获取朝代信息")
    @GetMapping("/common/getDynastyByDynastyId/{dynastyId}")
    public ResultBean getDynastyByDynastyId(@PathVariable Integer dynastyId){
        if (dynastyId != null){
            return dynastyService.getDynastyByDynastyId(dynastyId);
        }
        return ResultBean.error("朝代ID不能为空！");
    }


    @ApiOperation(value = "保存管理员添加的封面和地图返回网址")
    @PostMapping("/admin/addCoverAndMap")
    public List<String> addCoverAndMapReturnUrls(@RequestParam String imageCover,
                                                 @RequestParam String imageMap){
        // 图片后缀
        String suffix="jpg";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(new Date());

        Long time = System.currentTimeMillis();

        // 文件保存的路径
        String pathCover = basePath + "dynasty-info/cover/" + time + "_." + suffix;
        String pathMap = basePath + "dynasty-info/maps/" + time + "_." + suffix;

        // 同步到数据库中的路径(返回给前端的地址)
        String pathDBCover = "";
        String pathDBMap = "";

        File outFileCover = new File(pathCover);
        if (outFileCover.getParentFile() != null || !outFileCover.getParentFile().isDirectory()) {
            // 创建文件夹
            outFileCover.getParentFile().mkdirs();
        }

        File outFileMap = new File(pathMap);
        if (outFileMap.getParentFile() != null || !outFileMap.getParentFile().isDirectory()) {
            // 创建文件夹
            outFileMap.getParentFile().mkdirs();
        }
        try {
            // 将前端传递的文件保存到本地服务器路径下
            ImageUtil.generateImage(imageCover, pathCover);
            ImageUtil.generateImage(imageMap, pathMap);
            pathDBCover = "http://" + ip + ":" + port + "/files/"  + "dynasty-info/cover/" + time + "_." + suffix;
            pathDBMap = "http://" + ip + ":" + port + "/files/" + "dynasty-info/maps/" + time + "_." + suffix;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Arrays.asList(pathDBCover,pathDBMap);
    }


    @ApiOperation(value = "添加新的朝代")
    @PostMapping("/admin/daaDynasty")
    public ResultBean daaDynasty(@RequestBody Dynasty dynasty){
        if (dynasty != null){
            return dynastyService.daaDynasty(dynasty);
        }
        return ResultBean.error("参数不能为空！");
    }


    @ApiOperation(value = "通过关键词查询朝代信息")
    @GetMapping("/admin/getDynastyByKeywords/{keywords}")
    public ResultBean getDynastyByKeywords(@PathVariable String keywords){
        if (StringUtils.isNoneBlank(keywords)){
            return dynastyService.getDynastyByKeywords(keywords);
        }
        return ResultBean.error("关键词不能为空！");
    }


    @ApiOperation(value = "通过朝代id删除朝代")
    @DeleteMapping("/admin/deleteDynastyByDynastyId/{dynastyId}")
    public ResultBean deleteDynastyByDynastyId(@PathVariable Integer dynastyId){
        if (dynastyId != null){
            return dynastyService.deleteDynastyByDynastyId(dynastyId);
        }
        return ResultBean.error("删除成功！");
    }


    @ApiOperation(value = "保存修改之后的封面，返回url给前端")
    @PostMapping("/admin/editCover")
    public String editCover(@RequestParam MultipartFile imageCover){

        String suffix = imageCover.getContentType();
        suffix = suffix.substring(suffix.indexOf("/") + 1);
        if ("jpeg".equals(suffix)){
            suffix = "jpg";
        }

        long time = System.currentTimeMillis();

        // 文件保存的路径
        String path = basePath + "dynasty-info/cover/" + time + "_." + suffix;

        // 同步到数据库中的路径(返回给前端的地址)
        String pathDB = "";

        File outFile = new File(path);
        if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
            // 创建文件夹
            outFile.getParentFile().mkdirs();
        }

        try {
            // 将前端传递的文件保存到本地服务器路径下
            imageCover.transferTo(new File(path));

            pathDB = "http://" + ip + ":" + port + "/files/"  + "dynasty-info/cover/" + time + "_." + suffix;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return pathDB;
    }


    @ApiOperation(value = "保存修改之后的地图，返回url给前端")
    @PostMapping("/admin/editMap")
    public String editMap(@RequestParam MultipartFile imageMap){

        String suffix = imageMap.getContentType();
        suffix = suffix.substring(suffix.indexOf("/") + 1);
        if ("jpeg".equals(suffix)){
            suffix = "jpg";
        }

        long time = System.currentTimeMillis();

        // 文件保存的路径
        String path = basePath + "dynasty-info/maps/" + time + "_." + suffix;

        // 同步到数据库中的路径(返回给前端的地址)
        String pathDB = "";

        File outFile = new File(path);
        if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
            // 创建文件夹
            outFile.getParentFile().mkdirs();
        }

        try {
            // 将前端传递的文件保存到本地服务器路径下
            imageMap.transferTo(new File(path));
            pathDB = "http://" + ip + ":" + port + "/files/"  + "dynasty-info/maps/" + time + "_." + suffix;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pathDB;
    }


    @ApiOperation(value = "保存朝代修改信息")
    @PostMapping("/admin/updateDynasty")
    public ResultBean updateDynasty(@RequestBody Dynasty dynasty){
        if (dynasty != null){
            return dynastyService.updateDynasty(dynasty);
        }
        return ResultBean.error("参数不能为空！");
    }


    @ApiOperation(value = "查询朝代数量")
    @GetMapping("/admin/getDynastyInfo/all")
    public ResultBean getDynastyInfo(){
        return dynastyService.getDynastyInfo();
    }


    @ApiOperation(value = "获取每一个朝代的中的文章数量")
    @GetMapping("/admin/getArticleCountEveryDynasty/all")
    public ResultBean getArticleCountEveryDynasty(){
        return dynastyService.getArticleCountEveryDynasty();
    }

}
