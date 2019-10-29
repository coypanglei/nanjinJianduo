package com.shaoyue.weizhegou.module.user.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;

import com.shaoyue.weizhegou.entity.user.UserMsgDetailsBean;



public class NotesAdapter extends BaseQuickAdapter<UserMsgDetailsBean, BaseViewHolder> {


    public NotesAdapter() {
        super(R.layout.item_notes_details);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserMsgDetailsBean item) {
        helper.setText(R.id.note_content, item.getDepart_name() + " " + item.getKhmc() + " " + item.getDqhj());
        helper.setText(R.id.tv_time, item.getStarttime());
    }
}
