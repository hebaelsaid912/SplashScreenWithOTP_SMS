package com.example.android.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Path;

public class loginActivity extends AppCompatActivity {
    TextInputLayout username , password;
    Button login;
    String name="" , pass="";
    TextView GO_TO_SIGNUP;
    ImageView imageView;
    TextView welcomeText , sloganText;
    private String idFromDB ,fullnameFromDB,usernameFromDB,emailFromDB,phoneFromDB , passwordFromDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (TextInputLayout)findViewById(R.id.username);
        password = (TextInputLayout)findViewById(R.id.password);
        GO_TO_SIGNUP = (TextView) findViewById(R.id.goto_signup);
        imageView = (ImageView) findViewById(R.id.image2);
        sloganText = (TextView) findViewById(R.id.sloganTV);
        welcomeText = (TextView) findViewById(R.id.textwellcome);

        login = findViewById(R.id.loginbtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUserName() | !validatePassword()) {
                    return;
                } else {
                    isUser();
                }
            }

        });

        GO_TO_SIGNUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this, SignupActivity.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View,String>(imageView,"logo_image");
                pairs[1] = new Pair<View,String>(welcomeText,"logo_text");
                pairs[2] = new Pair<View,String>(sloganText,"logo_desc");
                pairs[3] = new Pair<View,String>(username,"username_tran");
                pairs[4] = new Pair<View,String>(password,"password_tran");
                pairs[5] = new Pair<View,String>(GO_TO_SIGNUP,"login_signup_tran");
                pairs[6] = new Pair<View,String>(login,"submitbtn_tran");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(loginActivity.this , pairs);
                startActivity(intent,options.toBundle());
            }
        });
    }

    private void isUser(){
        name = username.getEditText().getText().toString().trim();
        pass = password.getEditText().getText().toString().trim();
        System.out.println("name " + name);
        System.out.println("pass " + pass);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(name);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    username.setError(null);
                    username.setErrorEnabled(false);
                    usernameFromDB = (String) snapshot.child(name).child("username").getValue(String.class);
                    passwordFromDB = (String) snapshot.child(name).child("password").getValue(String.class);
                    System.out.println("usernameFromDB " + usernameFromDB);
                    System.out.println("passwordFromDB " + passwordFromDB);
                    if (!passwordFromDB.equals(pass)){
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }else{
                        password.setError(null);
                        password.setErrorEnabled(false);
                        idFromDB = snapshot.child(usernameFromDB).child("id").getValue(String.class);
                        fullnameFromDB = snapshot.child(usernameFromDB).child("fullname").getValue(String.class);
                        usernameFromDB = snapshot.child(usernameFromDB).child("username").getValue(String.class);
                        emailFromDB = snapshot.child(usernameFromDB).child("email").getValue(String.class);
                        phoneFromDB = snapshot.child(usernameFromDB).child("phone").getValue(String.class);
                        Intent intent = new Intent(loginActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("id",idFromDB);
                        intent.putExtra("fullname",fullnameFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("phone", phoneFromDB);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    username.setError("No such user exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private Boolean validateUserName() {
        name = username.getEditText().getText().toString().trim();
        if (name.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        }else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword() {
        pass = password.getEditText().getText().toString().trim();
        if (pass.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        }else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }

}