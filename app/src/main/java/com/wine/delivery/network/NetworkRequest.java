package com.wine.delivery.network;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.wine.delivery.model.Fields;
import com.wine.delivery.widgets.ProgressTips;

import org.apache.http.Header;


/**
 * @author 柏江龙
 * @ClassName NetworkRequest
 * @Description 网络访问封装
 * @date 2015-4-11
 */
public class NetworkRequest {


    /**
     * 网络访问返回回调
     */
    public interface OnNetworkResponseHandler {

        /**
         * 访问之后返回回调方法
         *
         * @param responseBody
         */
        public void onResponse(String responseBody);
    }


    /**
     * 访问网络通过GET方式请求
     *
     * @param url
     */
    public static void requestByGet(final Context context,String message, String url, final OnNetworkResponseHandler handler) {
        Log.d(Fields.TAG, url);
        if (!Tools.checkNetwork(context)) {
            return;
        }
        if (!TextUtils.isEmpty(message)) {
            ProgressTips.getInstance(context).show(message);
        }

        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get(url, new AsyncHttpResponseHandler() {

            /**
             * 访问成功之后返回回调方法
             */
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (handler != null) {
                    Log.d(Fields.TAG, "receive " + responseBody);

                    String response = new String(responseBody);

                    String state = Interfaces.getJson(response, "state");
                    String message = Interfaces.getJson(response, "message");
                    if (TextUtils.equals(state, "1")) {
                        handler.onResponse(Interfaces.getJson(response, "data"));
                        ProgressTips.getInstance(context).dismiss();
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    } else if(TextUtils.equals(state, "3")) {
                        handler.onResponse(null);
                        ProgressTips.getInstance(context).dismiss();
                    } else {
                        Tools.handleRequestState(context, message);
                    }
                }
            }

            /**
             * 访问失败之后返回回调方法
             */
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Tools.handleRequestState(context, "服务器异常，请联系管理员");
            }
        });
    }



}
