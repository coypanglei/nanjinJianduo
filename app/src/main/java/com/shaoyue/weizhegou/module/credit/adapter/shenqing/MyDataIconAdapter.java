package com.shaoyue.weizhegou.module.credit.adapter.shenqing;


import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;

/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class MyDataIconAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public MyDataIconAdapter() {
        super(R.layout.item_navigation_select);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final String item) {
        ImageView iv = helper.getView(R.id.iv_icon);
        String imgUrl = "http://212.129.138.235:8080/jeecg-boot/mobieImage/" + item;
//        LogUtils.e(imgUrl);
        GlideNewImageLoader.displayImageNoCacheNoDefault(mContext,iv, imgUrl);

    }
}
