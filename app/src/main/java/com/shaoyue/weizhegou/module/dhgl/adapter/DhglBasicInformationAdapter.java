package com.shaoyue.weizhegou.module.dhgl.adapter;


import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;
import com.shaoyue.weizhegou.entity.cedit.RefreshBean;
import com.shaoyue.weizhegou.entity.cedit.TimeSelect;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.widget.DropDownView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者：PangLei on 2019/3/21 0021 11:03
 * <p>
 * 邮箱：xjs250@163.com
 */
public class

DhglBasicInformationAdapter extends BaseQuickAdapter<BasicInformationBean.RecordsBean, BaseViewHolder> {

    public FragmentActivity getActivity() {
        return activity;
    }

    public void setActivity(FragmentActivity activity) {
        this.activity = activity;
    }

    private FragmentActivity activity;


    public
    DhglBasicInformationAdapter() {
        super(null);
        setMultiTypeDelegate(new MultiTypeDelegate<BasicInformationBean.RecordsBean>() {
            @Override
            protected int getItemType(BasicInformationBean.RecordsBean entity) {
                //根据你的实体类来判断布局类型

                if ("select".equals(entity.getParamtype())) {
                    return BasicInformationBean.SELECT;
                } else if ("date".equals(entity.getParamtype())) {
                    return BasicInformationBean.TIME;
                } else if ("text".equals(entity.getParamtype())) {
                    return BasicInformationBean.EDIT_LARGE;
                } else if ("select_chang".equals(entity.getParamtype())) {
                    return BasicInformationBean.SELECT_CHANGE;
                } else if ("full".equals(entity.getParamtype())) {
                    return BasicInformationBean.EDIT_FULL;
                } else {
                    return BasicInformationBean.EDIT;
                }
            }
        });
        //Step.2
        getMultiTypeDelegate()
                .registerItemType(BasicInformationBean.SELECT, R.layout.item_select_kuang)
                .registerItemType(BasicInformationBean.TIME, R.layout.item_time_kuang)
                .registerItemType(BasicInformationBean.EDIT, R.layout.item_edit_kuang)
                .registerItemType(BasicInformationBean.EDIT_LARGE, R.layout.item_edit_kuang_large)
                .registerItemType(BasicInformationBean.EDIT_FULL, R.layout.item_edit_full)
                .registerItemType(BasicInformationBean.SELECT_CHANGE, R.layout.item_select_chang_kuang);

    }

    @Override
    protected void convert(final BaseViewHolder helper, final BasicInformationBean.RecordsBean item) {
        helper.setIsRecyclable(false);

        helper.setText(R.id.tv_name_title, Html.fromHtml(item.getTitile() + ":"));

        if (item.getRequire().equals("false")) {
            //非必填
            final TextView textView = helper.getView(R.id.tv_name_title);
            textView.setCompoundDrawablesWithIntrinsicBounds(null,
                    null, null, null);
        }

        if ("select".equals(item.getParamtype()) || "select_chang".equals(item.getParamtype())) {
            DropDownView mDdvXB = helper.getView(R.id.ddv_xb);
            if ("noedit".equals(item.getType())) {
                mDdvXB.setClickable(false);
                mDdvXB.setBackground(mContext.getResources().getDrawable(R.drawable.bg_edit_shadow));
            } else {
                mDdvXB.setClickable(true);
            }
            if ("国际行业分类".equals(item.getTitile()) || "国标行业分类".equals(item.getTitile())) {
                mDdvXB.setSelectName(item.getDefaultvalue());
                mDdvXB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        UIHelper.showGjhyDialog(activity);
                    }
                });
            } else {
                List<Map<String, Object>> dataList = new ArrayList<>();
                if (ObjectUtils.isNotEmpty(item.getOptionlist())) {
                    for (int i = 0; i < item.getOptionlist().size(); i++) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("name", item.getOptionlist().get(i).getName());
                        map.put("key", item.getOptionlist().get(i).getName());
                        dataList.add(map);
                    }
                    mDdvXB.setupDataList(dataList);
                    mDdvXB.setSelectName(item.getDefaultvalue());
                    mDdvXB.setOnItemClickListener(new DropDownView.OnItemClickListener() {
                        @Override
                        public void onItemClick(Map<String, Object> map, int pos, int realPos) {
                            item.setDefaultvalue(map.get("key").toString());
                            EventBus.getDefault().post(item);
                        }
                    });
                }
            }

        } else {
            if ("date".equals(item.getParamtype())) {
                final TextView textView = helper.getView(R.id.tv_time);
                if (ObjectUtils.isEmpty(item.getDefaultvalue())) {
                    textView.setText("");
                } else {
                    textView.setText(item.getDefaultvalue());
                }
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        EventBus.getDefault().post(new TimeSelect(textView.getText().toString(), item.getTitile()));
                    }
                });

            } else {


                final EditText mDdvXB = helper.getView(R.id.et_name);

                if ("blank".equals(item.getParamtype())) {
                    mDdvXB.setVisibility(View.INVISIBLE);
                    helper.setVisible(R.id.tv_name_title, false);
                } else {
                    mDdvXB.setVisibility(View.VISIBLE);
                    helper.setVisible(R.id.tv_name_title, true);
                }
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
                            EventBus.getDefault().post(new RefreshBean());
                            if ("pgdj".equals(item.getName())) {
                                EventBus.getDefault().post(new RefreshBean("计算"));

                            }
                            if ("jzmj".equals(item.getName())) {
                                EventBus.getDefault().post(new RefreshBean("计算"));
                            }
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
    }


}
