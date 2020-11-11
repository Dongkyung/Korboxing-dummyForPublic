package com.box.korBoxing.vo.common;

import com.google.gson.Gson;

/**
 * Object json 응답 객체
 */
public class ObjectJsonResponseVO {
	private final int statusCode;
    private final Object result;

    public ObjectJsonResponseVO(int statusCode, Object result) {
    	this.statusCode = statusCode;
        this.result = result;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public int getStatusCode() {
        return statusCode;
    }
    
    public Object getResult() {
        return result;
    }
}
