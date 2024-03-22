package com.example.projectnma;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder>{
    private Context context;
    private List<NotesModel> notesModelList;

    public NotesAdapter(Context context, List<NotesModel> notesList) {
        this.context = context;
        this.notesModelList = notesList;
        if (this.notesModelList == null) {
            this.notesModelList = new ArrayList<>();
        }
    }


    public void add(NotesModel notesModel){
        if (notesModelList != null) {
            notesModelList.add(notesModel);
            notifyDataSetChanged();
        }
    }

    public void clear(){
        if (notesModelList != null) {
            notesModelList.clear();
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_tow,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        NotesModel notesModel=notesModelList.get(position);
        holder.title.setText(notesModel.getTitle());
        holder.description.setText(notesModel.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, UpdateActivity.class);
                intent.putExtra("id",notesModel.getId());
                intent.putExtra("title",notesModel.getTitle());
                intent.putExtra("description",notesModel.getDescription());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title,description;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
        }
    }
}