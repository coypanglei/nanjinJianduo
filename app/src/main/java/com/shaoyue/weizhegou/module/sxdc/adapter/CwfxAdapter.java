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
import com.shaoyue.weizhegou.entity.cedit.TimeSelect;
import com.shaoyue.weizhegou.entity.dhgl.CwfxListBean;

import org.greenrobot.eventbus.EventBus;


/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class CwfxAdapter extends BaseQuickAdapter<CwfxListBean.RecordsBean, BaseViewHolder> {


    public CwfxAdapter() {
        super(R.layout.item_cwfx);
    }

    public FragmentActivity getActivity() {
        return activity;
    }

    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    private FragmentActivity activity;


    @Override
    protected void convert(final BaseViewHolder helper, final CwfxListBean.RecordsBean item) {

        /**
         * 如果解析结果是普通对象，如 单纯的 POJO，则可以使用 fromJson(String json, Class<T> classOfT)
         * 如果解析结果是复杂类型，如 List<T> 这种，则应该使用 fromJson(String json, Type typeOfT)
         * json：被转换的 json 格式的字符串
         * typeOfT：解析结果类型
         */

// android:drawableRight="@drawable/icon_bottom_go"


        helper.setText(R.id.et_rq, item.getRq());
        helper.setText(R.id.et_ldbl, item.getLdbl());
        helper.setText(R.id.et_xsmll, item.getXsmll());
        helper.setText(R.id.et_ldzc, item.getLdzc());
        helper.setText(R.id.et_lrze, item.getLrze());
        helper.setText(R.id.et_yysr, item.getYysr());
        helper.setText(R.id.et_zcfzl, item.getZcfzl());
        helper.setText(R.id.et_zcze, item.getZcze());
        helper.setText(R.id.et_ldfz, item.getLdfz());
        helper.setText(R.id.et_fzze, item.getFzze());
        getEdit(R.id.et_ldbl, helper, item);
        getEdit(R.id.et_xsmll, helper, item);
        getEdit(R.id.et_ldzc, helper, item);
        getEdit(R.id.et_lrze, helper, item);
        getEdit(R.id.et_yysr, helper, item);
        getEdit(R.id.et_zcfzl, helper, item);
        getEdit(R.id.et_zcze, helper, item);
        getEdit(R.id.et_ldfz, helper, item);
        getEdit(R.id.et_fzze, helper, item);
        getEdit(R.id.et_rq, helper, item);


    }

    public void getEdit(final int id, final BaseViewHolder helper, final CwfxListBean.RecordsBean item) {
        final EditText mDdvXB = helper.getView(id);

        if ("3".equals(item.getType())) {

            mDdvXB.setInputType(InputType.TYPE_CLASS_NUMBER);
            mDdvXB.setEnabled(true);
            mDdvXB.setBackground(mContext.getResources().getDrawable(R.drawable.bg_edit_add_info));
            if(id==R.id.et_rq) {
                mDdvXB.setFocusable(false);
                mDdvXB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EventBus.getDefault().post(new TimeSelect(mDdvXB.getText().toString().trim(),"财务分析时间"));
                    }
                });
            }
        } else if ("2".equals(item.getType())) {
            mDdvXB.setEnabled(false);
            mDdvXB.setBackground(mContext.getResources().getDrawable(R.drawable.bg_edit_shadow));
        } else if ("1".equals(item.getType())) {
            mDdvXB.setEnabled(false);
            mDdvXB.setBackgroundColor(mContext.getResources().getColor(R.color.reveal));
        }


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
                        case R.id.et_ldbl:
                            item.setLdbl(editable.toString().trim());
                            break;
                        case R.id.et_xsmll:
                            item.setXsmll(editable.toString().trim());
                            break;
                        case R.id.et_ldzc:
                            item.setLdzc(editable.toString().trim());
                            break;
                        case R.id.et_lrze:
                            item.setLrze(editable.toString().trim());
                            break;
                        case R.id.et_yysr:
                            item.setYysr(editable.toString().trim());
                            break;
                        case R.id.et_zcfzl:
                            item.setZcfzl(editable.toString().trim());
                            break;

                        case R.id.et_zcze:
                            item.setZcze(editable.toString().trim());
                            break;
                        case R.id.et_ldfz:
                            item.setLdfz(editable.toString().trim());
                            break;
                        case R.id.et_fzze:
                            item.setFzze(editable.toString().trim());
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


    }
}


