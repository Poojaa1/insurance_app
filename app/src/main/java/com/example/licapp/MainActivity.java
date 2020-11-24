package com.example.licapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.licapp.adduser.Adduser;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   CardView uploadNotice;
   CardView About;
   CardView Plans;
   CardView Contact,signin;
   CardView Plan_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uploadNotice=findViewById(R.id.feedback);
uploadNotice.setOnClickListener(this);
        About=findViewById(R.id.aboutus);
        About.setOnClickListener(this);
        Plans=findViewById(R.id.pans);
        Plans.setOnClickListener(this);
        Contact=findViewById(R.id.plans);
        Contact.setOnClickListener(this);
        signin=findViewById(R.id.signin);
        signin.setOnClickListener(this);
        Plan_details=findViewById(R.id.Plan_details);
        Plan_details.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent;

        switch (v.getId()){
    case R.id.feedback:
        intent=new Intent(MainActivity.this,comment.class);
startActivity(intent);
break;
    case R.id.aboutus:
  intent=new Intent(MainActivity.this,about_us.class);
    startActivity(intent);
    break;
            case R.id.pans:
                intent=new Intent(MainActivity.this,Premium_plans.class);
                startActivity(intent);
                break;
            case R.id.plans:
                intent=new Intent(MainActivity.this,final_contact.class);
                startActivity(intent);
                break;
            case R.id.signin:
                intent=new Intent(MainActivity.this, Adduser.class);
                startActivity(intent);
                break;
            case R.id.Plan_details:
                intent=new Intent(MainActivity.this, plandetails.class);
                startActivity(intent);
                break;


}
    }

}
