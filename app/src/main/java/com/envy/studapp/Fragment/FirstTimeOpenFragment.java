package com.envy.studapp.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.envy.studapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstTimeOpenFragment extends DialogFragment{

    @BindView(R.id.spinner)
    Spinner spinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_time_open, null);
        ButterKnife.bind(this, view);
//        String selected = spinner.getSelectedItem().toString();
  //      Log.d("spinner", selected);
        //v.findViewById(R.id.btnYes).setOnClickListener(this);
        //v.setBackgroundColor(getResources().getColor(R.color.cardViewColor));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.button_accept)
    public void accept(){
        dismiss();
    }
}
