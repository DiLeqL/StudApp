package com.envy.studapp.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

import java.util.List;


public class GroupListArrayAdapter extends ArrayAdapter {

    private List<String> groupList;

    @Override
    public int getCount() {
        if (groupList != null) return groupList.size();
        else return 0;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        if (groupList != null) return groupList.get(position);
        else return 0;
    }

    public GroupListArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }

    public void setGroupList(List<String> groupList){
        this.groupList = groupList;
    }

}
