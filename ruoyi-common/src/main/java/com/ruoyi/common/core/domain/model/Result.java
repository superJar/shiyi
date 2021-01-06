package com.ruoyi.common.core.domain.model;

import lombok.Data;

import java.util.List;

/**
 * @author:superJar
 * @date:2021/1/1
 * @time:14:50
 * @details:
 */
@Data
public class Result<T> {

    private Integer code;

    private String message;

    private T data;

    private List<T> dataList;

    private static Result result = new Result();

    public static Result ok(){
        result.setCode(200);
        result.setMessage("请求成功！");
        result.setData(null);
        result.setDataList(null);
        return result;
    }

    public static <T> Result ok(T data){
        result.setCode(200);
        result.setMessage("请求成功！");
        result.setData(data);
        result.setDataList(null);
        return result;
    }

    public static <T> Result ok(List<T> dataList){
        result.setCode(200);
        result.setMessage("请求成功！");
        result.setData(null);
        result.setDataList(dataList);
        return result;
    }
    public static <T> Result ok(String msg, List<T> dataList){
        result.setCode(200);
        result.setMessage(msg);
        result.setData(null);
        result.setDataList(dataList);
        return result;
    }

    public static <T> Result fail(String msg){
        result.setCode(500);
        result.setMessage(msg);
        result.setData(null);
        result.setDataList(null);
        return result;
    }

    public static <T> Result fail(){
        result.setCode(500);
        result.setMessage("操作失败！");
        result.setData(null);
        result.setDataList(null);
        return result;
    }

}
