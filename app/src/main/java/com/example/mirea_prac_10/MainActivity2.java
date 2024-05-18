package com.example.mirea_prac_10;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mirea_prac_10.db.MyDbManager;

public class MainActivity2 extends AppCompatActivity {
    private MyDbManager myDbManager;
    EditText inputName;
    EditText inputPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myDbManager = new MyDbManager(this);

        inputName = findViewById(R.id.editTextName);
        inputPhone = findViewById(R.id.editTextPhone);


    }

    protected void onResume() {
        super.onResume();
        myDbManager.openDb();
    }

    protected void onDestroy() {
        super.onDestroy();
        myDbManager.closeDb();
    }

    public void delete(View view) {
        myDbManager.openDb();
        myDbManager.deleteFromDb();
    }

    public void upgrade(View view) {
        myDbManager.openDb();
        myDbManager.updateDb("ИЗМЕНЕНО","ТЕКСТ ДЛЯ ИЗМЕНЕНО");
    }

    public void add(View view) {
        String name = inputName.getText().toString();
        String phone = inputPhone.getText().toString();
        myDbManager.openDb();
        myDbManager.insertToDb(name,phone);
    }

    public void find(View view) {
        String phone = inputPhone.getText().toString();
        myDbManager.openDb();
        String find_number = myDbManager.findInBd(phone);

    }




}