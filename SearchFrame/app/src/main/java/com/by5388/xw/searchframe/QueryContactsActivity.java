package com.by5388.xw.searchframe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.by5388.xw.searchframe.view.OnListItemClickListener;
import com.by5388.xw.searchframe.view.SearchFragment;
import com.by5388.xw.searchframe.view.SearchLinkManFragment;

/**
 * @author by5388
 * @date 2018/11/07
 */
public class QueryContactsActivity extends AppCompatActivity
        implements TextWatcher, OnListItemClickListener {

    SearchLinkManFragment view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_contacts);
        initData();
        initView();
        loadData();
    }

    private void initData() {
        view = SearchLinkManFragment.newInstance();
        // TODO: 2018/11/7
    }

    private void loadData() {
        // TODO: 2018/11/7
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, view);
        fragmentTransaction.commit();
    }

    private void initView() {
        // TODO: 2018/11/7
        EditText editText = findViewById(R.id.edit_query);
        editText.addTextChangedListener(this);

    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(s)) {
            return;
        }
        view.queryContact(s.toString(), this);
    }

    @Override
    public void afterTextChanged(Editable s) {
        // TODO: 2018/11/7
    }

    @Override
    public void onListItemClick(String number) {
        if (TextUtils.isEmpty(number)) {
            Toast.makeText(this, R.string.empty_number, Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri uri = Uri.parse("tel:" + number);
        intent.setData(uri);
        startActivity(intent);
    }
}
