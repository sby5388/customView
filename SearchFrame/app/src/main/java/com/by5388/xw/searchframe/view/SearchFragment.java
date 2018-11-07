package com.by5388.xw.searchframe.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.by5388.xw.searchframe.LinkMan;
import com.by5388.xw.searchframe.R;
import com.by5388.xw.searchframe.presenter.ISearchPresenter;
import com.by5388.xw.searchframe.presenter.SearchPresenter;

import java.util.List;
import java.util.Map;

/**
 * @author by5388  on 2018/11/7.
 */
public class SearchFragment extends ListFragment implements ISearchView {
    private static final String TAG = "SearchFragment";
    private ISearchPresenter presenter;
    private OnListItemClickListener mClickListener;
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

    @Override
    public void notifyListAdapter(List<Map<String, Object>> list) {
        // TODO: 2018/11/7
        final String[] from = {"telphone", "name"};
        final int[] to = {R.id.title, R.id.info};
        SimpleAdapter adapter = new SimpleAdapter(getContext(),
                list, R.layout.list_item, from, to);
        // TODO: 2018/11/7 重写设配器

        this.setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView listView, View v, int position, long id) {
        super.onListItemClick(listView, v, position, id);
        // TODO: 2018/11/7 应当使用一个实体类来表示数据
        Map<String, Object> map = (Map<String, Object>) listView.getAdapter().getItem(position);
        Log.d(TAG, "position = " + map.get("telphone"));
        String number = (String) map.get("telphone");
        if (mClickListener != null) {
            mClickListener.onListItemClick(number);
        } else {
            defaultListener.onListItemClick(number);
        }
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_view, container, false);
        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    public void queryContact(String queryStr, OnListItemClickListener mClickListener) {
        this.mClickListener = mClickListener;
        presenter.fetchQuery(queryStr);
    }

    @Override
    public void notifyListAdapter2(List<LinkMan> list) {
        // TODO: 2018/11/7
    }
}
