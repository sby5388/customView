package com.by5388.xw.supportview;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * @author by5388  on 2018/11/13.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    final protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && showBackHome()) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        setContentView(getLayoutResID());
        initData();
        initView();
        loadData();
    }

    /**
     * 是否在ActionBar 显示返回按钮
     *
     * @return true: 显示
     */
    protected abstract boolean showBackHome();

    /**
     * 子页面布局
     *
     * @return 布局文件
     */
    protected abstract @LayoutRes
    int getLayoutResID();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化页面
     */
    protected abstract void initView();

    /**
     * 数据与页面之间的绑定，如listView.setAdapter(adapter)
     */
    protected abstract void loadData();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
