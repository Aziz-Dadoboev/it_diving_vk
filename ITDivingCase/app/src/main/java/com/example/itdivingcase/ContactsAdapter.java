package com.example.itdivingcase;

import android.content.Context;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private Context context;
    private List<Person> contacts;

    private SelectListener listener;
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView numberTextView;
        ImageView photoImageView;

        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.contact_name);
            numberTextView = itemView.findViewById(R.id.contact_number);
            photoImageView = itemView.findViewById(R.id.profile_photo);
            cardView = itemView.findViewById(R.id.recyclerview_item_ll);
        }
    }

    public ContactsAdapter(Context context, List<Person> contacts, SelectListener listener) {
        this.context = context;
        this.contacts = contacts;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.ViewHolder holder, int position) {
        holder.nameTextView.setText(contacts.get(position).getName());
        holder.numberTextView.setText(contacts.get(position).getNumber());
        holder.photoImageView.setImageResource(contacts.get(position).getImage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(contacts.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

}
