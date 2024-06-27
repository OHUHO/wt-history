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
@TableName("h_background")
@ApiModel(value = "Background对象", description = "")
public class Background implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("图片背景")
    private String imageUrl;

    @ApiModelProperty("背景图片描述")
    private String describe;

    @ApiModelProperty("背景图片的类型（0：首页顶部，1：时间线条，2：时空地图，3：朝代背景）")
    private Integer type;

    @ApiModelProperty("是否启用该图片（每一种图片类型最多只能启用一张图片）")
    private Integer isAlive;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty("创建时间")
    private String createTime;
}
