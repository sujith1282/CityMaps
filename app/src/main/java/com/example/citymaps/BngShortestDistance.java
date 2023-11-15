package com.example.citymaps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class BngShortestDistance extends AppCompatActivity {
    Button cal;
    Spinner spin1,spin2;
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bng_shortest_distance);

        Intent intent=getIntent();
        boolean pathCheck=(boolean)intent.getSerializableExtra("path");

        createMap();

        spin1=(Spinner) findViewById(R.id.spinner3);
        spin2=(Spinner) findViewById(R.id.spinner4);

        cal=(Button) findViewById(R.id.button4);
        tv1=(TextView) findViewById(R.id.textView6);
        tv2=(TextView) findViewById(R.id.textView7);


        ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, vertices);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin1.setAdapter(aa);
        spin2.setAdapter(aa);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String src = spin1.getSelectedItem().toString();
                String dest = spin2.getSelectedItem().toString();


                if (parent.get(src) == null || parent.get(dest) == null) {
                    tv1.setText("Enter correct place");
                } else {
                    if(pathCheck) {
                        dijkstraD(src, dest, adj, parent, distances);
                    }
                    else{
                        dijkstraT(src,dest,adj,parent,distances);
                    }


                    StringBuffer path = new StringBuffer();
                    String child = dest;
                    path.insert(0, child);
                    child = parent.get(child);

                    while (!child.equals(src)) {
                        path.insert(0, child + " -> "); // Insert at the beginning
                        child = parent.get(child);
                    }

                    path.insert(0, src + " -> "); // Insert source node at the beginning

                    if(pathCheck) {
                        tv1.setText("Shortest Distance Path: " + path.toString());


                        tv2.setText("Shortest Distance:" + distances.get(dest));
                    }
                    else{
                        tv1.setText("Shortest Time Path: " + path.toString());


                        tv2.setText("Shortest Time:" + distances.get(dest));
                    }
                }
            }
        });



    }



    public class pair{
        String place;
        int dist;
        int time;
        pair(String x,int y,int z){
            this.place=x;
            this.dist=y;
            this.time=z;
        }
    }

    HashMap<String, List<pair>> adj=new HashMap<>();
    ArrayList<String> vertices=new ArrayList<>();
    HashMap<String,String> parent=new HashMap<>();
    HashMap<String,Integer> distances=new HashMap<>();


    public void addVertex(String p){
        adj.put(p,new ArrayList<>());
        vertices.add(p);
        parent.put(p,"");
        distances.put(p,Integer.MAX_VALUE);
    }

    public void addEdge(String u,String v,int d,int t){
        pair uToV=new pair(v,d,t);
        pair vToU=new pair(u,d,t);

        adj.get(u).add(uToV);
        adj.get(v).add(vToU);
    }

    public void createMap(){
        addVertex("Shamshabad");
        addVertex("RGI Airport");
        addVertex("Aram Ghar");
        addVertex("Mehdipatnam");
        addVertex("Afzul Gunj");
        addVertex("Uppal");
        addVertex("LB Nagar");

        addEdge("Shamshabad","RGI Airport",5,20);
        addEdge("Shamshabad","Aram Ghar",7,15);
        addEdge("Aram Ghar","Mehdipatnam",5,25);
        addEdge("Aram Ghar","Afzul Gunj",13,30);
        addEdge("Aram Ghar","LB Nagar",17,35);
        addEdge("LB Nagar","Uppal",8,25);
        addEdge("Mehdipatnam","Afzul Gunj",6,30);


    }

    public class setBDType{
        int dist;
        String place;
        setBDType(int d,String p){
            this.dist=d;
            this.place=p;
        }
    }


    public void dijkstraD(String src, String dest, HashMap<String, List<pair>> adj, HashMap<String,String> parent, HashMap<String,Integer> distances){
        PriorityQueue<setBDType> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.dist));
        distances.put(src,0);
        pq.add(new setBDType(0,src));
        while(!pq.isEmpty()){
            setBDType top=pq.poll();
            String p=top.place;
            int d=top.dist;

            for(pair nbr : adj.get(p)){
                if(d+nbr.dist<distances.get(nbr.place)){
                    distances.put(nbr.place,d+nbr.dist);
                    parent.put(nbr.place,p);

                    pq.add(new setBDType(d+nbr.dist,nbr.place));
                }
            }

        }
    }

    public class setTType{
        int time;
        String place;
        setTType(int t,String p){
            this.time=t;
            this.place=p;
        }
    }


    public void dijkstraT(String src, String dest, HashMap<String, List<pair>> adj, HashMap<String,String> parent, HashMap<String,Integer> distances){
        PriorityQueue<setTType> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.time));
        distances.put(src,0);
        pq.add(new setTType(0,src));
        while(!pq.isEmpty()){
            setTType top=pq.poll();
            String p=top.place;
            int t=top.time;

            for(BngShortestDistance.pair nbr : adj.get(p)){
                if(t+nbr.dist<distances.get(nbr.place)){
                    distances.put(nbr.place,t+nbr.time);
                    parent.put(nbr.place,p);

                    pq.add(new setTType(t+nbr.time,nbr.place));
                }
            }

        }
    }

}