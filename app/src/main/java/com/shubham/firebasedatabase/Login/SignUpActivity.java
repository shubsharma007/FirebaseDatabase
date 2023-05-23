package com.shubham.firebasedatabase.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.shubham.firebasedatabase.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    String email, password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        binding.gotosignup.setOnClickListener(v1 -> {
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });
        binding.btnSignUp.setOnClickListener(v -> {


            email = binding.txtEmail.getText().toString().trim();
            password = binding.txtPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Enter email and password both...", Toast.LENGTH_SHORT).show();
            } else {
                SignUp(email, password);
            }
        });
    }

    private void SignUp(String email, String password) {
        binding.mainLayout.setVisibility(View.GONE);
        binding.lottie.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    binding.mainLayout.setVisibility(View.VISIBLE);
                    binding.lottie.setVisibility(View.GONE);
                    finish();
                    Toast.makeText(SignUpActivity.this, "Successful SignUp Please Login...", Toast.LENGTH_SHORT).show();
                } else {
                    binding.mainLayout.setVisibility(View.VISIBLE);
                    binding.lottie.setVisibility(View.GONE);
                    Toast.makeText(SignUpActivity.this, "Sorry We Can't Do This...", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}