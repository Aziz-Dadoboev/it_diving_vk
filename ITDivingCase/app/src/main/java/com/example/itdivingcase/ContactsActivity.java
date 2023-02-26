package com.example.itdivingcase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity implements SelectListener {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    String[] contactNames = {"Альберто Фернандес" ,
            "Дэвид Хёрли"};

    String[] contactDescription = {"Президент Аргентины" ,
            "Генерал-губернатор Австралии"};

    int[] contactImages = {R.drawable.argentina, R.drawable.australia};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setTitle("Контакты");
        setContentView(R.layout.activity_contacts);

        recyclerView = findViewById(R.id.contacts_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Person> contacts = new ArrayList<>();
        for (int i = 0; i < contactNames.length; ++i) {
            Person person = new Person(contactNames[i], contactDescription[i], contactImages[i]);
            contacts.add(person);
        }
        ContactsAdapter contactsAdapter = new ContactsAdapter(this, contacts, this);
        recyclerView.setAdapter(contactsAdapter);
    }

    @Override
    public void onItemClicked(Person person) {
        setResult(1,
                new Intent().putExtra("name", person.getName())
                        .putExtra("image", person.getImage()));
        finish();
    }
}