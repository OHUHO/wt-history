package com.history.mapper;

import com.history.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CSL
 * @since 2023-02-14
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}