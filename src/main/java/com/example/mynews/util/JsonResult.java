package com.example.mynews.util;

import java.io.Serializable;

public class JsonResult<E> implements Serializable {
    private Integer state;
    private String message;
    private E data;//JSON的复杂程度取决于这个对象什么结构。。。

    public JsonResult() {
        super();
    }

    public JsonResult(Integer state) {
        super();
        this.state = state;
    }

    public JsonResult(Throwable e) {
        super();
        // 获取异常对象中的异常信息
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, E data) {
        super();
        this.state = state;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
