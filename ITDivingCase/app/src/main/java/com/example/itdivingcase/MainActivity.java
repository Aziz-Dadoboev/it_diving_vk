package com.example.itdivingcase;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityResultLauncher<Intent> someActivityResultLauncher;
    List<Contact> contactList = new ArrayList<>();
    List<View> views;
    List<Drawable> drawableList;

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
        ImageView imageView2 = findViewById(R.id.gridview_image2);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TextView textView2 = findViewById(R.id.gridview_text2);

        Contact contact1 = new Contact(R.drawable.legolas, "You", R.drawable.top_background,
                0);
        Contact contact2 = new Contact(R.drawable.colors_palette_circle,
                "Long Contact For Test and Test and Again and Again and Again and One More Time",
                R.drawable.bottom_background, 1);
        contactList.add(contact1);
        contactList.add(contact2);

        views = new ArrayList<>();
        views.add(imageView1); //0
        views.add(textView1); //1
        views.add(imageView2); //2
        views.add(textView2); //3

        drawableList = new ArrayList<>();
        Drawable videoOff = ContextCompat.getDrawable(this, R.drawable.camera_off);
        Drawable micOff = ContextCompat.getDrawable(this, R.drawable.mic_off);
        drawableList.add(videoOff); //0
        drawableList.add(videoOff); //1
        drawableList.add(micOff); //2
        drawableList.add(micOff); //3

        update(contactList, views, drawableList);

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
                        String name = data.getStringExtra("name");
                        int image = data.getIntExtra("image", R.drawable.profile);

                        for (int i = 0; i < contactList.size(); ++i) {
                            Contact contact = contactList.get(i);
                            if (contact.getId() == 1) {
                                contact.setImage(image);
                                contact.setName(name);
                                contactList.set(i, contact);
                                break;
                            }
                        }
                        views = update(contactList, views, drawableList);
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
                ImageView video = (ImageView) findViewById(R.id.video);
                if (drawableList.get(1).getConstantState() == drawableList.get(0).getConstantState()) {
                    video.setImageResource(R.drawable.camera_on);
                    Log.d("MYTAG", "Video turned ON");
                    drawableList.set(1, ContextCompat.getDrawable(this, R.drawable.camera_on));
                } else {
                    video.setImageResource(R.drawable.camera_off);
                    Log.d("MYTAG", "Video turned OFF");
                    drawableList.set(1, drawableList.get(0));
                }
                break;
            case R.id.mic:
                int index = 0;
                for (int k = 0; k < contactList.size(); ++k) {
                    Contact contact = contactList.get(k);
                    if (contact.getId() == 0) {
                        index = k;
                        break;
                    }
                }

                ImageView mic = (ImageView) findViewById(R.id.mic);
                TextView textView;
                if (index == 0) {
                    textView = findViewById(R.id.gridview_text);
                } else {
                    textView = findViewById(R.id.gridview_text2);
                }


                if (drawableList.get(3).getConstantState() == drawableList.get(2).getConstantState()) {
                    mic.setImageResource(R.drawable.mic_on);
                    drawableList.set(3, ContextCompat.getDrawable(this, R.drawable.mic_on));
                    textView.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                            R.drawable.baseline_mic_24, 0);
                } else {
                    mic.setImageResource(R.drawable.mic_off);
                    textView.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                            R.drawable.baseline_mic_off_24, 0);
                    drawableList.set(3, drawableList.get(2));
                }
                break;
            case R.id.alert:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.dialog_message).setTitle(R.string.dialog_title);
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
                views = update(contactList, views, drawableList);
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

    public List<View> update(List<Contact> contacts, List<View> views, final List<Drawable> drawableList) {
        List<View> viewList = new ArrayList<>();
        ImageView imageView = (ImageView) views.get(0);
        TextView textView = (TextView) views.get(1);

        imageView.setImageResource(contacts.get(0).getImage());
        textView.setText(contacts.get(0).getName());

        if (contacts.get(0).getId() == 1) {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_mic_off_24, 0);
        } else if (drawableList.get(2).getConstantState() != drawableList.get(3).getConstantState()) {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_mic_24, 0);
        }

        viewList.add(imageView);
        viewList.add(textView);

        imageView = (ImageView) views.get(2);
        textView = (TextView) views.get(3);

        if (contacts.get(1).getId() == 1) {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_mic_off_24, 0);
        } else if (drawableList.get(2).getConstantState() != drawableList.get(3).getConstantState()) {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_mic_24, 0);
        }

        imageView.setImageResource(contacts.get(1).getImage());
        textView.setText(contacts.get(1).getName());
        viewList.add(imageView);
        viewList.add(textView);

        return viewList;
    }
}