package com.history.vo;

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
@ApiModel(value = "UserVO对象", description = "")
public class UserVO {


    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("用户头像")
    private String avatar;

}
