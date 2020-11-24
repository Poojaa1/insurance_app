package com.example.licapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class comment extends AppCompatActivity {
private CardView addImage;
private ImageView feedbackImageView;
private final int REQ=1;
private Bitmap bitmap;
private EditText feedbacktitle;
private Button uploadfeedback;
private DatabaseReference reference;
private StorageReference storageReference;
String downloadUrl="";
private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        reference= FirebaseDatabase.getInstance().getReference();
        storageReference= FirebaseStorage.getInstance().getReference();
        pd=new ProgressDialog(this);
        addImage =findViewById(R.id.uploadnotice);
        feedbackImageView=findViewById(R.id.feedbackImageView);
        feedbacktitle= findViewById(R.id.feedbacktitle);
        uploadfeedback=findViewById(R.id.uploadfeedback);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();

            }
        });
        uploadfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(feedbacktitle.getText().toString().isEmpty()) {
                    feedbacktitle.setError("Empty");
                    feedbacktitle.requestFocus();
                }else if(bitmap==null){
                    uploadData();
                }else{
                    uploadImage();
                }

            }
        });
    }

    private void uploadImage() {
        pd.setMessage("Uploading...");
        pd.show();
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalImg=baos.toByteArray();
        final StorageReference filePath;
        filePath= storageReference.child("Feddback").child(finalImg+"jpg");
        final UploadTask uploadTask=filePath.putBytes(finalImg);
        uploadTask.addOnCompleteListener(comment.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl=String.valueOf(uri);
                                    uploadData();
                                }
                            });
                        }
                    });
                }
                else
                {
                    pd.dismiss();
                    Toast.makeText(comment.this,"SomeThing Went wrong",Toast.LENGTH_SHORT);
                }
            }
        });
    }
    private void uploadData() {
reference=reference.child("Feedback");
final String uniqueKey=reference.push().getKey();
String title= feedbacktitle.getText().toString() ;
        Calendar calforDate=Calendar.getInstance();
        SimpleDateFormat currentDate=new SimpleDateFormat("dd-MM-yy");
String Date=currentDate.format(calforDate.getTime());
        Calendar calforTime=Calendar.getInstance();
        SimpleDateFormat currentTime=new SimpleDateFormat("hh:mm a");
        String time=currentTime.format(calforTime.getTime());
        FeedBackData feedbackdata=new FeedBackData(title,downloadUrl,Date,time,uniqueKey);
        reference.child(uniqueKey).setValue(feedbackdata).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                pd.dismiss();
                Toast.makeText(comment.this,"Feedback Uploaded",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
         Toast.makeText(comment.this,"Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void openGallery() {
        Intent pickImage= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage,REQ);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ && resultCode==RESULT_OK)
        {
            Uri uri=data.getData();
            try {
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            feedbackImageView.setImageBitmap(bitmap);
        }
    }
}