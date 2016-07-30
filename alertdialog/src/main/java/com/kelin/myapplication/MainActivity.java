package com.kelin.myapplication;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private AlertDialog editDialog;
    private EditText edit_name,edit_tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        textView.setText("");
        //初始化对话框
        initDialog();
    }
    private void initDialog() {
        //生成对话框使用的布局对象
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
        //获取布局对象中的视图对象，因为需要从这些视图对象中获取用户输入的数据
        edit_name = (EditText) view.findViewById(R.id.edit_name);
        edit_tel = (EditText) view.findViewById(R.id.edit_tel);

        editDialog = new AlertDialog.Builder(this)
                .setTitle("人员信息编辑对话框")
                .setIcon(R.drawable.ic_launcher)
                .setView(view)//设置对话框使用的自定义布局
                .setPositiveButton("确定", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //当用户点击确定按钮时，得到用户输入的姓名和电话，并添加到textView上
                        String name =  edit_name.getText().toString();
                        String tel = edit_tel.getText().toString();
                        textView.append("\r\n姓名:"+name);
                        textView.append("  电话:"+tel);
                    }
                })
                .create();
    }






    //点击按钮时弹出可以添加人员信息的对话框
    public void addInfo(View v)
    {
        editDialog.show();
    }

}
