package com.example.mirea_prac_10;

import android.content.Context;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
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
    // Метод вызывающий создание JSON-строки, сохранение её в файл
    public void json(View view) {
        Log.d("RRR",createJsonUsingGson());
        saveJsonToFile(this,"json_file.json",createJsonUsingGson());
        readJson();
    }

    // Создать Json из класса
    public String createJsonUsingGson() {
        Contact contact = new Contact();
        contact.id = 1;
        contact.name = "Misha";
        contact.phone = "88055353543";

        Gson gson = new Gson();
        return gson.toJson(contact);
    }

    // Сохранить Json-строку в файл
    public void saveJsonToFile(Context context, String fileName, String jsonString) {
        try (FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE)) {
            fos.write(jsonString.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    // Прочитать строку из Json-файла
    public String readJsonFromFile(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileInputStream fis = context.openFileInput(fileName);
             InputStreamReader inputStreamReader = new InputStreamReader(fis);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    // Json-строку перевести в класс Contact
    public Contact parseJsonToContact(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, Contact.class);
    }

    // Добавить в поля таблицы поля класса Contact, полученного из Json-строки
    public void readJson() {
        String jsonString = readJsonFromFile(this, "json_file.json");
        Contact contact = parseJsonToContact(jsonString);

        String name = contact.name;
        String phone = contact.phone;

        myDbManager.openDb();
        myDbManager.insertToDb(name,phone);

    }


}


