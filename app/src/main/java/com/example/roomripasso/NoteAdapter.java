package com.example.roomripasso;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> allNotes= new ArrayList<>();
    private Context context;

    public NoteAdapter (Context context){
        this.context= context;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item,parent,false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.title.setText(allNotes.get(position).getTitle());
        holder.description.setText(allNotes.get(position).getContent());
        int month = GregorianCalendar.getInstance().getTime().getMonth();
        int day = GregorianCalendar.getInstance().getTime().getDay();
        holder.date.setText(""+month+"/"+day);
        // Quando viene cliccata la cardview apro l'Activity NoteContent e gli passo l'id della Nota associata
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note note = allNotes.get(holder.getAdapterPosition());
                Intent intent = new Intent(context,AddNoteActivity.class);
                intent.putExtra("note",note);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    public void setAllNotes(List<Note> allNotes){
        this.allNotes = allNotes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position){
        return allNotes.get(position);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView date;
        private TextView description;
        private CardView cardView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_view_title);
            date = itemView.findViewById(R.id.text_view_date);
            cardView = itemView.findViewById(R.id.cardView);
            description = itemView.findViewById(R.id.tect_view_description);

        }
    }
}
