package com.by5388.xw.searchframe.call.history.model;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;

import com.by5388.xw.searchframe.App;
import com.by5388.xw.searchframe.call.history.CallItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.provider.CallLog.Calls.CACHED_NAME;
import static android.provider.CallLog.Calls.DATE;
import static android.provider.CallLog.Calls.NUMBER;
import static android.provider.CallLog.Calls.TYPE;

/**
 * @author by5388  on 2018/11/10.
 */
public class LogModel implements ILogModel {
    private List<CallItem> callItems;

    public LogModel() {
        callItems = new ArrayList<>();
    }

    @Override
    public void getCallLog(ILogModelCallBack callBack) {
        // TODO: 2018/11/10
        callItems.clear();
        Context context = App.getInstance();
        ContentResolver cr = context.getContentResolver();
        if (context.checkCallingOrSelfPermission(Manifest.permission.READ_CALL_LOG) == PackageManager.PERMISSION_GRANTED) {
            Cursor cursor = cr
                    .query(CallLog.Calls.CONTENT_URI,
                            new String[]{
                                    NUMBER,
                                    DATE,
                                    CallLog.Calls.TYPE,
                                    CACHED_NAME,
                                    CallLog.Calls.DURATION,
                                    CallLog.Calls.GEOCODED_LOCATION},
                            null,
                            null,
                            CallLog.Calls.DEFAULT_SORT_ORDER);
            if (cursor == null) {
                return;
            }
            while (cursor.moveToNext()) {
                String number = cursor.getString(cursor.getColumnIndex(NUMBER));
                String name = cursor.getString(cursor.getColumnIndex(CACHED_NAME));
                int callType = cursor.getInt(cursor.getColumnIndex(TYPE));
                Date date = new Date(cursor.getLong(cursor.getColumnIndex(DATE)));
                callItems.add(new CallItem().setName(name != null ? name : number).setPhone(number).setCallType(callType).setCallTime(date));
            }
            cursor.close();
        }
        callBack.queryCompleted(callItems);
    }
}
