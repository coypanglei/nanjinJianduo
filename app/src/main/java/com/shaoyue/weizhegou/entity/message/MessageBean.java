package com.shaoyue.weizhegou.entity.message;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/7/16 0016 09:56
 * <p>
 * 邮箱：434604925@qq.com
 */
public class MessageBean extends BaseBean {

    // "id":1,
    //                "content":"您在微折购服务中心有未付款的订单,订单号201909091010请及时付款",//消息内容
    //                "skip_info":{//跳转信息
    //                    "type":"order_details",//跳转类型 order_details为订单详情 空为不跳转
    //                    "param":"order_sn=201909091010"//跳转参数
    //                },
    //                "state":1,//状态 0为未读 1为已读 2为已读，并且已点击过跳转链接
    //                "create_time":"2019-07-11 15:53:44"//通知时间
    //

    private int select;

    public int getSelect() {
        return select;
    }

    public void setSelect(int select) {
        this.select = select;
    }

    @SerializedName("id")
    private String id;

    @SerializedName("content")
    private String content;

    @SerializedName("skip_info")
    private MessageInfoBean skip_info;


    @SerializedName("state")
    private int state;

    @SerializedName("create_time")
    private String create_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageInfoBean getSkip_info() {
        return skip_info;
    }

    public void setSkip_info(MessageInfoBean skip_info) {
        this.skip_info = skip_info;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}

