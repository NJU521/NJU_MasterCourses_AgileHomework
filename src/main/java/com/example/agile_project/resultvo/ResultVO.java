package com.example.agile_project.resultvo;

import java.io.Serializable;

public class ResultVO<T> implements Serializable {

    // 暂时不知道这个怎么用
    private static final long serialVersionUID = 7974328632719741438L;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Number getTotal() {
        return total;
    }

    public void setTotal(Number total) {
        this.total = total;
    }

    /**
     * 返回Http状态码，例：成功200; Bad Request 400; 服务器内部错误: 500
     */
    private Integer status;

    /**
     * 错误信息，成功时可为null
     */
    private String message;

    /**
     * 返回结果携带的数据
     */
    private T data;

    /**
     * 是否成功：true/false
     */
    private Boolean success;

    /**
     * 当返回结果为List类型时，可计算列的长度，平时置为null
     */
    private Number total;

}