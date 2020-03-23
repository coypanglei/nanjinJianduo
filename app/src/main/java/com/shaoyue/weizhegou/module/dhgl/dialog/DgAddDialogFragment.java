package com.shaoyue.weizhegou.module.dhgl.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.api.remote.TyApi;
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;
import com.shaoyue.weizhegou.entity.cedit.GoAllSelect;
import com.shaoyue.weizhegou.entity.cedit.RefreshBean;
import com.shaoyue.weizhegou.entity.cedit.TimeSelect;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.BasicInformationAdapter;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ToastUtil;
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
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：PangLei on 2019/5/15 0015 10:25
 * <p>
 * 邮箱：434604925@qq.com
 */
public class DgAddDialogFragment extends DialogFragment {


    Unbinder unbinder;
    @BindView(R.id.tv_my_info)
    TextView mTvinfo;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    BasicInformationAdapter mAdapter;
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;

    private CustomDatePicker mDatePicker;
    private String timeTitle;
    private List<BasicInformationBean.RecordsBean> mlist = new ArrayList<>();

    private GoAllSelect goAllSelect;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_danbaodiy, null);
        unbinder = ButterKnife.bind(this, view);
        initView(dialog, view);
        mAdapter = new BasicInformationAdapter();
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvList.setAdapter(mAdapter);
        if (goAllSelect.isAdd()) {
            mTvinfo.setText("新增" + goAllSelect.getTitle());
        } else {
            mTvinfo.setText("修改" + goAllSelect.getTitle());
            tvAdd.setText("修改");
        }
        shaxing();
        initDatePicker();
        return dialog;
    }


    public static DgAddDialogFragment newInstance(GoAllSelect goAllSelect) {
        Bundle args = new Bundle();
        args.putSerializable("goAllSelect", goAllSelect);
        DgAddDialogFragment fragment = new DgAddDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            goAllSelect = (GoAllSelect) getArguments().getSerializable("goAllSelect");
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final TimeSelect timeSelect) {

        if ("日期".equals(timeSelect.getTitle())) {
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
     * 刷新数据
     */

    private void shaxing() {
        CeditApi.getListAll("对公选择行业", goAllSelect.getTitle(), new BaseCallback<BaseResponse<BasicInformationBean>>() {
            @Override
            public void onSucc(BaseResponse<BasicInformationBean> result) {
                mlist.clear();
                try {
                    if (goAllSelect.isAdd()) {
                        for (BasicInformationBean.RecordsBean bean : result.data.getRecords()) {
                            mlist.add(bean);
                        }
                    } else {

//
                        Map<String, String> map = goAllSelect.getJsonObjectmap();
                        for (BasicInformationBean.RecordsBean bean : result.data.getRecords()) {
                            if (map.containsKey(bean.getName())) {
                                bean.setEdit(true);
                                bean.setDefaultvalue(map.get(bean.getName()));

                            }
                            mlist.add(bean);
                        }
                    }
                    mAdapter.setNewData(mlist);
                    mAdapter.notifyDataSetChanged();
                    llBottom.setVisibility(View.VISIBLE);
                } catch (Exception e) {

                }


            }

            @Override
            public void onFail(ApiException apiError) {
                super.onFail(apiError);

            }
        }, this);
    }


    /**
     * 初始化View
     *
     * @param dialog
     * @param view
     */
    private void initView(Dialog dialog, View view) {

        //动画
        dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnim;

        Window window = dialog.getWindow();
//// 把 DecorView 的默认 padding 取消，同时 DecorView 的默认大小也会取消
        WindowManager.LayoutParams layoutParams = window.getAttributes();
// 设置宽度
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);
// 给 DecorView 设置背景颜色，很重要，不然导致 Dialog 内容显示不全，有一部分内容会充当 padding，上面例子有举出
        dialog.setContentView(view, layoutParams);
        dialog.setCanceledOnTouchOutside(false);
    }


    @OnClick({R.id.tv_cancel, R.id.iv_close, R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_add:
                final Map<String, String> map = new HashMap<>();
                List<BasicInformationBean.RecordsBean> list = mAdapter.getData();
                if (ObjectUtils.isNotEmpty(list)) {
                    for (BasicInformationBean.RecordsBean bean : list) {

                        if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {
                            LogUtils.e(bean.getDefaultvalue());
                            if (!bean.getDefaultvalue().contains("请输入") && !bean.getDefaultvalue().contains("选择")) {
                                map.put(bean.getName(), bean.getDefaultvalue());
                            } else {
                                if (bean.getRequire().equals("true")) {
                                    ToastUtil.showBlackToastSucess(bean.getTitile() + "不能为空");
                                    return;
                                }
                            }
                            if ("idcard".equals(bean.getParamtype()) && "true".equals(bean.getRequire())) {
                                if (!RegexUtils.isIDCard18(bean.getDefaultvalue())) {
                                    ToastUtil.showBlackToastSucess(bean.getTitile() + "不正确");
                                    return;
                                }
                            }
                        } else {
                            if (bean.getRequire().equals("true")) {
                                ToastUtil.showBlackToastSucess(bean.getTitile() + "不能为空");
                                return;
                            }
                        }

                    }

                }
                if (goAllSelect.isAdd()) {

                    map.put("id",goAllSelect.getJsonObjectmap().get("id").toString());
                    TyApi.editTyINfo(goAllSelect.getTitle(), map, new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {
                            dismiss();

                            UIHelper.showDgCommonActivity("对公", getActivity(), "对公检查" , map.get("sshy").toString());
                            ToastUtil.showBlackToastSucess("保存成功");
                        }
                    }, this);

                } else {
                    map.put("id",goAllSelect.getJsonObjectmap().get("id").toString());
                    TyApi.editTyINfo(goAllSelect.getTitle(), map, new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {
                            dismiss();
                            EventBus.getDefault().post(new RefreshBean("对公选择行业"));
                            ToastUtil.showBlackToastSucess("修改成功");
                        }
                    }, this);
                }

                break;
        }
    }


}