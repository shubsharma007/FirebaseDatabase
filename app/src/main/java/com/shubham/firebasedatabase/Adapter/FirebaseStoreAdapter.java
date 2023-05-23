package com.shubham.firebasedatabase.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;
import com.shubham.firebasedatabase.R;

import java.util.ArrayList;
import java.util.List;

public class FirebaseStoreAdapter extends RecyclerView.Adapter<FirebaseStoreAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> nameList;
    FirebaseStoreAdapter firebaseStoreAdapter;


    public FirebaseStoreAdapter(Context context, ArrayList<String> nameList) {
        this.context = context;
        this.nameList = nameList;

    }

    @NonNull
    @Override
    public FirebaseStoreAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FirebaseStoreAdapter.MyViewHolder holder, int position) {
        String pos = String.valueOf(position);
        holder.showName.setText(nameList.get(position));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView showName;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            showName = itemView.findViewById(R.id.showName);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
