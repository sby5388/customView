package com.by5388.xw.searchframe.call.history.model;

import com.by5388.xw.searchframe.LinkMan;
import com.by5388.xw.searchframe.call.history.CallItem;

import java.util.List;

/**
 * @author by5388  on 2018/11/10.
 */
public interface ILogModel {
    /**
     * 获取通话记录
     *
     * @param callBack 回调接口
     *                 其实结果也可以用返回值来传递，但是因为可能涉及到耗时操作，会影响
     *                 使用RxJava会更方便，省去了各种回调的操作
     */
    void getCallLog(ILogModelCallBack callBack);

    /**
     * 结果回调接口
     */
    interface ILogModelCallBack {
        /**
         * 把数据传回给Presenter
         *
         * @param callItems 结果
         */
        void queryCompleted(List<CallItem> callItems);
    }
}
