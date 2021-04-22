package com.example.android.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.android.splashscreen.Users.UserHelper;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    TextInputLayout fullname, username, email, phone, password;
    private String user_fullname, user_username, user_email, user_phone, user_password;
    static int userID = 0;
    Button submit;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    UserHelper helperclass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        fullname = (TextInputLayout) findViewById(R.id.reg_fullname);
        username = (TextInputLayout) findViewById(R.id.reg_username);
        email = (TextInputLayout) findViewById(R.id.reg_email);
        phone = (TextInputLayout) findViewById(R.id.reg_phoneNumber);
        password = (TextInputLayout) findViewById(R.id.reg_password);

        submit = (Button) findViewById(R.id.Go_btn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateFullName() | !validateUserName() | !validateEmail()
                        | !validatePhoneNumber() | !validatePassword()) {
                    return;
                }else {
                    rootNode = FirebaseDatabase.getInstance();
                    reference = rootNode.getReference("users");
                    /*user_fullname = fullname.getEditText().getText().toString().trim();
                    user_username = username.getEditText().getText().toString().trim();
                    user_email = email.getEditText().getText().toString().trim();
                    user_phone = phone.getEditText().getText().toString().trim();
                    user_password = password.getEditText().getText().toString().trim();*/
                    userID++;
                    String id = userID + "";
                    helperclass = new UserHelper(id,user_fullname, user_username,
                            user_email, user_phone, user_password);
                    reference.child(user_username).setValue(helperclass);
                    user_phone = phone.getEditText().getText().toString().trim();
                    Intent intent = new Intent(SignupActivity.this, ForgetPasswordActivity.class);
                    intent.putExtra("phoneNo", user_phone);
                    startActivity(intent);
                    /*fullname.getEditText().setText("");
                    username.getEditText().setText("");
                    email.getEditText().setText("");
                    phone.getEditText().setText("");
                    password.getEditText().setText("");*/

                }
            }
        });

    }



    private Boolean validateFullName() {
        user_fullname = fullname.getEditText().getText().toString().trim();
        if (user_fullname.isEmpty()) {
            fullname.setError("Field cannot be empty");
            return false;
        } else {
            fullname.setError(null);
            fullname.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateUserName() {
        user_username = username.getEditText().getText().toString().trim();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (user_username.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        }else if (user_username.length()>=15){
            username.setError("Username too long");
            return false;
        }else if(!user_username.matches(noWhiteSpace)) {
            username.setError("White spaces are not allowed");
            return false;
        }else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateEmail() {
        user_email = email.getEditText().getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (user_email.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!user_email.matches(emailPattern)) {
            email.setError("Invalid email address");
            return false;
        }
        else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePhoneNumber() {
        user_phone = phone.getEditText().getText().toString().trim();
        if (user_phone.isEmpty()) {
            phone.setError("Field cannot be empty");
            return false;
        } else {
            phone.setError(null);
            phone.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword() {
        user_password = password.getEditText().getText().toString().trim();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (user_password.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        }else if (!user_password.matches(passwordVal)) {
            password.setError("Password is too weak");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
}