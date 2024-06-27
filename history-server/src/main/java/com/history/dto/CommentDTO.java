package com.history.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@ApiModel(value = "Comment对象", description = "")
public class CommentDTO implements Serializable {


    @ApiModelProperty("评论id")
    private Long commentId;

    @ApiModelProperty("评论用户的id")
    private Long userId;

    @ApiModelProperty("评论用户的昵称")
    private String nickname;

    @ApiModelProperty("评论用户的头像")
    private String avatar;

    @ApiModelProperty("评论文章的id")
    private Long articleId;

    @ApiModelProperty("评论的内容")
    private String content;

    @ApiModelProperty("获赞数量")
    private Long goodNum;

    @ApiModelProperty("是否点赞")
    private boolean hasClickGood;

    @ApiModelProperty("评论时间")
    private String createTime;
}
