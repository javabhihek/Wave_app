package com.example.zoom_app;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingScreen extends AppCompatActivity {

    Button backfromsetting,gotogeleral,gotoaccessibility,gotovesion,gotofeedback,gotoabout,gotorate,gotoprivacy,gototerms,gotocommunity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);

//        backfromsetting=findViewById(R.id.backfromsetting);
        gotogeleral=findViewById(R.id.gotogeleral);
        gotoaccessibility=findViewById(R.id.gotoaccessibility);
        gotovesion=findViewById(R.id.gotovesion);
        gotofeedback=findViewById(R.id.gotofeedback);
        gotoabout=findViewById(R.id.gotoabout);
        gotorate=findViewById(R.id.gotorate);
        gotoprivacy=findViewById(R.id.gotoprivacy);
        gototerms=findViewById(R.id.gototerms);
        gotocommunity=findViewById(R.id.gotocommunity);

//        backfromsetting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(SettingScreen.this,MainActivity.class);
//                startActivity(i);
//            }
//        });
//
//        backfromsetting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(SettingScreen.this,MainActivity.class);
//                startActivity(i);
//            }
//        });

        gotogeleral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SettingScreen.this,MainActivity.class);
                startActivity(i);
            }
        });

        gotoaccessibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SettingScreen.this,MainActivity.class);
                startActivity(i);
            }
        });

        gotovesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SettingScreen.this,MainActivity.class);
                startActivity(i);
            }
        });

        gotofeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SettingScreen.this,MainActivity.class);
                startActivity(i);
            }
        });

        gotoabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SettingScreen.this,MainActivity.class);
                startActivity(i);
            }
        });

        gotorate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SettingScreen.this,MainActivity.class);
                startActivity(i);
            }
        });

        gotoprivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SettingScreen.this,MainActivity.class);
                startActivity(i);
            }
        });

        gototerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SettingScreen.this,MainActivity.class);
                startActivity(i);
            }
        });

        gotocommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(SettingScreen.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
    @Override
    public void onBackPressed() {
        if(SettingScreen.isDrawerOpen(GravityCompat.START))
        {
            SettingScreen.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    private static void closeDrawer(int start) {
    }

    private static boolean isDrawerOpen(int start) {
        return false;
    }
}