package com.example.notes_lab4;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private ArrayList<String> noteList;
    private Context context;

    public NoteAdapter(Context context, ArrayList<String> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        String note = noteList.get(position);
        String[] parts = note.split(": ", 2);
        String name = parts[0];
        String content = parts.length > 1 ? parts[1] : "";

        holder.noteNameTextView.setText(name);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, NoteDetailActivity.class);
            intent.putExtra("note_name", name);
            intent.putExtra("note_content", content);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView noteNameTextView;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteNameTextView = itemView.findViewById(R.id.noteNameTextView);
        }
    }
}
