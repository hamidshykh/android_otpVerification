package com.hamid.sindh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    EditText phnnum;
    Button phnbtn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phnnum = findViewById(R.id.phnnum);
        phnbtn = findViewById(R.id.phnbtn);
        progressBar = findViewById(R.id.probar1);

        phnbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!phnnum.getText().toString().trim().isEmpty()){
                    if ((phnnum.getText().toString().trim()).length() ==10){

                        progressBar.setVisibility(v.VISIBLE);
                        progressBar.setVisibility(v.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+92" + phnnum.getText().toString(), 60,
                                TimeUnit.SECONDS, MainActivity.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressBar.setVisibility(v.VISIBLE);
                                        progressBar.setVisibility(v.INVISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {

                                        progressBar.setVisibility(v.VISIBLE);
                                        progressBar.setVisibility(v.INVISIBLE);
                                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        Intent intent = new Intent(getApplicationContext(),Verification.class);
                                        // intent.putExtra("mobile",phnnum.getText().toString());
                                        intent.putExtra("backendotp",backendotp);
                                        startActivity(intent);
                                    }
                                }
                        );




                    }else{
                        Toast.makeText(MainActivity.this, "Please Enter Correct Number", Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(MainActivity.this, "Enter Mobile NUmber", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}