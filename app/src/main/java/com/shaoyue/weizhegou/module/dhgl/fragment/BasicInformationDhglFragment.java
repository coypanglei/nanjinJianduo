package com.shaoyue.weizhegou.module.dhgl.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

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
import com.shaoyue.weizhegou.entity.dhgl.DhglInfoGetBean;
import com.shaoyue.weizhegou.module.dhgl.adapter.DhglBasicInformationAdapter;
import com.shaoyue.weizhegou.util.ObjectToMapUtils;
import com.shaoyue.weizhegou.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


public class BasicInformationDhglFragment extends BaseAppFragment {


    @BindView(R.id.id_jiben_1)
    RecyclerView mIdJiben1;
    DhglBasicInformationAdapter mAdapter;
    DhglBasicInformationAdapter mAdapter2;

    @BindView(R.id.id_jiben_2)
    RecyclerView mIdJiben2;

    @BindView(R.id.ll_all)
    LinearLayout llAll;

    @BindView(R.id.sb_edit)
    StateButton sbEdit;


    private String id;


    private List<BasicInformationBean.RecordsBean> mlist1 = new ArrayList<>();

    private List<BasicInformationBean.RecordsBean> mlist2 = new ArrayList<>();


    public static BasicInformationDhglFragment newInstance() {

        Bundle args = new Bundle();

        BasicInformationDhglFragment fragment = new BasicInformationDhglFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {

            sbEdit.setVisibility(View.GONE);
        }


        mAdapter = new DhglBasicInformationAdapter();
        mAdapter.setActivity(getActivity());
        mIdJiben1.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mIdJiben1.setAdapter(mAdapter);
        mIdJiben1.setNestedScrollingEnabled(false);//禁止滑动

        mAdapter2 = new DhglBasicInformationAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mIdJiben2.setLayoutManager(manager);
        mIdJiben2.setAdapter(mAdapter2);
        mIdJiben2.setNestedScrollingEnabled(false);//禁止滑动
        startProgressDialog(true);
        initview();

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dhgl_basic_information;
    }


    /**
     * 刷新数据
     */

    private void initview() {
        CeditApi.getListAll("现场检验", null, new BaseCallback<BaseResponse<BasicInformationBean>>() {
            @Override
            public void onSucc(BaseResponse<BasicInformationBean> result) {
                llAll.setVisibility(View.VISIBLE);
                stopProgressDialog();
                mlist1.clear();
                mlist2.clear();

                for (BasicInformationBean.RecordsBean bean : result.data.getRecords()) {
                    if (bean.getCategory().equals("基本信息")) {
                        mlist1.add(bean);
                    } else if (bean.getCategory().equals("风险信息")) {
                        if ("是否有负面信息".equals(bean.getTitile())) {
                            bean.setParamtype("select_chang");
                        }
                        mlist2.add(bean);
                    }
                }
                mAdapter.setNewData(mlist1);
                mAdapter2.setNewData(mlist2);


                getListById();
            }

            @Override
            public void onFail(ApiException apiError) {
                super.onFail(apiError);
                stopProgressDialog();
            }
        }, this);
    }


    @Override
    public void onResume() {
        super.onResume();
        getListById();
    }


    /**
     * 获取界面数据byid
     */
    private void getListById() {
        DhApi.searchById(new BaseCallback<BaseResponse<DhglInfoGetBean>>() {
            @Override
            public void onSucc(BaseResponse<DhglInfoGetBean> result) {
//                if (ObjectUtils.isNotEmpty(result.data.getId())) {
//
//                } else {
//                    sbEdit.setVisibility(View.VISIBLE);
//                    sbZancun.setVisibility(View.VISIBLE);
                if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {

                    sbEdit.setVisibility(View.GONE);
                }
//                }
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

//                        if ("其他负面信息".equals(bean.getTitile())) {
//                            //首先是拼接字符串
//                            String content = "<font color=\"#FF4747\">" + "含法华网信息" + "</font>";
//                            //换行
//                            bean.setTitile(bean.getTitile() + "<br>" + content);
//                        }
                        if (map.containsKey(bean.getName())) {
                            bean.setEdit(true);
                            bean.setDefaultvalue(map.get(bean.getName()));

                        }
                    }
                    mAdapter2.setNewData(list2);
                    mAdapter2.notifyDataSetChanged();
                }


            }
        }, this);
    }

    @OnClick({R.id.sb_edit})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.sb_edit:

                Map<String, String> map = getMap();
                if (ObjectUtils.isNotEmpty(id)) {
                    map.put("id", id);
                    DhApi.editMyData(map, new BaseCallback<BaseResponse<Void>>() {
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
        List<BasicInformationBean.RecordsBean> list = mAdapter.getData();
        if (ObjectUtils.isNotEmpty(list)) {
            for (BasicInformationBean.RecordsBean bean : list) {
                if (ObjectUtils.isNotEmpty(bean.getDefaultvalue())) {
                    map.put(bean.getName(), bean.getDefaultvalue());
                }
            }

        }
        List<BasicInformationBean.RecordsBean> list2 = mAdapter2.getData();
        if (ObjectUtils.isNotEmpty(list2)) {
            for (BasicInformationBean.RecordsBean bean : list2) {
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
        List<BasicInformationBean.RecordsBean> list2 = mAdapter2.getData();
        if (ObjectUtils.isNotEmpty(list2)) {
            for (BasicInformationBean.RecordsBean bean : list2) {
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

        DhApi.addInfo(map, new BaseCallback<BaseResponse<Void>>() {
            @Override
            public void onSucc(BaseResponse<Void> result) {
                ToastUtil.showBlackToastSucess("保存成功");
                getListById();
            }
        }, this);

    }


}
