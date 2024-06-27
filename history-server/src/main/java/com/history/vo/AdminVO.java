package com.history.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@ToString
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(value = "AdminVO对象",description = "登录")
public class AdminVO {

    @ApiModelProperty(value = "用户名")
    private String  username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;

}
