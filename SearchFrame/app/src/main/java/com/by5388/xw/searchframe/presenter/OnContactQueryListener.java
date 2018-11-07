package com.by5388.xw.searchframe.presenter;

import com.by5388.xw.searchframe.LinkMan;

import java.util.List;
import java.util.Map;

/**
 * @author by5388  on 2018/11/7.
 */
public interface OnContactQueryListener {
    /**
     * 查询完成回调
     *
     * @param list 查询完成
     */
    void onComplete(List<Map<String, Object>> list);

    /**
     * 查询完成回调
     *
     * @param list 查询完成
     */
    void onCompleted(List<LinkMan> list);
}
