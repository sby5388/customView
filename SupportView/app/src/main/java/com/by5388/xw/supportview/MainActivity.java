package com.by5388.xw.supportview;

import android.content.Intent;
import android.view.View;

import com.by5388.xw.supportview.download.DownLoadActivity;
import com.by5388.xw.supportview.recycler.view.RecyclerViewActivity;

/**
 * Android-Support View or ViewGroup
 *
 * @author 5388
 * @date 2018/11/13
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        findViewById(R.id.button_to_recycler_view).setOnClickListener(this);
        findViewById(R.id.button_down).setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_to_recycler_view:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case R.id.button_down:
                startActivity(new Intent(this, DownLoadActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    protected boolean showBackHome() {
        return false;
    }
}

