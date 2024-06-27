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
@TableName("h_admin")
@ApiModel(value = "Admin对象", description = "")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("管理员id")
    @TableId(value = "admin_id", type = IdType.AUTO)
    private Integer adminId;

    @ApiModelProperty("管理员名称")
    private String username;

    @ApiModelProperty("管理员密码")
    private String password;

    @ApiModelProperty("管理员头像")
    private String avatar;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("是否被禁")
    private Integer isAlive;

    @ApiModelProperty("逻辑删除")
    @TableLogic
    private Integer isDelete;

    @ApiModelProperty("注册时间")
    private String createTime;
}
