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
 * @since 2023-02-17
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("h_dynasty")
@ApiModel(value = "Dynasty对象", description = "")
public class Dynasty implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("朝代id")
    @TableId(value = "dynasty_id", type = IdType.AUTO)
    private Integer dynastyId;

    @ApiModelProperty("朝代名字")
    private String dynastyName;

    @ApiModelProperty("朝代的描述")
    private String dynastyDescribe;

    @ApiModelProperty("朝代的封面")
    private String dynastyCover;

    @ApiModelProperty("朝代的时期")
    private String dynastyPeriod;

    @ApiModelProperty("朝代的地图")
    private String dynastyMap;

    @ApiModelProperty("朝代是否被关闭")
    private Integer isAlive;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty("创建时间")
    private String createTime;
}
