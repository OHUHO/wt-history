package com.history.service;

import com.history.entity.Dynasty;
import com.baomidou.mybatisplus.extension.service.IService;
import com.history.utils.ResultBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CSL
 * @since 2023-02-17
 */
public interface DynastyService extends IService<Dynasty> {


    /**
     * 获取所有的朝代信息
     * @return
     */
    List<Dynasty> getDynasties();

    /**
     * 通过朝代ID获取朝代信息
     * @param dynastyId
     * @return
     */
    ResultBean getDynastyByDynastyId(Integer dynastyId);

    /**
     * 添加朝代
     * @param dynasty
     * @return
     */
    ResultBean daaDynasty(Dynasty dynasty);


    /**
     * 通过关键词查询朝代信息
     * @param keywords
     * @return
     */
    ResultBean getDynastyByKeywords(String keywords);


    /**
     * 通过朝代id删除朝代信息
     * @param dynastyId
     * @return
     */
    ResultBean deleteDynastyByDynastyId(Integer dynastyId);


    /**
     * 更新朝代信息
     * @param dynasty
     * @return
     */
    ResultBean updateDynasty(Dynasty dynasty);

    /**
     * 查询朝代与朝代中的文章数量
     * @return
     */
    ResultBean getDynastyInfo();

    /**
     * 获取每一个朝代中的文章数量
     * @return
     */
    ResultBean getArticleCountEveryDynasty();
}
