package com.by5388.xw.searchframe.search.contact.view;


import com.by5388.xw.searchframe.LinkMan;

import java.util.List;
import java.util.Map;

/**
 * @author by5388  on 2018/11/7.
 */
public interface ISearchView {
    /**
     * 更新listView的设配器
     *
     * @param list 数据源
     */
    void notifyListAdapter(List<Map<String, Object>> list);

    /**
     * 更新listView的设配器
     *
     * @param list 数据源
     */
    void notifyListAdapter2(List<LinkMan> list);

    /**
     * 讲道理 不能这么用的
     *
     * @param queryStr 关键字
     */
    void queryContact(String queryStr);
}
