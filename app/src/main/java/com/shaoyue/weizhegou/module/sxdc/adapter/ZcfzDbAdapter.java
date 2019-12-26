package com.shaoyue.weizhegou.module.sxdc.adapter;


import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.RefreshBean;
import com.shaoyue.weizhegou.entity.sxdc.DbBean;

import org.greenrobot.eventbus.EventBus;

/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class ZcfzDbAdapter extends BaseQuickAdapter<DbBean, BaseViewHolder> {

    public ZcfzDbAdapter() {
        super(R.layout.item_danbao);
    }

    public FragmentActivity getActivity() {
        return activity;
    }

    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    private FragmentActivity activity;


    @Override
    protected void convert(final BaseViewHolder helper, final DbBean item) {

        final EditText mDdvXB = helper.getView(R.id.et_2);
        mDdvXB.setInputType(InputType.TYPE_CLASS_NUMBER);
        if (helper.getAdapterPosition() == 0) {

            helper.setText(R.id.et_1, item.getJkr());
            helper.setText(R.id.et_2, item.getJkje());
            helper.setText(R.id.et_3, item.getWjfl());
            helper.setText(R.id.et_4, item.getJkbz());
            helper.getView(R.id.et_1).setEnabled(false);
            helper.getView(R.id.et_2).setEnabled(false);
            helper.getView(R.id.et_3).setEnabled(false);
            helper.getView(R.id.et_4).setEnabled(false);
            helper.setBackgroundColor(R.id.et_1, activity.getResources().getColor(R.color.reveal));
            helper.setBackgroundColor(R.id.et_2, activity.getResources().getColor(R.color.reveal));
            helper.setBackgroundColor(R.id.et_3, activity.getResources().getColor(R.color.reveal));
            helper.setBackgroundColor(R.id.et_4, activity.getResources().getColor(R.color.reveal));
        } else {
            getEdit(R.id.et_1, helper, item);
            getEdit(R.id.et_2, helper, item);
            getEdit(R.id.et_3, helper, item);
            getEdit(R.id.et_4, helper, item);

        }

    }

    public void getEdit(final int id, final BaseViewHolder helper, final DbBean item) {
        final EditText mDdvXB = helper.getView(id);

        final TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (mDdvXB.hasFocus()) {
                    switch (id) {
                        case R.id.et_1:
                            item.setJkr(editable.toString().trim());
                            break;
                        case R.id.et_2:
                            item.setJkje(editable.toString().trim());
                            break;
                        case R.id.et_3:
                            item.setWjfl(editable.toString().trim());
                            break;
                        case R.id.et_4:
                            item.setJkbz(editable.toString().trim());
                            break;
                    }

                }
            }
        };
        mDdvXB.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    mDdvXB.addTextChangedListener(textWatcher);
                } else {

                    mDdvXB.removeTextChangedListener(textWatcher);
                    EventBus.getDefault().post(new RefreshBean());
                }
            }
        });

        switch (id) {
            case R.id.et_1:
                mDdvXB.setText(item.getJkr());
                break;
            case R.id.et_2:

                mDdvXB.setText(item.getJkje());
                break;
            case R.id.et_3:

                mDdvXB.setText(item.getWjfl());
                break;
            case R.id.et_4:

                mDdvXB.setText(item.getJkbz());
                break;
        }

    }
}


