package com.shubham.firebasedatabase.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.shubham.firebasedatabase.RealtimeDatabase.MainActivity;
import com.shubham.firebasedatabase.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    String email, password;
    FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        sharedPreferences = LoginActivity.this.getSharedPreferences("login", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        mAuth = FirebaseAuth.getInstance();

        binding.gotologin.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), SignUpActivity.class));
            finish();
        });
        binding.btnLogin.setOnClickListener(v -> {

            email = binding.txtEmail.getText().toString().trim();
            password = binding.txtPassword.getText().toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Enter email and password both...", Toast.LENGTH_SHORT).show();
            } else {

                LoginUser(email, password);

            }

        });

    }

    private void LoginUser(String email, String password) {
        binding.mainLayout.setVisibility(View.GONE);
        binding.lottie.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    editor.putString("Email", email);
                    editor.apply();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    binding.mainLayout.setVisibility(View.VISIBLE);
                    binding.lottie.setVisibility(View.GONE);
                    finish();
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                } else {
                    binding.mainLayout.setVisibility(View.VISIBLE);
                    binding.lottie.setVisibility(View.GONE);

                    Toast.makeText(LoginActivity.this, "Enter Correct Details...", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(LoginActivity.this, new OnSuccessListener<AuthResult>() {
//            @Override
//            public void onSuccess(AuthResult authResult) {
//
//                editor.putString("Email", email);
//                editor.apply();
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                binding.mainLayout.setVisibility(View.VISIBLE);
//                binding.lottie.setVisibility(View.GONE);
//                finish();
//                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
//            }
//
//
//        });

    }
}