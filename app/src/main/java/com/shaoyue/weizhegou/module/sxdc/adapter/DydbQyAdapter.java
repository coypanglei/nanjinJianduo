package com.shaoyue.weizhegou.module.sxdc.adapter;


import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;

/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class DydbQyAdapter extends BaseQuickAdapter<BasicInformationBean.RecordsBean, BaseViewHolder> {

    public DydbQyAdapter() {
        super(R.layout.item_syjb);
    }

    public FragmentActivity getActivity() {
        return activity;
    }

    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    private FragmentActivity activity;


    @Override
    protected void convert(final BaseViewHolder helper, final BasicInformationBean.RecordsBean item) {

        /**
         * 如果解析结果是普通对象，如 单纯的 POJO，则可以使用 fromJson(String json, Class<T> classOfT)
         * 如果解析结果是复杂类型，如 List<T> 这种，则应该使用 fromJson(String json, Type typeOfT)
         * json：被转换的 json 格式的字符串
         * typeOfT：解析结果类型
         */


        String titles = item.getTitile();
        String[] strings = titles.split(",");

        if ("top".equals(item.getParamtype())) {
            helper.setText(R.id.tv_title, "");
            helper.setText(R.id.et_1, strings[0]);
            helper.setText(R.id.et_2, strings[1]);
            helper.setText(R.id.et_3, strings[2]);
            helper.setText(R.id.et_4, strings[3]);
            helper.getView(R.id.et_1).setEnabled(false);
            helper.getView(R.id.et_2).setEnabled(false);
            helper.getView(R.id.et_3).setEnabled(false);
            helper.getView(R.id.et_4).setEnabled(false);
            helper.setBackgroundColor(R.id.et_1, activity.getResources().getColor(R.color.reveal));
            helper.setBackgroundColor(R.id.et_2, activity.getResources().getColor(R.color.reveal));
            helper.setBackgroundColor(R.id.et_3, activity.getResources().getColor(R.color.reveal));
            helper.setBackgroundColor(R.id.et_4, activity.getResources().getColor(R.color.reveal));
        } else {
            helper.setText(R.id.tv_title, item.getTitile());
            getEdit(R.id.et_1, helper, item.getList().get(0));
            getEdit(R.id.et_2, helper, item.getList().get(1));
            getEdit(R.id.et_3, helper, item.getList().get(2));
            getEdit(R.id.et_4, helper, item.getList().get(3));
        }

    }

    public void getEdit(final int id, final BaseViewHolder helper, final BasicInformationBean.RecordsBean item) {
        final EditText mDdvXB = helper.getView(id);

        if ("number".equals(item.getParamtype())) {
            mDdvXB.setInputType(InputType.TYPE_CLASS_NUMBER);

        } else if ("idcard".equals(item.getParamtype())) {
            mDdvXB.setKeyListener(DigitsKeyListener.getInstance("0123456789xyzXYZ"));
            InputFilter[] filters = {new InputFilter.LengthFilter(18)};
            mDdvXB.setFilters(filters);
        } else if ("noedit".equals(item.getType()) || "noedit".equals(item.getParamtype())) {
            mDdvXB.setEnabled(false);
            mDdvXB.setBackground(mContext.getResources().getDrawable(R.drawable.bg_edit_shadow));
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
                    item.setDefaultvalue(editable.toString().trim());

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
                }
            }
        });
        if (item.isEdit()) {
            mDdvXB.setText(item.getDefaultvalue());
        } else {
            mDdvXB.setHint(item.getDefaultvalue());
        }

    }
}


