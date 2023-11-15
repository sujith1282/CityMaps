package com.example.citymaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BengaluruMap extends AppCompatActivity {
    Button distance,time;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bengaluru_map);


        distance=(Button) findViewById(R.id.button2);
        time=(Button) findViewById(R.id.button3);
        tv=(TextView)findViewById(R.id.textView5);


        distance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BengaluruMap.this, BngShortestDistance.class);
                i.putExtra("path",true);
                startActivity(i);

            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BengaluruMap.this, BngShortestDistance.class);
                i.putExtra("path",false);
                startActivity(i);
            }
        });
    }
}