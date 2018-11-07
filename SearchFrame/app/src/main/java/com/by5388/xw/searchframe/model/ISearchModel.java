package com.by5388.xw.searchframe.model;

import com.by5388.xw.searchframe.presenter.OnContactQueryListener;

/**
 * @author by5388  on 2018/11/7.
 */
public interface ISearchModel {
    /**
     * 查询完成
     *
     * @param search   参数1
     * @param listener 回调函数
     */
    void queryContact(String search, OnContactQueryListener listener);
}
