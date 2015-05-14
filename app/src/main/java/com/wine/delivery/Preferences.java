package com.wine.delivery;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * @author 柏江龙
 * @ClassName Preferences
 * @Description 数据存储
 * @date 2015-4-11
 */
public class Preferences {

    /**
     * 实体
     */
    private static Preferences instance;

    /**
     * android上下文
     */
    private Context context;

    /**
     * 数据存储
     */
    private SharedPreferences share;

    /**
     * 百度推送UserId
     */
    public static final String BAIDU_USERID = "baidu_userId";

    /**
     * 百度推送通道ID
     */
    public static final String BAIDU_CHANNELID = "baidu_channelid";

    /**
     * 构造函数
     *
     * @param context
     */
    private Preferences(Context context) {
        this.context = context;
        share = context.getSharedPreferences("EHouse", Context.MODE_APPEND);
    }

    /**
     * 录入字符串变量
     *
     * @param key
     * @param value
     */
    public void putString(String key, String value) {
        SharedPreferences.Editor editor = share.edit();
        editor.putString(key, value);
        editor.commit();
    }


    /**
     * 获得字符串变量
     *
     * @param key 内容Key
     * @return 内容
     */
    public String getValue(String key) {

        return share.getString(key, "");
    }


    /**
     * 获得存储数据实体
     *
     * @param context
     * @return
     */
    public static Preferences getInstance(Context context) {
        if (instance == null) {
            instance = new Preferences(context);
        }
        return instance;
    }


    /**
     * 录入百度通道号和UserId
     *
     * @param channelId
     * @param userId
     */
    public void putBaiduPush(String channelId, String userId) {
        putString(BAIDU_CHANNELID, channelId);
        putString(BAIDU_USERID, userId);
    }


}
