package com.shaoyue.weizhegou.module.dhgl.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.libracore.lib.widget.StateButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.DhApi;
import com.shaoyue.weizhegou.api.remote.TyApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.cedit.TimeSelect;
import com.shaoyue.weizhegou.entity.dhgl.CwfxListBean;
import com.shaoyue.weizhegou.module.sxdc.adapter.CwfxAdapter;
import com.shaoyue.weizhegou.util.ObjectToMapUtils;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.widget.datepicker.CustomDatePicker;
import com.shaoyue.weizhegou.widget.datepicker.DateFormatUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class FcwfxFragment extends BaseAppFragment {

    @BindView(R.id.sb_edit)
    StateButton sbEdit;
    @BindView(R.id.sb_add)
    StateButton sbAdd;
    @BindView(R.id.rv_cwfx)
    RecyclerView rvCwfx;
    CwfxAdapter cwfxAdapter;
    private CwfxListBean.RecordsBean recordsBean;

    private List<CwfxListBean.RecordsBean> mlist = new ArrayList<>();

    public static FcwfxFragment newInstance() {

        Bundle args = new Bundle();

        FcwfxFragment fragment = new FcwfxFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cwfx;
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        cwfxAdapter = new CwfxAdapter();
        LinearLayoutManager linearLayout = new LinearLayoutManager(getActivity());
        linearLayout.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvCwfx.setLayoutManager(linearLayout);
        rvCwfx.setAdapter(cwfxAdapter);

        recordsBean = new CwfxListBean.RecordsBean("1");

        recordsBean.setRq("");
        recordsBean.setLdfz("流动负债率%");
        recordsBean.setXsmll("销售毛利率\n(利润总额/营业收入)");
        recordsBean.setLdbl("流动比率%");
        recordsBean.setLdzc("流动资产(万元)");
        recordsBean.setLrze("利润总额");
        recordsBean.setYysr("营业收入");
        recordsBean.setZcfzl("资产负债率%\n(负债总额/资产总额)");
        recordsBean.setZcze("资产总额(万元)");
        recordsBean.setFzze("负债总额(万元)");

        reshData();
        //时间view
        initDatePicker();
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
    private CustomDatePicker mDatePicker;
    private String timeTitle;


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final TimeSelect timeSelect) {

        if ("财务分析时间".equals(timeSelect.getTitle())) {
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
                List<CwfxListBean.RecordsBean> list = cwfxAdapter.getData();
                if (ObjectUtils.isNotEmpty(list.get(3))) {
                    list.get(3).setRq(DateFormatUtils.long2Str(timestamp, false));
                    cwfxAdapter.setNewData(list);
                    cwfxAdapter.notifyDataSetChanged();
                }
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
     * 获取财务分析
     */
    private void reshData() {
        startProgressDialog(true);
        DhApi.getCwfxList(new BaseCallback<BaseResponse<CwfxListBean>>() {
            @Override
            public void onSucc(BaseResponse<CwfxListBean> result) {
                stopProgressDialog();
                mlist.clear();

                mlist.add(recordsBean);
                mlist.add(new CwfxListBean.RecordsBean("2"));
                mlist.add(new CwfxListBean.RecordsBean("2"));
                mlist.add(new CwfxListBean.RecordsBean("3"));

                if (ObjectUtils.isNotEmpty(result.data.getRecords())) {
                    if (result.data.getRecords().size() == 1) {
                        CwfxListBean.RecordsBean recordsBean3 = result.data.getRecords().get(0);
                        recordsBean3.setType("3");
                        mlist.set(3, recordsBean3);
                    }
                    if (result.data.getRecords().size() == 2) {
                        CwfxListBean.RecordsBean recordsBean2 = result.data.getRecords().get(1);
                        recordsBean2.setType("2");
                        CwfxListBean.RecordsBean recordsBean3 = result.data.getRecords().get(0);
                        recordsBean3.setType("3");
                        mlist.set(3, recordsBean3);
                        mlist.set(2, recordsBean2);


                    }
                    if (result.data.getRecords().size() == 3) {

                        CwfxListBean.RecordsBean recordsBean2 = result.data.getRecords().get(1);
                        recordsBean2.setType("2");
                        mlist.set(2, recordsBean2);
                        CwfxListBean.RecordsBean recordsBean3 = result.data.getRecords().get(2);
                        recordsBean3.setType("2");
                        mlist.set(1, recordsBean3);
                        CwfxListBean.RecordsBean recordsBean4 = result.data.getRecords().get(0);
                        recordsBean4.setType("3");
                        mlist.set(3, recordsBean4);
                    }


                }

                LogUtils.e(mlist);
                cwfxAdapter.setNewData(mlist);
                cwfxAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFail(ApiException apiError) {
                super.onFail(apiError);
                stopProgressDialog();
            }
        }, this);

    }

    @OnClick({R.id.sb_edit, R.id.sb_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sb_edit:
                List<CwfxListBean.RecordsBean> list = cwfxAdapter.getData();
                if (ObjectUtils.isNotEmpty(list)) {
                    if (ObjectUtils.isNotEmpty(list.get(3).getId())) {
                        TyApi.editTyINfo("对公财务分析", ObjectToMapUtils.str2Map(list.get(3)), new BaseCallback<BaseResponse<Void>>() {
                            @Override
                            public void onSucc(BaseResponse<Void> result) {
                                ToastUtil.showBlackToastSucess("保存成功");
                                reshData();
                            }
                        }, this);
                    }

                }


                break;
            case R.id.sb_add:

                List<CwfxListBean.RecordsBean> list2 = cwfxAdapter.getData();
                if (ObjectUtils.isNotEmpty(list2)) {
                    if (ObjectUtils.isNotEmpty(list2.get(3))) {
                        Map<String, String> map = ObjectToMapUtils.str2Map(list2.get(3));
                        map.remove("id");
                        TyApi.addTyINfo("对公财务分析", map, new BaseCallback<BaseResponse<Void>>() {
                            @Override
                            public void onSucc(BaseResponse<Void> result) {
                                ToastUtil.showBlackToastSucess("新建成功");
                                reshData();
                            }
                        }, this);
                    }

                }
//                UIHelper.showCwfxFragment(getActivity(), new GoAllSelect(true, "财务分析"));
                break;
        }
    }
}
