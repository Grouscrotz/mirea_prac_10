package com.example.mirea_prac_10;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mirea_prac_10.db.MyDbManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private MyDbManager myDbManager;
    EditText inputName;
    EditText inputPhone;
    TextView textView;


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
        String name = inputName.getText().toString();
        String phone = inputPhone.getText().toString();

        myDbManager.openDb();
        myDbManager.deleteFromDb(name,phone);
    }


    public void upgrade(View view) {
        String name = inputName.getText().toString();
        String phone = inputPhone.getText().toString();
        myDbManager.openDb();
        myDbManager.updateDb(name,phone);
    }

    public void add(View view) {
        String name = inputName.getText().toString();
        String phone = inputPhone.getText().toString();
        myDbManager.openDb();
        myDbManager.insertToDb(name,phone);
    }

    public void find(View view) {
        textView = findViewById(R.id.textView2);
        String phone = inputPhone.getText().toString();
        myDbManager.openDb();
        String find_number = myDbManager.findInBd(phone);
        Log.d("RRR",find_number);
        textView.setText(find_number);

    }

    public void showAll(View view) {
        List<String> all_list = new ArrayList<>();
        all_list = myDbManager.getFromDb();
        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,all_list);
        listView.setAdapter(adapter);

    }






}
