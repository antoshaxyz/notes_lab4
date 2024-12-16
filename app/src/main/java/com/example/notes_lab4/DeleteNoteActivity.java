package com.example.notes_lab4;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class DeleteNoteActivity extends AppCompatActivity {
    ListView listView;
    Button btnDelete;
    ArrayList<String> noteList;
    ArrayAdapter<String> adapter;
    SharedPreferences sharedPreferences;
    int selectedNoteIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        listView = findViewById(R.id.listViewDelete);
        btnDelete = findViewById(R.id.btnDelete);
        sharedPreferences = getSharedPreferences("Notes", MODE_PRIVATE);

        loadNotes();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedNoteIndex = position;
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedNoteIndex != -1) {
                    deleteNote();
                } else {
                    Toast.makeText(DeleteNoteActivity.this, "Choose note to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loadNotes() {
        noteList = new ArrayList<>();
        String notesJson = sharedPreferences.getString("notes", "[]");
        try {
            JSONArray jsonArray = new JSONArray(notesJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                noteList.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, noteList);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    private void deleteNote() {
        String notesJson = sharedPreferences.getString("notes", "[]");
        try {
            JSONArray jsonArray = new JSONArray(notesJson);
            jsonArray.remove(selectedNoteIndex);
            sharedPreferences.edit().putString("notes", jsonArray.toString()).apply();
            Toast.makeText(this, "Note was deleted", Toast.LENGTH_SHORT).show();
            finish();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
