package com.by5388.xw.searchframe.search.contact;

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
import android.widget.TextView;
import android.widget.Toast;

import com.by5388.xw.searchframe.R;
import com.by5388.xw.searchframe.utils.PhoneNumberFormatter;
import com.by5388.xw.searchframe.search.contact.view.OnListItemClickListener;
import com.by5388.xw.searchframe.search.contact.view.SearchLinkManFragment;

/**
 * @author by5388
 * @date 2018/11/07
 */
public class QueryContactsActivity extends AppCompatActivity
        implements TextWatcher, OnListItemClickListener {

    SearchLinkManFragment fragment;
    EditText editText;
    TextView showNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_contacts);
        initData();
        initView();
        loadData();
    }

    private void initData() {
        fragment = SearchLinkManFragment.newInstance();
        // TODO: 2018/11/7
    }

    private void loadData() {
        // TODO: 2018/11/7
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private void initView() {
        // TODO: 2018/11/7
        editText = findViewById(R.id.edit_query);
        editText.addTextChangedListener(this);
        showNumber = findViewById(R.id.show_number);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(s)) {
            return;
        }
        fragment.queryContact(s.toString());
//        PhoneNumberFormatter.setPhoneNumberFormattingTextWatcher(this, showNumber);
        PhoneNumberFormatter.setPhoneNumberFormattingTextWatcher(this, showNumber);
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
