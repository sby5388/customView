package com.by5388.xw.searchframe.call.history;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.by5388.xw.searchframe.R;
import com.by5388.xw.searchframe.call.history.presenter.ILogPresenter;
import com.by5388.xw.searchframe.call.history.presenter.LogPresenter;
import com.by5388.xw.searchframe.call.history.view.ILogView;

import java.util.ArrayList;
import java.util.List;

/**
 * 通话记录
 * Fixme  读取通话记录的权限
 *
 * @author by5388
 * @date 2018-11-10
 */
public class CallHistoryActivity extends AppCompatActivity implements ILogView {
    private ILogPresenter presenter;
    private CallItemAdapter adapter;
    private ListView listView;

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
        adapter = new CallItemAdapter(this, new ArrayList<CallItem>());

    }

    private void initView() {
        listView = findViewById(R.id.listView_call_history);
    }

    private void loadData() {
        listView.setAdapter(adapter);
        presenter.queryCallHistory();
    }

    @Override
    public void updateCallHistory(List<CallItem> callItems) {
        if (null == callItems || callItems.isEmpty()) {
            return;
        }
        adapter.setCallItems(callItems);
    }
}
