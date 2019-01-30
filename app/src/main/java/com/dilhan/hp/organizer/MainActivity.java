package com.dilhan.hp.organizer;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView l1;
    ArrayList arrayList = new ArrayList();
    DBOrganizer dbOrganizer = new DBOrganizer(MainActivity.this);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_button, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            Intent intent = new Intent(MainActivity.this, AddNote.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        l1 = findViewById(R.id.noteList);

        Cursor c = dbOrganizer.displayNotes(dbOrganizer);

        while (c.moveToNext()) {
            arrayList.add(c.getString(0));
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, R.layout.note_list_view, arrayList);
        l1.setAdapter(arrayAdapter);

        l1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dbOrganizer.deleteNote(dbOrganizer,String.valueOf(arrayList.get(position)));
                Toast.makeText(MainActivity.this,"Note Deleted",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
                return true;
            }
        });

        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent  intent= new Intent(MainActivity.this,AddNote.class);
                intent.putExtra("Title",String.valueOf(arrayList.get(position)));
                intent.putExtra("IsUpdate",true);
                startActivity(intent);
            }
        });
    }


}
