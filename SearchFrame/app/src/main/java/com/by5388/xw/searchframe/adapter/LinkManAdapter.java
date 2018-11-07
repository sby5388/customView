package com.by5388.xw.searchframe.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.by5388.xw.searchframe.LinkMan;

import java.util.List;

/**
 * @author by5388  on 2018/11/7.
 */
public class LinkManAdapter extends BaseAdapter {
    private static final String TAG = "LinkManAdapter";

    private List<LinkMan> linkManList;
    private LayoutInflater inflater;

    public LinkManAdapter(Context context, List<LinkMan> linkManList) {
        this.linkManList = linkManList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return linkManList.size();
    }

    @Override
    public LinkMan getItem(int position) {
        return linkManList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinkMan man = getItem(position);
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.two_line_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (man != null) {
            holder.name.setText(man.getName());
            holder.telephone.setText(man.getTelephone());
        }
        return convertView;
    }

    public void setLinkManList(List<LinkMan> linkManList) {
        if (linkManList != null) {
            Log.d(TAG, "setLinkManList: " + linkManList.size());
            this.linkManList = linkManList;
            notifyDataSetChanged();
        }
    }

    private static class ViewHolder {
        final TextView telephone, name;

        ViewHolder(View view) {
            telephone = view.findViewById(android.R.id.text1);
            name = view.findViewById(android.R.id.text2);
        }
    }

}
