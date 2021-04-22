package com.example.android.splashscreen.Users;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.android.splashscreen.ForgetPasswordActivity;
import com.example.android.splashscreen.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SendOTPSMS  extends BroadcastReceiver {
    private static String phoneNo = "";
    private String mVerificationID;
    private FirebaseAuth mAuth;
    public  int flag = 0;
   public Activity activity ;
    public String FinalCode;
    EditText editText_otp;
    public SendOTPSMS(String phoneNo , EditText editText_otp) {
        this.phoneNo = phoneNo;
        mAuth = FirebaseAuth.getInstance();
        this.editText_otp = editText_otp;
    }
    public static String getPhoneNo() {
        return phoneNo;
    }

    public static void setPhoneNo(String phoneNo) {
        SendOTPSMS.phoneNo = phoneNo;
    }

    public void sendVerificationCodeToUser(String phoneNo) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+20" + phoneNo)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(activity)                 // Activity (for callback binding)
                        .setCallbacks(mCallback)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onCodeSent(@NonNull String s,
                                       @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    mVerificationID = s;

                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    String code = phoneAuthCredential.getSmsCode();
                    if (code != null) {
                        FinalCode = code;
                        verifyCode(code);
                        System.out.println("FinalCode : " + FinalCode);
                    }

                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {
                    System.out.println(e.getMessage());
                    Log.e("FireBase Messages :" , e.getMessage());
                }
            };

    public PhoneAuthCredential verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationID, code);
        return credential;
    }

    public void signInTheUserByCredentials(PhoneAuthCredential credential) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithCredential(credential)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            flag = 1;
                        } else {
                            flag = 0;
                            System.out.println(task.getException().getMessage());
                        }
                    }
                });
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        for (SmsMessage smsMessage : smsMessages){
            String message_body = smsMessage.getMessageBody();
            String getOTP = message_body.split(":")[1];
            ForgetPasswordActivity.codeVerify.setText(FinalCode);
            editText_otp.setText(getOTP);
        }
    }
}
