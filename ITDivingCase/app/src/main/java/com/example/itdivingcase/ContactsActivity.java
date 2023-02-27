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

    String[] contactNames = {"Альберто Фернандес" , "Дэвид Хёрли", "Лула да Силва",
            "Олаф Шольц", "Нарендра Моди", "Джоко Видодо", "Джо́рджа Мелони", "Джастин Трюдо",
    "Си Цзинпин", "Риши Сунак", "Андрес Мануэль Лопес Обрадор", "Владимир Путин", "Джо Байден",
    "Реджеп Тайип Эрдоган", "Эмманюэль Макрон", "Юн Сок Ёль", "Сирил Рамафоса", "Фумио Кисида",
    "Салман ибн Абдул-Азиз Аль Сауд"};

    String[] contactDescription = {"Президент Аргентины" , "Генерал-губернатор Австралии",
            "Президент Бразилии", "Канцлер Германии", "Премьер-министр Индии",
    "Президент Индонезии", "Премьер-министр Италии", "Премьер-министр Канады", "Председатель КНР",
    "Премьер-министр Великобритании", "Президент Мексики", "Президент России", "Президент США",
    "Президент Турции", "Президент Франции", "Президент Южной Кореи", "Президент ЮАР",
    "Премьер-министр Японии", "Король Саудовской Аравии"};

    int[] contactImages = {R.drawable.argentina, R.drawable.australia, R.drawable.brazil,
    R.drawable.germany, R.drawable.india, R.drawable.indonezia, R.drawable.italy,
            R.drawable.canada, R.drawable.china, R.drawable.uk, R.drawable.mexico,
    R.drawable.russia, R.drawable.usa, R.drawable.turkey, R.drawable.france, R.drawable.korea,
    R.drawable.south_africa, R.drawable.japan, R.drawable.saudi_arabia};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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