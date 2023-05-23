package com.shubham.firebasedatabase.FirestoreDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.shubham.firebasedatabase.RealtimeDatabase.MainActivity;
import com.shubham.firebasedatabase.databinding.ActivityFireStoreBinding;

import java.util.HashMap;
import java.util.Map;

public class FireStoreActivity extends AppCompatActivity {

    ActivityFireStoreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFireStoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        binding.btnGoToRealtimeDatabase.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        });
        binding.btnShowUser.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AllFirebaseStoreActivity.class));
        });
        binding.btnFireSubmit.setOnClickListener(v -> {

            Map<String, String> V = new HashMap<>();
//            Map<String, Float> S = new HashMap<>();

//            S.put("name", Float.valueOf(binding.txtSalary.getText().toString()));

            V.put("name", binding.txtName.getText().toString());
            V.put("age", binding.txtAge.getText().toString());
            V.put("salary", binding.txtSalary.getText().toString());

            FirebaseFirestore.getInstance().collection("Friends").add(V).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    Toast.makeText(FireStoreActivity.this, "Submitted", Toast.LENGTH_SHORT).show();

                }
            });

        });

    }
}