package com.jarzabek.presidentlistrecycleview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    List<President> presidentList;
    Context context;

    public RecyclerViewAdapter(List<President> presidentList, Context context) {
        this.presidentList = presidentList;
        this.context = context;
    }

    // this method will execute on create
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        // look for docs
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_president, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.tv_presidentName.setText(presidentList.get(position).getName());
        holder.tv_date.setText(String.valueOf(presidentList.get(position).getDateOfElection()));
        Glide.with(context).load(presidentList.get(position).getImageURL()).into(holder.iv_presidentImage);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddEditOne.class);
                intent.putExtra("id", presidentList.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    // number of items in the view
    @Override
    public int getItemCount() {
        return presidentList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_presidentImage;
        TextView tv_presidentName;
        TextView tv_date;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_presidentImage = itemView.findViewById(R.id.iv_presidentPicture);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_presidentName = itemView.findViewById(R.id.tv_presidentName);
            parentLayout = itemView.findViewById(R.id.oneLinePresidentLayout);
        }
    }
}
