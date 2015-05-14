package com.wine.delivery.widgets;

import android.app.ProgressDialog;
import android.content.Context;


/**
 * @ClassName ProgressTips
 * @Description 进度提示
 * @author 柏江龙
 * @date 2015-4-11
 */
public class ProgressTips {

    private static ProgressTips instance;

    private ProgressDialog dialog;

    private Context mContext;

    private boolean isShow;

    public ProgressTips(Context context) {
        this.mContext = context;
    }

    public static ProgressTips getInstance(Context context) {
        if (instance == null) {
            instance = new ProgressTips(context);
        }
        instance.mContext = context;
        return instance;
    }

    public void show(String message){
        if (dialog == null){
            dialog = getDialog();
        }

        if (dialog.isShowing() || isShow){
            dismiss();
            dialog = getDialog();
        }

        dialog.setMessage(message);
        dialog.show();
        isShow = true;
    }

    private ProgressDialog getDialog(){
        ProgressDialog dialog = new ProgressDialog(mContext);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    public void dismiss(){
        if (dialog != null){
            dialog.dismiss();
            dialog = null;
        }

        isShow = false;
    }

    public void destory(){
        instance = null;
    }

}
