package com.envy.studapp.Fragment;

import android.app.Dialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.allattentionhere.fabulousfilter.AAH_FabulousFragment;
import com.envy.studapp.R;
import com.google.android.flexbox.FlexboxLayout;

import static com.envy.studapp.R.id.vp_types;

/**
 * Created by ENVY on 31.07.2017.
 */

public class FilterFabFragment extends AAH_FabulousFragment{

    SectionsPagerAdapter mAdapter;

    TabLayout tabs_types;

    public static FilterFabFragment newInstance() {
        return new FilterFabFragment();
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.schedule_filter_view, null);
        RelativeLayout rl_content = (RelativeLayout) contentView.findViewById(R.id.rl_content);
        LinearLayout ll_buttons = (LinearLayout) contentView.findViewById(R.id.ll_buttons);
        ViewPager vp_types = (ViewPager) contentView.findViewById(R.id.vp_types);
        tabs_types = (TabLayout) contentView.findViewById(R.id.tabs_types);
        contentView.findViewById(R.id.imgbtn_apply).setOnClickListener(v -> closeFilter("closed"));

        mAdapter = new SectionsPagerAdapter();
        vp_types.setOffscreenPageLimit(4);
        vp_types.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        tabs_types.setupWithViewPager(vp_types);

        //params to set
        setAnimationDuration(350); //optional; default 500ms
        setPeekHeight(300); // optional; default 400dp
//        setCallbacks((Callbacks) getActivity()); //optional; to get back result
 //       setAnimationListener((AnimationListener) getActivity()); //optional; to get animation callbacks
        setViewgroupStatic(ll_buttons); // optional; layout to stick at bottom on slide
        //setViewPager(vp_types); //optional; if you use viewpager that has scrollview
        setViewMain(rl_content); //necessary; main bottomsheet view
        setMainContentView(contentView); // necessary; call at end before super
        super.setupDialog(dialog, style); //call super at last
    }

    public class SectionsPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup collection, int position) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.schedule_view_filters_sorters,
                                                                                collection, false);
            FlexboxLayout fbl = (FlexboxLayout) layout.findViewById(R.id.fbl);
//            LinearLayout ll_scroll = (LinearLayout) layout.findViewById(R.id.ll_scroll);
//            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (metrics.heightPixels-(104*metrics.density)));
//            ll_scroll.setLayoutParams(lp);
            switch (position) {
                case 0:
                    //inflateLayoutWithFilters("genre", fbl);
                    break;
                case 1:
                    //inflateLayoutWithFilters("rating", fbl);
                    break;
                case 2:
                    //inflateLayoutWithFilters("year", fbl);
                    break;
            }
            collection.addView(layout);
            return layout;

        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "DAY";
                case 1:
                    return "TEACHER";
                case 2:
                    return "GROUP";
            }
            return "";
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
