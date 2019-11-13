package com.shaoyue.weizhegou.module.credit.adapter.shenqing;


import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.user.MainClickBean;
import com.shaoyue.weizhegou.manager.DomainMgr;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;

/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class CreditNavigationAdapter extends BaseQuickAdapter<MainClickBean, BaseViewHolder> {


    public CreditNavigationAdapter() {
        super(R.layout.item_credit_navigation);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final MainClickBean item) {
        helper.addOnClickListener(R.id.iv_icon);
        ImageView iv = helper.getView(R.id.iv_icon);
        String imgUrl = DomainMgr.getInstance().getBaseUrl()+ "jeecg-boot/" + item.getPicPath();

        GlideNewImageLoader.displayImageNoDefault(mContext,iv, imgUrl);
        helper.setText(R.id.tv_title, item.getTitle());
    }
}
