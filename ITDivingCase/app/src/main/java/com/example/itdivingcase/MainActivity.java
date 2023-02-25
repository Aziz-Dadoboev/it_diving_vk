package com.example.itdivingcase;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityResultLauncher<Intent> someActivityResultLauncher;
    List<Contact> contactList = new ArrayList<>();
    List<View> views = new ArrayList<>();

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ImageView messagesIcon = findViewById(R.id.messages_icon);
        ImageView video = findViewById(R.id.video);
        ImageView mic = findViewById(R.id.mic);
        ImageView hand = findViewById(R.id.alert);
        ImageView exit = findViewById(R.id.exit);
        ImageView contacts = findViewById(R.id.group_icon);
        ImageView table = findViewById(R.id.shelf_icon);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageView imageView1 = findViewById(R.id.gridview_image);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView textView1 = findViewById(R.id.gridview_text);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        LinearLayout container1 = findViewById(R.id.container1_gridview);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageView imageView2 = findViewById(R.id.gridview_image2);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView textView2 = findViewById(R.id.gridview_text2);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        LinearLayout container2 = findViewById(R.id.container1_gridview2);


        Contact contact1 = new Contact(R.drawable.legolas, "You", R.drawable.top_background);
        Contact contact2 = new Contact(R.drawable.colors_palette_circle,
                "Long Contact For Test and Test and Again and Again and Again and One More Time",
                R.drawable.bottom_background);
        contactList.add(contact1);
        contactList.add(contact2);

        views.add(imageView1); //0
        views.add(textView1); //1
        views.add(imageView2); //2
        views.add(textView2); //3

        update(contactList, views);

        // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == 1) {
                        // There are no request codes
                        Intent data = result.getData();
                        if (data == null) {
                            Toast.makeText(MainActivity.this, "Error",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                        String[] names = {"You", data.getStringExtra("name")};
                        int[] images = {R.drawable.cheburashka, data.getIntExtra("image",
                                R.drawable.walter_white_20)};

                        contact2.setImage(images[1]);
                        contact2.setName(names[1]);
                        contactList.set(1, contact2);
                        views = update(contactList, views);
                    }
                });

        messagesIcon.setOnClickListener(this);
        video.setOnClickListener(this);
        mic.setOnClickListener(this);
        hand.setOnClickListener(this);
        exit.setOnClickListener(this);
        contacts.setOnClickListener(this);
        table.setOnClickListener(this);
    }

    @SuppressLint({"NonConstantResourceId", "UseCompatLoadingForDrawables"})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.messages_icon:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("sms:"));
                startActivity(i);
                break;
            case R.id.video:
                ImageView video = findViewById(R.id.video);
                if (video.getDrawable().getConstantState() == getDrawable(R.drawable.video_off)
                        .getConstantState()) {
                    video.setImageResource(R.drawable.video_on);
                    Log.d("MYTAG", "Video turned ON");
                } else {
                    video.setImageResource(R.drawable.video_off);
                    Log.d("MYTAG", "Video turned OFF");
                }
                break;
            case R.id.mic:
                ImageView mic = findViewById(R.id.mic);
                if (mic.getDrawable().getConstantState() == getDrawable(R.drawable.mic_off)
                        .getConstantState()) {
                    mic.setImageResource(R.drawable.mic_on);
                } else {
                    mic.setImageResource(R.drawable.mic_off);
                }
                break;
            case R.id.alert:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                break;
            case R.id.group_icon:
                Intent myIntent = new Intent(MainActivity.this,
                        ContactsActivity.class);
                someActivityResultLauncher.launch(myIntent);
                break;

            case R.id.shelf_icon:
                contactList = swap(contactList);
                views = update(contactList, views);
                break;
            default:
                finishAndRemoveTask();
        }
    }

    public List<Contact> swap(List<Contact> contacts) {
        Contact temp = contacts.get(0);
        contacts.set(0, contacts.get(1));
        contacts.set(1, temp);
        return contacts;
    }

    public List<View> update(List<Contact> contacts, List<View> views) {
        List<View> viewList = new ArrayList<>();
        ImageView imageView = (ImageView) views.get(0);
        TextView textView = (TextView) views.get(1);

        imageView.setImageResource(contacts.get(0).getImage());
        textView.setText(contacts.get(0).getName());
        viewList.add(imageView);
        viewList.add(textView);

        imageView = (ImageView) views.get(2);
        textView = (TextView) views.get(3);

        imageView.setImageResource(contacts.get(1).getImage());
        textView.setText(contacts.get(1).getName());
        viewList.add(imageView);
        viewList.add(textView);

        return viewList;
    }
}