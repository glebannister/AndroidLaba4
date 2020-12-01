package com.example.lab3tav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class SignUpActivity extends AppCompatActivity {

    private AdView mAdView;
    private EditText editMail, editPassword;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

    }

    private void init(){
        editMail = findViewById(R.id.editMail);
        editPassword = findViewById(R.id.editPassword);
        mAuth = FirebaseAuth.getInstance();
    }

    public void onClickSignUp(View view){
        if (!TextUtils.isEmpty(editMail.getText().toString()) && !TextUtils.isEmpty(editPassword.getText().toString())){
            mAuth.createUserWithEmailAndPassword(editMail.getText().toString(), editPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                    }else{
                        Toast.makeText(getApplicationContext(), "При регистрации возникла ошибка", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }else if(TextUtils.isEmpty(editMail.getText().toString())){
            editMail.setError("Введите почту");
        } else if(TextUtils.isEmpty(editPassword.getText().toString())){
            editPassword.setError("Введите пароль");
        }


    }

    public void moveToLogIn(View view){

        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);

    }
}