package com.by5388.xw.searchframe.presenter;

import com.by5388.xw.searchframe.view.ISearchView;

/**
 * @author by5388  on 2018/11/7.
 */
public interface ISearchPresenter {
    /**
     * 关联 绑定
     *
     * @param view view
     */
    void onAttach(ISearchView view);

    /**
     * 解除关联
     */
    void onDetach();

    /**
     * 开始查询
     *
     * @param string 关键字
     */
    void fetchQuery(String string);
}
