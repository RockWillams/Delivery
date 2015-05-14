package com.wine.delivery.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wine.delivery.R;
import com.wine.delivery.filter.OrderFilterTab;
import com.wine.delivery.model.Fields;


/**
 * @author 柏江龙
 * @ClassName: OrderFilterViewPagerAdapter
 * @Description 阿姨筛选ViewPager
 * @date 2015-4-11
 */
public class OrderFilterViewPagerAdapter extends PagerAdapter {


    /**
     * Android上下文
     */

    public Context mContext;


    /**
     * 构造函数
     */
    public OrderFilterViewPagerAdapter(Context context) {
        this.mContext = context;

    }


    /**
     * 产生View
     *
     * @return
     */
    @Override
    public View instantiateItem(ViewGroup container, final int position) {
        Log.d(Fields.TAG, "产生" + position);
        Context context = container.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = null;
        if (position == OrderFilterTab.FILTER_CURRENT.ordinal()) {
            view = inflater.inflate(R.layout.order_undistribute, null);
        } else {
            view = inflater.inflate(R.layout.order_history, null);
        }

        container.addView(view);
        return view;
    }

    /**
     * 销毁Item
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        Log.d(Fields.TAG, "销毁" + position);
    }


    /**
     * 判断是否为同一个View
     *
     * @param view
     * @param object
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * View的数量
     *
     * @return
     */
    @Override
    public int getCount() {
        return OrderFilterTab.values().length;
    }

}
