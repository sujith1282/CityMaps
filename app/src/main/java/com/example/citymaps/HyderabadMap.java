package com.example.citymaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HyderabadMap extends AppCompatActivity  {

    Button distance,time;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hyderabad_map);


        distance=(Button) findViewById(R.id.dist);
        time=(Button) findViewById(R.id.time);
        tv=(TextView)findViewById(R.id.textView2);


        distance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HyderabadMap.this, HydShortestDistance.class);
                i.putExtra("path",true);
                startActivity(i);

            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HyderabadMap.this, HydShortestDistance.class);
                i.putExtra("path",false);
                startActivity(i);
            }
        });


    }








}

