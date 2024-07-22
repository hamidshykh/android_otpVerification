package com.hamid.sindh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class Verification extends AppCompatActivity {

    EditText et1,et2,et3,et4,et5,et6;
    Button btn;
    String getotpbackend;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        et1 = findViewById(R.id.otp1);
        et2 = findViewById(R.id.otp2);
        et3 = findViewById(R.id.otp3);
        et4 = findViewById(R.id.otp4);
        et5 = findViewById(R.id.otp5);
        et6 = findViewById(R.id.otp6);
        btn = findViewById(R.id.verifybtn);
        progressBar = findViewById(R.id.probar2);

        getotpbackend = getIntent().getStringExtra("backendotp");



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et1.getText().toString().trim().isEmpty() && !et2.getText().toString().trim().isEmpty()
                        && !et3.getText().toString().trim().isEmpty()
                        && !et4.getText().toString().trim().isEmpty()
                        && !et5.getText().toString().trim().isEmpty()
                        && !et6.getText().toString().trim().isEmpty()){

                    String getuserotp = et1.getText().toString() +
                            et2.getText().toString() +
                            et3.getText().toString() +
                            et4.getText().toString() +
                            et5.getText().toString() +
                            et6.getText().toString() ;
                    if ( getotpbackend != null){
                        progressBar.setVisibility(v.VISIBLE);
                        progressBar.setVisibility(v.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(getotpbackend,getuserotp);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                progressBar.setVisibility(v.GONE);
                                progressBar.setVisibility(v.VISIBLE);

                                if (task.isSuccessful()){
                                    Intent intent = new Intent(getApplicationContext(),dashboard.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }else {
                                    Toast.makeText(Verification.this, "Enter Correct Otp", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                    }else{
                        Toast.makeText(Verification.this, "Please Check Internet", Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(Verification.this, "Please Fill All Numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });

        moveNumToNext();

    }

    private void moveNumToNext() {

        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty() ){
                    et2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty() ){
                    et3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty() ){
                    et4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty() ){
                    et5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!s.toString().trim().isEmpty() ){
                    et6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
 }
