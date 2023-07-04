package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static java.lang.System.exit;


public class MyActivity extends AppCompatActivity {
    private Button btnregister;
    private EditText editname;
    private RadioGroup rad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        btnregister = (Button)findViewById(R.id.btnregister);
        editname = (EditText)findViewById(R.id.editname);
        rad = (RadioGroup)findViewById(R.id.radioGroup);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name,sex = "";
                Intent it =new Intent(MyActivity.this, MyActivity2.class);
                name =editname.getText().toString();
                //遍历RadioGroup找出被选中的单选按钮
                for(int i = 0;i < rad.getChildCount();i++)
                {
                    RadioButton rd = (RadioButton)rad.getChildAt(i);
                    if(rd.isChecked())
                    {
                        sex = rd.getText().toString();
                        break;
                    }
                }
                //新建Bundle对象,并把数据写入
                Bundle bd =new Bundle();
                bd.putCharSequence("user",name);
                bd.putCharSequence("sex",sex);
                //将数据包Bundle绑定到Intent上
                it.putExtras(bd);
                startActivity(it);
                //关闭第一个Activity
                finish();
            }
        });
    }
    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            ActivityCollector.finishAll();
            ActivityManager activityMgr = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            exit(0);
        } catch (Exception ignored) {}
    }
    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };
    //保存点击的时间
    private long exitTime = 0;
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                exit(0);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
