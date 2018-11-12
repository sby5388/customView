package com.by5388.xw.searchframe.call.history;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.by5388.xw.searchframe.R;
import com.by5388.xw.searchframe.call.history.presenter.ILogPresenter;
import com.by5388.xw.searchframe.call.history.presenter.LogPresenter;
import com.by5388.xw.searchframe.call.history.view.ILogView;

import java.util.List;

/**
 * 通话记录
 * Fixme  读取通话记录的权限
 *
 * @author by5388
 * @date 2018-11-10
 */
public class CallHistory extends AppCompatActivity implements ILogView {
    private ILogPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_history);
        initData();
        initView();
        loadData();
    }

    private void initData() {
        presenter = new LogPresenter(this);
    }

    private void initView() {

    }

    private void loadData() {
        presenter.queryCallHistory();
    }

    @Override
    public void updateCallHistory(List<CallItem> callItems) {
        // TODO: 2018/11/10 listView + BaseAdapter 显示结果
    }
}
