package com.baymax.language_app.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.baymax.language_app.Message;

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

    public long insertDataWord(int id_fk, String src){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.ID_LT, id_fk);
        cv.put(DBHelper.SRC_WORD, src);
        long id = db.insert(DBHelper.TABLE_WORD, null, cv);
        return id;
    }


    static class DBHelper extends SQLiteOpenHelper{

        private static final int DATABASE_VERSION = 5;
        private static final String DATABASE_NAME = "PicaWord";


        //---------------------------LEVEL TABLE--------------------------------
        private static final String TABLE_LEVEL = "Level";
        private static final String SRC_LEVEL = "src";
        private static final String CREATE_TABLE_LEVEL = "CREATE TABLE " + TABLE_LEVEL + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+SRC_LEVEL+" VARCHAR(50));";
        //---------------------------LEVEL TABLE--------------------------------

        //------------------------CATEGORY TABLE--------------------------------
        private static final String TABLE_CATEGORY = "Category";
        private static final String ID_LET = "id_let";
        private static final String SRC_CATEGORY = "src";
        private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+ID_LET+" INTEGER, "+SRC_CATEGORY+" VARCHAR(50), FOREIGN KEY("+ID_LET+") REFERENCES "+TABLE_LEVEL+"(_id));";
        //------------------------CATEGORY TABLE--------------------------------

        //--------------------------LESSON TABLE--------------------------------
        private static final String TABLE_LESSON = "Lesson";
        private static final String ID_CT = "id_let";
        private static final String SRC_LESSON = "src";
        private static final String CREATE_TABLE_LESSON = "CREATE TABLE " + TABLE_LESSON + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+ID_CT+" INTEGER, "+SRC_LESSON+" VARCHAR(50), FOREIGN KEY("+ID_CT+") REFERENCES "+TABLE_CATEGORY+"(_id));";
        //--------------------------LESSON TABLE--------------------------------

        //----------------------------WORD TABLE--------------------------------
        private static final String TABLE_WORD = "Word";
        private static final String ID_LT = "id_let";
        private static final String SRC_WORD = "src";
        private static final String CREATE_TABLE_WORD = "CREATE TABLE " + TABLE_WORD + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+ID_LT+" INTEGER, "+SRC_WORD+" VARCHAR(50), FOREIGN KEY("+ID_LT+") REFERENCES "+TABLE_LESSON+"(_id));";
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