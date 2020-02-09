package com.shaoyue.weizhegou.module.credit.dialog;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
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
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.CeditApi;
import com.shaoyue.weizhegou.entity.cedit.NewQianZiBean;
import com.shaoyue.weizhegou.entity.cedit.RefreshBean;
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
public class SxSpDialogFragment extends DialogFragment {


    Unbinder unbinder;


    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.tv_pass)
    TextView tvPass;
    @BindView(R.id.tv_ht)
    TextView tvHt;
    @BindView(R.id.tv_my_info)
    TextView tvMyInfo;
    @BindView(R.id.ddv_xb)
    DropDownView ddvXb;
    @BindView(R.id.iv_geren_qian)
    ImageView ivGerenQian;
    @BindView(R.id.rl_select)
    RelativeLayout rlSelect;
    @BindView(R.id.ddv_xb_2)
    DropDownView ddvXb2;
    @BindView(R.id.rl_select_2)
    RelativeLayout rlSelect2;
    @BindView(R.id.tv_fj)
    TextView tvFj;

    @BindView(R.id.rl_qz)
    RelativeLayout rlqz;



    private String id;

    private String canRutrnid;

    private TiJiaoBean tiJiaoBean;
    String img;
    private String cjjg = "agreed";
    boolean qzVisable;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sxsp, null);
        unbinder = ButterKnife.bind(this, view);
        if (qzVisable) {
            rlqz.setVisibility(View.GONE);
        }
        if (ObjectUtils.isNotEmpty(tiJiaoBean.getVsfc())) {
            tvFj.setVisibility(View.VISIBLE);
            if ("stop".equals(tiJiaoBean.getVsfc())) {
                tvFj.setText("终止");
            } else if ("reject".equals(tiJiaoBean.getVsfc())) {
                tvFj.setText("否决");
            }
        }
        tvMyInfo.setText(tiJiaoBean.getNextNodeName());
        List<Map<String, Object>> dataList = new ArrayList<>();
        List<Map<String, Object>> dataList2 = new ArrayList<>();
        rlSelect.setVisibility(View.GONE);
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


        if (ObjectUtils.isNotEmpty(tiJiaoBean.getCanReturnTo())) {
            for (int i = 0; i < tiJiaoBean.getCanReturnTo().size(); i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", tiJiaoBean.getCanReturnTo().get(i).getActivityName() + tiJiaoBean.getCanReturnTo().get(i).getAssignee());
                map.put("key", tiJiaoBean.getCanReturnTo().get(i).getActivityId());
                dataList2.add(map);
            }
            ddvXb2.setupDataList(dataList2);
            ddvXb2.setOnItemClickListener(new DropDownView.OnItemClickListener() {
                @Override
                public void onItemClick(Map<String, Object> map, int pos, int realPos) {
                    canRutrnid = map.get("key").toString();
                }
            });
        }
        etName.clearFocus();
        initView(dialog, view);

        return dialog;
    }


    public static SxSpDialogFragment newInstance(TiJiaoBean id, boolean qianzivisable) {
        Bundle args = new Bundle();
        args.putSerializable("id", id);
        args.putBoolean("qianzi", qianzivisable);
        SxSpDialogFragment fragment = new SxSpDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            tiJiaoBean = (TiJiaoBean) getArguments().getSerializable("id");
            qzVisable = getArguments().getBoolean("qianzi");
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
            GlideNewImageLoader.displayImageNoCacheNoDefault(getActivity(), ivGerenQian, imgUrl);
            img = event.getImg();
        }
    }

    @OnClick({R.id.tv_cancel, R.id.iv_close, R.id.tv_pass, R.id.tv_ht, R.id.tv_save, R.id.iv_geren_qian, R.id.tv_fj})
    public void onViewClicked(View view) {

        Drawable drawable = getResources().getDrawable(R.drawable.icon_left_star_black);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        Drawable drawable2 = getResources().getDrawable(R.drawable.icon_left_blue);
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        switch (view.getId()) {
            case R.id.iv_geren_qian:
                UIHelper.showXezibanDialog(getActivity());
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.iv_close:
                dismiss();
                break;
            case R.id.tv_fj:
                cjjg = tiJiaoBean.getVsfc();
                rlSelect2.setVisibility(View.GONE);
                rlSelect.setVisibility(View.GONE);
                tvPass.setCompoundDrawables(drawable, null, null, null);
                tvHt.setCompoundDrawables(drawable, null, null, null);
                tvFj.setCompoundDrawables(drawable2, null, null, null);
                break;
            case R.id.tv_pass:
                rlSelect2.setVisibility(View.GONE);
                if (ObjectUtils.isNotEmpty(tiJiaoBean.isSelectApprovalUser()) && tiJiaoBean.isSelectApprovalUser()) {
                    rlSelect.setVisibility(View.VISIBLE);
                } else {
                    rlSelect.setVisibility(View.GONE);
                }
                cjjg = "agreed";
                tvHt.setCompoundDrawables(drawable, null, null, null);
                tvPass.setCompoundDrawables(drawable2, null, null, null);
                tvFj.setCompoundDrawables(drawable, null, null, null);
                break;
            case R.id.tv_ht:
                cjjg = "returnTo";
                rlSelect.setVisibility(View.GONE);
                rlSelect2.setVisibility(View.VISIBLE);
                tvPass.setCompoundDrawables(drawable, null, null, null);
                tvHt.setCompoundDrawables(drawable2, null, null, null);
                tvFj.setCompoundDrawables(drawable, null, null, null);
                break;
            case R.id.tv_save:
                String clyi = etName.getText().toString().trim();
                Map<String, String> params = new HashMap<>();
                params.put("result", cjjg);
                //params.put("attachmentsUrl", "[\"" + img + "\"]");
                params.put("description", clyi);
                params.put("target", canRutrnid);
                params.put(tiJiaoBean.getVariableName(), id);
                if (ObjectUtils.isEmpty(img) && !qzVisable) {
                    ToastUtil.showBlackToastSucess("未签名");
                    return;
                }
                params.put("attachmentsUrl", "[\"" + img + "\"]");


                CeditApi.putSplc(params, new BaseCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSucc(BaseResponse<Void> result) {
                        ToastUtil.showBlackToastSucess("保存成功");
                        EventBus.getDefault().post(new RefreshBean());
                        dismiss();
                    }
                }, this);
                break;

        }
    }


}