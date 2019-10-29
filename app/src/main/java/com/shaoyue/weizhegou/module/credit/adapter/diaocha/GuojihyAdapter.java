package com.shaoyue.weizhegou.module.credit.adapter.diaocha;


import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.diaocha.AddressSelectBean;

import org.greenrobot.eventbus.EventBus;


/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class GuojihyAdapter extends BaseQuickAdapter<AddressSelectBean, BaseViewHolder> {
    private DialogFragment dialogFragment;

    public GuojihyAdapter() {
        super(R.layout.item_guojihy);

    }

    public DialogFragment getDialogFragment() {
        return dialogFragment;
    }

    public void setDialogFragment(DialogFragment dialogFragment) {
        this.dialogFragment = dialogFragment;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final AddressSelectBean item) {
        helper.setIsRecyclable(false);
        helper.setText(R.id.tv_title, item.getTitle());
        final RecyclerView recyclerView = helper.getView(R.id.rv_hy);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        final ImageView iv = helper.getView(R.id.iv_vs);
        final TextView tv =helper.getView(R.id.tv_title);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ObjectUtils.isNotEmpty(dialogFragment)) {
                    EventBus.getDefault().post(item);
                    dialogFragment.dismiss();
                }
            }
        });
        if(ObjectUtils.isEmpty(item.getChildren())){
            iv.setVisibility(View.INVISIBLE);
        }
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (("invisable").equals(view.getTag())) {
                    iv.setTag("visable");
                    iv.setImageResource(R.drawable.icon_sj_bottom);
                    recyclerView.setVisibility(View.VISIBLE);
                }else {
                    iv.setTag("invisable");
                    iv.setImageResource(R.drawable.icon_sj_left);
                    recyclerView.setVisibility(View.GONE);
                }
            }
        });
        GuojihyAdapter mAdapter = new GuojihyAdapter();
        mAdapter.setDialogFragment(dialogFragment);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setNewData(item.getChildren());

    }
}
