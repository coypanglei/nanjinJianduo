package com.shaoyue.weizhegou.module.account.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.exception.ApiException;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.MessageApi;
import com.shaoyue.weizhegou.base.BaseTitleFragment;
import com.shaoyue.weizhegou.entity.goods.ShopCarBean;
import com.shaoyue.weizhegou.entity.message.MessageBean;
import com.shaoyue.weizhegou.entity.message.MessageListBean;
import com.shaoyue.weizhegou.entity.message.MessageUnreadBean;
import com.shaoyue.weizhegou.module.account.adapter.MessageCenterAdapter;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ThreadUtil;
import com.shaoyue.weizhegou.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 作者：PangLei on 2019/7/4 0004 15:47
 * <p>
 * 邮箱：434604925@qq.com
 */
public class InfoFragment extends BaseTitleFragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    @BindView(R.id.empty_img)
    ImageView mEmptyImg;
    @BindView(R.id.empty_text)
    TextView mEmptyText;
    @BindView(R.id.empty_relative)
    RelativeLayout mEmptyRelative;
    @BindView(R.id.rv_goods)
    RecyclerView mRvGoods;
    @BindView(R.id.refreshLayout)
    BGARefreshLayout mRefreshLayout;
    @BindView(R.id.rl_all)
    RelativeLayout mRlAll;
    @BindView(R.id.tv_msg_num)
    TextView mTvMsgNum;
    @BindView(R.id.iv_cancel_or_select)
    ImageView mIvCancelOrSelect;
    Unbinder unbinder;

    private MessageCenterAdapter messageCenterAdapter;
    private int page = 1;

    private int total = 0;

    private List<String> mSelectIds = new ArrayList<>();

    private List<MessageBean> messageBeanList = new ArrayList<>();

    public static InfoFragment newInstance() {

        Bundle args = new Bundle();

        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_info;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        initData();
        setCommonTitle("消息中心").setRightBtnV3(R.drawable.icon_right_logo, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.showMainPage(getActivity());
            }
        });
        messageCenterAdapter = new MessageCenterAdapter();
        mRvGoods.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvGoods.setAdapter(messageCenterAdapter);
        messageCenterAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                final MessageBean messageBean = (MessageBean) adapter.getData().get(position);
                messageBeanList = adapter.getData();
                switch (view.getId()) {
                    case R.id.iv_select:

                        if (messageBean.getSelect() == 0) {
                            if (!mSelectIds.contains(messageBean.getId())) {
                                mSelectIds.add(messageBean.getId());
                            }
                        } else {
                            if (mSelectIds.contains(messageBean.getId())) {
                                mSelectIds.remove(messageBean.getId());
                            }
                        }
                        for (MessageBean msgBean : messageBeanList) {
                            if (mSelectIds.contains(msgBean.getId())) {
                                msgBean.setSelect(1);
                            } else {
                                msgBean.setSelect(0);
                            }
                        }
                        adapter.setNewData(messageBeanList);
                        adapter.notifyDataSetChanged();
                        setAllSelect();
                        break;
                    case R.id.ll_click:
                        MessageApi.clickMessage(messageBean.getId(), new BaseCallback<BaseResponse<Void>>() {
                            @Override
                            public void onSucc(BaseResponse<Void> result) {
                                messageBeanList.get(position).setState(1);
                                messageCenterAdapter.setNewData(messageBeanList);
                                messageCenterAdapter.notifyDataSetChanged();
                                if (ObjectUtils.isNotEmpty(messageBean.getSkip_info().getType())) {

                                }
                                setInfoNum();
                            }

                            @Override
                            public void onFail(ApiException apiError) {

                            }
                        }, this);

                        break;
                }

            }
        });
        mEmptyImg.setImageResource(R.drawable.icon_empty_msg);
        mEmptyText.setText("暂无消息" + ToastUtil.getToastMsg());
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity(), true));
        mRefreshLayout.setIsShowLoadingMoreView(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        setInfoNum();
    }

    /**
     * 根据条件刷新数据
     */
    private void initData() {
        page = 1;
        MessageApi.getMessageIndex(page, new BaseCallback<BaseResponse<MessageListBean>>() {
            @Override
            public void onSucc(BaseResponse<MessageListBean> result) {
                total = result.data.getTotal();
                messageBeanList = result.data.getData();
                for (MessageBean msgBean : messageBeanList) {
                    if (mSelectIds.contains(msgBean.getId())) {
                        msgBean.setSelect(1);
                    } else {
                        msgBean.setSelect(0);
                    }

                }
                messageCenterAdapter.setNewData(messageBeanList);
                messageCenterAdapter.notifyDataSetChanged();
                setAllSelect();

                if (total == 0) {
                    mEmptyRelative.setVisibility(View.VISIBLE);
                    mRlAll.setVisibility(View.GONE);
                } else {
                    mEmptyRelative.setVisibility(View.GONE);
                    mRlAll.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFail(ApiException apiError) {
                super.onFail(apiError);

                mEmptyRelative.setVisibility(View.VISIBLE);
            }
        }, this);

    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        ThreadUtil.runInUIThread(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.endRefreshing();
                initData();
            }
        }, 1000);
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
        int size = (total / 20) + 1;
        if (page == size) {
            mRefreshLayout.endLoadingMore();
//            ToastUtil.showErrorToast("没有更多的数据了");
            return false;
        }
        MessageApi.getMessageIndex(page + 1, new BaseCallback<BaseResponse<MessageListBean>>() {
            @Override
            public void onSucc(final BaseResponse<MessageListBean> result) {
                total = result.data.getTotal();
                ThreadUtil.runInUIThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mRefreshLayout == null) {
                            return;
                        }
                        mRefreshLayout.endLoadingMore();
                        messageCenterAdapter.addData(result.data.getData());
                        setAllSelect();
                        page++;
                    }
                }, 1000);
            }

            @Override
            public void onFail(ApiException apiError) {
                mRefreshLayout.endLoadingMore();
//                ToastUtil.showErrorToast(apiError.getErrMsg());
            }
        }, this);

        return true;
    }

    /**
     * 设置消息数量
     */
    private void setInfoNum() {
        //获取会员信息是否已读
        MessageApi.getMessageUnread(new BaseCallback<BaseResponse<MessageUnreadBean>>() {
            @Override
            public void onSucc(BaseResponse<MessageUnreadBean> result) {
                if (result.data.getCount() == 0) {
                    mTvMsgNum.setText("(未读:" + result.data.getCount() + ")");
                } else {
                    mTvMsgNum.setText("(未读:" + result.data.getCount() + ")");
                }
            }

            @Override
            public void onFail(ApiException apiError) {

            }
        }, this);
    }

    /**
     * 全选全取消
     */
    private void setAllSelect() {
        if (mSelectIds.size() == messageCenterAdapter.getData().size() && mSelectIds.size() > 0) {
            mIvCancelOrSelect.setImageResource(R.drawable.icon_goods_select);
            mIvCancelOrSelect.setTag("select");
        } else {
            mIvCancelOrSelect.setImageResource(R.drawable.icon_goods_not_select);
            mIvCancelOrSelect.setTag("no_select");
        }
    }


    @OnClick({R.id.ll_cancel_or_select, R.id.tv_settlement, R.id.ll_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_cancel_or_select:
                if (mIvCancelOrSelect.getTag().equals("select")) {
                    mIvCancelOrSelect.setImageResource(R.drawable.icon_goods_not_select);
                    mIvCancelOrSelect.setTag("no_select");
                    mSelectIds.clear();
                    for (MessageBean msgBean : messageBeanList) {
                        msgBean.setSelect(0);
                    }
                    messageCenterAdapter.setNewData(messageBeanList);
                    messageCenterAdapter.notifyDataSetChanged();
                } else {
                    mIvCancelOrSelect.setImageResource(R.drawable.icon_goods_select);
                    mIvCancelOrSelect.setTag("select");
                    for (MessageBean msgBean : messageBeanList) {
                        mSelectIds.add(msgBean.getId());
                        msgBean.setSelect(1);
                    }
                    messageCenterAdapter.setNewData(messageBeanList);
                    messageCenterAdapter.notifyDataSetChanged();
                }
                break;
            //删除
            case R.id.tv_settlement:
                StringBuilder sb = new StringBuilder();
                for (MessageBean mMessageBean : messageBeanList) {
                    if (mMessageBean.getSelect() == 1) {
                        sb.append(mMessageBean.getId());
                        sb.append(",");
                    }
                }
                String selectSb = sb.toString();
                if (ObjectUtils.isEmpty(selectSb) && selectSb.length() == 0) {
                    ToastUtil.showBlackToastSucess("请选择需要删除的消息");
                    return;
                }
                String ids = selectSb.substring(0, sb.length() - 1);
                MessageApi.removeMessage(ids, new BaseCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSucc(BaseResponse<Void> result) {
                        initData();
                        ToastUtil.showBlackToastSucess("已删除");
                    }

                    @Override
                    public void onFail(ApiException apiError) {
                        ToastUtil.showBlackToastSucess(apiError.getMessage());
                    }
                }, this);
                break;
            //标记已读
            case R.id.ll_clear:
                MessageApi.getMessageRead(new BaseCallback<BaseResponse<Void>>() {
                    @Override
                    public void onSucc(BaseResponse<Void> result) {
                        setInfoNum();
                        initData();
                        ToastUtil.showBlackToastSucess("已全部标记为已读");
                    }

                    @Override
                    public void onFail(ApiException apiError) {
                        ToastUtil.showBlackToastSucess(apiError.getMessage());
                    }
                }, this);
                break;
        }
    }
}
