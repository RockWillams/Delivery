package com.wine.delivery.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wine.delivery.R;
import com.wine.delivery.push.Utils;


/**
 * @author 柏江龙
 * @ClassName: HeaderActivity
 * @Description 标题Activity
 * @date 2015-4-11
 */
public class HeaderActivity extends Activity implements View.OnClickListener {


    public enum HeaderStyle {
        BOTH, LEFT, NONE;
    }

    /**
     * 右边按钮
     */
    public ImageButton right;

    /**
     * 左边按钮
     */
    public ImageButton left;


    private ProgressDialog dialog;


    /**
     * 启动弹出
     *
     * @param message
     */
    protected void show(String message) {
        dialog = new ProgressDialog(this);
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
     * 初始化HeaderView
     */
    protected void initHeaderView() {
        left = (ImageButton) findViewById(R.id.back);
        left.setOnClickListener(this);
        right = (ImageButton) findViewById(R.id.right);
        right.setOnClickListener(this);
    }

    /**
     * 启动时调用
     */
    @Override
    protected void onStart() {
        super.onStart();
        initHeaderView();
    }

    /**
     * 初始化标题样式
     *
     * @param style
     * @param title
     */
    public void initHeaderStyle(HeaderStyle style, String title) {
        updateHeaderStyle(style);
        View view = findViewById(R.id.title);
        if (view instanceof TextView) {
            ((TextView) view).setText(title);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Utils.setLogText(getApplicationContext(), Utils.logStringCache);
    }

    /**
     * 初始化标题样式
     *
     * @param style
     * @param title
     */
    public void initHeaderStyle(HeaderStyle style, int title) {
        updateHeaderStyle(style);
        View view = findViewById(R.id.title);
        if (view instanceof TextView) {
            ((TextView) view).setText(title);
        }
    }


    /**
     * 初始化标题样式
     */
    private void updateHeaderStyle(HeaderStyle style) {
        if (style == HeaderStyle.LEFT) {
            right.setVisibility(View.INVISIBLE);
            left.setVisibility(View.VISIBLE);
        } else if (style == HeaderStyle.BOTH) {
            right.setVisibility(View.VISIBLE);
            left.setVisibility(View.VISIBLE);
        } else {
            right.setVisibility(View.INVISIBLE);
            left.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 初始化标题样式
     *
     * @param style
     * @param title
     */
    public void initHeaderStyleOne(HeaderStyle style, int title) {
        updateHeaderStyle(style);
        View view = findViewById(R.id.title);
        if (view instanceof TextView) {
            ((TextView) view).setText(title);
        }
    }

    /**
     * 当Back或则右边按钮点击时调用
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.back:
                finish();
                break;
            case R.id.right:
                onRightClick(v);
                break;
            default:
                performClick(v);
        }
    }

    /**
     * 右边点击按钮
     *
     * @param view
     */
    public void performClick(View view) {

    }


    /**
     * 左边点击按钮
     *
     * @param view
     */
    protected void onRightClick(View view) {

    }
}
