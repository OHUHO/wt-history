package com.history.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@ApiModel(value = "CommentVO对象", description = "")
public class CommentVO implements Serializable {

    @ApiModelProperty("评论用户的id")
    private Long userId;

    @ApiModelProperty("评论文章的id")
    private Long articleId;

    @ApiModelProperty("评论的内容")
    private String content;
}
