package com.baymax.language_app.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.baymax.language_app.Message;

import java.util.ArrayList;

public class DBAdapter {

    DBHelper helper;

    public DBAdapter(Context context){
        helper = new DBHelper(context);
    }

    public long insertDataLevel(String src){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.SRC_LEVEL, src);
        long id = db.insert(DBHelper.TABLE_LEVEL, null, cv);
        return id;
    }

    public long insertDataCategory(int id_fk, String src){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.ID_LET, id_fk);
        cv.put(DBHelper.SRC_CATEGORY, src);
        long id = db.insert(DBHelper.TABLE_CATEGORY, null, cv);
        return id;
    }

    public long insertDataLesson(int id_fk, String src){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.ID_CT, id_fk);
        cv.put(DBHelper.SRC_LESSON, src);
        long id = db.insert(DBHelper.TABLE_LESSON, null, cv);
        return id;
    }

    public long insertDataWord(int id_fk, int complete, String src){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.ID_LT, id_fk);
        cv.put(DBHelper.COMPLETE, complete);
        cv.put(DBHelper.SRC_WORD, src);
        long id = db.insert(DBHelper.TABLE_WORD, null, cv);
        return id;
    }

    public ArrayList<String> getAllDataLevel(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {DBHelper.ID_LEVEL,DBHelper.SRC_LEVEL};
        Cursor cursor = db.query(DBHelper.TABLE_LEVEL, columns, null, null, null, null, null);
        ArrayList<String> information = new ArrayList<String>();
        while(cursor.moveToNext()){
            int index1 = cursor.getColumnIndex(DBHelper.ID_LEVEL);
            int index2 = cursor.getColumnIndex(DBHelper.SRC_LEVEL);
            int id = cursor.getInt(0);
            String src = cursor.getString(1);
            information.add(id + " " + src + "\n");
        }
        return information;
    }

    public ArrayList<String> getAllDataCategory(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {DBHelper.ID_CATEGORY, DBHelper.ID_LET, DBHelper.SRC_CATEGORY};
        Cursor cursor = db.query(DBHelper.TABLE_CATEGORY, columns, null, null, null, null, null);
        ArrayList<String> information = new ArrayList<String>();
        while(cursor.moveToNext()){
            int cid = cursor.getInt(0);
            int cif = cursor.getInt(1);
            String src = cursor.getString(2);
            information.add(cid + " " + cif + " " + src + "\n");
        }
        return information;
    }

    public ArrayList<String> getAllDataLesson(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {DBHelper.ID_LESSON, DBHelper.ID_CT, DBHelper.SRC_LESSON};
        Cursor cursor = db.query(DBHelper.TABLE_LESSON, columns, null, null, null, null, null);
        ArrayList<String> information = new ArrayList<String>();
        while(cursor.moveToNext()){
            int cid = cursor.getInt(0);
            int cif = cursor.getInt(1);
            String src = cursor.getString(2);
            information.add(cid + " " + cif + " " + src + "\n");
        }
        return information;
    }

    public ArrayList<String> getAllDataWord(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {DBHelper.ID_WORD, DBHelper.ID_LT, DBHelper.COMPLETE, DBHelper.SRC_WORD};
        Cursor cursor = db.query(DBHelper.TABLE_WORD, columns, null, null, null, null, null);
        ArrayList<String> information = new ArrayList<String>();
        while(cursor.moveToNext()){
            int cid = cursor.getInt(0);
            int cif = cursor.getInt(1);
            String com = cursor.getString(2);
            String src = cursor.getString(3);
            information.add(cid + " " + cif + " " + com + " " + src + "\n");
        }
        return information;
    }

    public int UpdateComplete(String oldvalue, String value){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.COMPLETE, value);
        String[] whereArgs = {oldvalue};
        int count = db.update(DBHelper.TABLE_WORD, cv, DBHelper.COMPLETE + " =? ", whereArgs);
        return count;
    }

    static class DBHelper extends SQLiteOpenHelper{

        private static final int DATABASE_VERSION = 7;
        private static final String DATABASE_NAME = "PicaWord";

        //---------------------------LEVEL TABLE--------------------------------
        private static final String TABLE_LEVEL = "Level";
        private static final String ID_LEVEL = "_id";
        private static final String SRC_LEVEL = "src";
        private static final String CREATE_TABLE_LEVEL = "CREATE TABLE " + TABLE_LEVEL + " ("+ID_LEVEL+" INTEGER PRIMARY KEY AUTOINCREMENT, "+SRC_LEVEL+" VARCHAR(50));";
        //---------------------------LEVEL TABLE--------------------------------

        //------------------------CATEGORY TABLE--------------------------------
        private static final String TABLE_CATEGORY = "Category";
        private static final String ID_CATEGORY = "_id";
        private static final String ID_LET = "id_let";
        private static final String SRC_CATEGORY = "src";
        private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY + " ("+ID_CATEGORY+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ID_LET+" INTEGER, "+SRC_CATEGORY+" VARCHAR(50), FOREIGN KEY("+ID_LET+") REFERENCES "+TABLE_LEVEL+"(_id));";
        //------------------------CATEGORY TABLE--------------------------------

        //--------------------------LESSON TABLE--------------------------------
        private static final String TABLE_LESSON = "Lesson";
        private static final String ID_LESSON = "_id";
        private static final String ID_CT = "id_let";
        private static final String SRC_LESSON = "src";
        private static final String CREATE_TABLE_LESSON = "CREATE TABLE " + TABLE_LESSON + " ("+ID_LESSON+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ID_CT+" INTEGER, "+SRC_LESSON+" VARCHAR(50), FOREIGN KEY("+ID_CT+") REFERENCES "+TABLE_CATEGORY+"(_id));";
        //--------------------------LESSON TABLE--------------------------------

        //----------------------------WORD TABLE--------------------------------
        private static final String TABLE_WORD = "Word";
        private static final String ID_WORD = "_id";
        private static final String ID_LT = "id_let";
        private static final String COMPLETE = "complete";
        private static final String SRC_WORD = "src";
        private static final String CREATE_TABLE_WORD = "CREATE TABLE " + TABLE_WORD + " ("+ID_WORD+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ID_LT+" INTEGER, "+COMPLETE+" VARCHAR(50), "+SRC_WORD+" VARCHAR(50), FOREIGN KEY("+ID_LT+") REFERENCES "+TABLE_LESSON+"(_id));";
        //----------------------------WORD TABLE--------------------------------

        private static final String DROP_TABLE_LEVEL = "DROP TABLE IF EXISTS " + TABLE_LEVEL;
        private static final String DROP_TABLE_CATEGORY =  "DROP TABLE IF EXISTS " + TABLE_CATEGORY;
        private static final String DROP_TABLE_LESSON =  "DROP TABLE IF EXISTS " + TABLE_LESSON;
        private static final String DROP_TABLE_WORD =  "DROP TABLE IF EXISTS " + TABLE_WORD;

        private Context context;

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            Message.message(context, "en constructor");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                Message.message(context, "en create");
                db.execSQL(CREATE_TABLE_LEVEL);
                db.execSQL(CREATE_TABLE_CATEGORY);
                db.execSQL(CREATE_TABLE_LESSON);
                db.execSQL(CREATE_TABLE_WORD);
            }catch (Exception e) {
                Message.message(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE_LEVEL);
                db.execSQL(DROP_TABLE_CATEGORY);
                db.execSQL(DROP_TABLE_LESSON);
                db.execSQL(DROP_TABLE_WORD);
                Message.message(context, "en upgrade");
                onCreate(db);
            }catch (Exception e) {
                Message.message(context, "" + e);
            }
        }

    }

}
