package com.envy.studapp.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import android.widget.ArrayAdapter;

import java.util.List;



public class GroupListAdapter extends ArrayAdapter {

    private List<String> groupList;

    public GroupListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }

    public void setGroupList(List<String> groupList){
        this.groupList = groupList;
    }

}
