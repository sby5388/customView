package com.by5388.xw.searchframe.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.by5388.xw.searchframe.LinkMan;
import com.by5388.xw.searchframe.R;
import com.by5388.xw.searchframe.adapter.LinkManAdapter;
import com.by5388.xw.searchframe.presenter.ISearchPresenter;
import com.by5388.xw.searchframe.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 自定义的联系人查找显示页面
 *
 * @author by5388  on 2018/11/7.
 */
public class SearchLinkManFragment extends Fragment implements ISearchView {
    private static final String TAG = "SearchLinkMan";
    private ISearchPresenter presenter;
    private OnListItemClickListener mClickListener;
    private LinkManAdapter adapter;
    private ListView listView;
    /**
     * 没有配置时的回调事件
     */
    private OnListItemClickListener defaultListener = new OnListItemClickListener() {
        @Override
        public void onListItemClick(String number) {
            if (TextUtils.isEmpty(number)) {
                Toast.makeText(getContext(), R.string.empty_number, Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:" + number);
            intent.setData(uri);
            startActivity(intent);
        }
    };


    public static SearchLinkManFragment newInstance() {
        SearchLinkManFragment fragment = new SearchLinkManFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        presenter = new SearchPresenter(this);
        adapter = new LinkManAdapter(getContext(), new ArrayList<LinkMan>());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_link_man_fragment, container, false);
        initView(view);
        loadData();
        return view;
    }

    /**
     * 加载数据
     */
    private void loadData() {
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LinkMan man = adapter.getItem(position);
                if (man == null) {
                    return;
                }
                if (mClickListener != null) {
                    mClickListener.onListItemClick(man.getTelephone());
                } else {
                    defaultListener.onListItemClick(man.getTelephone());
                }
            }
        });
    }

    private void initView(View view) {
        listView = view.findViewById(R.id.listView_link_man);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    public void queryContact(String queryStr, OnListItemClickListener mClickListener) {
        Log.d(TAG, "queryContact: " + queryStr);
        this.mClickListener = mClickListener;
        presenter.fetchQuery(queryStr);
    }

    @Override
    public void notifyListAdapter(List<Map<String, Object>> list) {

    }

    @Override
    public void notifyListAdapter2(List<LinkMan> list) {
        Log.d(TAG, "notifyListAdapter2: "+list.size());
        adapter.setLinkManList(list);
    }
}
