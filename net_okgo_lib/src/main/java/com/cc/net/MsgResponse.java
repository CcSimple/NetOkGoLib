package com.cc.net;

import java.io.Serializable;

/**
 * @author: Administrator on 2018-04-16 15:29
 * @github: https://github.com/CcSimple
 * @describe:  常用提示返回格式(code,msg)
 */

public class MsgResponse<T> implements Serializable {
    private int code;
    private T msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getMsg() {
        return msg;
    }

    public void setMsg(T msg) {
        this.msg = msg;
    }
}
