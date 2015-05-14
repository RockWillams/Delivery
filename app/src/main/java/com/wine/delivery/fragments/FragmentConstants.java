package com.wine.delivery.fragments;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wine.delivery.R;

import java.util.HashMap;
import java.util.Stack;


/**
 * @ClassName: FragmentConstants
 * @Description 界面实体
 * @author 柏江龙
 * @date 2015-4-11
 */
public class FragmentConstants {

    /**
     * 底部Tab选项卡
     */
    public enum Tab {

        TAB_DRIVING("订单", R.id.tab_order, new OrderFragment(), R.drawable.selector_tab_order), TAB_PRODUCT("消息", R.id.tab_msg, new TestFragment(), R.drawable.selector_tab_order), TAB_GROUP("团购产品", R.id.tab_product, new TestFragment(), R.drawable.selector_tab_order),  TAB_MY("我", R.id.tab_my, new TestFragment(), R.drawable.selector_tab_order);
        /**
         * 以上分别是驾校、产品、团购、我的
         */

        private String name;

        public int id;


        private int tab_button_res;


        public DeliveryFragment fragment;



        Tab(String name, int id, DeliveryFragment _fragment,int tab_button_res) {
            this.name = name;
            this.id = id;
            this.fragment = _fragment;
            this.tab_button_res = tab_button_res;
        }


        public String toString() {
            return name;
        }

        public boolean compare(Tab _tab) {
            if (_tab.toString().equals(name)) {
                return true;
            }
            return false;
        }

        /**
         * 选中某个Tab
         *
         * @param view
         */
        public void select(FragmentActivity view) {
            for (Tab tab : values()) {
                View f = view.findViewById(tab.id);
                LinearLayout tabLayout = ((LinearLayout) view.findViewById(tab.id));
                ImageView button = (ImageView) tabLayout.findViewById(R.id.image);
                button.setImageResource(tab.tab_button_res);
                button.setSelected(false);
                button.refreshDrawableState();

                TextView label = (TextView)tabLayout.findViewById(R.id.label);
                label.setText(tab.name);
            }
            LinearLayout tabLayout = ((LinearLayout) view.findViewById(id));
            ImageView button = ((ImageView) tabLayout.findViewById(R.id.image));
            button.setSelected(true);
            button.setImageResource(tab_button_res);
            button.refreshDrawableState();
        }
    }

    /**
     * 选项卡界面切换回调
     */
    public interface FragmentChangeListener {

        public void changeFragment(Tab tab, DeliveryFragment fragment);
    }

    private static FragmentConstants constants;

    //应用中用到的Fragment堆栈
    private HashMap<String, Stack<DeliveryFragment>> mStacks;

    private FragmentActivity mParent;

    private FragmentManager fm;

    private static final String TAG = "FragmentConstants";

    //当前正在使用的选项卡
    private Tab mCurrentTab;

    private FragmentChangeListener mChangeListener;

    private FragmentConstants() {

    }

    public Context getFragmentContext() {
        return mParent;
    }

    public FragmentManager getFragmentManager() {
        return fm;
    }

    /**
     * 初始化Fragment切换窗口
     *
     * @param parent
     */
    public void initFragmentActivity(FragmentActivity parent) {
        this.mParent = parent;
        mStacks = new HashMap<String, Stack<DeliveryFragment>>();
        this.fm = mParent.getSupportFragmentManager();
        initStack();
    }

    /**
     * 获得Fragment主要窗口对象，此对象为一个单例类
     *
     * @return
     */
    public static synchronized FragmentConstants getInstances() {
        if (constants == null) {
            constants = new FragmentConstants();
        }
        return constants;
    }

    /**
     * 设置Fragment切换回调事件
     *
     * @param listener
     */
    public void setOnFragmentChangeListener(FragmentChangeListener listener) {
        this.mChangeListener = listener;
    }

    /**
     * 从堆栈中提出此Tab的堆列表
     *
     * @param tab
     * @return
     */
    public Stack<DeliveryFragment> peekFragmentStack(Tab tab) {
        if (mStacks.containsKey(tab.toString())) {
            return mStacks.get(tab.toString());
        }
        Stack<DeliveryFragment> stack = new Stack<DeliveryFragment>();
        mStacks.put(tab.toString(), stack);
        return stack;
    }

    /**
     * 从当前Tab中提出当前展示的Fragment
     */
    public DeliveryFragment popCurrentFragment() {

        Stack<DeliveryFragment> stack = peekFragmentStack(mCurrentTab);
        if(stack.size() == 0){
            return null;
        }
        return stack.get(stack.size() - 1);
    }

    /**
     * 像对应的Tab中加入一个新的界面（Fragment）
     *
     * @param tab
     * @param fragment
     */
    private void putFragmentToStack(Tab tab, DeliveryFragment fragment) {
        if (mStacks.containsKey(tab.toString())) {
            mStacks.get(tab.toString()).add(fragment);
            return;
        }
        Stack<DeliveryFragment> stack = new Stack<DeliveryFragment>();
        stack.add(fragment);
        mStacks.put(tab.toString(), stack);
    }

    /**
     * 默认初始化首页的Fragment
     */
    private void initStack() {
//        for (Tab tab : Tab.values()) {
//            addTabFragment(tab, tab.fragment);
//        }
        addTabFragment(Tab.TAB_DRIVING, Tab.TAB_DRIVING.fragment);
    }

    /**
     * 从堆栈中提出一个Fragment
     *
     * @return
     */
    public boolean popBackFragment() {
        Stack<DeliveryFragment> stack = peekFragmentStack(mCurrentTab);

        if (stack == null || stack.isEmpty()){
            return false;
        }
        DeliveryFragment destory = stack.pop();
        if (stack.size() == 0) {
            return false;
        }
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(destory);
        ft.show(stack.lastElement());
        ft.commit();
        if (mChangeListener != null) {
            mChangeListener.changeFragment(mCurrentTab, stack.lastElement());
        }
        return true;
    }

    /**
     * 像对应的Tab中加入一个新的界面（Fragment）
     *
     * @param tab
     * @param fragment
     */
    public void addTabFragment(Tab tab, DeliveryFragment fragment) {
        putFragmentToStack(tab, fragment);
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container_fragment, fragment, tab.name);
        ft.hide(fragment);
        ft.commit();

    }

    /**
     * 像对应的Tab中加入一个新的界面（Fragment）
     *
     * @param fragment
     */
    public void pushFragment(DeliveryFragment fragment) {
        putFragmentToStack(mCurrentTab, fragment);
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container_fragment, fragment);

        if (mCurrentTab != null) {
            DeliveryFragment currentFragment = peekFragmentStack(mCurrentTab).lastElement();
            ft.hide(currentFragment);
        }

        ft.show(fragment);

        ft.commit();
        if (mChangeListener != null) {
            mChangeListener.changeFragment(mCurrentTab, fragment);
        }
    }

    /**
     * 获得当前选项卡
     *
     * @return
     */
    public String getCurrentTab() {
        return mCurrentTab.name;
    }

    /**
     * 切换选项卡
     *
     * @param tab
     */
    public void changeTab(Tab tab) {
        if (mCurrentTab != null && tab.compare(mCurrentTab)) {
            return;
        }

        FragmentTransaction transaction = fm.beginTransaction();
        if (mCurrentTab != null) {
            transaction.hide(peekFragmentStack(mCurrentTab).lastElement());
        }
        transaction.commit();

        this.mCurrentTab = tab;
        this.mCurrentTab.select(mParent);

        if (peekFragmentStack(tab).size() == 0) {
            pushFragment(tab.fragment);
        } else {
            FragmentTransaction showTrans = fm.beginTransaction();

            DeliveryFragment currentFragment = peekFragmentStack(tab).lastElement();
            showTrans.show(currentFragment);

            showTrans.commit();
            if (mChangeListener != null) {
                mChangeListener.changeFragment(tab, currentFragment);
            }
        }

    }


    /**
     * 重置
     */
    public void reset() {
        mCurrentTab = null;
    }
}
