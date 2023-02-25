package com.example.itdivingcase;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.w3c.dom.Text;

import java.util.Arrays;

public class GridAdapter extends BaseAdapter {
    Context context;
    String[] names;
    int[] icons;
    int[] images;
//    int[] size;

    LayoutInflater inflater;

    public GridAdapter(Context context, String[] names, int[] icons, int[] images) {
        this.context = context;
        this.names = names;
        this.icons = icons;
        this.images = images;
//        this.size = size;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.gridview_item, null);
        }

        LinearLayout container = convertView.findViewById(R.id.gridview_container);
        ImageView imageView = convertView.findViewById(R.id.gridview_image);
        TextView textView = convertView.findViewById(R.id.gridview_text);

        imageView.setImageResource(images[position]);
        textView.setText(names[position]);
        container.setBackgroundColor(icons[position]);

        return convertView;
    }

    public void updateItems(String[] names, int[] icons, int[] images){
        this.names = names;
        this.icons = icons;
        this.images = images;
        notifyDataSetChanged();
    }

    public void swapItems(){
        String tmpName = this.names[0];
        int tempImage = this.images[0];
        int tempColor = this.icons[0];
        this.names[0] = this.names[1];
        this.images[0] = this.images[1];
        this.icons[0] = this.icons[1];
        this.names[1] = tmpName;
        this.images[1] = tempImage;
        this.icons[1] = tempColor;
        notifyDataSetChanged();
    }
}
