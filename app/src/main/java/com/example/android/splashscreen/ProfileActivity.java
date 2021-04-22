package com.example.android.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.splashscreen.Users.UserLoginData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class ProfileActivity extends AppCompatActivity {
    TextInputLayout fullname, username, email, phone, password;
    TextView profileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profileName = (TextView) findViewById(R.id.TV_display_name);
        fullname = (TextInputLayout) findViewById(R.id.prof_fullname);
        username = (TextInputLayout) findViewById(R.id.prof_username);
        email = (TextInputLayout) findViewById(R.id.prof_Email);
        phone = (TextInputLayout) findViewById(R.id.prof_phonenumber);
        showUserProfileData();
        FloatingActionButton fab = findViewById(R.id.email_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This feature will be add soon", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }
    private void showUserProfileData() {
        System.out.println("username " + UserLoginData.getUsername());
        System.out.println("fullname " + UserLoginData.getFullname());
        System.out.println("email " + UserLoginData.getEmail());
        System.out.println("phone " + UserLoginData.getPhone());
        profileName.setText(UserLoginData.getUsername());
        username.getEditText().setText(UserLoginData.getUsername());
        fullname.getEditText().setText(UserLoginData.getFullname());
        email.getEditText().setText(UserLoginData.getEmail());
        phone.getEditText().setText(UserLoginData.getPhone());
    }

    public void backToHome(View view) {
        Intent intent = new Intent(ProfileActivity.this , HomeActivity.class);
        startActivity(intent);
    }

    public void enableEdit(View view) {
        username.setEnabled(true);
        fullname.setEnabled(true);
        phone.setEnabled(true);
        email.setEnabled(true);
    }

    public void ubdateData(View view) {

    }
}