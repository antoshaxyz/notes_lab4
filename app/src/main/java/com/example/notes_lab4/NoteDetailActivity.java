package com.example.notes_lab4;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class NoteDetailActivity extends AppCompatActivity {
    TextView noteNameTextView, noteContentTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        noteNameTextView = findViewById(R.id.noteNameTextView);
        noteContentTextView = findViewById(R.id.noteContentTextView);

        String noteName = getIntent().getStringExtra("note_name");
        String noteContent = getIntent().getStringExtra("note_content");

        noteNameTextView.setText(noteName);
        noteContentTextView.setText(noteContent);
    }
}
