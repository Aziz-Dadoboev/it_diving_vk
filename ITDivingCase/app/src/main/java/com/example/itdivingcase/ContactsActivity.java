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

    String[] contactNames = {"Карпова Лада Савельевна" ,
            "Галкин Тихон Арсентьевич" ,
            "Фокина Вера Давидовна" ,
            "Одинцова Мария Мироновна" ,
            "Семенова Василиса Робертовна" ,
            "Баранова Екатерина Артёмовна" ,
            "Моисеев Роберт Викторович" ,
            "Кожевников Владислав Дмитриевич" ,
            "Кольцова Яна Сергеевна" ,
            "Ефимова Анастасия Лукинична" ,
            "Петров Максим Ростиславович" ,
            "Соловьева Елизавета Ивановна" ,
            "Орлов Глеб Григорьевич" ,
            "Денисова Полина Дмитриевна" ,
            "Лазарева София Ивановна" ,
            "Троицкий Леон Романович" ,
            "Коновалова Мария Львовна" ,
            "Колесникова Елена Владиславовна" ,
            "Осипов Дмитрий Владимирович" ,
            "Емельянов Георгий Евгеньевич"};

    String[] contactNumbers = {"7(673)508-04-65644" ,
            "423(20)631-29-83759" ,
            "80(89)266-90-07430" ,
            "50(13)333-70-87468" ,
            "57(832)324-75-53056" ,
            "8(51)322-06-98155" ,
            "389(21)971-28-68059" ,
            "74(9795)772-34-52374" ,
            "34(461)526-57-39626" ,
            "83(567)293-94-93827" ,
            "48(65)485-11-27961" ,
            "29(498)387-11-56537" ,
            "9(689)926-98-84179" ,
            "68(06)173-83-75569" ,
            "4(0406)957-02-76957" ,
            "151(8925)824-07-21138" ,
            "23(26)954-65-62651" ,
            "87(69)668-88-16239" ,
            "65(618)645-39-60742" ,
            "38(06)885-12-45809"};

    int[] contactImages = {R.drawable.anonymous_mask_01, R.drawable.bmo_02,
            R.drawable.dipper_pines_03, R.drawable.finn_04, R.drawable.flame_princess_05,
            R.drawable.genie_06, R.drawable.grinch_07, R.drawable.gunter_08,
            R.drawable.hello_kitty_09, R.drawable.ice_king_10, R.drawable.iron_man_11,
            R.drawable.jake_12, R.drawable.joker_suicide_squad_13, R.drawable.kermit_the_frog_14,
            R.drawable.lumpy_space_princess_15, R.drawable.mabel_pines_16, R.drawable.my_melody_17,
            R.drawable.smurf_18, R.drawable.super_mario_19, R.drawable.walter_white_20};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_contacts);

        recyclerView = findViewById(R.id.contacts_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Person> contacts = new ArrayList<>();
        for (int i = 0; i < contactNames.length; ++i) {
            Person person = new Person(contactNames[i], contactNumbers[i], contactImages[i]);
            contacts.add(person);
//            Log.d("MYTAG", contacts.get(i).getName());
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