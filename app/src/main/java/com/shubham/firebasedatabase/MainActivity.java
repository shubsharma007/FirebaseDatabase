package com.shubham.firebasedatabase;

import static java.lang.String.valueOf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.shubham.firebasedatabase.databinding.ActivityMainBinding;

import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String txtName;
    String txtAge;
    String txtSalary;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));
        sharedPreferences = MainActivity.this.getSharedPreferences("login", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        binding.btnGoToFireStore.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), FireStoreActivity.class));
            finish();
        });
        binding.btnShowUser.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AllUserActivity.class));
        });
        binding.btnSubmit.setOnClickListener(v -> {

            txtName = binding.txtName.getText().toString();
            txtAge = binding.txtAge.getText().toString();
            txtSalary = binding.txtSalary.getText().toString();

            if (TextUtils.isEmpty(txtName) || TextUtils.isEmpty(txtAge) || TextUtils.isEmpty(txtSalary)) {
                Toast.makeText(this, "Enter All Details...", Toast.LENGTH_SHORT).show();

            } else {
                binding.mainLayout.setVisibility(View.GONE);
                binding.lottie.setVisibility(View.VISIBLE);
                HashMap<String, Object> m = new HashMap<String, Object>();

                m.put("name", txtName);
                m.put("age", txtAge);
                m.put("salary", txtSalary);
                Toast.makeText(this, "User Added Successful...", Toast.LENGTH_SHORT).show();
                FirebaseDatabase.getInstance().getReference().child("Friends").push().setValue(m);
                binding.mainLayout.setVisibility(View.VISIBLE);
                binding.lottie.setVisibility(View.GONE);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (R.id.menu_setting == item.getItemId()) {
            Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();

        } else if (R.id.menu_logout == item.getItemId()) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Logout");
            dialog.setCancelable(false);
            dialog.setMessage("Are you sure");


            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    editor.remove("Email");
                    editor.apply();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_SHORT).show();

                    finish();

                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog alertDialog = dialog.create();
            alertDialog.show();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

}