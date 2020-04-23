package com.shaoyue.weizhegou.module.dhgl.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.libracore.lib.widget.StateButton;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.BasicTitle;
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;
import com.shaoyue.weizhegou.entity.cedit.GoAllSelect;
import com.shaoyue.weizhegou.entity.cedit.QianziBean;
import com.shaoyue.weizhegou.entity.cedit.RefreshBean;
import com.shaoyue.weizhegou.entity.cedit.TimeSelect;
import com.shaoyue.weizhegou.entity.diaocha.AddressSelectBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.manager.DomainMgr;
import com.shaoyue.weizhegou.module.sxdc.adapter.BasicInformationNewAdapter;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.widget.datepicker.CustomDatePicker;
import com.shaoyue.weizhegou.widget.datepicker.DateFormatUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：PangLei on 2019/5/15 0015 10:25
 * <p>
 * 邮箱：434604925@qq.com
 */
public class DcAddOrChangeDialogFragment extends BaseTitleFragment {


    @BindView(R.id.sb_edit)
    StateButton sbEdit;
    @BindView(R.id.sb_add)
    StateButton sbAdd;
    @BindView(R.id.rv_cwfx)
    RecyclerView rvCwfx;
    @BindView(R.id.id_ns)
    NestedScrollView idNs;
    @BindView(R.id.iv_geren_qian)
    ImageView ivGerenQian;
    @BindView(R.id.iv_po_qian)
    ImageView ivPoQian;
    @BindView(R.id.ll_qian)
    LinearLayout llQian;


    //标题集合
    private List<BasicTitle> titles = new ArrayList<>();
    private CustomDatePicker mDatePicker;
    private String timeTitle;

    private String gbhyflmc;
    private String gbhyfl;
    private GoAllSelect goAllSelect;
    BasicInformationNewAdapter mAdapter;
    private QianziBean qianziBean;

    @Override
    protected int getContentLayoutId() {
        return R.layout.frgament_dc_add_or_edit;
    }


    public static DcAddOrChangeDialogFragment newInstance(GoAllSelect goAllSelect) {
        Bundle args = new Bundle();
        args.putSerializable("goAllSelect", goAllSelect);
        DcAddOrChangeDialogFragment fragment = new DcAddOrChangeDialogFragment();
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
        qianziBean = new QianziBean();
    }

    private String qz;

    private String poQz;

    /**
     * 刷新界面
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(QianziBean event) {
        LogUtils.e(event);
        if (ObjectUtils.isNotEmpty(event)) {
            if (ObjectUtils.isEmpty(qianziBean.getId())) {
                qianziBean = event;

                initData();
            }
        }
    }

    //删除图片
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(OkOrCancelEvent event) {
        if (event.getmType().equals("是否删除所选图片")) {
            if (event.getId().equals(qianziBean.getUploadImg())) {
                qianziBean.setUploadImg("");

                initData();
            }

        }
    }
    Double pgdj = 0.0;
    Double jzmj = 0.0;
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final RefreshBean refreshBean) {
       if("计算".equals(refreshBean.getTitle())) {
           final List<BasicTitle> list = mAdapter.getData();
           //评估单价x建筑面积

           if (ObjectUtils.isNotEmpty(list)) {
               for (BasicTitle title : list) {
                   for (BasicInformationBean.RecordsBean bean : title.getMlist()) {
                       if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {
                           if ("pgdj".equals(bean.getName())) {
                               pgdj = Double.valueOf(bean.getDefaultvalue());

                           }
                           if ("jzmj".equals(bean.getName())) {
                               jzmj = Double.valueOf(bean.getDefaultvalue());
                           }
                       }
                   }

               }
           }

       }

    }

    /**
     * 刷新数据
     */
    private void initData() {
        //签字版上传图片

        if (ObjectUtils.isNotEmpty(qianziBean)) {
            LogUtils.e(qianziBean.getSqrqm());
            //个人签名文件
            if (ObjectUtils.isNotEmpty(qianziBean.getSqrqm())) {

                GlideNewImageLoader.displayImageNoCacheNoDefault(getActivity(), ivGerenQian, DomainMgr.getInstance().getBaseUrlImg() + qianziBean.getSqrqm());
            }
            if (ObjectUtils.isNotEmpty(qianziBean.getSqrjbkhjlqm())) {
                //ivPoQian.setBackgroundResource(R.color.white);
                GlideNewImageLoader.displayImageNoCacheNoDefault(getActivity(), ivPoQian, DomainMgr.getInstance().getBaseUrlImg() + qianziBean.getSqrjbkhjlqm());
            }


        }
    }


    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        mAdapter = new BasicInformationNewAdapter();
        rvCwfx.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCwfx.setAdapter(mAdapter);
        if (goAllSelect.isAdd()) {
            setCommonTitle("新增" + goAllSelect.getTitle().replace("新增", ""));
        } else {
            setCommonTitle("修改" + goAllSelect.getTitle().replace("新增", ""));

        }
        sbAdd.setVisibility(View.GONE);
        switch (goAllSelect.getTitle()) {
            case "授信调查(自然人)担保分析":
                llQian.setVisibility(View.VISIBLE);
                titles.add(new BasicTitle("担保人", new ArrayList<BasicInformationBean.RecordsBean>()));
                titles.add(new BasicTitle("配偶", new ArrayList<BasicInformationBean.RecordsBean>()));
                titles.add(new BasicTitle("担保信息", new ArrayList<BasicInformationBean.RecordsBean>()));
                break;
            case "新增授信调查-抵(质)押分析":
                titles.add(new BasicTitle("抵押物信息", new ArrayList<BasicInformationBean.RecordsBean>()));
                titles.add(new BasicTitle("土地使用情况", new ArrayList<BasicInformationBean.RecordsBean>()));
                titles.add(new BasicTitle("房屋价值", new ArrayList<BasicInformationBean.RecordsBean>()));
                titles.add(new BasicTitle("评估信息", new ArrayList<BasicInformationBean.RecordsBean>()));
                break;
        }
        shaxing();
        initDatePicker();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final AddressSelectBean addressSelectBean) {

        List<BasicTitle> list = mAdapter.getData();

        if (ObjectUtils.isNotEmpty(list)) {
            for (BasicTitle title : list) {
                for (BasicInformationBean.RecordsBean bean : title.getMlist()) {
                    if (bean.getName().equals("gbhyfl")) {
                        bean.setDefaultvalue(addressSelectBean.getTitle());
                        gbhyfl = addressSelectBean.getKey();

                    }

                }
            }
            mAdapter.setNewData(list);
            mAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(final TimeSelect timeSelect) {
//        if (ObjectUtils.isNotEmpty(timeSelect.getTime())) {
        List<BasicTitle> list = mAdapter.getData();
        for (BasicTitle title : list) {
            for (BasicInformationBean.RecordsBean bean : title.getMlist()) {
                LogUtils.e(timeSelect.getTitle());
                if (bean.getTitile().equals(timeSelect.getTitle())) {

                    mDatePicker.show(timeSelect.getTime());
                    timeTitle = timeSelect.getTitle();
                }
            }


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
                List<BasicTitle> list = mAdapter.getData();
                for (BasicTitle title : list) {
                    for (BasicInformationBean.RecordsBean bean : title.getMlist()) {
                        if (bean.getTitile().equals(timeTitle)) {
                            bean.setDefaultvalue(DateFormatUtils.long2Str(timestamp, false));
                        }
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
        CeditApi.getListAll(goAllSelect.getTitle(), null, new BaseCallback<BaseResponse<BasicInformationBean>>() {
            @Override
            public void onSucc(BaseResponse<BasicInformationBean> result) {

                try {
                    idNs.setVisibility(View.VISIBLE);
                    stopProgressDialog();
                    if (ObjectUtils.isNotEmpty(result.data.getRecords())) {
                        for (BasicTitle title : titles) {
                            for (BasicInformationBean.RecordsBean bean : result.data.getRecords()) {
                                if (bean.getCategory().equals(title.getTitle())) {
                                    title.getMlist().add(bean);
                                }
                            }
                        }
                        mAdapter.setNewData(titles);

                    }
                    //修改时添加数据
                    if (!goAllSelect.isAdd()) {
                        getListById();
                    }

                } catch (Exception e) {
                    LogUtils.e(e);
                }


            }

            @Override
            public void onFail(ApiException apiError) {
                super.onFail(apiError);
                stopProgressDialog();
            }
        }, this);
    }

    /**
     * 获取界面数据byid
     */
    private void getListById() {

        try {

            Map<String, String> map = goAllSelect.getJsonObjectmap();

            List<BasicTitle> list = mAdapter.getData();

            if (ObjectUtils.isNotEmpty(list)) {
                for (BasicTitle title : list) {
                    for (BasicInformationBean.RecordsBean bean : title.getMlist()) {
                        if (map.containsKey(bean.getName())) {
                            bean.setEdit(true);
                            if (bean.getName().equals("gbhyfl")) {
                                LogUtils.e(gbhyflmc);
                                bean.setDefaultvalue(gbhyflmc);
                                gbhyfl = map.get(bean.getName());

                            } else {
                                bean.setDefaultvalue(map.get(bean.getName()));
                            }
                        }
                    }
                }
                mAdapter.setNewData(list);
                mAdapter.notifyDataSetChanged();
            }
            LogUtils.e(map.get("dbrqz"));
            if (ObjectUtils.isNotEmpty(map.get("dbrqz"))) {
                qianziBean.setSqrqm(map.get("dbrqz"));
            }
            if (ObjectUtils.isNotEmpty(map.get("porqz"))) {
                qianziBean.setSqrjbkhjlqm(map.get("porqz"));
            }
            initData();
        } catch (Exception e) {
            LogUtils.e(e);
        }


//
//


    }

    /**
     * 把所有参数转换 成map
     *
     * @return
     */
    private Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>();
        List<BasicTitle> list = mAdapter.getData();

        if (ObjectUtils.isNotEmpty(list)) {
            for (BasicTitle title : list) {
                for (BasicInformationBean.RecordsBean bean : title.getMlist()) {
                    if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {
                        if (bean.getName().equals("gbhyfl")) {
                            LogUtils.e(gbhyfl);
                            map.put(bean.getName(), gbhyfl);
                        } else {
                            map.put(bean.getName(), bean.getDefaultvalue());
                        }
                    }
                }

            }
        }

        return map;
    }

    @OnClick({R.id.sb_edit, R.id.iv_geren_qian, R.id.iv_po_qian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_geren_qian:
                if ("查看详情".equals(SPUtils.getInstance().getString("status")) || (ObjectUtils.isNotEmpty(qianziBean.getId()))) {
                    return;
                }
                qianziBean.setType(true);
                UIHelper.showXezibanDialog(getActivity(), qianziBean);
                break;
            case R.id.iv_po_qian:
                if ("查看详情".equals(SPUtils.getInstance().getString("status")) || (ObjectUtils.isNotEmpty(qianziBean.getId()))) {
                    return;
                }
                qianziBean.setType(false);
                UIHelper.showXezibanDialog(getActivity(), qianziBean);
                break;
            case R.id.sb_edit:

                Map<String, String> map = getMap();

                List<BasicTitle> list = mAdapter.getData();
                if (ObjectUtils.isNotEmpty(list)) {
                    for (BasicTitle title : list) {
                        for (BasicInformationBean.RecordsBean bean : title.getMlist()) {
                            if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {

                            } else {
                                if (bean.getRequire().equals("true")) {
                                    ToastUtil.showBlackToastSucess(bean.getTitile() + "不能为空");
                                    return;
                                }
                            }


                        }

                    }
                }
                Double pgj = pgdj * jzmj / 10000;
                if (pgj != 0) {
                    DecimalFormat f = new DecimalFormat("#######.######");
                    String string = f.format(pgj );
                    map.put("pgj", string);
                }
                if (ObjectUtils.isNotEmpty(goAllSelect.getJsonObjectmap().get("id"))) {
                    map.put("id", goAllSelect.getJsonObjectmap().get("id").toString());
                }
                if (goAllSelect.isAdd()) {

                    String add = CeditApi.DANBAOREN_ADD;
                    if ("授信调查(自然人)担保分析".equals(goAllSelect.getTitle())) {
                        add = CeditApi.DANBAOREN_ADD + "All";
                    } else if ("新增授信调查-抵(质)押分析".equals(goAllSelect.getTitle())) {
                        add = CeditApi.DANBAODIYA_ADD + "All";
                    } else if ("公司担保分析".equals(goAllSelect.getTitle())) {
                        add = CeditApi.DANBAOREN_ADD_GONGSI + "All";
                    } else if ("授信调查-企业担保分析".equals(goAllSelect.getTitle())) {
                        add = CeditApi.DANBAOREN_ADD_QIYE + "All";
                    }
                    CeditApi.addDanbaoInfo(add, map, new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {
                            removeFragment();

                            ToastUtil.showBlackToastSucess("保存成功");
                        }
                    }, this);
                } else {
                    String edit = CeditApi.DANBAOREN_EDIT;
                    if ("授信调查(自然人)担保分析".equals(goAllSelect.getTitle())) {
                        edit = CeditApi.DANBAOREN_EDIT + "All";
                        map.put("dbrqz", qianziBean.getSqrqm());
                        map.put("porqz", qianziBean.getSqrjbkhjlqm());
                    } else if ("新增授信调查-抵(质)押分析".equals(goAllSelect.getTitle())) {
                        edit = CeditApi.DANBAOZHIYA_EDIT + "All";

                    } else if ("公司担保分析".equals(goAllSelect.getTitle())) {
                        edit = CeditApi.DANBAOREN_EDIT_GONGSI + "All";

                    } else if ("授信调查-企业担保分析".equals(goAllSelect.getTitle())) {
                        edit = CeditApi.DANBAOREN_EDIT_QIYE + "All";

                    }

                    CeditApi.editDanbaoInfo(edit, map, new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {
                            removeFragment();
                            ToastUtil.showBlackToastSucess("修改成功");
                        }
                    }, this);
                }
                break;
        }
    }


}