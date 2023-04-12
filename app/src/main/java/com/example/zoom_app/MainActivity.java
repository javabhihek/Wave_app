package com.example.zoom_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                startActivity(new Intent(MainActivity.this,Signin.class));
            }
        },2000);
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() !=null){
            startActivity(new Intent(this,Welcome_page.class));
            finish();
        }
    }
}