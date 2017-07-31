package com.envy.studapp.Fragment;

import android.app.Dialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.allattentionhere.fabulousfilter.AAH_FabulousFragment;
import com.envy.studapp.R;

/**
 * Created by ENVY on 31.07.2017.
 */

public class FilterFabFragment extends AAH_FabulousFragment{

    public static FilterFabFragment newInstance() {
        return new FilterFabFragment();
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.fragment_filter_fab, null);
        RelativeLayout rl_content = (RelativeLayout) contentView.findViewById(R.id.rl_content);
        LinearLayout ll_buttons = (LinearLayout) contentView.findViewById(R.id.ll_buttons);
        contentView.findViewById(R.id.btn_close).setOnClickListener(v -> closeFilter("closed"));

        //params to set
        setAnimationDuration(600); //optional; default 500ms
        setPeekHeight(300); // optional; default 400dp
//        setCallbacks((Callbacks) getActivity()); //optional; to get back result
 //       setAnimationListener((AnimationListener) getActivity()); //optional; to get animation callbacks
        setViewgroupStatic(ll_buttons); // optional; layout to stick at bottom on slide
        //setViewPager(vp_types); //optional; if you use viewpager that has scrollview
        setViewMain(rl_content); //necessary; main bottomsheet view
        setMainContentView(contentView); // necessary; call at end before super
        super.setupDialog(dialog, style); //call super at last
    }
}
