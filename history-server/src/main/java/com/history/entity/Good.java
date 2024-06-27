package com.history.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author CSL
 * @since 2023-02-14
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("h_good")
@ApiModel(value = "Good对象", description = "")
public class Good implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("获赞id")
    @TableId(value = "good_id", type = IdType.AUTO)
    private Long goodId;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("文章id")
    private Long articleId;

    @ApiModelProperty("评论的id")
    private Long commentId;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty("点赞时间")
    private String createTime;
}
