package com.by5388.xw.supportview.recycler.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.by5388.xw.supportview.BaseActivity;
import com.by5388.xw.supportview.R;
import com.by5388.xw.supportview.dialog.PictureDialog;
import com.by5388.xw.supportview.dialog.ShowPicture;
import com.by5388.xw.supportview.recycler.view.adapter.PictureAdapter;
import com.by5388.xw.supportview.recycler.view.adapter.PictureItemOnClickListener;
import com.by5388.xw.supportview.recycler.view.bean.PictureBean;
import com.by5388.xw.supportview.recycler.view.presenter.IPicturePresenter;
import com.by5388.xw.supportview.recycler.view.presenter.PicturePresenter;
import com.by5388.xw.supportview.recycler.view.view.IPictureView;
import com.by5388.xw.supportview.download.DownLoadTask;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView 显示相应的ListView、GridView
 * 还要横向滑动，瀑布流
 *
 * @author by5388  on 2018/11/13.
 */
public class RecyclerViewActivity extends BaseActivity
        implements IPictureView,
        View.OnClickListener,
        PictureItemOnClickListener,
        ShowPicture {
    private EditText editTextNumber, editTextPage;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private PictureAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private IPicturePresenter presenter;
    private int page = 1;
    private int number = 10;
    private static final int ACTION_NULL = 0;
    private static final int ACTION_NEXT = 1;
    private static final int ACTION_LAST = 2;
    private PictureDialog dialog;
    private int position = 0;
    private DownLoadTask task;
    private static final int REQUEST_CODE = 20;
    String url;

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_recycler_view;
    }

    @Override
    protected void initData() {
        presenter = new PicturePresenter(this);
        adapter = new PictureAdapter(this, new ArrayList<PictureBean>(), this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        layoutManager = linearLayoutManager;
        task = DownLoadTask.getInstance();
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        editTextNumber = findViewById(R.id.editText_number);
        editTextPage = findViewById(R.id.editText_page);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        findViewById(R.id.button_show_gridView).setOnClickListener(this);
        findViewById(R.id.button_show_listView).setOnClickListener(this);
        // TODO: 2018/11/14 使用缓存技术来缓存相关的图片
        findViewById(R.id.button_jump).setOnClickListener(this);
        findViewById(R.id.button_next_page).setOnClickListener(this);
        findViewById(R.id.button_last_page).setOnClickListener(this);
    }

    @Override
    protected void loadData() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        query(ACTION_NULL);
    }

    private void query(int action) {
        if (action == ACTION_NULL) {
            try {
                number = Integer.parseInt(editTextNumber.getText().toString().trim());
                page = Integer.parseInt(editTextPage.getText().toString().trim());
            } catch (NumberFormatException e) {
                Toast.makeText(this, R.string.error_input, Toast.LENGTH_SHORT).show();
                return;
            }
        } else if (action == ACTION_NEXT || action == ACTION_LAST) {
            if (action == ACTION_NEXT && page < 15) {
                page++;
            } else if (action == ACTION_LAST && page > 1) {
                page--;
            }
            editTextPage.setText(String.valueOf(page));
        }
        presenter.queryPicture(number, page);
    }


    @Override
    public void updatePicture(@NonNull List<PictureBean> beans) {
        adapter.setBeans(beans);
    }

    @Override
    public void startQuery() {
        //增加下拉查询的进度条
        swipeRefreshLayout.setRefreshing(true);

    }

    @Override
    public void completeQuery() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showTip(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_show_listView:
                setLayoutManager(linearLayoutManager);
                break;
            case R.id.button_show_gridView:
                setLayoutManager(gridLayoutManager);
                break;
            case R.id.button_jump:
                query(ACTION_NULL);
                break;
            case R.id.button_next_page:
                query(ACTION_NEXT);
                break;
            case R.id.button_last_page:
                query(ACTION_LAST);
                break;
            default:
                break;
        }
    }

    private void setLayoutManager(LinearLayoutManager layoutManager) {
        if (this.layoutManager == layoutManager) {
            return;
        }
        this.layoutManager = layoutManager;
        recyclerView.setLayoutManager(this.layoutManager);
    }

    @Override
    protected boolean showBackHome() {
        return true;
    }

    @Override
    public void operateBitmap(String url) {
        // TODO: 2018/11/14 保存图片、下载图片 需要编写一个下载工具，并且在通知栏更新下载进度。
        //  -->浏览器的下载功能类似，下载完成时会有新的通知
        // TODO: 2018/11/15  暂时做个图片放大的Dialog
//        if (dialog == null) {
//            dialog = new PictureDialog(this, this, adapter.getItem(0).getUrl());
//        }
//        dialog.setUrl(adapter.getItem(position).getUrl()).show();
        //startActivity(PictureActivity.toPictureActivity(this, url));
        this.url = url;
        checkFilePermission();


    }

    @Override
    public String getNext() {
        position++;
        if (position >= adapter.getItemCount()) {
            position = 0;
        }
        return adapter.getItem(position).getUrl();
    }

    @Override
    public String getLast() {
        position--;
        if (position < 0) {
            position = adapter.getItemCount() - 1;
        }
        return adapter.getItem(position).getUrl();
    }


    private void checkFilePermission() {
        if (lackPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            return;
        }
        if (lackPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            return;
        }
        task.addTask(url).startTask();
    }

    /**
     * 缺乏权限
     *
     * @param permission 权限
     * @return true  缺乏权限
     */
    private boolean lackPermission(String permission) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), permission);
            intent.setData(uri);
            startActivity(intent);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, REQUEST_CODE);
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //授权成功-->跳转页面
                    checkFilePermission();
                } else {
                    // 授权失败！
                    Toast.makeText(this, "授权失败！", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
