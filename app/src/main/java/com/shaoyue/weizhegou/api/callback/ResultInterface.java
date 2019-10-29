package com.shaoyue.weizhegou.api.callback;


import com.shaoyue.weizhegou.api.exception.ApiException;

public interface ResultInterface<T>{

    void onSucc(T result);

    void onFail(ApiException apiError);

}
