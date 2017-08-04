package com.envy.studapp;

import android.widget.TextView;

import java.util.List;


public class BaseFilterKey {

    private boolean isSingleOption;

    public void setSingleOption(boolean singleOption){
        this.isSingleOption = singleOption;
    }

    public TextView getViewItemByIndex(List<TextView> textViews, int position){
        return textViews.get(position);
    }

    public boolean getSingleOption() {
        return isSingleOption;
    }

}
