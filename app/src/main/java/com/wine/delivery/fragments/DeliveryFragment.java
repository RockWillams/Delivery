package com.wine.delivery.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.wine.delivery.R;


/**
 * @author 柏江龙
 * @ClassName: EDriveFragment
 * @Description 所有界面的基类
 * @date 2015-4-11
 */
public abstract class DeliveryFragment extends Fragment {


    /**
     * 创建当前界面
     *
     * @param savedInstanceState 保存的状态
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    private ProgressDialog dialog;

    /**
     * 启动弹出
     *
     * @param message
     */
    protected void show(String message) {
        dialog = new ProgressDialog(getActivity());
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        dialog.setMessage(message);
        dialog.show();
    }

    /**
     * 隐藏弹出
     */
    protected void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    /**
     * 获得当前界面的标题
     *
     * @return
     */
    public abstract String getTitle();

    /**
     * 是否改变了右边按钮资源
     *
     * @return
     */
    public boolean isRight() {
        return false;
    }

    /**
     * 是否改变了左边边按钮资源
     *
     * @return
     */
    public boolean isLeft() {
        return true;
    }

    /**
     * 获得右边按钮资源
     *
     * @return
     */
    public int getRightResource() {
        return R.mipmap.ic_launcher;
    }

    /**
     * 右边按钮点击时调用
     *
     * @param view
     */
    public void onRightClick(View view) {
    }





}
