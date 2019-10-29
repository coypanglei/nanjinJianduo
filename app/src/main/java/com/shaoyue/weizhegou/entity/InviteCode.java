package com.shaoyue.weizhegou.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 2019/1/4.
 */

public class InviteCode {

    @SerializedName("invite_code")
    private String inviteCode;

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getIsFillCode() {
        return isFillCode;
    }

    public void setIsFillCode(String isFillCode) {
        this.isFillCode = isFillCode;
    }

    @SerializedName("is_fill_code")
    private String isFillCode;

    public String getInvitePerson() {
        return invitePerson;
    }

    public void setInvitePerson(String invitePerson) {
        this.invitePerson = invitePerson;
    }

    @SerializedName("invite_person")
    private String invitePerson;
}
