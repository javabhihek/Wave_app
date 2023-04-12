package com.example.zoom_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class User_profile extends AppCompatActivity {
    private final int cam_req_code=100;
    private final int gallery_req_code=1000;
    TextView name_p,email_p,mobile_p,gender_p, dob_p,txt_setting;
    ImageView icam,profile_picture,cng_profile_picture;


    FirebaseAuth auth= FirebaseAuth.getInstance();
    FirebaseUser user=auth.getCurrentUser();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        email_p=findViewById(R.id.email_p);
        profile_picture=findViewById(R.id.profile_picture);
        cng_profile_picture=findViewById(R.id.cng_profile_picture);

        name_p=findViewById(R.id.name_p);
        txt_setting=findViewById(R.id.txt_setting);

        cng_profile_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent icam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(icam, cam_req_code);
            }
        });

        profile_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent igallery = new Intent(Intent.ACTION_PICK);
                igallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(igallery, gallery_req_code);
            }
        });

        txt_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(User_profile.this,SettingScreen.class);
                startActivity(i);
            }
        });

        if (user !=null){
            name_p.setText(user.getDisplayName());
            email_p.setText(user.getEmail());
//            mobile_p.setText(user.getPhoneNumber());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RESULT_OK){
            if(requestCode==cam_req_code){
                // for camera

                Bitmap img = (Bitmap)data.getExtras().get("data");
                icam.setImageBitmap(img);
            }if(requestCode==gallery_req_code){
                //for gallery

                profile_picture.setImageURI(data.getData());
            }
        }
    }
    protected void onStart() {
        super.onStart();
        if (user == null){
            startActivity(new Intent(this,Signin.class));
            finish();
        }
    }

}