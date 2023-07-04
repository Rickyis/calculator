package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;
import androidx.annotation.Nullable;

public class calculator extends Activity {

    private TextView mini;
    private TextView max;
    private StringBuilder str;
    private int index = 0;

    //数字按钮事件，按对应数字字符串尾加相应数字，index++，同时max.setText(str)实现文本显示
    public void number_0(View view) { str.append("0"); index ++ ; max.setText(str); }
    public void number_1(View view) { str.append("1"); index ++ ; max.setText(str); }
    public void number_2(View view) { str.append("2"); index ++ ; max.setText(str); }
    public void number_3(View view) { str.append("3"); index ++ ; max.setText(str); }
    public void number_4(View view) { str.append("4"); index ++ ; max.setText(str); }
    public void number_5(View view) { str.append("5"); index ++ ; max.setText(str); }
    public void number_6(View view) { str.append("6"); index ++ ; max.setText(str); }
    public void number_7(View view) { str.append("7"); index ++ ; max.setText(str); }
    public void number_8(View view) { str.append("8"); index ++ ; max.setText(str); }
    public void number_9(View view) { str.append("9"); index ++ ; max.setText(str); }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
//初始化定义的变量
        mini = (TextView) findViewById(R.id.minitext);
        max = (TextView) findViewById(R.id.maxtext);
        str = new StringBuilder();
        index = 0;
    }

    //加、减、乘、除、求余的简单运算方法
    public String calc(StringBuilder strB) {
        String allS = strB.toString();
        String[] asmd = {"＋","－","×","÷","%"};
        String result = null;
        for (int i = 0; i < 5; i++) {
// 返回{"＋","－","×","÷","%"}在源字符串中的位置
            int inde = strB.indexOf(asmd[i]);
            if (inde > 0) {
//                a是符号左边数字
                String a = allS.split(asmd[i])[0];
//                b是符号右边数字
                String b = allS.split(asmd[i])[1];
//                asmd[0]是+
                if (i == 0) result = String.valueOf(Double.parseDouble(a) + Double.parseDouble(b));
//                asmd[1]是-
                if (i == 1) result = String.valueOf(Double.parseDouble(a) - Double.parseDouble(b));
//                asmd[2]是*
                if (i == 2) result = String.valueOf(Double.parseDouble(a) * Double.parseDouble(b));
//                asmd[3]是/
                if (i == 3) {
                    if (Double.parseDouble(b) == 0) {
                        Toast.makeText(this,"0不能为除数",Toast.LENGTH_SHORT).show();
                        result = String.valueOf(Double.parseDouble(b));
                    }else {
                        result = String.valueOf(Double.parseDouble(a) / Double.parseDouble(b));
                    }
                }
//                asmd[4]是%
                if (i == 4) result = String.valueOf(Double.parseDouble(a) % Double.parseDouble(b));
            }
        }
        return result;
    }


    //回退按钮事件
    public void backsprce(View view) {
        if (str.length() == 0) return;
        index = str.length();
        str.deleteCharAt(--index);
        max.setText(str);
    }

    //正负号转换
    public void change(View view) {
        String allS = str.toString();
        String[] asmd = {"＋","－","×","÷","%"};
        int inde = 0;
        String a = null;    //保存字符串中运算符部分
        String b = null;    //保存字符串中数字部分
        for (int i = 0; i < 5; i ++) {
            inde = str.indexOf(asmd[i]);
            if (inde != -1) {
                a = asmd[i];
                break;
            }
        }
        //A 字符形 改变 A 正负值
        if (inde == -1) {
            clearc(view);
//            Sting->int
            double c = - Double.parseDouble(allS);
            str.append(String.valueOf(c));
            index = String.valueOf(c).length();
            max.setText(str);
        }
        //A + 符号 + B 字符串形 改变 B 正负值
        if (inde >= 0 && inde < index) {
            clearc(view);
            b = allS.split(a)[0];
            int c = - Integer.parseInt(allS.split(a)[1]);
            str.append(b).append(a).append(String.valueOf(c));
            index = String.valueOf(c).length();
            max.setText(str);
        }
    }
//    小数点
    public void pointer(View view){
        str.append(".");
        index ++ ;
        max.setText(str);
    }
    //清空按钮事件
    public void clearc(View view) {
        StringBuilder temp = new StringBuilder();
        str = temp.append("");
        max.setText(str);
        index = 0;
    }
    public void clearce(View view) {
        StringBuilder temp = new StringBuilder();
        str = temp.append("");
        mini.setText("");
        max.setText("");
        index = 0;
    }

    //等于按钮事件
    public void equal(View view) {
        String[] asmd = {"＋","－","×","÷","%"};
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < 5; i ++) {
            int inde = str.indexOf(asmd[i]);
//            最后输入的位不是以上符号
            if (inde > 0 && inde != index-1) {
                mini.setText(calc(str));
                String a = calc(str);
                str = temp.append(a);
                max.setText(str);
                index = str.length();
                return;
            }else if (inde > 0 && inde == index-1) {
                str.deleteCharAt(--index);
                mini.setText(str);
                max.setText(str);
            }
        }
        mini.setText(str);
        max.setText(str);
    }

    //加
    public void addition(View view) {
        if (str.length() == 0) return;
        String[] asmd = {"＋","－","×","÷","%"};
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int inde = str.indexOf(asmd[i]);
//            最后一位是数字
            if (inde > 0 && str.charAt(index-1) >= '0' && str.charAt(index-1) <= '9') {
                mini.setText(calc(str));
                String a = calc(str);
                str = temp.append(a).append('＋');
                max.setText(str);
                index = str.length();
                return;
            }
        }
        char a = str.charAt(index-1);
        if (a == '＋') { return; }
        if (a == '－' || a == '×' || a == '÷' || a == '%') {
            str.setCharAt(index-1,'＋');
        }else {
            str.append("＋");
            index ++ ;
        }
        max.setText(str);
    }
    //减
    public void subtraction(View view) {
        if (str.length() == 0) return;
        String[] asmd = {"＋","－","×","÷","%"};
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < 5; i ++) {
            int inde = str.indexOf(asmd[i]);
            if (inde > 0 && str.charAt(index-1) >= '0' && str.charAt(index-1) <= '9') {
                mini.setText(calc(str));
                String a = calc(str);
                str = temp.append(a).append('－');
                max.setText(str);
                index = str.length();
                return;
            }
        }
        char a = str.charAt(index-1);
        if (a == '－') { return; }
        if (a == '＋' || a == '×' || a == '÷' || a == '%') {
            str.setCharAt(index-1,'－');
        }else {
            str.append("－");
            index ++ ;
        }
        max.setText(str);
    }
    //乘
    public void multiplication(View view) {
        if (str.length() == 0) return;
        String[] asmd = {"＋","－","×","÷","%"};
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < 5; i ++) {
            int inde = str.indexOf(asmd[i]);
            if (inde > 0 && str.charAt(index-1) >= '0' && str.charAt(index-1) <= '9') {
                mini.setText(calc(str));
                String a = calc(str);
                str = temp.append(a).append('×');
                max.setText(str);
                index = str.length();
                return;
            }
        }
        char a = str.charAt(index-1);
        if (a == '×') { return; }
        if (a == '＋' || a == '－' || a == '÷' || a == '%') {
            str.setCharAt(index-1,'×');
        }else {
            str.append("×");
            index ++ ;
        }
        max.setText(str);
    }
    //除
    public void division(View view) {
        if (str.length() == 0) return;
        String[] asmd = {"＋","－","×","÷","%"};
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < 5; i ++) {
            int inde = str.indexOf(asmd[i]);
            if (inde > 0 && str.charAt(index-1) >= '0' && str.charAt(index-1) <= '9') {
                mini.setText(calc(str));
                String a = calc(str);
                str = temp.append(a).append('÷');
                max.setText(str);
                index = str.length();
                return;
            }
        }
        char a = str.charAt(index-1);
        if (a == '÷') { return; }
        if (a == '＋' || a == '－' || a == '×' || a == '%') {
            str.setCharAt(index-1,'÷');
        }else {
            str.append("÷");
            index ++ ;
        }
        max.setText(str);
    }

    //求余
    public void surplus(View view) {
        if (str.length() == 0) return;
        String[] asmd = {"＋","－","×","÷","%"};
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < 5; i ++) {
            int inde = str.indexOf(asmd[i]);
            if (inde > 0 && str.charAt(index-1) >= '0' && str.charAt(index-1) <= '9') {
                mini.setText(calc(str));
                String a = calc(str);
                str = temp.append(a).append('%');
                max.setText(str);
                index = str.length();
                return;
            }
        }
        char a = str.charAt(index-1);
        if (a == '%') { return; }
        if (a == '＋' || a == '－' || a == '×' || a == '÷') {
            str.setCharAt(index-1,'%');
        }else {
            str.append("%");
            index ++ ;
        }
        max.setText(str);
    }

    //求倒数按钮事件
    public void reciprocal(View view) {
        if (str.length() == 0) return;
        String[] asmd = {"＋","－","×","÷","%"};
        for (int i = 0; i < 5; i ++) {
            int inde = str.indexOf(asmd[i]);
            if (inde > -1) { return; }
        }
        double a = Double.parseDouble(str.toString().trim());
        if (a == 0) {
            Toast.makeText(this,"0不能为除数",Toast.LENGTH_SHORT).show();
            return;
        }
        clearc(view);
        str.append(1/a);
        index = String.valueOf(1/a).length();
        max.setText(str);
    }

    //平方按钮事件
    public void square(View view) {
        if (str.length() == 0) return;
        String[] asmd = {"＋","－","×","÷","%"};
        for (int i = 0; i < 5; i ++) {
            int inde = str.indexOf(asmd[i]);
            if (inde > -1) { return; }
        }
        double a = Double.parseDouble(str.toString().trim());
        clearc(view);
        str.append(a*a);
        index = String.valueOf(a*a).length();
        max.setText(str);
        mini.setText(str);
    }

    //开平方按钮事件
    public void squareroot(View view) {
        if (str.length() == 0) return;
        String[] asmd = {"＋","－","×","÷","%"};
        for (int i = 0; i < 5; i ++) {
            int inde = str.indexOf(asmd[i]);
            if (inde > -1) { return; }
        }
        double a = Double.parseDouble(str.toString().trim());
        clearc(view);
        if(a > 0.0) {
            str.append((double) Math.sqrt(a));
            index = String.valueOf((double) Math.sqrt(a)).length();
            max.setText(str);
            mini.setText(str);
        }else{
            Toast.makeText(this,"负数不能开平方",Toast.LENGTH_SHORT).show();
        }

    }

}


