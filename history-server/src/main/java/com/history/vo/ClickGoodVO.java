package com.history.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ApiModel(value = "ClickGoodVO对象", description = "")
public class ClickGoodVO {

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("文章id")
    private Long articleId;

    @ApiModelProperty("评论id")
    private Long commentId;

    @ApiModelProperty("操作码：1 -> 点赞，-1 -> 取消赞")
    private Integer operation;

}
