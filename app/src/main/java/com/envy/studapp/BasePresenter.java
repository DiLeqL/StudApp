package com.envy.studapp;

import android.os.Bundle;


/**
 * Created by ENVY on 14.06.2017.
 */

public class BasePresenter<T> {

    boolean isVisible;

    protected T view;


    public void onCreateView(T view,
                             Bundle savedInstanceState) {
        this.view = view;

    }

    public void onStart() {
        isVisible = true;
    }

    public void onStop() {
        isVisible = false;
    }

    public void onDestroyView() {
        view = null;
    }


    public boolean isVisibleView(){
        return isVisible && view != null;
    }

}
