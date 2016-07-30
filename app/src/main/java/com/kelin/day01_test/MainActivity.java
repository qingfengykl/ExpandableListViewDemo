package com.kelin.day01_test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView mListView;
    private List<String> mGroupdata;//定义组数据
    private List<List<String>> mChildrendata;//定义组中的子数据
    private int mPosition;
    private long mPackedPosition;
    private long mId;
    private int mPackedPositionType;
    private AlertDialog.Builder mBuilder;
    private EditText mEditText;
    private MyExpandableListAdapter mAdapter;
    private PopupMenu mPopupMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initData();
        mListView = new ExpandableListView(this);
        mListView = ((ExpandableListView) findViewById(R.id.main_expand));
        mAdapter = new MyExpandableListAdapter(this, mGroupdata, mChildrendata);
        mListView.setAdapter(mAdapter);
        /**
         * 给列表注册上下文菜单
         */
//        registerForContextMenu(mListView);

        mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                initPopupMenu(view);
                return true;
            }
        });


        /**
         * AlertDialog
         */
        mBuilder = new AlertDialog.Builder(this);
        mEditText = (EditText) getLayoutInflater().inflate(R.layout.alert_dialog_edit, null);
        mBuilder.setTitle("添加").setIcon(R.mipmap.ic_launcher)
                .setView(mEditText)
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = mEditText.getText().toString();
                        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                        if (mPackedPositionType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
                            mGroupdata.add((int) mId, text);
                            mChildrendata.add(new ArrayList<String>());
                            mAdapter.notifyDataSetChanged();
                        } else {


                        }

                    }
                })
                .create();
    }

    private void initPopupMenu(View v) {
        /**
         * PopupMenu
         */
        mPopupMenu = new PopupMenu(this, v);
        getMenuInflater().inflate(R.menu.main, mPopupMenu.getMenu());
        mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add:
                        mBuilder.show();
                        break;
                    case R.id.edit:
                        break;
                    case R.id.delete:
                        break;
                    case R.id.cancel:
                        mPopupMenu.dismiss();
                        break;
                }

                return false;
            }
        });
        mPopupMenu.show();
    }

    /**
     * 上下文菜单
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;
        mPackedPosition = info.packedPosition;
        mId = info.id;
        /**
         * 用于判断是父空间还是子控件，0是父控件，1是子控件。该类中定义了常量
         */
        mPackedPositionType = ExpandableListView.getPackedPositionType(mPackedPosition);


        Toast.makeText(MainActivity.this, mPackedPosition + "===" + mId + "===" + mPackedPositionType, Toast.LENGTH_SHORT).show();
        getMenuInflater().inflate(R.menu.main, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:

                break;
            case R.id.edit:
                break;
            case R.id.delete:
                break;
            case R.id.cancel:
                break;
        }
        Toast.makeText(MainActivity.this, mPackedPositionType + "===" + (mPackedPositionType == ExpandableListView.PACKED_POSITION_TYPE_GROUP ? "父空间" : "子控件") + "第个" + mId + "控件", Toast.LENGTH_SHORT).show();
        mBuilder.show();
        return super.onContextItemSelected(item);
    }


    private void initData() {
        mGroupdata = new ArrayList<>();
        mGroupdata.add("国家");
        mGroupdata.add("人物");
        mGroupdata.add("武器");

        mChildrendata = new ArrayList<>();
        List<String> Child1 = new ArrayList<>();
        Child1.add("蜀国");
        Child1.add("魏国");
        Child1.add("吴国");
        mChildrendata.add(Child1);
        List<String> Child2 = new ArrayList<>();
        Child2.add("关羽");
        Child2.add("张飞");
        Child2.add("典韦");
        Child2.add("吕布");
        Child2.add("曹操");
        Child2.add("甘宁");
        Child2.add("郭嘉");
        Child2.add("周瑜");
        mChildrendata.add(Child2);
        List<String> Child3 = new ArrayList<>();
        Child3.add("青龙偃月刀");
        Child3.add("丈八蛇矛枪");
        Child3.add("青钢剑");
        Child3.add("麒麟弓");
        Child3.add("银月枪");
        mChildrendata.add(Child3);
    }
}
