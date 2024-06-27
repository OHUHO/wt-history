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
@TableName("h_article")
@ApiModel(value = "Article对象", description = "")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("文章id")
    @TableId(value = "article_id", type = IdType.AUTO)
    private Long articleId;

    @ApiModelProperty("文章标题")
    private String articleTitle;

    @ApiModelProperty("文章封面")
    private String articleCover;

    @ApiModelProperty("文章摘要")
    private String articleSummary;

    @ApiModelProperty("文章作者id")
    private Long articleAuthorId;

    @ApiModelProperty("文章内容（富文本）")
    private String articleContent;

    @ApiModelProperty("文章内容（html）")
    private String articleContentHtml;

    @ApiModelProperty("文章属于的朝代id")
    private Integer articleDynastyId;

    @ApiModelProperty("文章属于的词条标签id")
    private Integer articleLabelId;

    @ApiModelProperty("文章事件")
    private String articleEvent;

    @ApiModelProperty("文章获赞数量")
    private Long goodNum;

    @ApiModelProperty("文章是否被审核通过")
    private Integer isAlive;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty("文章发布时间")
    private String createTime;
}
