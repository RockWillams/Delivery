package com.wine.delivery.filter;

import android.view.ViewGroup;
import android.widget.TextView;

import com.wine.delivery.R;


/**
 * @ClassName OrderFilterTab
 * @Description 阿姨筛选实体
 * @author 柏江龙
 * @date 2015-4-11
 */
public enum OrderFilterTab {


    FILTER_CURRENT(R.id.filter_current), FILTER_HISTORY(R.id.filter_history);


    public int id;


    OrderFilterTab(int id) {
        this.id = id;
    }


    /**
     * 选中某个Tab
     *
     * @param view
     */
    public void select(ViewGroup view) {
        for (OrderFilterTab tab : values()) {
            TextView button = ((TextView) view.findViewById(tab.id));
            button.setSelected(false);
            button.refreshDrawableState();
        }
        TextView button = ((TextView) view.findViewById(id));
        button.setSelected(true);
        button.refreshDrawableState();
    }
}
