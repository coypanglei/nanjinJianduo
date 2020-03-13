package com.shaoyue.weizhegou.module.credit.fragment.apply;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.UserApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.cedit.QianziBean;
import com.shaoyue.weizhegou.entity.cedit.ScreenObject;
import com.shaoyue.weizhegou.entity.cedit.VideoMaterialBean;
import com.shaoyue.weizhegou.entity.user.MainClickBean;
import com.shaoyue.weizhegou.event.OkOrCancelEvent;
import com.shaoyue.weizhegou.manager.DomainMgr;
import com.shaoyue.weizhegou.manager.UserMgr;
import com.shaoyue.weizhegou.module.credit.adapter.shenqing.QianzibanAdapter;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.GlideNewImageLoader;
import com.shaoyue.weizhegou.util.ThreadUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;


public class QianZiBanNewFragment extends BaseAppFragment {


    @BindView(R.id.rv_click)
    RecyclerView mRvClick;
    //有图片
    List<VideoMaterialBean.ListBean> unPic = new ArrayList<>();
    QianzibanAdapter mAdapter;
    @BindView(R.id.iv_geren_qian)
    ImageView mIvGerenQian;
    @BindView(R.id.iv_yh)
    ImageView mIvGzQian;
    @BindView(R.id.tv_time)
    TextView mTvTime;

    @BindView(R.id.tv_sfzh)
    TextView mTvSfzh;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.sc_all)
    ScrollView scAll;
    @BindView(R.id.ll_9)
    LinearLayout ll9;
    Unbinder unbinder;
    @BindView(R.id.tv_txrq)
    TextView tvTxrq;


    private Bitmap bitmap;
    private List<MainClickBean> mlist = new ArrayList<>();
    private int title;
    private QianziBean qianziBean;

    public static QianZiBanNewFragment newInstance(QianziBean mQianziBean, int title) {
        Bundle args = new Bundle();
        args.putSerializable("qianziban", mQianziBean);
        args.putInt("title", title);
        QianZiBanNewFragment fragment = new QianZiBanNewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_qianzi_ban;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        if (ObjectUtils.isNotEmpty(getArguments())) {
            qianziBean = (QianziBean) getArguments().getSerializable("qianziban");
            title = getArguments().getInt("title");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 刷新界面
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(QianziBean event) {
        LogUtils.e(event);
        if (ObjectUtils.isNotEmpty(event)) {
            if (qianziBean.getJs().equals(event.getJs())) {
                qianziBean = event;
                initData();
                ThreadUtil.runInUIThread(new Runnable() {
                    @Override
                    public void run() {
                        if (ObjectUtils.isNotEmpty(qianziBean.getSqrqm()) && ObjectUtils.isNotEmpty(qianziBean.getSqrjbkhjlqm()) && ObjectUtils.isEmpty(qianziBean.getUploadImg())) {
                            saveBitmapFile(scrollViewScreenShot(ll9),qianziBean);
                        }
                    }
                }, 1000);
            }
        }
    }

    public String saveBitmapFile(Bitmap fileNa, final QianziBean qianziBean) {
        // 首先保存图String fileName
        File appDir = new File(Environment.getExternalStorageDirectory(), "vgmap");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName =   qianziBean.getJs()+".jpg";
        File file = new File(appDir, fileName);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            fileNa.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
            UserApi.updatePic(file, SPUtils.getInstance().getString(UserMgr.SP_ID_CARD), new BaseCallback<BaseResponse<String>>() {
                @Override
                public void onSucc(BaseResponse<String> result) {
                    stopProgressDialog();
                    EventBus.getDefault().post(new ScreenObject(result.msg, qianziBean.getJs()));



                }


            }, this);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
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

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        mAdapter = new QianzibanAdapter();
        mlist.add(new MainClickBean("审核个人信贷业务申请;", false));
        mlist.add(new MainClickBean("审核货记卡、准货记卡申请:", false));
        mlist.add(new MainClickBean("审核本人作为担保人;", false));
        mlist.add(new MainClickBean("受理法人或其他组织的信贷申请或其作为担保人,需要查询其法定代表人、出资人及关联人信用状况;", false));
        mlist.add(new MainClickBean("对公业务贷后管理需查询法定代表人出资人及关联人信用状况;", false));
        mlist.add(new MainClickBean("特约商户实名审查;", false));
        mlist.add(new MainClickBean("资信审查;", false));
        mlist.add(new MainClickBean("客户准入资格审查;", false));
        mlist.add(new MainClickBean("提取本人或者配偶公积金;", false));
        mlist.get(title).setSelect(true);
        mRvClick.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvClick.setNestedScrollingEnabled(false);//禁止滑动
        mRvClick.setAdapter(mAdapter);
        mAdapter.setNewData(mlist);
        mAdapter.notifyDataSetChanged();

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                List<MainClickBean> mlist = adapter.getData();
//                for (MainClickBean mainClickBean : mlist) {
//                    mainClickBean.setSelect(false);
//                }
//                mlist.get(position).setSelect(true);
//                String cxlx = (position + 1) + "";
//                qianziBean.setCxlx(cxlx);
//                adapter.setNewData(mlist);
//                adapter.notifyDataSetChanged();
            }
        });
        //ViewTreeObserver.OnTouchModeChangeListener
//        scAll.getViewTreeObserver().OnTouchModeChangeListener
//                bitmap = Bitmap.createBitmap(scAll.getWidth(), scAll.getChildAt(0).getHeight(), Bitmap.Config.RGB_565);
//
//                final Canvas canvas = new Canvas(bitmap);
//                scAll.draw(canvas);

//        int h = 0;
//        Bitmap bitmap = null;
//        for (int i = 0; i < scrollView.getChildCount(); i++) {
//            h += scrollView.getChildAt(i).getHeight();
//            scrollView.getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"));
//        }
//        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h, Bitmap.Config.RGB_565);
//        final Canvas canvas = new Canvas(bitmap);
//        scrollView.draw(canvas);
//        return bitmap;


        initData();
    }

    /**
     * 刷新数据
     */
    private void initData() {

        //签字版上传图片
//        LogUtils.e(qianziBean.getUploadImg());
        if (ObjectUtils.isNotEmpty(qianziBean.getUploadImg())) {
            GlideNewImageLoader.displayImageNoDefault(getActivity(), ivImg, DomainMgr.getInstance().getBaseUrlImg() + qianziBean.getUploadImg());
            scAll.setVisibility(View.GONE);
            ivImg.setVisibility(View.VISIBLE);
        } else {
            scAll.setVisibility(View.VISIBLE);
            ivImg.setVisibility(View.GONE);
        }
        if (ObjectUtils.isNotEmpty(qianziBean)) {
            mTvSfzh.setText("证件号码:" + qianziBean.getZjhm());


            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss
//获取当前时间
            Date date = new Date(System.currentTimeMillis());
            if (ObjectUtils.isNotEmpty(qianziBean.getSxrq())) {
                mTvTime.setText("授权日期:" + qianziBean.getSxrq());
                tvTxrq.setText("填写日期:" + qianziBean.getSxrq());
            } else {
                mTvTime.setText("授权日期:" + simpleDateFormat.format(date));
                tvTxrq.setText("填写日期:" + simpleDateFormat.format(date));
            }
            //个人签名文件
            if (ObjectUtils.isNotEmpty(qianziBean.getSqrqm())) {
                mIvGerenQian.setBackgroundResource(R.color.white);
                GlideNewImageLoader.displayImageNoCacheNoDefault(getActivity(), mIvGerenQian, DomainMgr.getInstance().getBaseUrlImg() + qianziBean.getSqrqm());
            }
            //银行签名
            if (ObjectUtils.isNotEmpty(qianziBean.getSqrjbkhjlqm())) {
                mIvGzQian.setBackgroundResource(R.color.white);
                GlideNewImageLoader.displayImageNoCacheNoDefault(getActivity(), mIvGzQian, DomainMgr.getInstance().getBaseUrlImg() + qianziBean.getSqrjbkhjlqm());
            }

            if (ObjectUtils.isNotEmpty(qianziBean.getCxlx())) {
                int cxlx = Integer.parseInt(qianziBean.getCxlx());
                List<MainClickBean> mlist = mAdapter.getData();
                for (MainClickBean mainClickBean : mlist) {
                    mainClickBean.setSelect(false);
                }
                mlist.get(cxlx - 1).setSelect(true);
                mAdapter.setNewData(mlist);
                mAdapter.notifyDataSetChanged();
            }


        }
    }


    @OnClick({R.id.iv_geren_qian, R.id.iv_yh, R.id.iv_img})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.iv_img:
                unPic.clear();
                VideoMaterialBean.ListBean bean = new VideoMaterialBean.ListBean();
                bean.setZldz(qianziBean.getUploadImg());
                bean.setId(qianziBean.getUploadImg());
                unPic.add(bean);
                VideoMaterialBean videoMaterialBean = new VideoMaterialBean();
                videoMaterialBean.setUrl("ZXSQ");
                videoMaterialBean.setSelect(0);
                videoMaterialBean.setList(unPic);

                if (ObjectUtils.isNotEmpty(qianziBean.getId())) {
                    videoMaterialBean.setVisable("invisable");
                }

                UIHelper.showPicDialog(getActivity(), videoMaterialBean);
                break;
            case R.id.iv_geren_qian:
                if ("查看详情".equals(SPUtils.getInstance().getString("status")) || (ObjectUtils.isNotEmpty(qianziBean.getId())) || "调查".equals(SPUtils.getInstance().getString(UserMgr.SP_XT_TYPE))) {
                    return;
                }
                qianziBean.setType(true);
                UIHelper.showXezibanDialog(getActivity(), qianziBean);
                break;
            case R.id.iv_yh:
                if ("查看详情".equals(SPUtils.getInstance().getString("status")) || (ObjectUtils.isNotEmpty(qianziBean.getId())) || "调查".equals(SPUtils.getInstance().getString(UserMgr.SP_XT_TYPE))) {
                    return;
                }
                qianziBean.setType(false);
                UIHelper.showXezibanDialog(getActivity(), qianziBean);

                break;
        }
    }

    /**
     * 获取scrollview的截屏
     */
    public static Bitmap scrollViewScreenShot(LinearLayout scrollView) {


        Bitmap bitmap = null;
        scrollView.setBackgroundColor(Color.parseColor("#F5F5F5"));
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), scrollView.getHeight(), Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;
    }


}
