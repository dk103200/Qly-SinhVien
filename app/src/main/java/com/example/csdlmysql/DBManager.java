package com.example.csdlmysql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    private final String TAG = "DBManager";
    private static final String DATABASE_NAME = "student_manager";
    private static final String TABLE_NAME = "sinhvien";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String CLASS = "lop";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";

    private  Context context;
    private String sqlQuery = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " integer primary key, " +
            NAME + " TEXT, " +
            CLASS + " TEXT, " +
            PHONE + " TEXT," +
            EMAIL + " TEXT)";

    public DBManager(Context txt) {
        super(txt, DATABASE_NAME, null, 1);
        this.context = txt;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addStudent (Student st){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, st.getName());
        values.put(CLASS,st.getlop());
        values.put(PHONE,st.getPhone());
        values.put(EMAIL,st.getemail());

        db.insert(TABLE_NAME,null,values);
        Log.d(TAG,values.toString());
        db.close();


    }
    public List<Student> getAll(){
        List<Student> listStudent = new ArrayList<Student>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do{
                Student st = new Student();
                st.setId(cursor.getInt(0));
                st.setName(cursor.getString(1));
                st.setlop(cursor.getString(2));
                st.setPhone(cursor.getString(3));
                st.setemail(cursor.getString(4));
                listStudent.add(st);
            }while (cursor.moveToNext());
        }
        db.close();
        return listStudent;
    }

    public int editStudent(Student st){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, st.getName());
        values.put(CLASS,st.getlop());
        values.put(PHONE,st.getPhone());
        values.put(EMAIL,st.getemail());

        return db.update(TABLE_NAME,values,ID + "=?", new String[]{String.valueOf(st.getId())});

    }

    public int delStudent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,ID+"=?", new String[]{String.valueOf(id)});
    }
}
