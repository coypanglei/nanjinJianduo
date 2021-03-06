package com.shaoyue.weizhegou.module.credit.adapter.shenqing;


import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huantansheng.easyphotos.EasyPhotos;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.entity.cedit.VideoMaterialBean;
import com.shaoyue.weizhegou.util.GlideEngine;
import com.shaoyue.weizhegou.widget.HorizontalRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


public class VideoListAdapter extends BaseQuickAdapter<VideoMaterialBean, BaseViewHolder> {

    private FragmentActivity mFragment;


    public VideoListAdapter(FragmentActivity context) {
        super(R.layout.item_video_list);
        mFragment = context;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final VideoMaterialBean item) {
        VideoAdapter mAdapter = new VideoAdapter();
        final HorizontalRecyclerView mRecyler = helper.getView(R.id.rv_hor);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyler.setLayoutManager(manager);
        mRecyler.setAdapter(mAdapter);
        //优化嵌套卡顿
        mRecyler.setHasFixedSize(true);
        mRecyler.setNestedScrollingEnabled(false);
        mRecyler.setItemViewCacheSize(600);
        RecyclerView.RecycledViewPool recycledViewPool = new
                RecyclerView.RecycledViewPool();
        mRecyler.setRecycledViewPool(recycledViewPool);

        if (ObjectUtils.isEmpty(item.getList())) {
            List<VideoMaterialBean.ListBean> mlist = new ArrayList<>();
            VideoMaterialBean.ListBean data = new VideoMaterialBean.ListBean();
            VideoMaterialBean.ListBean data2 = new VideoMaterialBean.ListBean();
            if (item.getTitle().contains("借款人身份证") || item.getTitle().contains("配偶身份证")) {
                data.setSxsfzh("正");
                data.setZllx(item.getTitle());
                data2.setSxsfzh("反");
                data2.setZllx(item.getTitle());
                mlist.add(data);
                mlist.add(data2);
                item.setList(mlist);
            } else {
                data.setZllx(item.getTitle());
                mlist.add(data);
                item.setList(mlist);
            }
            //集合不为空
        } else {
            List<VideoMaterialBean.ListBean> mlist = item.getList();
            VideoMaterialBean.ListBean data = new VideoMaterialBean.ListBean();

            if (item.getTitle().contains("借款人身份证") || item.getTitle().contains("配偶身份证")) {
                if (mlist.size() == 1) {
                    if (mlist.get(0).getSxsfzh().equals("正")) {
                        data.setSxsfzh("反");
                        data.setZllx(item.getTitle());
                        mlist.add(data);
                        item.setList(mlist);
                    } else {
                        data.setSxsfzh("正");
                        data.setZllx(item.getTitle());
                        List<VideoMaterialBean.ListBean> mNewlist = new ArrayList<>();
                        mNewlist.add(data);
                        mNewlist.add(mlist.get(0));
                        item.setList(mNewlist);
                    }
                }
            } else {
                boolean add = true;
                for (VideoMaterialBean.ListBean listBean : item.getList()) {
                    if (ObjectUtils.isEmpty(listBean.getId())) {
                        add = false;
                    }
                }
                if (add && item.getList().size() < 1000) {
                    data.setZllx(item.getTitle());
                    mlist.add(data);
                    item.setList(mlist);
                }
            }
        }

        mAdapter.setNewData(item.getList());
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                if ("查看详情".equals(SPUtils.getInstance().getString("status")) || "调查".equals(SPUtils.getInstance().getString(UserMgr.SP_XT_TYPE))) {
//                    return;
//                }
                if ("查看详情".equals(SPUtils.getInstance().getString("status"))) {
                    return;
                }
                List<VideoMaterialBean.ListBean> selet = adapter.getData();
                if (selet.size() <= 1000) {
                    if (ObjectUtils.isEmpty(selet.get(position).getId())) {

                        SPUtils.getInstance().put("selectPic", item.getCategory());
                        SPUtils.getInstance().put("selectPic_id", selet.get(position).getId());
                        SPUtils.getInstance().put("selectPic_zllx", selet.get(position).getZllx());
                        SPUtils.getInstance().put("selectPic_sxsfzh", selet.get(position).getSxsfzh());
                        if (item.getTitle().contains("借款人身份证") || item.getTitle().contains("配偶身份证")) {
                            EasyPhotos.createAlbum(mFragment, true, GlideEngine.getInstance())//参数说明：上下文，是否显示相机按钮，[配置Glide为图片加载引擎](https://github.com/HuanTanSheng/EasyPhotos/wiki/12-%E9%85%8D%E7%BD%AEImageEngine%EF%BC%8C%E6%94%AF%E6%8C%81%E6%89%80%E6%9C%89%E5%9B%BE%E7%89%87%E5%8A%A0%E8%BD%BD%E5%BA%93)
                                    .setFileProviderAuthority("com.shaoyue.weizhegou.fileprovider")//参数说明：见下方`FileProvider的配置`
                                    .setCount(1)//参数说明：最大可选数，默认1
                                    .start(1007);
                        }else {
                            EasyPhotos.createAlbum(mFragment, true, GlideEngine.getInstance())//参数说明：上下文，是否显示相机按钮，[配置Glide为图片加载引擎](https://github.com/HuanTanSheng/EasyPhotos/wiki/12-%E9%85%8D%E7%BD%AEImageEngine%EF%BC%8C%E6%94%AF%E6%8C%81%E6%89%80%E6%9C%89%E5%9B%BE%E7%89%87%E5%8A%A0%E8%BD%BD%E5%BA%93)
                                    .setFileProviderAuthority("com.shaoyue.weizhegou.fileprovider")//参数说明：见下方`FileProvider的配置`
                                    .setCount(8)//参数说明：最大可选数，默认1
                                    .start(1007);
                        }
                    } else {
                        SPUtils.getInstance().put("selectPic", item.getCategory());
                        SPUtils.getInstance().put("selectPic_id", selet.get(position).getId());
                        SPUtils.getInstance().put("selectPic_zllx", selet.get(position).getZllx());
                        SPUtils.getInstance().put("selectPic_sxsfzh", selet.get(position).getSxsfzh());
                        selet.get(position).setDelFlag(true);
                        EventBus.getDefault().post(selet.get(position));
                    }
                }
            }
        });
    }
}
