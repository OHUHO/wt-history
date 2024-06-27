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
@TableName("h_comment")
@ApiModel(value = "Comment对象", description = "")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评论id")
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long commentId;

    @ApiModelProperty("评论用户的id")
    private Long userId;

    @ApiModelProperty("评论文章的id")
    private Long articleId;

    @ApiModelProperty("评论的内容")
    private String content;

    @ApiModelProperty("获赞数量")
    private Long goodNum;

    @ApiModelProperty("是否审核通过")
    private Integer isAlive;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty("评论时间")
    private String createTime;
}
