package com.history.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.history.entity.Event;
import com.history.mapper.EventMapper;
import com.history.service.EventService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.history.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements EventService {

    @Autowired
    private EventMapper eventMapper;



    @Override
    public ResultBean getEventsByDynastyId(Integer dynastyId) {
        List<Event> events = eventMapper.selectList(
                new LambdaQueryWrapper<Event>()
                        .eq(Event::getDynastyId, dynastyId)
        );
        return ResultBean.success("获取历史事件成功！",events);
    }
}
