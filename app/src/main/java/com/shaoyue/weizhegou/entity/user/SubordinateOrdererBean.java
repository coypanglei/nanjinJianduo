package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/5/24 0024 15:44
 * <p>
 * 邮箱：434604925@qq.com
 */
public class SubordinateOrdererBean extends BaseBean {
// {
//                "id":60,
//                "headerpic":"",
//                "nickname":null,
//                "mobile":"",
//                "rank_name":"店主",
//                "create_time":"1970-01-01"
//                 wechat_nickname
//                 parent_nickname
//"level":"
//            }

    @SerializedName("level")
    private String level;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @SerializedName("parent_nickname")
    private String parent_nickname;

    public String getParent_nickname() {
        return parent_nickname;
    }

    public void setParent_nickname(String parent_nickname) {
        this.parent_nickname = parent_nickname;
    }

    @SerializedName("wechat_nickname")
    private String wechat_nickname;

    @SerializedName("id")
    private String id;

    @SerializedName("headerpic")
    private String headerpic;

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("rank_name")
    private String rank_name;


    @SerializedName("create_time")
    private String create_time;

    public String getWechat_nickname() {
        return wechat_nickname;
    }

    public void setWechat_nickname(String wechat_nickname) {
        this.wechat_nickname = wechat_nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeaderpic() {
        return headerpic;
    }

    public void setHeaderpic(String headerpic) {
        this.headerpic = headerpic;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRank_name() {
        return rank_name;
    }

    public void setRank_name(String rank_name) {
        this.rank_name = rank_name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
