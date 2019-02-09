package com.dilhan.hp.organizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.method.NumberKeyListener;

public class DBOrganizer extends SQLiteOpenHelper {
    public DBOrganizer(Context context) {
        super(context, "OrganizerDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE NOTE (Title Varchar, Content Varchar);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertNote(DBOrganizer dbOrganizer, String title, String content) {
        SQLiteDatabase sqLiteDatabase = dbOrganizer.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Title", title);
        contentValues.put("Content", content);
        sqLiteDatabase.insert("NOTE", null, contentValues);
    }

    public Cursor displayNotes(DBOrganizer dbOrganizer) {
        SQLiteDatabase sqLiteDatabase = dbOrganizer.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM NOTE", null);
        return c;
    }

    public void deleteNote(DBOrganizer dbOrganizer, String title) {
        SQLiteDatabase sqLiteDatabase = dbOrganizer.getWritableDatabase();
        sqLiteDatabase.delete("NOTE", "Title=?", new String[]{title});
    }

    public void updateNote(DBOrganizer dbOrganizer, String title, String content) {
        SQLiteDatabase sqLiteDatabase = dbOrganizer.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Content", content);
        sqLiteDatabase.update("NOTE", contentValues, "Title=?", new String[]{title});
    }
}
