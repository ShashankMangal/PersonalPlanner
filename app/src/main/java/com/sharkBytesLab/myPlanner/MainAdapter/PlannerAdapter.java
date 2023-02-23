package com.sharkBytesLab.myPlanner.MainAdapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sharkBytesLab.myPlanner.AddNewTask;
import com.sharkBytesLab.myPlanner.MainActivity;
import com.sharkBytesLab.myPlanner.MainModel.PlannerModel;
import com.sharkBytesLab.myPlanner.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class PlannerAdapter extends RecyclerView.Adapter<PlannerAdapter.viewHolder> {

    private List<PlannerModel> list;
    private MainActivity activity;
    private FirebaseFirestore firestore;

    public PlannerAdapter(List<PlannerModel> list, MainActivity activity) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.add_task,parent,false);
        firestore = FirebaseFirestore.getInstance();
        return new viewHolder(view);
    }

    public void deleteTask(int position){
        PlannerModel model = list.get(position);
        firestore.collection("task").document(model.TaskId).delete();
        list.remove(position);
        notifyItemRemoved(position);
    }

    public Context getContext(){
        return activity;

    }
    public void editTask(int position){
        PlannerModel model = list.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("task", model.getTask());
        bundle.putString("due", model.getDue());
        bundle.putString("id", model.TaskId);

        AddNewTask addNewTask = new AddNewTask();
        addNewTask.setArguments(bundle);
        addNewTask.show(activity.getSupportFragmentManager(), addNewTask.getTag());

    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PlannerModel model = list.get(position);
        holder.mCheckBox.setText(model.getTask());
        holder.mDuedate.setText("Due On "+model.getDue());
        holder.mCheckBox.setChecked(toBoolean(model.getStatus()));
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    firestore.collection("task").document(model.TaskId).update("status",1);
                }else{
                    firestore.collection("task").document(model.TaskId).update("status",0);
                }
            }
        });


    }
    private boolean toBoolean(int status){
        return status!=0;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{

        TextView mDuedate;
        CheckBox mCheckBox;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            mDuedate = itemView.findViewById(R.id.due_date_tv);
            mCheckBox = itemView.findViewById(R.id.mCheckBox);
        }
    }
}
