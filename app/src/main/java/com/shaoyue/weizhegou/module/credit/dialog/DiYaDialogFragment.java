package com.shaoyue.weizhegou.module.credit.dialog;

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
import com.shaoyue.weizhegou.entity.cedit.BasicInformationBean;
import com.shaoyue.weizhegou.entity.cedit.GoAllSelect;
import com.shaoyue.weizhegou.entity.cedit.RefreshBean;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.BasicInformationAdapter;
import com.shaoyue.weizhegou.util.ObjectToMapUtils;
import com.shaoyue.weizhegou.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

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
public class DiYaDialogFragment extends DialogFragment {


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

        return dialog;
    }


    public static DiYaDialogFragment newInstance(GoAllSelect goAllSelect) {
        Bundle args = new Bundle();
        args.putSerializable("goAllSelect", goAllSelect);
        DiYaDialogFragment fragment = new DiYaDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            goAllSelect = (GoAllSelect) getArguments().getSerializable("goAllSelect");
        }
    }

    /**
     * 刷新数据
     */

    private void shaxing() {
        CeditApi.getListAll("抵押担保", goAllSelect.getTitle(), new BaseCallback<BaseResponse<BasicInformationBean>>() {
            @Override
            public void onSucc(BaseResponse<BasicInformationBean> result) {

                mlist.clear();
                if (goAllSelect.isAdd()) {
                    for (BasicInformationBean.RecordsBean bean : result.data.getRecords()) {
                        mlist.add(bean);
                    }
                } else {
                    Map<String, String> map = null;
                    if ("自然人担保分析".equals(goAllSelect.getTitle())) {
                        map = ObjectToMapUtils.str2Map(goAllSelect.getmZiRanDanBao());
                    } else if ("抵(质)押分析".equals(goAllSelect.getTitle())) {
                        map = ObjectToMapUtils.str2Map(goAllSelect.getmDiyaDanBao());
                    } else if ("公司担保分析".equals(goAllSelect.getTitle())) {
                        map = ObjectToMapUtils.str2Map(goAllSelect.getmGongsiDanBao());
                    } else if ("企业担保分析".equals(goAllSelect.getTitle())) {
                        map = ObjectToMapUtils.str2Map(goAllSelect.getmQiYeDanBao());
                    }

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
                Map<String, String> map = new HashMap<>();
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

                    String add = CeditApi.DANBAOREN_ADD;
                    if ("自然人担保分析".equals(goAllSelect.getTitle())) {
                        add = CeditApi.DANBAOREN_ADD;
                    } else if ("抵(质)押分析".equals(goAllSelect.getTitle())) {
                        add = CeditApi.DANBAODIYA_ADD;
                    } else if ("公司担保分析".equals(goAllSelect.getTitle())) {
                        add = CeditApi.DANBAOREN_ADD_GONGSI;
                    } else if ("企业担保分析".equals(goAllSelect.getTitle())) {
                        add = CeditApi.DANBAOREN_ADD_QIYE;
                    }
                    CeditApi.addDanbaoInfo(add, map, new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {
                            dismiss();
                            EventBus.getDefault().post(new RefreshBean());
                            ToastUtil.showBlackToastSucess("保存成功");
                        }
                    }, this);
                } else {
                    String edit = CeditApi.DANBAOREN_EDIT;
                    if ("自然人担保分析".equals(goAllSelect.getTitle())) {
                        edit = CeditApi.DANBAOREN_EDIT;
                        map.put("id", goAllSelect.getmZiRanDanBao().getId());
                    } else if ("抵(质)押分析".equals(goAllSelect.getTitle())) {
                        edit = CeditApi.DANBAOZHIYA_EDIT;
                        map.put("id", goAllSelect.getmDiyaDanBao().getId());
                    } else if ("公司担保分析".equals(goAllSelect.getTitle())) {
                        edit = CeditApi.DANBAOREN_EDIT_GONGSI;
                        map.put("id", goAllSelect.getmGongsiDanBao().getId());
                    } else if ("企业担保分析".equals(goAllSelect.getTitle())) {
                        edit = CeditApi.DANBAOREN_EDIT_QIYE;
                        map.put("id", goAllSelect.getmDiyaDanBao().getId());
                    }

                    CeditApi.editDanbaoInfo(edit, map, new BaseCallback<BaseResponse<Void>>() {
                        @Override
                        public void onSucc(BaseResponse<Void> result) {
                            dismiss();
                            EventBus.getDefault().post(new RefreshBean());
                            ToastUtil.showBlackToastSucess("修改成功");
                        }
                    }, this);
                }

                break;
        }
    }


}