package ee.testprep;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import com.aspose.cells.Row;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper implements Serializable{

    private static final String className = DataBaseHelper.class.getName();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "eeTestPrep2.db";

    // Table Names
    private static final String TABLE_USERDATA = "userData";
    private static final String TABLE_QBANK = "qBank";

    public static final String CREATE_TABLE_MAIN = "CREATE TABLE "
            + TABLE_QBANK + "(" +
            BaseColumns._ID + " INTEGER PRIMARY KEY, "
            + DBRow.KEY_EXAM + " TEXT, "
            + DBRow.KEY_YEAR + " TEXT, "
            //+ DBRow.KEY_QNO + " INTEGER, "
            + DBRow.KEY_QUESTION + " TEXT, "
            + DBRow.KEY_OPTA + " TEXT, "
            + DBRow.KEY_OPTB + " TEXT, "
            + DBRow.KEY_OPTC + " TEXT, "
            + DBRow.KEY_OPTD + " TEXT, "
            + DBRow.KEY_ANSWER + " TEXT, "
            + DBRow.KEY_IPC + " TEXT, "
            + DBRow.KEY_SUBJECT + " TEXT)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_QBANK;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if(!isTableExists(db, TABLE_QBANK)) {
            db.execSQL(CREATE_TABLE_MAIN);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL(SQL_DELETE_ENTRIES);
        // create new tables
        onCreate(db);
    }

    private boolean isTableExists(SQLiteDatabase db, String tableName) {

/*        if(openDb) {
            if(db == null || !db.isOpen()) {
                db = getReadableDatabase();
            }

            if(!db.isReadOnly()) {
                db.close();
                db = getReadableDatabase();
            }
        }*/

        Cursor cursor = db.rawQuery(
                "select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'",
                null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }

    public long insertRow(DBRow row) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBRow.KEY_EXAM, row.exam);
        values.put(DBRow.KEY_YEAR, row.year);
        //values.put(DBRow.KEY_QNO, row.qNo);
        values.put(DBRow.KEY_QUESTION, row.question);
        values.put(DBRow.KEY_OPTA, row.optionA);
        values.put(DBRow.KEY_OPTB, row.optionB);
        values.put(DBRow.KEY_OPTC, row.optionC);
        values.put(DBRow.KEY_OPTD, row.optionD);
        values.put(DBRow.KEY_ANSWER, row.answer);
        values.put(DBRow.KEY_IPC, row.ipc);
        values.put(DBRow.KEY_SUBJECT, row.subject);

        // insert row
        return db.insert(TABLE_QBANK, null, values);
    }

    /*
    Query database by year
     */
    public List<DBRow> queryByYear(String filter) {
        List<DBRow> questions = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_QBANK + " WHERE " + filter;

        L.d(className, "query by " + filter);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c != null && c.moveToFirst()) {
            do {
                DBRow row = new DBRow();
                row.exam = c.getString(c.getColumnIndex(DBRow.KEY_EXAM));
                row.year = c.getString(c.getColumnIndex(DBRow.KEY_YEAR));
                //row.qNo = c.getInt(c.getColumnIndex(DBRow.KEY_QNO));
                row.question = c.getString(c.getColumnIndex(DBRow.KEY_QUESTION));
                row.optionA = c.getString(c.getColumnIndex(DBRow.KEY_OPTA));
                row.optionB = c.getString(c.getColumnIndex(DBRow.KEY_OPTB));
                row.optionC = c.getString(c.getColumnIndex(DBRow.KEY_OPTC));
                row.optionD = c.getString(c.getColumnIndex(DBRow.KEY_OPTD));
                row.answer = c.getString(c.getColumnIndex(DBRow.KEY_ANSWER));
                row.ipc = c.getString(c.getColumnIndex(DBRow.KEY_IPC));
                row.subject = c.getString(c.getColumnIndex(DBRow.KEY_SUBJECT));

                // adding to todo list
                questions.add(row);
                L.d(className, row.toString());
            } while (c.moveToNext());
        }

        return questions;
    }

/*    public DBUSer checkQuizLevelExists(String quizlevel) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_USERDATA + " WHERE "
                + KEY_QUIZLEVEL + " = " + "'" + quizlevel + "'";

        Log.e(className, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);
        DBUSer td = null;
        if (c != null && c.moveToFirst()) {

            td = new DBUSer();
            td.setQuizName(c.getString(c.getColumnIndex(KEY_QUIZNAME)));
            td.setQuizlevelname(c.getString(c.getColumnIndex(KEY_QUIZLEVEL)));
            td.setScore(c.getInt(c.getColumnIndex(KEY_SCORE)));
            td.setAttempts(c.getInt(c.getColumnIndex(KEY_ATTEMPTS)));
            td.setTotalNoQuestions(c.getInt(c.getColumnIndex(KEY_TOTALQUESTIONS)));
            td.setDateofquiz(c.getString(c.getColumnIndex(KEY_DATEOFQUIZ)));
        }

        return td;
    }

    public List<DBUSer> getAllData() {
        List<DBUSer> todos = new ArrayList<DBUSer>();
        String selectQuery = "SELECT  * FROM " + TABLE_USERDATA;

        Log.e(className, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                DBUSer td = new DBUSer();
                td.setQuizName(c.getString(c.getColumnIndex(KEY_QUIZNAME)));
                td.setQuizlevelname(c.getString(c.getColumnIndex(KEY_QUIZLEVEL)));
                td.setScore(c.getInt(c.getColumnIndex(KEY_SCORE)));
                td.setAttempts(c.getInt(c.getColumnIndex(KEY_ATTEMPTS)));
                td.setTotalNoQuestions(c.getInt(c.getColumnIndex(KEY_TOTALQUESTIONS)));
                td.setDateofquiz(c.getString(c.getColumnIndex(KEY_DATEOFQUIZ)));
                // adding to todo list
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }

    *//**
     * getting todo count
     *//*
    public int getToDoCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USERDATA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    public int updateToDo(DBUSer todo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ATTEMPTS, todo.getAttempts());
        values.put(KEY_SCORE, todo.getScore());

        // updating row
        return db.update(TABLE_USERDATA, values, KEY_QUIZLEVEL + " = ?",
                new String[]{String.valueOf(todo.getQuizlevelname())});
    }*/

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }


}