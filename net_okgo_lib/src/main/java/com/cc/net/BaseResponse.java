package com.cc.net;

import java.io.Serializable;

/**
 * @author: Administrator on 2018-04-13 15:32
 * @github: https://github.com/CcSimple
 */

public class BaseResponse<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
