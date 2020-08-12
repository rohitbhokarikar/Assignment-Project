package com.assignment.com;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context context;
    private RecyclerViewClick recyclerViewClick;


    public MyAdapter(List<ListItem> listItems, Context context, RecyclerViewClick recyclerViewClick) {
        this.listItems = listItems;
        this.context = context;
        this.recyclerViewClick= recyclerViewClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final ListItem listItem = listItems.get(position);

        holder.name.setText(listItem.getName());
        holder.country.setText(" "+listItem.getCountry());
        holder.country.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_home_black_18dp, 0, 0, 0);
        holder.phone.setText(" "+listItem.getPhone());
        holder.phone.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_local_phone_black_18dp, 0, 0, 0);
        holder.dob.setText(" "+listItem.getDob());
        holder.dob.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_cake_black_18dp, 0, 0, 0);
        Picasso.get().load(listItems.get(position).getProfileImage()).into(holder.profileImage);
        holder.email.setText(listItem.getEmail());
        holder.email.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_mail_black_18dp, 0, 0, 0);


        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                holder.result.setVisibility(View.VISIBLE);
                holder.declinedButton.setVisibility(View.GONE);
                holder.result.setText("Member Accepted");
                holder.acceptButton.setVisibility(View.GONE);
            }
        });

        holder.declinedButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                holder.result.setVisibility(View.VISIBLE);
                holder.acceptButton.setVisibility(View.GONE);
                holder.result.setText("Member Declined");
                holder.declinedButton.setVisibility(View.GONE);

            }
        });
    }


    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView country;
        public TextView phone;
        public TextView dob;
        public ImageView profileImage;
        public Button acceptButton,declinedButton;
        public TextView result;
        public TextView email;


        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
            country = itemView.findViewById(R.id.textViewCountry);
            phone = itemView.findViewById(R.id.textViewPhone);
            dob = itemView.findViewById(R.id.textViewDOB);
            profileImage = itemView.findViewById(R.id.profileImage);
            acceptButton= itemView.findViewById(R.id.buttonAccept);
            declinedButton = itemView.findViewById(R.id.buttonDecline);
            result = itemView.findViewById(R.id.textViewResult);
            email =itemView.findViewById(R.id.textViewEmail);


        }
    }
}
