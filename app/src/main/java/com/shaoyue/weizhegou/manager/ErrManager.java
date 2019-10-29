package com.shaoyue.weizhegou.manager;


import com.shaoyue.weizhegou.api.exception.ApiException;

public class ErrManager {



    public static ApiException getErrorDetail(String msg) {

        return new ApiException(-1, "error");
    }

}
