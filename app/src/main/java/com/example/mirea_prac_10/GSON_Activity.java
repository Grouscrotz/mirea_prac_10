package com.example.mirea_prac_10;

import android.os.Bundle;
import com.google.gson.Gson;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GSON_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;
        setContentView(R.layout.activity_gson);

    }

    public String createJsonUsingGson() {
        Contact contact = new Contact();
        contact.id = 1;
        contact.name = "Stepan";
        contact.phone = "89383169999";

        Gson gson = new Gson();
        return gson.toJson(contact);
    }

}