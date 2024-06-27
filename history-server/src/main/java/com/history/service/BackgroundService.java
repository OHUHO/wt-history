package com.history.service;

import com.history.entity.Background;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CSL
 * @since 2023-02-14
 */
public interface BackgroundService extends IService<Background> {


    /**
     * 获取背景图片
     * @param type
     * @return
     */
    Background getBackgroundImgByType(Integer type);
}
