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
@TableName("h_label")
@ApiModel(value = "Label对象", description = "")
public class Label implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("词条标签id")
    @TableId(value = "label_id", type = IdType.AUTO)
    private Long labelId;

    @ApiModelProperty("词条标签名字")
    private String labelName;

    @ApiModelProperty("朝代id")
    private Integer dynastyId;

    @ApiModelProperty("是否被禁用")
    private Integer isAlive;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty("创建时间")
    private String createTime;
}
