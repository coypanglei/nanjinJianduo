package com.shaoyue.weizhegou.module.search.view;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.blankj.utilcode.util.StringUtils;
import com.shaoyue.weizhegou.R;
import com.shaoyue.weizhegou.event.SearchContentEvent;
import com.shaoyue.weizhegou.module.search.view.helper.RecordSQLiteOpenHelper;
import com.shaoyue.weizhegou.router.UIHelper;
import com.shaoyue.weizhegou.util.ToastUtil;
import com.shaoyue.weizhegou.widget.ViewStringGroup;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dd on 18/4/25.
 */
public class Search_View extends LinearLayout {

    private Context context;

    /*UI组件*/
    private LinearLayout current_search_layout;
    private ImageView tv_clear;//清除历史记录
    private EditText et_search;

    private ImageView tv_delete;//清空输入框的内容

    /*列表及其适配器*/
    private ViewStringGroup viewStringGroup;

    /*数据库变量*/
    private RecordSQLiteOpenHelper helper;
    private SQLiteDatabase db;


    //无数据界面
    public RelativeLayout empty_relative;
    public ImageView empty_img;
    public TextView empty_text;

    private int type;


    private boolean isAll;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    /*三个构造函数*/
    //在构造函数里直接对搜索框进行初始化 - init()
    public Search_View(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public Search_View(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public Search_View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private FragmentActivity activity;

    public void setContext(FragmentActivity activity) {
        this.activity = activity;

    }

    /*初始化搜索框*/
    private void init() {

        //初始化UI组件
        initView();

        //实例化数据库SQLiteOpenHelper子类对象
        helper = new RecordSQLiteOpenHelper(context);

        // 第一次进入时查询所有的历史记录
        queryData("");

        //"清空搜索历史"按钮
        tv_clear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空数据库
                UIHelper.showOkClearDialog(activity, "确定清空记录");
            }
        });

        et_search.setHint("请输入搜索内容");


        tv_delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                et_search.setText("");
            }
        });

        //搜索框的文本变化实时监听
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            //输入后调用该方法
            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().trim().length() == 0) {
                    //若搜索框为空,则模糊搜索空字符,即显示所有的搜索历史
//                    tv_tip.setText("搜索历史");
                } else {
//                    tv_tip.setText("搜索结果");
                }

                //每次输入后都查询数据库并显示
                //根据输入的值去模糊查询数据库中有没有数据
                String tempName = et_search.getText().toString();
                queryData(tempName);

            }
        });


        // 搜索框的键盘搜索键
        // 点击回调
        et_search.setOnKeyListener(new OnKeyListener() {// 输入完后按键盘上的搜索键


            // 修改回车键功能
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 按完搜索键后将当前查询的关键字保存起来,如果该关键字已经存在就不执行保存
                    String inputStr = et_search.getText().toString().trim();
                    if (StringUtils.isEmpty(inputStr)) {
                        ToastUtil.showErrorToast("输入内容不能为空");
                        return false;
                    }
                    boolean hasData = hasData(inputStr);
                    if (!hasData) {
                        insertData(inputStr);
                        queryData("");
                    }
                    //根据输入的内容模糊查询商品，并跳转到另一个界面，这个需要根据需求实现
                    EventBus.getDefault().post(new SearchContentEvent(inputStr,type));
                    activity.finish();
                }
                return false;
            }
        });
    }


    /**
     * 封装的函数
     */

    /*初始化组件*/
    private void initView() {
        LayoutInflater.from(context).inflate(R.layout.search_layout, this);
        et_search = (EditText) findViewById(R.id.search_edtv);
        tv_clear = (ImageView) findViewById(R.id.history_delete);
        tv_delete = (ImageView) findViewById(R.id.serch_delete);
        viewStringGroup = (ViewStringGroup) findViewById(R.id.search_viewgroup);
        empty_relative = (RelativeLayout) findViewById(R.id.empty_relative);
        empty_img = (ImageView) findViewById(R.id.empty_img);
//        empty_text = (TextView) findViewById(R.id.empty_text);
        current_search_layout = (LinearLayout) findViewById(R.id.current_search_layout);


    }

    /*插入数据*/
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }

    /*模糊查询数据 并显示在ListView列表上*/
    private void queryData(String tempName) {
        List<String> list = new ArrayList<>();
        //模糊搜索
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);
        // 装入模糊搜索的结果
        int count = 0;
        while (cursor.moveToNext()) {
            count++;
            int nameColumnIndex = cursor.getColumnIndex("name");
            String strValue = cursor.getString(nameColumnIndex);
            if (count <= 10) {
                //根据列名获取列索引
                list.add(strValue);
            } else {
                deleteData(strValue);
            }

        }

//        empty_img.setImageResource(R.mipmap.ico_defalult_no_data);
//        empty_text.setText(R.string.no_more_record_data);
        if (list.size() == 0) {
            empty_relative.setVisibility(View.VISIBLE);
            viewStringGroup.setVisibility(View.GONE);
            current_search_layout.setVisibility(GONE);
        } else {
            empty_relative.setVisibility(View.GONE);
            viewStringGroup.setVisibility(View.VISIBLE);
            current_search_layout.setVisibility(VISIBLE);
            viewStringGroup.addItemViews(list);
        }
        //列表监听
        //即当用户点击搜索历史里的字段后,会直接将结果当作搜索字段进行搜索
        viewStringGroup.setGroupClickListener(new ViewStringGroup.OnGroupItemClickListener() {
            @Override
            public void onGroupItemClick(int itemPos, String value) {
                et_search.setText(value);
//                //进入其界面搜索成功
                EventBus.getDefault().post(new SearchContentEvent(et_search.getText().toString().trim(),type));
                activity.finish();
            }
        });

    }

    /*检查数据库中是否已经有该条记录*/
    private boolean hasData(String tempName) {
        //从Record这个表里找到name=tempName的id
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //判断是否有下一个
        return cursor.moveToNext();
    }

    /*清空数据*/
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();

        viewStringGroup.deleteItemViews();
        et_search.setText("");
    }

    private void deleteData(String str) {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records where name = '" + str + "'");
        db.close();

        viewStringGroup.deleteItemViews();
        et_search.setText("");
    }

    /**
     * 设置EditView内容
     */
    public void setEtText(String text) {
        et_search.setText(text);
    }

    public void clearAll() {
        deleteData();
        queryData("");
    }


}
