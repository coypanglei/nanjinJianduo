package com.shaoyue.weizhegou.module.credit.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.entity.cedit.NewQianZiBean;
import com.shaoyue.weizhegou.entity.cedit.TiJiaoBean;
import com.shaoyue.weizhegou.manager.DomainMgr;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.widget.DropDownView;

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
public class ZhuChaFragment extends DialogFragment {


    Unbinder unbinder;
    @BindView(R.id.ddv_xb)
    DropDownView ddvXb;
    @BindView(R.id.rl_select)
    RelativeLayout rlSelect;
    @BindView(R.id.iv_qianzi)
    ImageView ivQianzi;
    @BindView(R.id.et_ms)
    EditText etMs;

    private String id;

    private TiJiaoBean tiJiaoBean;
    String img;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_tijiao, null);
        unbinder = ButterKnife.bind(this, view);
        List<Map<String, Object>> dataList = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(tiJiaoBean.isSelectApprovalUser()) && tiJiaoBean.isSelectApprovalUser()) {
            rlSelect.setVisibility(View.VISIBLE);
            if (ObjectUtils.isNotEmpty(tiJiaoBean.getUsers())) {
                for (int i = 0; i < tiJiaoBean.getUsers().size(); i++) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", tiJiaoBean.getUsers().get(i).getRealname() + tiJiaoBean.getUsers().get(i).getPositionName());
                    map.put("key", tiJiaoBean.getUsers().get(i).getId());
                    dataList.add(map);
                }
                ddvXb.setupDataList(dataList);
                ddvXb.setOnItemClickListener(new DropDownView.OnItemClickListener() {
                    @Override
                    public void onItemClick(Map<String, Object> map, int pos, int realPos) {
                        id = map.get("key").toString();
                    }
                });
            }
        }

        initView(dialog, view);

        return dialog;
    }


    public static ZhuChaFragment newInstance(TiJiaoBean tiJiaoBean) {
        Bundle args = new Bundle();
        args.putSerializable("tijiao", tiJiaoBean);
        ZhuChaFragment fragment = new ZhuChaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            tiJiaoBean = (TiJiaoBean) getArguments().getSerializable("tijiao");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }

    /**
     * 刷新界面
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(NewQianZiBean event) {
        if (ObjectUtils.isNotEmpty(event)) {
            String imgUrl = DomainMgr.getInstance().getBaseUrlImg() + event.getImg();
            GlideNewImageLoader.displayImageNoCacheNoDefault(getActivity(), ivQianzi, imgUrl);
            img = event.getImg();
        }
    }

    @OnClick({R.id.iv_close, R.id.iv_qianzi, R.id.tv_cancel, R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_close:
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.iv_qianzi:
                UIHelper.showXezibanDialog(getActivity());
                break;
            //保存
            case R.id.tv_add:
                String ms = etMs.getText().toString().trim();
                if(ObjectUtils.isEmpty(id)){
                    ToastUtil.showBlackToastSucess("未填写下环节审核人员");
                    return;
                }
                if(ObjectUtils.isEmpty(img)){
                    ToastUtil.showBlackToastSucess("未签名");
                    return;
                }
                Map<String, String> params = new HashMap<>();
                params.put("processName", "SXSQLC");
                params.put("attachments", "[]");
                params.put("attachmentsUrl", "[\"" + img + "\"]");
                params.put("description", ms);
                params.put(tiJiaoBean.getVariableName(), id);

                CeditApi.putInitiationProcess(params, new BaseCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSucc(BaseResponse<Void> result) {
                        ToastUtil.showBlackToastSucess("保存成功");
                        dismiss();
                    }
                }, this);
                break;
        }
    }


}