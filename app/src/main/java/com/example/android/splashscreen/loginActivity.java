package com.example.android.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class loginActivity extends AppCompatActivity {
    TextInputLayout username , password;
    Button login;
    String name="" , pass="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (TextInputLayout)findViewById(R.id.username);
        password = (TextInputLayout)findViewById(R.id.password);

        login = findViewById(R.id.loginbtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = username.getEditText().getText().toString().trim();
                pass = password.getEditText().getText().toString().trim();
                if(!name.isEmpty() && !pass.isEmpty()) {
                    System.out.println("name " + name);
                    System.out.println("pass " + pass);
                    if (name.equals("heba") && pass.equals("12345")) {
                        Intent intent = new Intent(loginActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    }
                }else{
                    System.out.println("No data ");
                }
            }
        });


    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }

}