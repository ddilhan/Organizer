package com.dilhan.hp.organizer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNote extends AppCompatActivity {

    EditText e1,e2;
    Button add;
    DBOrganizer dbOrganizer= new DBOrganizer(AddNote.this);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_note);

        e1=findViewById(R.id.txtTitle);
        e2=findViewById(R.id.txtBody);

        add=findViewById(R.id.btnAdd);

        Intent i= getIntent();
        e1.setText(i.getStringExtra("Title"));

       boolean flag= i.getBooleanExtra("IsUpdate",true);

        if(flag){
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dbOrganizer.updateNote(dbOrganizer,e1.getText().toString(),e2.getText().toString());
                    Toast.makeText(AddNote.this,"Note Updated",Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(AddNote.this,MainActivity.class);
                    startActivity(intent);
                }
            });
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbOrganizer.insertNote(dbOrganizer,e1.getText().toString(),e2.getText().toString());

                Toast.makeText(AddNote.this,"Note added",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(AddNote.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
