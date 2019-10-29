package com.shaoyue.weizhegou.api.remote;

import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.helper.ApiHttpClient;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.entity.message.MessageBean;
import com.shaoyue.weizhegou.entity.message.MessageListBean;
import com.shaoyue.weizhegou.entity.message.MessageUnreadBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：PangLei on 2019/7/16 0016 09:25
 * <p>
 * 邮箱：434604925@qq.com
 */
public class MessageApi {

    //消息列表
    private static final String API_MESSAGE_INDEX = "user/message/index";

    //是否有消息
    private static final String API_MESSAGE_UNREAD = "user/message/unread";

    //标记/批量标记消息为已读
    private static final String API_MESSAGE_READ = "user/message/read";

    //删除/批量删除消息
    private static final String API_MESSAGE_REMOVE = "user/message/remove";


    //标记消息为已点击跳转链接
    private static final String API_MESSAGE_CLICK = "user/message/click";


    /**
     * 是否有消息
     *
     * @param tag
     */
    public static void clickMessage(String ids, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("ids", ids);
        ApiHttpClient.post(API_MESSAGE_CLICK, params, callback, tag);
    }


    /**
     * 是否有消息
     *
     * @param tag
     */
    public static void removeMessage(String ids, BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("ids", ids);
        ApiHttpClient.post(API_MESSAGE_REMOVE, params, callback, tag);
    }


    /**
     * 是否有消息
     *
     * @param tag
     */
    public static void getMessageRead(BaseCallback<BaseResponse<Void>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("ids", "0");
        ApiHttpClient.post(API_MESSAGE_READ, params, callback, tag);
    }

    /**
     * 是否有消息
     *
     * @param tag
     */
    public static void getMessageUnread(BaseCallback<BaseResponse<MessageUnreadBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        ApiHttpClient.post(API_MESSAGE_UNREAD, params, callback, tag);
    }

    /**
     * 获取信息列表
     *
     * @param tag
     */
    public static void getMessageIndex(int page, BaseCallback<BaseResponse<MessageListBean>> callback, Object tag) {
        Map<String, String> params = new HashMap<>();
        params.put("page", page + "");
        ApiHttpClient.post(API_MESSAGE_INDEX, params, callback, tag);
    }

}
