package com.example.licapp.adduser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.licapp.FeedBackData;
import com.example.licapp.R;
import com.example.licapp.callus;
import com.example.licapp.comment;
import com.example.licapp.findus;
import com.example.licapp.mailus;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Adduser extends AppCompatActivity {
    private ImageView add;
private EditText addName;
    private EditText Email;
    private EditText MobileNumber,Age;
private Spinner addUserPlan;
private Button addNameButton;
private String category;
private Bitmap bitmap=null;
private String name,email,age,phonenumber,downloadUrl="";
private ProgressDialog pd;
private StorageReference storageReference;
private DatabaseReference reference,dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adduser);
        add=findViewById(R.id.add);
        addName=findViewById(R.id.addName);
        MobileNumber=findViewById(R.id.MobileNumber);
        Age=findViewById(R.id.Age);
        Email=findViewById(R.id.Email);
pd=new ProgressDialog(this);
        addUserPlan=findViewById(R.id.addUserPlan);
        addNameButton=findViewById(R.id.addNameButton);
        reference= FirebaseDatabase.getInstance().getReference().child("User");
        storageReference= FirebaseStorage.getInstance().getReference();
         String[] planss=new String[]{"Select plans","Child plan","PensionPlan","EndowmentPlan","HelathPlan"};
        addUserPlan.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,planss));
        addUserPlan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category=addUserPlan.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        addNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkVadilation();
            }

            private void checkVadilation() {
                name=addName.getText().toString();
                email=Email.getText().toString();
                age=Age.getText().toString();
                phonenumber=MobileNumber.getText().toString();
                if(name.isEmpty())
                {
                    addName.setError("Empty");
                    addName.requestFocus();
                }else if(email.isEmpty())
                {
                    Email.setError("Empty");
                    Email.requestFocus();
                }else if(age.isEmpty())
                {
                    Age.setError("Empty");
                    Age.requestFocus();
                }else if(phonenumber.isEmpty())
                {
                    MobileNumber.setError("Empty");
                    MobileNumber.requestFocus();
                }else if(category.equals("Select plans")){
                    Toast.makeText(Adduser.this,"Please Provide Your Plan",Toast.LENGTH_SHORT).show();
                }else
                {
                    insertData();
                }

            }

            private void insertData() {
                dbRef=reference.child("Category");
                final String uniqueKey=dbRef.push().getKey();

                Calendar calforDate=Calendar.getInstance();
                SimpleDateFormat currentDate=new SimpleDateFormat("dd-MM-yy");
                String Date=currentDate.format(calforDate.getTime());
                Calendar calforTime=Calendar.getInstance();
                SimpleDateFormat currentTime=new SimpleDateFormat("hh:mm a");
                String time=currentTime.format(calforTime.getTime());
                UserData userData=new UserData(name,email,uniqueKey,age,phonenumber,time,Date);
                dbRef.child(uniqueKey).setValue(userData).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        pd.dismiss();
                        Toast.makeText(Adduser.this,"User Modified",Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(Adduser.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            });
        }

}
