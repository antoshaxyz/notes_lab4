package com.example.notes_lab4;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;

public class AddNoteActivity extends AppCompatActivity {
    EditText editNoteName, editNoteContent;
    Button btnSave;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editNoteName = findViewById(R.id.editNoteName);
        editNoteContent = findViewById(R.id.editNoteContent);
        btnSave = findViewById(R.id.btnSave);
        sharedPreferences = getSharedPreferences("Notes", MODE_PRIVATE);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void saveNote() {
        String name = editNoteName.getText().toString().trim();
        String content = editNoteContent.getText().toString().trim();

        if (name.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "Note name and content cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        String note = name + ": " + content;
        String notesJson = sharedPreferences.getString("notes", "[]");

        try {
            JSONArray jsonArray = new JSONArray(notesJson);
            jsonArray.put(note);
            sharedPreferences.edit().putString("notes", jsonArray.toString()).apply();
            finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
