package com.envy.studapp.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.envy.studapp.R;
import com.envy.studapp.Schedule.Data.Model.SubjectModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ScheduleRecycleViewAdapter extends RecyclerView.Adapter<ScheduleRecycleViewAdapter.SubjectViewHolder>{


    private List<SubjectModel> subjectList;
    private Context context;

    public ScheduleRecycleViewAdapter(Context context, List<SubjectModel> subjectList){
        this.context = context;
        this.subjectList = subjectList;
    }

    public void setSubjectList(List<SubjectModel> subjectList) {
        this.subjectList = subjectList;
    }

    @Override
    public SubjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_card_view, parent, false);
        return new SubjectViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SubjectViewHolder holder, int position) {

        SubjectModel subjectModel = subjectList.get(position);
        holder.tvSubjectId.setText(subjectModel.getSubjectId());
        holder.tvBeginningTime.setText(subjectModel.getSubjectTime());
        holder.tvSubjectName.setText(subjectModel.getSubjectName());
        holder.tvTeacherName.setText(subjectModel.getSubjectTeacher());
        holder.tvRoomNum.setText(subjectModel.getSubjectRoom());
    }

    @Override
    public int getItemCount() {
        if (subjectList != null) return subjectList.size();
        else return 0;
    }

    class SubjectViewHolder extends RecyclerView.ViewHolder{

        CardView cvScheduleItem;

        @BindView(R.id.subject_id)
        TextView tvSubjectId;

        @BindView(R.id.beginning_time)
        TextView tvBeginningTime;

        @BindView(R.id.subject_name)
        TextView tvSubjectName;

        @BindView(R.id.teacher_name)
        TextView tvTeacherName;

        @BindView(R.id.room_num)
        TextView tvRoomNum;

        SubjectViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cvScheduleItem = (CardView) itemView.findViewById(R.id.cv_schedule);
        }
    }
}
