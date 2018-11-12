package com.by5388.xw.searchframe.call.history.view;

import com.by5388.xw.searchframe.LinkMan;
import com.by5388.xw.searchframe.call.history.CallItem;

import java.util.List;

/**
 * @author by5388  on 2018/11/10.
 */
public interface ILogView {
    /**
     * 更新通话记录列表
     *
     * @param callItems 通话记录
     */
    void updateCallHistory(List<CallItem> callItems);
}
