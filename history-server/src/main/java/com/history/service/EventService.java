package com.history.service;

import com.history.entity.Event;
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
public interface EventService extends IService<Event> {


    /**
     * 通过朝代获取历史事件信息
     * @param dynastyId
     * @return
     */
    ResultBean getEventsByDynastyId(Integer dynastyId);
}
