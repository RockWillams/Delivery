package com.wine.delivery.network;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.wine.delivery.R;
import com.wine.delivery.widgets.ProgressTips;

/**
 * @author 柏江龙
 * @ClassName Tools
 * @Description 系统工具类
 * @date 2015-4-11
 */
public class Tools {

    /**
     * 启动市场评论
     *
     * @param context     上下文
     * @param packageName 包名
     */
    public static void launchCommentApp(Context context, String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, "分享App"));
    }

    /**
     * 加载图片给ImageView
     *
     * @param url                  图片地址
     * @param imageView            图片组件
     * @param animateFirstListener 监听器
     */
    public static void loadImageResource(String url, ImageView imageView, SimpleImageLoadingListener animateFirstListener) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true).build();
        ImageLoader.getInstance().displayImage(url, imageView, options, animateFirstListener);
    }


    /**
     * 检查网络连接状态
     *
     * @return
     */
    public static boolean checkNetwork(Context context) {
        ConnectivityManager con = (ConnectivityManager) context.getSystemService(Activity.CONNECTIVITY_SERVICE);
        boolean wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
        boolean internet = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnectedOrConnecting();
        if (wifi | internet) {
            return true;
        } else {
            Toast.makeText(context,
                    "亲，网络连了么？", Toast.LENGTH_LONG)
                    .show();
        }
        return false;
    }

    public static void handleRequestState(Context context, String message) {
        ProgressTips.getInstance(context).dismiss();
        Toast.makeText(context, message,Toast.LENGTH_LONG).show();
    }



}
