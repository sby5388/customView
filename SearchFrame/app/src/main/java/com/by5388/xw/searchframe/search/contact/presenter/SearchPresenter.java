package com.by5388.xw.searchframe.search.contact.presenter;

import android.util.Log;

import com.by5388.xw.searchframe.LinkMan;
import com.by5388.xw.searchframe.search.contact.model.ISearchModel;
import com.by5388.xw.searchframe.search.contact.model.SearchModel;
import com.by5388.xw.searchframe.search.contact.view.ISearchView;

import java.util.List;
import java.util.Map;

/**
 * @author by5388  on 2018/11/7.
 */
public class SearchPresenter implements ISearchPresenter, OnContactQueryListener {
    private static final String TAG = "SearchPresenter";
    private ISearchView view;
    private ISearchModel model;

    public SearchPresenter(ISearchView view) {
        this.view = view;
        this.model = new SearchModel();
    }

    @Override
    public void onAttach(ISearchView view) {

    }

    @Override
    public void onDetach() {
        // TODO: 2018/11/7
        model = null;

    }

    @Override
    public void fetchQuery(String queryStr) {
        Log.d(TAG, "fetchQuery: " + queryStr);
        model.queryContact(queryStr, this);
    }

    @Override
    public void onComplete(List<Map<String, Object>> list) {
        view.notifyListAdapter(list);
    }

    @Override
    public void onCompleted(List<LinkMan> list) {
        // TODO: 2018/11/7
        Log.d(TAG, "onCompleted: " + list.size());
        view.notifyListAdapter2(list);
    }
}
