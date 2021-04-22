package com.example.android.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android.splashscreen.Users.SendOTPSMS;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ForgetPasswordActivity extends AppCompatActivity {
    Button verify_btn;
    public static EditText codeVerify;
    ProgressBar progressBar;
    SendOTPSMS sendSMS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        verify_btn = findViewById(R.id.verify_btn);
        codeVerify = findViewById(R.id.verification_code_entered_by_user);
        progressBar = findViewById(R.id.progress_bar);
        requestPermissions();
        String phoneNo = getIntent().getStringExtra("phoneNo");
        sendSMS = new SendOTPSMS(phoneNo,codeVerify);
        sendSMS.activity = ForgetPasswordActivity.this;
        sendSMS.sendVerificationCodeToUser(phoneNo);
        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = codeVerify.getText().toString();
                if (code.isEmpty() || code.length() < 6) {
                    codeVerify.setError("Wrong OTP...");
                    codeVerify.requestFocus();
                    return;
                }else{
                    PhoneAuthCredential credential = sendSMS.verifyCode(code);
                    signInTheUserByCredentials(credential);
                }
                progressBar.setVisibility(View.VISIBLE);

            }
        });
    }
    private void requestPermissions() {
        if (ContextCompat.checkSelfPermission(ForgetPasswordActivity.this, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ForgetPasswordActivity.this,new String[]{
                    Manifest.permission.RECEIVE_SMS
            },100);
        }


    }
    private void signInTheUserByCredentials(PhoneAuthCredential credential) {
        sendSMS.activity = ForgetPasswordActivity.this;
        sendSMS.signInTheUserByCredentials(credential);
        int res = sendSMS.flag;
        System.out.println("res : " + res);
       if(res == 1){
           Toast.makeText(ForgetPasswordActivity.this, "Your Account has been created successfully!", Toast.LENGTH_SHORT).show();
           //Perform Your required action here to either let the user sign In or do something required
           Intent intent = new Intent(ForgetPasswordActivity.this, loginActivity.class);
           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
           startActivity(intent);
       }else{
           Toast.makeText(ForgetPasswordActivity.this, "Error", Toast.LENGTH_SHORT).show();
       }
    }
}