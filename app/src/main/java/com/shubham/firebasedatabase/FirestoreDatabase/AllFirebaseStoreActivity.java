package com.shubham.firebasedatabase.FirestoreDatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.shubham.firebasedatabase.Adapter.FirebaseStoreAdapter;
import com.shubham.firebasedatabase.databinding.ActivityAllFirebaseStoreBinding;

import java.util.ArrayList;

public class AllFirebaseStoreActivity extends AppCompatActivity {

    ActivityAllFirebaseStoreBinding binding;
    FirebaseStoreAdapter adapter;
    ArrayList<String> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllFirebaseStoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        nameList = new ArrayList<>();
        binding.btnRemoveAll.setOnClickListener(v -> {

            Toast.makeText(this, "Sorry this function is not available right now", Toast.LENGTH_SHORT).show();

        });
        FirebaseFirestore.getInstance().collection("Friends").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                nameList.clear();
                for (DocumentSnapshot s : value) {
                    nameList.add("Name:-" + s.getString("name") + "\n" + "Age:-" + s.getString("age") + "\n" + "Salary:-" +
                            s.getString("salary"));

                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(AllFirebaseStoreActivity.this));
                    binding.recyclerView.setAdapter(new FirebaseStoreAdapter(AllFirebaseStoreActivity.this, nameList));

                }
            }
        });

    }
}