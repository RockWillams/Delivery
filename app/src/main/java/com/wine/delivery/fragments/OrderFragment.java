package com.wine.delivery.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wine.delivery.R;
import com.wine.delivery.adapter.OrderFilterViewPagerAdapter;
import com.wine.delivery.filter.OrderFilterTab;

/**
 * Created by Administrator on 2015/5/14 0014.
 */
public class OrderFragment extends DeliveryFragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    @Override
    public String getTitle() {
        return "����";
    }

    /**
     * �л�չʾҳ��
     */
    private ViewPager pager;

    /**
     * ���̹���������
     */
    private OrderFilterViewPagerAdapter adapter;

    private LinearLayout filterLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_fragment, null);

        initView(view);
        return view;
    }

    private void initView(View view) {
        filterLayout = (LinearLayout) view.findViewById(R.id.filters);
        filterLayout.findViewById(R.id.filter_current).setOnClickListener(this);
        filterLayout.findViewById(R.id.filter_history).setOnClickListener(this);

        pager = (ViewPager) view.findViewById(R.id.pager);
        adapter = new OrderFilterViewPagerAdapter(getActivity());
        pager.setOnPageChangeListener(this);

        pager.setAdapter(adapter);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);


        OrderFilterTab.FILTER_CURRENT.select(filterLayout);
    }

    /**
     * ����¼��ص�
     */
    @Override
    public void onClick(View v) {

        int id = v.getId();
        OrderFilterTab _tab = OrderFilterTab.FILTER_CURRENT;
        for (OrderFilterTab tab : OrderFilterTab.values()) {
            if (tab.id == id) {
                _tab = tab;
            }
        }
        selectFilter(_tab);
    }

    /**
     * �л�ѡ�
     */
    private void selectFilter(OrderFilterTab _tab) {
        _tab.select(filterLayout);
        pager.setCurrentItem(_tab.ordinal());
    }

    /**
     * Pager����ʱ�ص�
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    /**
     * Pageѡ��ʱ�ص�
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        OrderFilterTab.values()[position].select(filterLayout);
    }

    /**
     * Page����״̬�ı�ʱ�ص�
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
