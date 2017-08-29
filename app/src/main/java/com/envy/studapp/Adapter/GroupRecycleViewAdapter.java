package com.envy.studapp.Adapter;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.envy.studapp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupRecycleViewAdapter extends RecyclerView.Adapter<GroupRecycleViewAdapter.GroupViewHolder>{

    private Context context;

    private List<String> groupList;

    private int selectedPosition = -1;

    public GroupRecycleViewAdapter(Context context, List<String> groupList){
        this.context = context;
        this.groupList = groupList;
    }

    public void setGroupList(List<String> groupList){
        this.groupList = groupList;
    }

    @Override
    public GroupRecycleViewAdapter.GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item_card_view, parent, false);
        return new GroupRecycleViewAdapter.GroupViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GroupRecycleViewAdapter.GroupViewHolder holder, int position) {

        String groupName = groupList.get(position);
        holder.tvGroupName.setText(groupName);
        holder.radioButton.setChecked(position == selectedPosition);

        //Set the position tag to both radio button and label
        holder.radioButton.setTag(position);
        holder.tvGroupName.setTag(position);
        holder.radioButton.setOnClickListener(this::itemCheckChanged);
        holder.tvGroupName.setOnClickListener(this::itemCheckChanged);
    }

    @Override
    public int getItemCount() {
        if (groupList != null) return groupList.size();
        else return 0;
    }

    private void itemCheckChanged(View v) {
        selectedPosition = (Integer) v.getTag();
        notifyDataSetChanged();
    }

    public String getSelectedItem() {
        if (selectedPosition != -1) {
            Toast.makeText(context, "Selected Item : " + groupList.get(selectedPosition), Toast.LENGTH_SHORT).show();
            return groupList.get(selectedPosition);
        }
        return "";
    }

    class GroupViewHolder extends RecyclerView.ViewHolder{

        CardView groupItem;

        @BindView(R.id.tv_group_name)
        TextView tvGroupName;

        @BindView(R.id.rb_group)
        RadioButton radioButton;

        GroupViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            groupItem = (CardView) itemView.findViewById(R.id.cv_group_item);
        }
    }
}
