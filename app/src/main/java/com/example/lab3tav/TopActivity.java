package com.example.lab3tav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    private List<Top> listTemp;
    private DatabaseReference mDataBase;
    private String TOP_KEY = "Top";
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        listView = findViewById(R.id.listView);

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

        getDataFromDB();
        setOnClickItem();

    }

    private void init(){

        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);

        listView.setAdapter(adapter);
        mDataBase = FirebaseDatabase.getInstance().getReference(TOP_KEY);




    }

    private void getDataFromDB(){

        ValueEventListener vListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(listData.size() > 0)listData.clear();
                if(listTemp.size() > 0)listTemp.clear();


                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Top top = ds.getValue(Top.class);
                    assert top != null;
                    listData.add(top.userName);
                    listTemp.add(top);
                }
                Collections.reverse(listData);
                Collections.reverse(listTemp);
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        mDataBase.orderByChild("points").limitToLast(10).addValueEventListener(vListener);

    }

    private void setOnClickItem(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Top top = listTemp.get(position);

                Intent i = new Intent(TopActivity.this, ShowActivity.class);
                i.putExtra(Constant.USER_NAME, top.userName);
                i.putExtra(Constant.POINTS, top.points);
                startActivity(i);

            }
        });
    }

    public void onClickBack1(View view){
        Intent in = new Intent(getApplicationContext(),RecultActivity.class);
        startActivity(in);
    }
}