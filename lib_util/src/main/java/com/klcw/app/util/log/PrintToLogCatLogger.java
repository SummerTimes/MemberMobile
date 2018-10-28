package com.klcw.app.util.log;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;


public class PrintToLogCatLogger implements ILogger {
    @Override
    public void d(String tag, String message) {
        Log.d(tag, message);
    }

    @Override
    public void e(String tag, String message) {
        Log.e(tag, message);
    }

    @Override
    public void i(String tag, String message) {
        if(TextUtils.isEmpty(message)){
            message = "null";
        }
        if (message.length() > 4000) {
            for (int i = 0; i < message.length(); i += 4000) {
                if (i + 4000 < message.length())
                    Log.i(tag, message.substring(i, i + 4000));
                else
                    Log.i(tag, message.substring(i, message.length()));
            }
        } else {
            Log.i(tag, message);
        }
    }


    public static void getStrList(String tag, String inputString, int length) {
        int size = inputString.length() / length;
        if (inputString.length() % length != 0) {
            size += 1;
        }
        getStrList(tag, inputString, length, size);
    }

    /**
     * 把原始字符串分割成指定长度的字符串列表
     *
     * @param inputString 原始字符串
     * @param length      指定长度
     * @param size        指定列表大小
     * @return
     */
    public static void getStrList(String tag, String inputString, int length,
                                  int size) {
        for (int index = 0; index < size; index++) {
            String childStr = substring(inputString, index * length,
                    (index + 1) * length);
            Log.i(tag, childStr);
        }
    }

    /**
     * 分割字符串，如果开始位置大于字符串长度，返回空
     *
     * @param str 原始字符串
     * @param f   开始位置
     * @param t   结束位置
     * @return
     */
    public static String substring(String str, int f, int t) {
        if (f > str.length())
            return null;
        if (t > str.length()) {
            return str.substring(f, str.length());
        } else {
            return str.substring(f, t);
        }
    }


    @Override
    public void v(String tag, String message) {
        Log.v(tag, message);
    }

    @Override
    public void w(String tag, String message) {
        Log.w(tag, message);
    }

    @Override
    public void println(int priority, String tag, String message) {
        Log.println(priority, tag, message);
    }

    @Override
    public void open() {

    }

    @Override
    public void close() {

    }

    @Override
    public File getFreshFile() {
        return null;
    }
}
