package com.history.service;

import com.history.entity.Label;
import com.baomidou.mybatisplus.extension.service.IService;
import com.history.utils.ResultBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CSL
 * @since 2023-02-14
 */
public interface LabelService extends IService<Label> {

    /**
     * 通过朝代ID获取词条标签
     * @param dynastyId
     * @return
     */
    ResultBean getLabelsByDynastyId(Integer dynastyId);

    /**
     * 获取所有的词条标签
     * @return
     */
    ResultBean getLabels();
}
