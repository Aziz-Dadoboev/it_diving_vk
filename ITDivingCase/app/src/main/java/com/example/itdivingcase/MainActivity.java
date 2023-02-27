package com.example.itdivingcase;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityResultLauncher<Intent> someActivityResultLauncher;
    List<Contact> contactList = new ArrayList<>();
    List<View> views;
    List<Drawable> drawableList;

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

        ImageView imageView1 = findViewById(R.id.gridview_image);
        TextView textView1 = findViewById(R.id.gridview_text);
        ImageView blurImage1 = (ImageView) findViewById(R.id.gridview_image_blur);

        ImageView imageView2 = findViewById(R.id.gridview_image2);
        TextView textView2 = findViewById(R.id.gridview_text2);
        ImageView blurImage2 = (ImageView) findViewById(R.id.gridview_image2_blur);

        Contact contact1 = new Contact(R.drawable.legolas, "You",
                0, R.drawable.top_background);
        Contact contact2 = new Contact(R.drawable.colors_palette_circle,
                "Long Contact For Test and Test and Again and Again and Again and One More Time",
                1, R.drawable.bottom_background);
        contactList.add(contact1);
        contactList.add(contact2);

        views = new ArrayList<>();
        views.add(imageView1); //0
        views.add(textView1); //1
        views.add(blurImage1); //2
        views.add(imageView2); //3
        views.add(textView2); //4
        views.add(blurImage2); //5

        drawableList = new ArrayList<>();
        Drawable videoOff = ContextCompat.getDrawable(this, R.drawable.camera_off);
        Drawable micOff = ContextCompat.getDrawable(this, R.drawable.mic_off);
        drawableList.add(videoOff); //0
        drawableList.add(videoOff); //1
        drawableList.add(micOff); //2
        drawableList.add(micOff); //3

        views = update(contactList, views, drawableList);

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
                        int image = data.getIntExtra("image", R.drawable.cheburashka);

                        for (int i = 0; i < contactList.size(); ++i) {
                            Contact contact = contactList.get(i);
                            if (contact.getId() == 1) {
                                contact.setImage(image);
                                contact.setName(name);
                                contact.setBlurred(image);
                                contactList.set(i, contact);
                                break;
                            }
                        }
                        views = setContact(contactList, views);
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.messages_icon) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("sms:"));
            startActivity(i);
        } else if (v.getId() == R.id.video) {
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
        } else if (v.getId() == R.id.mic) {
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
        } else if (v.getId() == R.id.alert) {
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
        } else if (v.getId() == R.id.group_icon) {
            Intent myIntent = new Intent(MainActivity.this,
                    ContactsActivity.class);
            someActivityResultLauncher.launch(myIntent);
        } else if (v.getId() == R.id.shelf_icon) {
            contactList = swap(contactList);
            views = update(contactList, views, drawableList);
        } else {
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
        ((ImageView) views.get(0)).setImageResource(contacts.get(0).getImage());
        TextView textView1 = (TextView) views.get(1);
        textView1.setText(contacts.get(0).getName());
        blurImage(contacts.get(0).getBlurred(), (ImageView) views.get(2));

        ((ImageView) views.get(3)).setImageResource(contacts.get(1).getImage());
        TextView textView2 = (TextView) views.get(4);
        textView2.setText(contacts.get(1).getName());
        blurImage(contacts.get(1).getBlurred(), ((ImageView) views.get(5)));

        if (contacts.get(0).getId() == 1 && drawableList.get(2).getConstantState() != drawableList.get(3).getConstantState()) {
            textView1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_mic_off_24, 0);
            textView2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_mic_24, 0);
        } else if (drawableList.get(2).getConstantState() != drawableList.get(3).getConstantState()) {
            textView1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_mic_24, 0);
            textView2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_mic_off_24, 0);
        }

        return views;
    }

    public List<View> setContact(final List<Contact> contacts, List<View> views) {
        for (int i = 0; i < contacts.size(); ++i) {
            if (contacts.get(i).getId() == 1) {
                int index = i * (i+2);
                Log.d("MYTAG", "i: " + i + " index: " + index);
                ((ImageView) views.get(index)).setImageResource(contacts.get(i).getImage());
                ((TextView) views.get(index+1)).setText(contacts.get(i).getName());
                blurImage(contacts.get(i).getImage(), ((ImageView) views.get(index+2)));
            }
        }
        return views;
    }

    public void blurImage(int drawableId, ImageView image) {
        MultiTransformation<Bitmap> transformation;
        transformation = new MultiTransformation<>(new BlurTransformation(25, 3),
                new ColorFilterTransformation(ContextCompat.getColor(this, R.color.colorDarkOverlay)));
        Glide.with(this).load(drawableId)
                .apply(RequestOptions.bitmapTransform(transformation)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(image);
    }

}