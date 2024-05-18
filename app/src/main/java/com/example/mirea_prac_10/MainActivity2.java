package com.example.mirea_prac_10;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mirea_prac_10.db.MyDbManager;

public class MainActivity2 extends AppCompatActivity {
    private MyDbManager myDbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myDbManager = new MyDbManager(this);


    }

    protected void onResume() {
        super.onResume();
        myDbManager.openDb();
        myDbManager.insertToDb("ПОГОДА4","сегодня было светло");
        Log.d("RRR","Заход 1");
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




}