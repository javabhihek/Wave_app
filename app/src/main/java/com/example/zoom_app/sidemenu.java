package com.example.zoom_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class sidemenu extends AppCompatActivity {
    TextView txt_home,txt_contact,txt_userprofile,txt_logout,txt_gallary,txt_setting,txt_rateus,txt_share,txt_name;

    FirebaseAuth auth= FirebaseAuth.getInstance();
    FirebaseUser user=auth.getCurrentUser();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidemenu);

        txt_home=findViewById(R.id.txt_home);
        txt_contact=findViewById(R.id.txt_contact);
        txt_gallary=findViewById(R.id.txt_gallery);
        txt_setting=findViewById(R.id.txt_setting);
        txt_logout=findViewById(R.id.txt_logout);
        txt_rateus=findViewById(R.id.txt_rateus);
        txt_share=findViewById(R.id.txt_share);
        txt_userprofile=findViewById(R.id.txt_userprofile);

         txt_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(sidemenu.this,Welcome_page.class);
                startActivity(i);
            }
        });
        txt_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(sidemenu.this,SettingScreen.class);
                startActivity(i);
            }
        });
        txt_userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(sidemenu.this,User_profile.class);
                startActivity(i);
            }
        });
        txt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                startActivity(new Intent(sidemenu.this,Signin.class));
                finish();
            }
        });

    }

}


