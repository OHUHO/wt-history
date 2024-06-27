package com.history.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.history.entity.Label;
import com.history.mapper.LabelMapper;
import com.history.service.LabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.history.utils.ResultBean;
import org.apache.ibatis.annotations.Lang;
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
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {

    @Autowired
    private LabelMapper labelMapper;


    @Override
    public ResultBean getLabelsByDynastyId(Integer dynastyId) {
        List<Label> labels = labelMapper.selectList(
                new LambdaQueryWrapper<Label>()
                        .eq(Label::getDynastyId, dynastyId)
        );
        return ResultBean.success("成功获取词条标签！",labels);

    }

    @Override
    public ResultBean getLabels() {
        List<Label> labels = labelMapper.selectList(null);
        return ResultBean.success("获取词条标签成功！",labels);
    }
}
