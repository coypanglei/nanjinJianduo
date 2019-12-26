package com.shaoyue.weizhegou.module.credit.fragment.diaocha;

import android.os.Bundle;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.column.Column;
import com.bin.david.form.data.format.bg.BaseBackgroundFormat;
import com.bin.david.form.data.format.draw.MultiLineDrawFormat;
import com.bin.david.form.data.table.TableData;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.api.callback.BaseCallback;
import com.shaoyue.weizhegou.api.model.BaseResponse;
import com.shaoyue.weizhegou.api.remote.DcApi;
import com.shaoyue.weizhegou.base.BaseAppFragment;
import com.shaoyue.weizhegou.entity.diaocha.PjzbBean;

import butterknife.BindView;

public class DcPjzbFragment extends BaseAppFragment {

    @BindView(R.id.table)
    SmartTable table;


    public static DcPjzbFragment newInstance() {

        Bundle args = new Bundle();

        DcPjzbFragment fragment = new DcPjzbFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dcpj;
    }

    @Override
    public void onResume() {
        super.onResume();
        DcApi.getPjzb(new BaseCallback<BaseResponse<PjzbBean>>() {
            @Override
            public void onSucc(BaseResponse<PjzbBean> result) {
                /**
                 * 得分 : 2
                 * 项目 : 个人情况
                 * 满分 : 4
                 * 指标定义或计算公式 : 【18,28），1分、【28,35），2分、【35,50）岁，4分、【50,60）岁,3分、≥60，0分
                 * 指标 : 29
                 * 关键指标名称 : 年龄
                 */
                //普通列
                Column<String> column1 = new Column<>("项目", "项目");
                column1.setAutoMerge(true);
                column1.setFixed(true);
                Column<String> column2 = new Column<>("关键指标名称", "关键指标名称");
                Column<String> column3 = new Column<>("满分", "满分");
                Column<String> column4 = new Column<>("指标定义或计算公式", "指标定义或计算公式");
                column4.setDrawFormat(new MultiLineDrawFormat(getActivity(),300));
                Column<String> column5 = new Column<>("指标", "指标");
                Column<String> column6 = new Column<>("得分", "得分");


                //表格数据 datas是需要填充的数据
                final TableData<PjzbBean.MoblidPjBean> tableData = new TableData<>("自然人评级表",
                        result.data.getMoblidPj(), column1, column2, column3, column4, column5, column6);
                tableData.setShowCount(false);
                //table.setZoom(true,3);是否缩放
                table.getConfig().setShowXSequence(false);
                table.getConfig().setShowYSequence(false);
                table.getConfig().setColumnTitleBackground(new BaseBackgroundFormat(getResources().getColor(R.color.color_dce6f1)));

                table.setTableData(tableData);


            }
        }, this);
    }


}
