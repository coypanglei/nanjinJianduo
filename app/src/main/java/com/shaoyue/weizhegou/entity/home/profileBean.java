package com.shaoyue.weizhegou.entity.home;

import com.shaoyue.weizhegou.base.BaseBean;
import com.shaoyue.weizhegou.entity.goods.SettlementCenterBean;
import com.shaoyue.weizhegou.entity.user.MainClickBean;

import java.util.List;

/**
 * 作者：PangLei on 2019/5/28 0028 14:19
 * <p>
 * 邮箱：434604925@qq.com
 */
public class profileBean extends BaseBean {
    //内容
    private String content;
    //标题
    private String[] mString;

    //跳转页面 0 主页 1
    private int type;

    //跳转的标题和信息
    private List<MainClickBean> mainClickBeans;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public profileBean(String content, int type) {
        this.content = content;
        this.type = type;
    }

    //购物车结算
    private SettlementCenterBean settlementCenterBean;

    public profileBean(int type, List<MainClickBean> mainClickBeans) {
        this.type = type;
        this.mainClickBeans = mainClickBeans;
    }

    public profileBean(SettlementCenterBean settlementCenterBean) {
        this.settlementCenterBean = settlementCenterBean;
    }

    public SettlementCenterBean getSettlementCenterBean() {
        return settlementCenterBean;
    }

    public void setSettlementCenterBean(SettlementCenterBean settlementCenterBean) {
        this.settlementCenterBean = settlementCenterBean;
    }

    public profileBean(String content, String[] mString) {
        this.content = content;
        this.mString = mString;
    }

    public List<MainClickBean> getMainClickBeans() {
        return mainClickBeans;
    }

    public void setMainClickBeans(List<MainClickBean> mainClickBeans) {
        this.mainClickBeans = mainClickBeans;
    }

    public profileBean(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getmString() {
        return mString;
    }

    public void setmString(String[] mString) {
        this.mString = mString;
    }
}
