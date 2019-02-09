package com.dilhan.hp.organizer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNote extends AppCompatActivity {

    EditText txtTitle, txtContent;
    Button addNote;
    DBOrganizer dbOrganizer = new DBOrganizer(AddNote.this);
    boolean isUpdate = false;
    Cursor cursor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_note);
        txtTitle = findViewById(R.id.txtTitle);
        txtContent = findViewById(R.id.txtBody);
        addNote = findViewById(R.id.btnAdd);
        cursor= dbOrganizer.displayNotes(dbOrganizer);

        Intent i = getIntent();
        txtTitle.setText(i.getStringExtra("Title"));
        txtContent.setText((i.getStringExtra("Content")));
        isUpdate = i.getBooleanExtra("IsUpdate", false);

        if (isUpdate == true) {
            addNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbOrganizer.updateNote(dbOrganizer, txtTitle.getText().toString(), txtContent.getText().toString());
                    Toast.makeText(AddNote.this, "Note Updated", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddNote.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            addNote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbOrganizer.insertNote(dbOrganizer, txtTitle.getText().toString(), txtContent.getText().toString());
                    Toast.makeText(AddNote.this, "Note added", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddNote.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    public boolean validate(Cursor cursor) {
         while (cursor.moveToNext()){

         }
         return true;
    }
}
