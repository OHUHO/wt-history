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
@ApiModel(value = "ArticleVO对象", description = "")
public class ArticleDTO implements Serializable {


    @ApiModelProperty("文章id")
    private Long articleId;

    @ApiModelProperty("文章标题")
    private String articleTitle;

    @ApiModelProperty("文章封面")
    private String articleCover;

    @ApiModelProperty("文章摘要")
    private String articleSummary;

    @ApiModelProperty("文章作者id")
    private Long articleAuthorId;

    @ApiModelProperty("文章作者名")
    private String authorName;

    @ApiModelProperty("文章作者头像")
    private String authorAvatar;

    @ApiModelProperty("文章内容（富文本）")
    private String articleContent;

    @ApiModelProperty("文章内容（html）")
    private String articleContentHtml;

    @ApiModelProperty("文章属于的朝代id")
    private Integer articleDynastyId;

    @ApiModelProperty("文章事件")
    private String articleEvent;

    @ApiModelProperty("文章获赞数量")
    private Long goodNum;

    @ApiModelProperty("是否点赞")
    private boolean hasClickGood;

    @ApiModelProperty("文章发布时间")
    private String createTime;
}
