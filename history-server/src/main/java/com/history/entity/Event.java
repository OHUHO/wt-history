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
@TableName("h_event")
@ApiModel(value = "Event对象", description = "")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("历史事件id")
    @TableId(value = "event_id", type = IdType.AUTO)
    private Long eventId;

    @ApiModelProperty("时间名称")
    private String eventName;

    @ApiModelProperty("朝代id")
    private Integer dynastyId;

    @ApiModelProperty("是否被禁")
    private Integer isAlive;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty("创建时间")
    private String createTime;
}
