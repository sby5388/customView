package com.by5388.xw.searchframe.call.history;

import android.support.annotation.DrawableRes;

import com.by5388.xw.searchframe.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import static android.provider.CallLog.Calls.INCOMING_TYPE;
import static android.provider.CallLog.Calls.MISSED_TYPE;
import static android.provider.CallLog.Calls.OUTGOING_TYPE;
import static android.provider.CallLog.Calls.REJECTED_TYPE;

/**
 * 通话记录 条目
 *
 * @author by5388  on 2018/11/10.
 */
public class CallItem {
    /**
     * 名称
     */
    private String name;
    /**
     * 号码
     */
    private String phone;
    /**
     * 呼叫时刻 具体到分钟
     * 如果是当天要特殊显示
     */
    private Date callTime;
    /**
     * 类型：拨出，拨入，未接电话，来电已拒绝等
     */
    private int callType;

    /**
     * 呼叫类型对应的文字
     */
    private int callTypeString;
    /**
     * 图片，与callTime 相一致
     */
    @DrawableRes
    private int iconRes;


    public String getName() {
        return name;
    }

    public int getCallTypeString() {
        return callTypeString;
    }

    public CallItem setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public CallItem setPhone(String phone) {
        this.phone = phone;
        return this;
    }


    public Date getCallTime() {
        return callTime;
    }

    public CallItem setCallTime(Date callTime) {
        this.callTime = callTime;
        return this;
    }

    public int getCallType() {
        return callType;
    }

    public CallItem setCallType(int callType) {
        this.callType = callType;
        // TODO: 2018/11/10
        setIconRes(callType);
        return this;
    }

    /**
     * 设置图片
     *
     * @param callType
     */
    private void setIconRes(int callType) {
        switch (callType) {
            case INCOMING_TYPE:
                this.callTypeString = R.string.incoming_call;
                this.iconRes = R.drawable.ic_call_arrow_in;
                break;
            case OUTGOING_TYPE:
                this.callTypeString = R.string.outgoing_call;
                this.iconRes = R.drawable.ic_call_arrow_out;
                break;
            case MISSED_TYPE:
                this.callTypeString = R.string.missed_call;
                this.iconRes = R.drawable.ic_call_arrow_missed;
                break;
            case REJECTED_TYPE:
                this.callTypeString = R.string.call_rejected;
                this.iconRes = R.drawable.ic_call_arrow_rejected;
                break;
            default:
                break;
        }
    }

    @DrawableRes
    public int getIconRes() {
        return iconRes;
    }

}
