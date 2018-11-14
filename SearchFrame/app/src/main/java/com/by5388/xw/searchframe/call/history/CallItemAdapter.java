package com.by5388.xw.searchframe.call.history;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.by5388.xw.searchframe.R;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author by5388  on 2018/11/10.
 */
public class CallItemAdapter extends BaseAdapter {
    private List<CallItem> callItems;
    private LayoutInflater inflater;
    private Context context;

    CallItemAdapter(Context context, List<CallItem> callItems) {
        this.context =context;
        this.inflater = LayoutInflater.from(context);
        this.callItems = callItems;
    }

    void setCallItems(@NonNull List<CallItem> callItems) {
        this.callItems = callItems;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return callItems.size();
    }

    @Override
    public CallItem getItem(int position) {
        return callItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CallItem callItem = getItem(position);
        ViewHolder holder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_call, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.showName.setText(callItem.getName());
        holder.showStatus.setText(showStatus(callItem.getCallTime(), callItem.getCallTypeString()));
        holder.callIcon.setImageResource(callItem.getIconRes());
        return convertView;
    }


    private String showStatus(@NonNull Date date, @StringRes int callType) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date);
        //if (calendar.get(Calendar.DATE) == calendar2.get(Calendar.DATE))


        String type = context.getResources().getString(callType);

        return type;
    }

    /*
        void ss(HashMap<String, Object> call, Date date) {
            call.put("dateandstate", new SimpleDateFormat(mReference.get().getResources().getString(R.string.date_format)).format(date) + " " + type);
            Date now = new Date();
            String today = new SimpleDateFormat("yyyyMMdd").format(now);
            String thisYear = new SimpleDateFormat("yyyy").format(now);
            String day = new SimpleDateFormat("yyyyMMdd").format(date);
            String year = new SimpleDateFormat("yyyy").format(date);
            String dateOrTime;
            if (day.equals(today)) {
                dateOrTime = new SimpleDateFormat("HH:mm").format(date);
            } else if (year.equals(thisYear)) {
                dateOrTime = new SimpleDateFormat(.get().getResources().getString(R.string.date_format_day)).
                format(date);
            } else {
                dateOrTime = new SimpleDateFormat(mReference.get().getResources().getString(R.string.date_format_year)).format(date);
            }

            call.put("dateandstatetype", dateOrTime + " " + type);
        }
    */
    private static class ViewHolder {
        final TextView showName, showStatus;
        final ImageView callIcon;

        ViewHolder(View view) {
            showName = view.findViewById(R.id.show_call_name);
            showStatus = view.findViewById(R.id.show_call_status);
            callIcon = view.findViewById(R.id.image_show_call);
        }

    }
}
