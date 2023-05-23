package com.shubham.firebasedatabase.RealtimeDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.shubham.firebasedatabase.R;
import com.shubham.firebasedatabase.Model.UserModel;
import com.shubham.firebasedatabase.databinding.ActivityAllUserBinding;

import java.util.ArrayList;

public class AllUserActivity extends AppCompatActivity {

    ActivityAllUserBinding binding;
    UserModel userModel = null;
    ArrayList<String> a;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayAdapter<String> adapter;
    private static final Object lockObject = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        binding.btnRemoveAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                firebaseDatabase.getReference().removeValue();
                a.clear();
                adapter.notifyDataSetChanged();
                Toast.makeText(AllUserActivity.this, "Remove All", Toast.LENGTH_SHORT).show();

            }
        });
        getSupportActionBar().hide();
        a = new ArrayList<>();
        adapter = new ArrayAdapter<>(AllUserActivity.this, R.layout.list_items, R.id.showName, a);
        binding.list.setAdapter(adapter);
        binding.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                a.remove(position);
//                String pos= String.valueOf(position);
//                firebaseDatabase.getReference(pos).removeValue();
//                adapter.notifyDataSetChanged();
//                firebaseDatabase.notify();

            }
        });
        FirebaseDatabase.getInstance().getReference().child("Friends").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    a.clear();
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        userModel = snapshot1.getValue(UserModel.class);
                        String t = "Name:-" + userModel.getName() + "\n" + "Age:-" + userModel.getAge() + "\n" + "Salary:-" + userModel.getSalary();
                        a.add(t);

                        Log.e("Name Hai", t);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}