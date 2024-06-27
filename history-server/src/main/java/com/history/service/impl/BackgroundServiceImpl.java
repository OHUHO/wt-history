package com.history.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.history.entity.Background;
import com.history.mapper.BackgroundMapper;
import com.history.service.BackgroundService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CSL
 * @since 2023-02-14
 */
@Service
public class BackgroundServiceImpl extends ServiceImpl<BackgroundMapper, Background> implements BackgroundService {

    @Autowired
    private BackgroundMapper backgroundMapper;


    @Override
    public Background getBackgroundImgByType(Integer type) {
        return backgroundMapper.selectOne(
                new LambdaQueryWrapper<Background>()
                        .eq(Background::getType,type)
        );
    }
}
