package com.shaoyue.weizhegou.entity.user;

import com.google.gson.annotations.SerializedName;
import com.shaoyue.weizhegou.base.BaseBean;

/**
 * 作者：PangLei on 2019/5/8 0008 10:29
 * <p>
 * 邮箱：434604925@qq.com
 */
public class UserInfoBean extends BaseBean {


    @SerializedName("create_time")
    private String create_time;

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    @SerializedName("rank_id")
    private int rank_id;

    public int getRank_id() {
        return rank_id;
    }

    public void setRank_id(int rank_id) {
        this.rank_id = rank_id;
    }

    @SerializedName("rank_name")
    private String rankName;

    @SerializedName("headerpic")
    private String headerPic;

    @SerializedName("nickname")
    private String nickName;

    @SerializedName("mobile")
    private String mobile;
    //消费金额
    @SerializedName("total_consume_amount")
    private String total_year_consume;
    //结余
    @SerializedName("balance")
    private String balance;
    //积分
    @SerializedName("score")
    private String score;
    //待收益
    @SerializedName("to_sum_amount")
    private String to_sum_amount;

    @SerializedName("withdraw_auth")
    private String withdraw_auth;

    @SerializedName("username")
    private String username;

    @SerializedName("realname")
    private String realname;

    @SerializedName("sex")
    private String sex;

    @SerializedName("email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    @SerializedName("phone")
    private String phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWithdraw_auth() {
        return withdraw_auth;
    }

    public void setWithdraw_auth(String withdraw_auth) {
        this.withdraw_auth = withdraw_auth;
    }


    public String getTo_sum_amount() {
        return to_sum_amount;
    }

    public void setTo_sum_amount(String to_sum_amount) {
        this.to_sum_amount = to_sum_amount;
    }

    public String getShop_protrait() {
        return shop_protrait;
    }

    public void setShop_protrait(String shop_protrait) {
        this.shop_protrait = shop_protrait;
    }

    @SerializedName("shop_portrait")
    private String shop_protrait;

    //佣金金额
    @SerializedName("commission_balance")
    private String commission_balance;
    //佣金总额
    @SerializedName("commission_income")
    private String commission_income;


    public String getCommission_income() {
        return commission_income;
    }

    public void setCommission_income(String commission_income) {
        this.commission_income = commission_income;
    }

    @SerializedName("referrer_id")
    private String referrer_id;

    @SerializedName("id")
    private String id;
    //用戶头像
    @SerializedName("avatar")
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReferrer_id() {
        return referrer_id;
    }

    public void setReferrer_id(String referrer_id) {
        this.referrer_id = referrer_id;
    }

    public String getRankName() {
        return rankName;
    }

    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public String getHeaderPic() {
        return headerPic;
    }

    public void setHeaderPic(String headerPic) {
        this.headerPic = headerPic;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTotal_year_consume() {
        return total_year_consume;
    }

    public void setTotal_year_consume(String total_year_consume) {
        this.total_year_consume = total_year_consume;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCommission_balance() {
        return commission_balance;
    }

    public void setCommission_balance(String commission_balance) {
        this.commission_balance = commission_balance;
    }


}
