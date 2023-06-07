package com.example.biblio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnAdd, btnRead, btnClear;
    Button btnAddBook, btnReadBook, btnClearBook;
    EditText etName, etEmail, etPassword;
    EditText etBook, etAuthor, etSummary;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);



        // Удаление базы данных и её пересоздание,дабы добавить или
        // удалить лишние или добавить новые столбцы или базу
        /*SQLiteDatabase database = dbHelper.getWritableDatabase();
        dbHelper.close();
        this.deleteDatabase(DBHelper.DATABASE_NAME);*/

        //Бд пользователей
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
        //поля бд пользователей
        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        //Бд книг
        btnAddBook = (Button) findViewById(R.id.btnAddBook);
        btnAddBook.setOnClickListener(this);

        btnReadBook = (Button) findViewById(R.id.btnReadBook);
        btnReadBook.setOnClickListener(this);

        btnClearBook = (Button) findViewById(R.id.btnClearBook);
        btnClearBook.setOnClickListener(this);
        //поля бд книг
        etBook = (EditText) findViewById(R.id.etBook);
        etAuthor = (EditText) findViewById(R.id.etAuthor);
        etSummary = (EditText) findViewById(R.id.etSummary);


    }

    @Override
    public void onClick(View v) {


        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        String bookName = etBook.getText().toString();
        String author = etAuthor.getText().toString();
        String summary = etSummary.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        switch (v.getId()) {

            case R.id.btnAdd:
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_MAIL, email);
                contentValues.put(DBHelper.KEY_PASSWORD, password);

                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                break;

            case R.id.btnRead:
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    int emailIndex = cursor.getColumnIndex(DBHelper.KEY_MAIL);
                    int passwordIndex = cursor.getColumnIndex(DBHelper.KEY_PASSWORD);
                    do {
                        Log.d("mLog", "ID = " + cursor.getInt(idIndex) +
                                ", name = " + cursor.getString(nameIndex) +
                                ", email = " + cursor.getString(emailIndex) +
                                ", password = " + cursor.getString(passwordIndex));
                    } while (cursor.moveToNext());
                } else
                    Log.d("mLog","0 rows");

                cursor.close();
                break;

            case R.id.btnClear:
                database.delete(DBHelper.TABLE_CONTACTS, null, null);
                break;
        }
        // бд книг ----------------------------
        switch (v.getId()) {

            case R.id.btnAddBook:
                contentValues.put(DBHelper.KEY_BOOK_NAME, bookName);
                contentValues.put(DBHelper.KEY_AUTHOR, author);
                contentValues.put(DBHelper.KEY_SUMMARY, summary);
                database.insert(DBHelper.TABLE_BOOKS, null, contentValues);
                break;

            case R.id.btnReadBook:
                Cursor bookCursor  = database.query(DBHelper.TABLE_BOOKS, null, null, null, null, null, null);

                if (bookCursor.moveToFirst()) {
                    int bookIdIndex  = bookCursor.getColumnIndex(DBHelper.KEY_ID);
                    int bookNameIndex  = bookCursor.getColumnIndex(DBHelper.KEY_BOOK_NAME);
                    int authorIndex = bookCursor.getColumnIndex(DBHelper.KEY_AUTHOR);
                    int summaryIndex  = bookCursor.getColumnIndex(DBHelper.KEY_SUMMARY);
                    do {
                        Log.d("mLog", "BookID = " + bookCursor.getInt(bookIdIndex) +
                                ", Book name = " + bookCursor.getString(bookNameIndex) +
                                ", author = " + bookCursor.getString(authorIndex) +
                                ", summary = " + bookCursor.getString(summaryIndex));
                    } while (bookCursor.moveToNext());
                } else
                    Log.d("mLog","0 rows");

                bookCursor.close();
                break;

            case R.id.btnClearBook:
                database.delete(DBHelper.TABLE_BOOKS, null, null);
                break;
        }

        dbHelper.close();
    }
}