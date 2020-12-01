package com.example.lab3tav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

public class ShowActivity extends AppCompatActivity {
    private TextView tvName, tvPoints;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
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
        init();
        getIntentMain();
    }

    private void init() {
        tvName = findViewById(R.id.tvName);
        tvPoints = findViewById(R.id.tvPoints);

    }
    private void getIntentMain(){
        Intent i = getIntent();
        if (i != null){
            tvName.setText(i.getStringExtra(Constant.USER_NAME));
            tvPoints.setText(i.getStringExtra(Constant.POINTS));
        }
    }

    public void onClickBack(View view){
        Intent in = new Intent(getApplicationContext(),TopActivity.class);
        startActivity(in);
    }
}