package com.envy.studapp.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allattentionhere.fabulousfilter.AAH_FabulousFragment;
import com.envy.studapp.Dagger.Schedule.Injection.DaggerScheduleComponent;
import com.envy.studapp.Dagger.Schedule.Module.AppModule;
import com.envy.studapp.Dagger.Schedule.Module.DBModule;
import com.envy.studapp.Filter.Data.FilterKey;
import com.envy.studapp.R;
import com.envy.studapp.Filter.Presentation.FilterPresenter;
import com.envy.studapp.Filter.Presentation.FilterView;
import com.google.android.flexbox.FlexboxLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FilterFabFragment extends AAH_FabulousFragment implements FilterView {

    @Inject
    FilterPresenter filterPresenter;

    SectionsPagerAdapter mAdapter;

    Integer previousItemIndex;

    ImageButton imgBtnApply;

    FilterKey filterKey;

    ArrayMap<String, List<String>> appliedFilters = new ArrayMap<>();

    List<TextView> textViews = new ArrayList<>();

    TabLayout tabsTypes;


    public static FilterFabFragment newInstance() {
        return new FilterFabFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerScheduleComponent.builder().appModule(new AppModule(getContext()))
                .dBModule(new DBModule(getContext())).build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        filterPresenter.onCreateView(this, savedInstanceState);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.schedule_filter_view, null);
        RelativeLayout rlContent = (RelativeLayout) contentView.findViewById(R.id.rl_content);
        LinearLayout llButtons = (LinearLayout) contentView.findViewById(R.id.ll_buttons);
        ViewPager vpTypes = (ViewPager) contentView.findViewById(R.id.vp_types);
        tabsTypes = (TabLayout) contentView.findViewById(R.id.tabs_types);
        //contentView.findViewById(R.id.imgbtn_apply).setOnClickListener(v -> closeFilter(appliedFilters));
        imgBtnApply = (ImageButton) contentView.findViewById(R.id.imgbtn_apply);
        imgBtnApply.setOnClickListener(v -> closeFilter(appliedFilters));
        mAdapter = new SectionsPagerAdapter();
        vpTypes.setOffscreenPageLimit(4);
        vpTypes.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        tabsTypes.setupWithViewPager(vpTypes);

        //params to set
        setAnimationDuration(350); //optional; default 500ms
        setPeekHeight(300); // optional; default 400dp
        setCallbacks((Callbacks) getFragmentManager().findFragmentById(R.id.schedule_fragment)); //optional; to get back result
        //       setAnimationListener((AnimationListener) getActivity()); //optional; to get animation callbacks
        setViewgroupStatic(llButtons); // optional; layout to stick at bottom on slide
        setViewPager(vpTypes); //optional; if you use viewpager that has scrollview
        setViewMain(rlContent); //necessary; main bottomsheet view
        setMainContentView(contentView); // necessary; call at end before super
        super.setupDialog(dialog, style); //call super at last
    }

    @Override
    public void setFilterKey(FilterKey filterKey) {
        this.filterKey = filterKey;
        mAdapter.setFilterKey(filterKey);
        mAdapter.notifyDataSetChanged();
    }

    private class SectionsPagerAdapter extends PagerAdapter {

        FilterKey filterKey;

        private void setFilterKey(FilterKey filterKey) {
            this.filterKey = filterKey;
        }


        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public Object instantiateItem(ViewGroup collection, int position) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.schedule_view_filters_sorters,
                    collection, false);
            FlexboxLayout fbl = (FlexboxLayout) layout.findViewById(R.id.fbl);
//            LinearLayout ll_scroll = (LinearLayout) layout.findViewById(R.id.ll_scroll);
//            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (metrics.heightPixels-(104*metrics.density)));
//            ll_scroll.setLayoutParams(lp);
            if (filterKey != null) {
                switch (position) {
                    case 0:
                        inflateLayoutWithFilters("day", fbl);
                        break;
                    case 1:
                        inflateLayoutWithFilters("teacher", fbl);
                        break;
                    case 2:
                        inflateLayoutWithFilters("group", fbl);
                        break;
                }
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

    private void inflateLayoutWithFilters(final String filter_category, FlexboxLayout fbl) {

        List<String> keys = new ArrayList<>();
        switch (filter_category) {
            case "day":
                keys = filterKey.getWeekdayKeyList();
                filterKey.setSingleOption(true);
                break;
            case "teacher":
                keys = filterKey.getTeacherKeyList();
                filterKey.setSingleOption(false);
                break;
            case "group":
                keys = filterKey.getGroupNumKeyList();
                filterKey.setSingleOption(true);
                break;
        }

        for (int i = 0; i < keys.size(); i++) {
            View subChild = getActivity().getLayoutInflater().inflate(R.layout.single_chip, null);
            final TextView tv = ((TextView) subChild.findViewById(R.id.txt_title));
            tv.setText(keys.get(i));
            final int finalI = i;
            final List<String> finalKeys = keys;
            if (filterKey.getSingleOption()){
                if (isItemSelected()){
                    tv.setOnClickListener(v -> {
                        if (previousItemIndex != null){
                            FilterFabFragment.this.removeFromSelectedMap(filter_category,
                                    finalKeys.get(previousItemIndex));
                            Log.d("previous", previousItemIndex.toString());
                        }
                        for (TextView tvItem : textViews) {
                            tvItem.setTag("unselected");
                            tvItem.setBackgroundResource(R.drawable.chip_unselected);
                            tvItem.setTextColor(ContextCompat.getColor(getContext(), R.color.filters_chips));
                        }
                        //FilterFabFragment.this.removeFromSelectedMap(filter_category, finalKeys.get(finalI));
                        Log.d("filter", finalKeys.get(finalI));
                        //appliedFilters.clear();
                        tv.setTag("selected");
                        tv.setBackgroundResource(R.drawable.chip_selected);
                        tv.setTextColor(ContextCompat.getColor(FilterFabFragment.this.getContext(),
                                R.color.filters_header));
                        FilterFabFragment.this.addToSelectedMap(filter_category, finalKeys.get(finalI));
                        previousItemIndex = finalI;
                    });
                }
            }
            else {
                tv.setOnClickListener(v -> {
                    if (tv.getTag() != null && tv.getTag().equals("selected")) {
                        tv.setTag("unselected");
                        tv.setBackgroundResource(R.drawable.chip_unselected);
                        tv.setTextColor(ContextCompat.getColor(FilterFabFragment.this.getContext(), R.color.filters_chips));
                        FilterFabFragment.this.removeFromSelectedMap(filter_category, finalKeys.get(finalI));
                    } else {
                        tv.setTag("selected");
                        tv.setBackgroundResource(R.drawable.chip_selected);
                        tv.setTextColor(ContextCompat.getColor(FilterFabFragment.this.getContext(), R.color.filters_header));
                        FilterFabFragment.this.addToSelectedMap(filter_category, finalKeys.get(finalI));
                    }
                });
            }

            /*if (appliedFilters != null && appliedFilters.get(filter_category) != null &&
                    appliedFilters.get(filter_category).contains(keys.get(finalI))) {
                tv.setTag("selected");
                tv.setBackgroundResource(R.drawable.chip_selected);
                tv.setTextColor(ContextCompat.getColor(getContext(), R.color.filters_header));
            } else {
                tv.setBackgroundResource(R.drawable.chip_unselected);
                tv.setTextColor(ContextCompat.getColor(getContext(), R.color.filters_chips));
            }*/
            textViews.add(tv);

            fbl.addView(subChild);
        }

    }

    private void addToSelectedMap(String key, String value) {
        if (appliedFilters.get(key) != null && !appliedFilters.get(key).contains(value)) {
            appliedFilters.get(key).add(value);
        } else {
            List<String> temp = new ArrayList<>();
            temp.add(value);
            appliedFilters.put(key, temp);
        }
    }

    private void removeFromSelectedMap(String key, String value) {
        if (appliedFilters.get(key).size() == 1) {
            appliedFilters.remove(key);
        } else {
            appliedFilters.get(key).remove(value);
        }
    }

    private boolean isItemSelected(){
        return textViews.size() >= 0;
    }
}
