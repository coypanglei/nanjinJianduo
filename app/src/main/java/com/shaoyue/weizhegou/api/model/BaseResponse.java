package com.shaoyue.weizhegou.api.model;

import com.google.gson.annotations.SerializedName;

import com.shaoyue.weizhegou.common.CommConfig;

import java.io.Serializable;

/**
 * Created by bin on 17/6/4.
 */

public class BaseResponse<T> implements Serializable {

    public int code;
    @SerializedName("message")
    public String msg;

    @SerializedName(CommConfig.RESPONSE_DATA_ANNOTATION)
    public T data;


    @SerializedName("timestamp")
    public long timeStamp;


    public BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    @Override
    public String toString() {
        return "BaseResponse{" +
                "code=" + code +
                ", msg='" +msg+ '\'' +
                ", result=" + data +
                '}';
    }
}
