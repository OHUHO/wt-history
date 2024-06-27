package com.history.utils;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共返回对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ResultBean对象",description = "公共返回对象")
public class ResultBean {
    private long code;
    private String message;
    private Object obj;

    /**
     * 成功返回结果
     * @param message
     * @return
     */
    public static ResultBean success(String message){
        return new ResultBean(200,message,null);
    }

    /**
     * 成功返回结果
     * @param message
     * @param obj
     * @return
     */
    public static ResultBean success(String message, Object obj){
        return new ResultBean(200,message,obj);
    }

    /**
     * 失败返回结果
     * @param message
     * @return
     */
    public static ResultBean error(String message){
        return new ResultBean(500,message,null);
    }

    /**
     * 失败返回结果
     * @param message
     * @param obj
     */
    public static ResultBean error(String message, Object obj){
        return new ResultBean(500,message,obj);
    }

}