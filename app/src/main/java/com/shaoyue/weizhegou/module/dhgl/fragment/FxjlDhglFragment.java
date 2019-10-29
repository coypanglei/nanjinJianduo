package com.shaoyue.weizhegou.module.dhgl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.libracore.lib.widget.StateButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.api.remote.DhApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;
import com.shaoyue.weizhegou.entity.cedit.TimeSelect;
import com.shaoyue.weizhegou.entity.dhgl.XcfxBean;
import com.shaoyue.weizhegou.module.dhgl.adapter.DhglBasicInformationAdapter;
import com.shaoyue.weizhegou.module.dhgl.adapter.DhglXCJYBAdapter;
import com.shaoyue.weizhegou.util.ObjectToMapUtils;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.widget.DropDownView;
import com.shaoyue.weizhegou.widget.datepicker.CustomDatePicker;
import com.shaoyue.weizhegou.widget.datepicker.DateFormatUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


public class FxjlDhglFragment extends BaseAppFragment {

    DhglBasicInformationAdapter mAdapter2;
    @BindView(R.id.id_jiben_1)
    RecyclerView mIdJiben1;
    DhglXCJYBAdapter mAdapter;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.id_jiben_2)
    RecyclerView idJiben2;
    @BindView(R.id.ddv_xb)
    DropDownView ddvXb;
    @BindView(R.id.et_xzyx)
    EditText etXzyx;
    @BindView(R.id.et_zzsx)
    EditText etZzsx;


    private CustomDatePicker mDatePicker;
    @BindView(R.id.ll_all)
    LinearLayout llAll;

    @BindView(R.id.sb_edit)
    StateButton sbEdit;
    private String timeTitle;

    private String id;

    private String status = "维持";

    private Double Ysxed = 0.00;


    public static FxjlDhglFragment newInstance() {

        Bundle args = new Bundle();

        FxjlDhglFragment fragment = new FxjlDhglFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {
            sbEdit.setVisibility(View.GONE);
        }
        initDatePicker();
        mAdapter = new DhglXCJYBAdapter();
        mAdapter.setActivity(getActivity());
        mIdJiben1.setLayoutManager(new LinearLayoutManager(getActivity()));
        mIdJiben1.setAdapter(mAdapter);
        mIdJiben1.setNestedScrollingEnabled(false);//禁止滑动
        mAdapter2 = new DhglBasicInformationAdapter();
        mAdapter2.setActivity(getActivity());
        idJiben2.setAdapter(mAdapter2);
        idJiben2.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        idJiben2.setNestedScrollingEnabled(false);//禁止滑动
        startProgressDialog(true);
        initview();

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dhgl_fxjl;
    }


    /**
     * 刷新数据
     */

    private void initview() {
        ddvXb.setOnItemClickListener(new DropDownView.OnItemClickListener() {
            @Override
            public void onItemClick(Map<String, Object> map, int pos, int realPos) {
                status = map.get("key").toString();
                switch (map.get("key").toString()) {
                    case "维持":
                        etZzsx.setEnabled(false);
                        etZzsx.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_edit_shadow));
                        etXzyx.setEnabled(false);
                        etXzyx.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_edit_shadow));
                        etZzsx.setText(Ysxed + "");
                        etXzyx.setText("0");
                        break;
                    case "取消":
                        etZzsx.setEnabled(false);
                        etZzsx.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_edit_shadow));
                        etXzyx.setEnabled(false);
                        etXzyx.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_edit_shadow));
                        etXzyx.setText(Ysxed + "");
                        etZzsx.setText("0");
                        break;
                    case "调减":
                        etZzsx.setEnabled(false);
                        etZzsx.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_edit_shadow));
                        etXzyx.setEnabled(true);
                        etXzyx.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_edit_add_info));
                        etXzyx.setText("");
                        etZzsx.setText("");
                        break;
                    case "调增":
                        etZzsx.setEnabled(true);
                        etZzsx.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_edit_add_info));
                        etXzyx.setEnabled(false);
                        etXzyx.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_edit_shadow));
                        etXzyx.setText("");
                        etZzsx.setText("");
                        break;
                }
            }
        });
        etXzyx.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (ObjectUtils.isNotEmpty(etXzyx)) {
                    if (b) {
                        etXzyx.addTextChangedListener(textWatcher);
                    } else {

                        etXzyx.removeTextChangedListener(textWatcher);
                    }
                }
            }
        });
        etZzsx.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (ObjectUtils.isNotEmpty(etZzsx)) {
                    if (b) {
                        etZzsx.addTextChangedListener(textWatcherdz);
                    } else {
                        etZzsx.removeTextChangedListener(textWatcherdz);
                    }
                }
            }
        });

        CeditApi.getListAll("分析结论", null, new BaseCallback<BaseResponse<BasicInformationBean>>() {
            @Override
            public void onSucc(BaseResponse<BasicInformationBean> result) {
                llAll.setVisibility(View.VISIBLE);
                stopProgressDialog();

                List<BasicInformationBean.RecordsBean> mlist = result.data.getRecords();
                mlist.add(3, new BasicInformationBean.RecordsBean("false","top", "现场验证情况"));
                mlist.add(new BasicInformationBean.RecordsBean("false","top", "检验结论"));
                mAdapter.setNewData(mlist);


                getListById();
            }

            @Override
            public void onFail(ApiException apiError) {
                super.onFail(apiError);
                stopProgressDialog();
            }
        }, this);


        CeditApi.getListAll("现场检验", "分析结论", new BaseCallback<BaseResponse<BasicInformationBean>>() {
            @Override
            public void onSucc(BaseResponse<BasicInformationBean> result) {
                llAll.setVisibility(View.VISIBLE);
                List<BasicInformationBean.RecordsBean> mlist = result.data.getRecords();
                mAdapter2.setNewData(mlist);
                getListById();
            }


        }, this);
    }


    @Override
    public void onResume() {
        super.onResume();
        getListById();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final TimeSelect timeSelect) {

        if (ObjectUtils.isNotEmpty(timeSelect.getTime())) {
            mDatePicker.show(timeSelect.getTime());
            timeTitle = timeSelect.getTitle();
        }

    }

    /**
     * 时间滑轮
     */
    private void initDatePicker() {
        long beginTimestamp = DateFormatUtils.str2Long("2009-05-01", false);
        long endTimestamp = DateFormatUtils.str2Long("2119-05-01", false);
        // 通过时间戳初始化日期，毫秒级别
        mDatePicker = new CustomDatePicker(getActivity(), new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                List<BasicInformationBean.RecordsBean> list = mAdapter.getData();
                for (BasicInformationBean.RecordsBean recordsBean : list) {
                    if (recordsBean.getTitile().equals(timeTitle)) {
                        recordsBean.setDefaultvalue(DateFormatUtils.long2Str(timestamp, false));
                    }
                }
                mAdapter.setNewData(list);
                mAdapter.notifyDataSetChanged();
            }
        }, beginTimestamp, endTimestamp);

        // 不允许点击屏幕或物理返回键关闭
        mDatePicker.setCancelable(false);
        // 不显示时和分
        mDatePicker.setCanShowPreciseTime(false);
        // 不允许循环滚动
        mDatePicker.setScrollLoop(false);
        // 不允许滚动动画
        mDatePicker.setCanShowAnim(false);
    }

    /**
     * 获取界面数据byid
     */
    private void getListById() {
        DhApi.searchFxjlByZjhm(new BaseCallback<BaseResponse<XcfxBean>>() {
            @Override
            public void onSucc(BaseResponse<XcfxBean> result) {

                if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {

                    sbEdit.setVisibility(View.GONE);
                }
                tvTitle.setText(result.data.getXtyx());

                id = result.data.getId();
                Map<String, String> map = ObjectToMapUtils.str2Map(result.data);
                List<BasicInformationBean.RecordsBean> list = mAdapter.getData();
                if (ObjectUtils.isNotEmpty(list)) {
                    for (BasicInformationBean.RecordsBean bean : list) {
                        if (map.containsKey(bean.getName())) {
                            bean.setEdit(true);
                            bean.setDefaultvalue(map.get(bean.getName()));
                        }
                    }
                    mAdapter.setNewData(list);
                    mAdapter.notifyDataSetChanged();
                }
                List<BasicInformationBean.RecordsBean> list2 = mAdapter2.getData();
                if (ObjectUtils.isNotEmpty(list2)) {
                    for (BasicInformationBean.RecordsBean bean : list2) {
                        if (map.containsKey(bean.getName())) {

                            bean.setEdit(true);
                            bean.setDefaultvalue(map.get(bean.getName()));
                        }
                    }
                    mAdapter2.setNewData(list2);
                    mAdapter2.notifyDataSetChanged();
                }
                if (ObjectUtils.isNotEmpty(result.data.getSxed())) {
                    ddvXb.setSelectName(result.data.getSxed());
                } else {
                    ddvXb.setSelectName("维持");
                }
                etXzyx.setText(result.data.getXzyx() + "");
                etZzsx.setText(result.data.getZysxed() + "");
                Ysxed = result.data.getYsxed();
                status = result.data.getSxed();
                if (ObjectUtils.isNotEmpty(result.data.getSxed())) {
                    switch (result.data.getSxed()) {
                        case "维持":
                            etZzsx.setEnabled(false);
                            etZzsx.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_edit_shadow));
                            etXzyx.setEnabled(false);
                            etXzyx.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_edit_shadow));
                            etZzsx.setText(result.data.getYsxed() + "");
                            etXzyx.setText("0");
                            break;
                        case "取消":
                            etZzsx.setEnabled(false);
                            etZzsx.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_edit_shadow));
                            etXzyx.setEnabled(false);
                            etXzyx.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_edit_shadow));
                            etXzyx.setText(result.data.getYsxed() + "");
                            etZzsx.setText("0");
                            break;
                        case "调减":
                            etZzsx.setEnabled(false);
                            etZzsx.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_edit_shadow));
                            etXzyx.setEnabled(true);
                            etXzyx.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_edit_add_info));
                            break;
                        case "调增":
                            etZzsx.setEnabled(true);
                            etZzsx.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_edit_add_info));
                            etXzyx.setEnabled(false);
                            etXzyx.setBackground(getActivity().getResources().getDrawable(R.drawable.bg_edit_shadow));

                            break;
                    }
                }


            }
        }, this);
    }

    final TextWatcher textWatcherdz = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {


            if (status.equals("调增")) {
                if (ObjectUtils.isNotEmpty(editable.toString())) {
                    Double xzyx = Double.valueOf(editable.toString());
                    if (ObjectUtils.isNotEmpty(etZzsx)) {
                        if (xzyx > Ysxed) {

                        } else {
                            etZzsx.setText("");
                            ToastUtil.showBlackToastSucess("最终授信额度要大于原授信额度");
                        }
                    }
                }

            }
        }
    };

    final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (status.equals("调减")) {
                if (ObjectUtils.isNotEmpty(editable.toString())) {
                    Double xzyx = Double.valueOf(editable.toString());
                    if (ObjectUtils.isNotEmpty(etZzsx)) {
                        if (xzyx < Ysxed) {

                            etZzsx.setText((Ysxed - xzyx) + "");

                        } else {
                            etZzsx.setText("");
                            ToastUtil.showBlackToastSucess("限制用信要小于原授信额度");
                        }
                    }
                }

            }
        }
    };

    @OnClick({R.id.sb_edit})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.sb_edit:

                Map<String, String> map = getMap();
                if (ObjectUtils.isNotEmpty(id)) {
                    map.put("id", id);
                    map.put("sxed", ddvXb.getSelectName());
                    String xzyxEd = etXzyx.getText().toString().trim();
                    String zzsxEd = etZzsx.getText().toString().trim();

                    if (ObjectUtils.isEmpty(zzsxEd)) {
                        ToastUtil.showBlackToastSucess("最终授信额度不能为空");
                        return;
                    }
                    if (ObjectUtils.isEmpty(xzyxEd)) {
                        ToastUtil.showBlackToastSucess("限制用信额度不能为空");
                        return;
                    }
                    map.put("xzyx", xzyxEd);
                    map.put("zysxed", zzsxEd);
                    DhApi.editFxjl(map, new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {
                            ToastUtil.showBlackToastSucess("保存成功");
                            getListById();
                        }
                    }, this);
                } else {
                    firstAdd();
                }


                break;
        }
    }

    /**
     * 把所有参数转换 成map
     *
     * @return
     */
    private Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>();
        List<BasicInformationBean.RecordsBean> list = new ArrayList<BasicInformationBean.RecordsBean>();
        list.clear();
        list.addAll(mAdapter.getData());
        list.addAll(mAdapter2.getData());
        if (ObjectUtils.isNotEmpty(list)) {
            for (BasicInformationBean.RecordsBean bean : list) {
                if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {
                    map.put(bean.getName(), bean.getDefaultvalue());
                }
            }
        }
        return map;
    }

    /**
     * 第一次添加
     */
    private void firstAdd() {
        Map<String, String> map = new HashMap<>();
        List<BasicInformationBean.RecordsBean> list = mAdapter.getData();
        if (ObjectUtils.isNotEmpty(list)) {
            for (BasicInformationBean.RecordsBean bean : list) {
                if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {
                    map.put(bean.getName(), bean.getDefaultvalue());
                } else {

                    if (bean.getRequire().equals("true")) {
                        ToastUtil.showBlackToastSucess(bean.getTitile() + "不能为空");
                        return;
                    }
                }

            }

        }

        map.put("sxed", ddvXb.getSelectName());
        String xzyxEd = etXzyx.getText().toString().trim();
        String zzsxEd = etZzsx.getText().toString().trim();

        if (ObjectUtils.isEmpty(zzsxEd)) {
            ToastUtil.showBlackToastSucess("最终授信额度不能为空");
            return;
        }
        if (ObjectUtils.isEmpty(xzyxEd)) {
            ToastUtil.showBlackToastSucess("限制用信额度不能为空");
            return;
        }
        map.put("xzyx", xzyxEd);
        map.put("zysxed", zzsxEd);
        DhApi.addFxjl(map, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                ToastUtil.showBlackToastSucess("保存成功");
                getListById();
            }
        }, this);

    }


}
