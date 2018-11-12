package com.by5388.xw.searchframe.call.history.presenter;

import com.by5388.xw.searchframe.call.history.CallItem;
import com.by5388.xw.searchframe.call.history.model.ILogModel;
import com.by5388.xw.searchframe.call.history.model.LogModel;
import com.by5388.xw.searchframe.call.history.view.ILogView;

import java.util.List;

/**
 * @author by5388  on 2018/11/10.
 */
public class LogPresenter implements ILogPresenter, ILogModel.ILogModelCallBack {
    private ILogView view;
    private ILogModel model;

    public LogPresenter(ILogView view) {
        this.view = view;
        this.model = new LogModel();
    }

    @Override
    public void queryCallHistory() {
        model.getCallLog(this);
    }

    @Override
    public void queryCompleted(List<CallItem> callItems) {
        if (callItems != null) {
            view.updateCallHistory(callItems);
        }
    }
}
