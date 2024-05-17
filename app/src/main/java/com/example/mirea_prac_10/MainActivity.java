package com.example.mirea_prac_10;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void saveData(View view) {
        // Сохранение данных

        SharedPreferences sharedPreferences = getSharedPreferences("myPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Сохранение строкового значения
        editor.putString("username","User123");
        editor.putInt("sessionCount", 5);
        editor.putBoolean("loggedIn", true);

        // Сохранение изменений
        editor.apply();

    }

    public void getData(View view) {
        // Получение данных

        SharedPreferences sharedPreferences = getSharedPreferences("myPreferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Чтение строкового значения
        String username = sharedPreferences.getString("username","defaultUsername");
        int sessionCount = sharedPreferences.getInt("sessionCount",0);
        boolean isLoggedIn = sharedPreferences.getBoolean("loggedIn",false);

        TextView textView = findViewById(R.id.textView);
        textView.setText(username);

    }

    public void deleteData(View view) {
        // Удаление данных

        SharedPreferences sharedPreferences = getSharedPreferences("myPreferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Удаление данных по ключу
        editor.remove("username");

        // Удаление всех данных
        editor.clear();

        // Применение изменений
        editor.apply();

    }

    public void setData(View view) {
        // Изменение данных

        SharedPreferences sharedPreferences = getSharedPreferences("myPreferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Находим EditText и читаем его
        EditText editText = findViewById(R.id.editTextText);
        String new_username = editText.getText().toString();

        // Удалём ключ username
        editor.remove("username");
        // Добавляем значение с ключом username
        editor.putString("username",new_username);

        editor.apply();

        editText.setText(null);

    }

    public void newActivity(View view) {
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent);
    }




}