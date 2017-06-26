package com.envy.studapp.Schedule.Presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.envy.studapp.R;

/**
 * Created by ENVY on 14.06.2017.
 */

public class BasePresenter<T> {

    boolean isVisible;

    T view;


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
