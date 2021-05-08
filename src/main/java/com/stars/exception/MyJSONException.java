package com.stars.exception;

import com.alibaba.fastjson.JSONException;

/**
 * @author zhaoQiXing
 * @version 1.0
 * @date 2021/5/8 13:54
 */
public class MyJSONException extends JSONException {

    public MyJSONException() {
    }

    public MyJSONException(String message) {
        super(message);
    }

    public MyJSONException(String message, Throwable cause) {
        super(message, cause);
    }
}
