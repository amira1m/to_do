package com.example.todo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Adapterr extends RecyclerView.Adapter<Adapterr.MyViewHolder> {
    private  Context context;
    private ArrayList id,title,description;
    int User_ID;
    Activity activity;

    Adapterr(Activity activity,Context context,ArrayList id,ArrayList title,ArrayList description,int UserID){
        this.activity=activity;
        this.context=context;
        this.id=id;
        this.title=title;
        this.description=description;
        this.User_ID = UserID;


    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater influter=LayoutInflater.from(context);
        View view= influter.inflate(R.layout.myrows,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView")final int position) {
        holder.title_txt.setText(String.valueOf(title.get(position)));
        holder.description_txt.setText(String.valueOf(description.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,UpdateActivity.class);
                intent.putExtra("ID",User_ID);
                intent.putExtra("id",String.valueOf(id.get(position)));
                intent.putExtra("title",String.valueOf(title.get(position)));
                intent.putExtra("description",String.valueOf(description.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id_txt,title_txt,description_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title_txt=itemView.findViewById(R.id.title3);
            description_txt=itemView.findViewById(R.id.textView);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }

}
