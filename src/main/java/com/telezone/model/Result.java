package com.telezone.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Result<T> {

    private T Data;
    private String Message;
    private Integer Status;
    private Boolean IsSuccess;

    public Result(T data, String message, Integer status, Boolean isSuccess) {
        Data = data;
        Message = message;
        Status = status;
        IsSuccess = isSuccess;
    }

    @JsonProperty("Data")
    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    @JsonProperty("Message")
    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @JsonProperty("Status")
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    @JsonProperty("IsSuccess")
    public Boolean getSuccess() {
        return IsSuccess;
    }

    public void setSuccess(Boolean success) {
        IsSuccess = success;
    }

    public static Result Ok(){
        return new Result(null, "操作成功", 100, true);
    }

    public static Result Error(){
        return new Result(null, "操作失败", -100, false);
    }
    public static Result Error(String msg){
        return new Result(null, msg, -100, false);
    }
}
