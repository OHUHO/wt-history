package com.history.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.history.entity.Dynasty;
import com.history.mapper.DynastyMapper;
import com.history.service.ArticleService;
import com.history.service.DynastyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.history.utils.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CSL
 * @since 2023-02-17
 */
@Service
public class DynastyServiceImpl extends ServiceImpl<DynastyMapper, Dynasty> implements DynastyService {

    @Autowired
    private DynastyMapper dynastyMapper;

    @Autowired
    private ArticleService articleService;

    @Override
    public List<Dynasty> getDynasties() {
        return dynastyMapper.selectList(null);
    }

    @Override
    public ResultBean getDynastyByDynastyId(Integer dynastyId) {
        Dynasty dynasty = dynastyMapper.selectById(dynastyId);
        if (dynasty != null){
            return ResultBean.success("成功获取朝代信息！",dynasty);
        }
        return ResultBean.error("没有该朝代信息！");
    }

    @Override
    public ResultBean daaDynasty(Dynasty dynasty) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date());

        dynasty.setCreateTime(date)
                .setIsAlive(0);

        dynastyMapper.insert(dynasty);

        return ResultBean.success("添加成功！");
    }

    @Override
    public ResultBean getDynastyByKeywords(String keywords) {

        List<Dynasty> dynasties = dynastyMapper.selectList(
                new LambdaQueryWrapper<Dynasty>()
                        .like(Dynasty::getDynastyName, keywords)
                        .or()
                        .like(Dynasty::getDynastyDescribe, keywords)
                        .or()
                        .like(Dynasty::getDynastyPeriod, keywords)

        );
        return ResultBean.success("搜索成功！",dynasties);

    }

    @Override
    public ResultBean deleteDynastyByDynastyId(Integer dynastyId) {
        dynastyMapper.deleteById(dynastyId);
        return ResultBean.success("删除成功！");

    }

    @Override
    public ResultBean updateDynasty(Dynasty dynasty) {

        dynastyMapper.updateById(dynasty);
        return ResultBean.success("编辑成功！");
    }

    @Override
    public ResultBean getDynastyInfo() {
        List<Dynasty> dynasties = dynastyMapper.selectList(null);
        List<String> dynastyNames = dynasties.stream()
                .map(dynasty -> dynasty.getDynastyName()).collect(Collectors.toList());
        return ResultBean.success("查询成功！",dynastyNames);
    }

    @Override
    public ResultBean getArticleCountEveryDynasty() {

        List<Dynasty> dynasties = dynastyMapper.selectList(null);

        List<Long> list = new ArrayList<>();
        dynasties.forEach(dynasty -> {
            Long articleCount = articleService.getArticleCountByDynastyId(dynasty.getDynastyId());
            list.add(articleCount);
        });
        return ResultBean.success("查询成功！",list);
    }
}
