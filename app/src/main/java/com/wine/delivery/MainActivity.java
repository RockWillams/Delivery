package com.wine.delivery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageButton;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.wine.delivery.fragments.DeliveryFragment;
import com.wine.delivery.fragments.FragmentConstants;
import com.wine.delivery.push.Utils;


/**
 * @author 柏江龙
 * @ClassName: MainActivity
 * @Description 家政用户端主界面
 * @date 2015-4-11
 */
public class MainActivity extends FragmentActivity implements FragmentConstants.FragmentChangeListener, View.OnClickListener {
    /**
     * 右边按钮
     */
    private ImageButton right;


    /**
     * 左边按钮
     */
    private ImageButton left;


    /**
     * 创建界面时调用
     *
     * @param savedInstanceState 保存的状态
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ehouse_fragment_layout);
        findViewById(R.id.back).setOnClickListener(this);
        right = (ImageButton) findViewById(R.id.right);
        right.setOnClickListener(this);
        left = (ImageButton) findViewById(R.id.back);
        left.setOnClickListener(this);
        left.setVisibility(View.INVISIBLE);

        PushManager.startWork(getApplicationContext(),
                PushConstants.LOGIN_TYPE_API_KEY,
                Utils.getMetaValue(this, "api_key"));


        FragmentConstants.getInstances().initFragmentActivity(this);
        FragmentConstants.getInstances().setOnFragmentChangeListener(this);
        FragmentConstants.getInstances().changeTab(FragmentConstants.Tab.TAB_DRIVING);

        findViewById(R.id.tab_order).setOnClickListener(this);
        findViewById(R.id.tab_msg).setOnClickListener(this);
        findViewById(R.id.tab_product).setOnClickListener(this);
        findViewById(R.id.tab_my).setOnClickListener(this);

    }



    /**
     * 当Fragment切换时调用
     *
     * @param tab      当前的Tab
     * @param fragment 当前展示的Fragment
     */
    @Override
    public void changeFragment(FragmentConstants.Tab tab, DeliveryFragment fragment) {

        if (fragment.isRight()) {
            right.setImageResource(fragment.getRightResource());
            right.setVisibility(View.VISIBLE);
        } else {
            right.setVisibility(View.INVISIBLE);
        }

        if (fragment.isLeft()) {
            left.setVisibility(View.VISIBLE);
        } else {
            left.setVisibility(View.INVISIBLE);
        }

//        mTitle.setText(fragment.getTitle());
    }

    /**
     * 当用户点击底部Tab时调用
     *
     * @param view 点击的View
     */
    public void onFragmentTab(View view) {
        FragmentConstants.Tab _tab = null;
        int id = view.getId();
        for (FragmentConstants.Tab tab : FragmentConstants.Tab.values()) {
            if (tab.id == id) {
                _tab = tab;
            }
        }

            FragmentConstants.getInstances().changeTab(_tab);

    }


    /**
     * 当主界面销毁时调用
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        FragmentConstants.getInstances().reset();
    }

    /**
     * 当点击back键时调用
     */
    @Override
    public void onBackPressed() {
        if (!FragmentConstants.getInstances().popBackFragment()) {
            super.onBackPressed();
        }
    }

    /**
     * 输入地址和时间之后跳转阿姨地图
     *
     * @param requestCode 访问code
     * @param resultCode  返回code
     * @param data        返回数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

    }


    /**
     * 当Back或则右边按钮点击时调用
     *
     * @param v 点击View
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.back:
                FragmentConstants.getInstances().popBackFragment();
                break;
            case R.id.right:
                DeliveryFragment fragment = FragmentConstants.getInstances().popCurrentFragment();
                if (fragment != null){
                 fragment.onRightClick(v);
                }

                break;
            default:
                onFragmentTab(v);
        }
    }


}
