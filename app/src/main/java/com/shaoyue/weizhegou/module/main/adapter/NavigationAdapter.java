package com.shaoyue.weizhegou.module.main.adapter;


import android.widget.ImageView;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.XtPerssionBean;
import com.shaoyue.weizhegou.manager.DomainMgr;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;

/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class NavigationAdapter extends BaseQuickAdapter<XtPerssionBean, BaseViewHolder> {


    public NavigationAdapter() {
        super(R.layout.item_navigation);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final XtPerssionBean item) {
        helper.addOnClickListener(R.id.tv_msg);
        helper.addOnClickListener(R.id.iv_icon);

        ImageView iv = helper.getView(R.id.iv_icon);
        String imgUrl = DomainMgr.getInstance().getBaseUrl() + "jeecg-boot/" + item.getMobileImg();

        GlideNewImageLoader.displayImageNoDefault(mContext, iv, imgUrl);
        helper.setText(R.id.tv_title, item.getTitle());
        if (ObjectUtils.isNotEmpty(item.getNums())) {
            if (item.getNums() > 0) {
                helper.setText(R.id.tv_msg, item.getNums());
                helper.setGone(R.id.tv_msg, true);
            } else {
                helper.setGone(R.id.tv_msg, false);
            }
        }

    }
}
