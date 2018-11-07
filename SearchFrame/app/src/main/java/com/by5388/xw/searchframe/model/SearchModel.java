package com.by5388.xw.searchframe.model;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import com.by5388.xw.searchframe.App;
import com.by5388.xw.searchframe.LinkMan;
import com.by5388.xw.searchframe.R;
import com.by5388.xw.searchframe.presenter.OnContactQueryListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by5388  on 2018/11/7.
 */
public class SearchModel implements ISearchModel {
    private static final String TAG = "SearchModel";
    private List<Map<String, Object>> list;

    /**
     * 查询到的联系人
     */
    private List<LinkMan> linkManList;

    public SearchModel() {
        list = new ArrayList<>();
        linkManList = new ArrayList<>();
    }

    @Override
    public void queryContact(String search, OnContactQueryListener listener) {
        list.clear();
        linkManList.clear();
        Log.d(TAG, "queryContact: " + search);
        Map<String, Object> listItem;
        Cursor cursor = App.getInstance()
                .getContentResolver()
                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " like ? or " +
                                ContactsContract.CommonDataKinds.Phone.NUMBER + " like ? or " +
                                ContactsContract.CommonDataKinds.Phone.SORT_KEY_PRIMARY + " like ?",
                        new String[]{"%" + search + "%", "%" + search + "%", pyQueryStringBuilder(search)},
                        ContactsContract.Contacts.SORT_KEY_PRIMARY);
        if (null != cursor) {
            while (cursor.moveToNext()) {
                listItem = new HashMap<>(16);
                String telephone = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                Long id = cursor.getLong(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID));
                listItem.put("telphone", telephone);
                listItem.put("name", name == null ? "" : name);
                listItem.put("display", name == null ? App.getInstance().getResources().getString(R.string.no_name_display) : name);
                listItem.put("ID", id);
                list.add(listItem);
                linkManList.add(new LinkMan().setId(id).setName(name).setTelephone(telephone));
                Log.d(TAG, "number = " + telephone);
                Log.d(TAG, "name = " + name);
            }
            cursor.close();
        }
        if (search.isEmpty()) {
            list.clear();
            linkManList.clear();
        }
        listener.onComplete(list);
        listener.onCompleted(linkManList);
    }

    private String pyQueryStringBuilder(String search) {
        String[] items = search.split("");
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : items) {
            stringBuilder.append(item).append("%");
        }
        return stringBuilder.toString();
    }
}
