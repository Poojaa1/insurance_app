package com.example.licapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class final_contact extends AppCompatActivity {
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_contact);
        listView=findViewById(R.id.listView);
        String[] values=new String[]{
                "Call Us","Email Us","Find Us"
        };
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,android.R.id.text1,values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    Intent intent=new Intent(view.getContext(),callus.class);
                    startActivity(intent);
                }
                if (position==1)
                {
                    Intent intent1=new Intent(view.getContext(),mailus.class);
                    startActivity(intent1);
                }
                if(position==2)
                {
                    Intent intent2=new Intent(view.getContext(),findus.class);
                    startActivity(intent2);
                }

            }
        });


    }
}