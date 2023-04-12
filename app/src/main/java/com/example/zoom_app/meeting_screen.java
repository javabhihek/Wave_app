package com.example.zoom_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;
import org.jitsi.meet.sdk.JitsiMeetUserInfo;

import java.net.MalformedURLException;
import java.net.URL;

public class meeting_screen extends AppCompatActivity {
    Button btn_join,btn_share;
    EditText edt_code;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_screen);
        btn_join=findViewById(R.id.btn_join);
        edt_code=findViewById(R.id.edt_code);
        btn_share=findViewById(R.id.btn_share);

        URL serverURL;
        try {
            serverURL= new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions=new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(serverURL)
                    .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        btn_join.setOnClickListener(view ->  {validateUser();});
        }

    private void validateUser() {
        String code;
        code=edt_code.getText().toString();
        if (code.isEmpty()) {
            Toast.makeText(this, "Please enter meeting code.", Toast.LENGTH_SHORT).show();
        }else {
            startmeeting();
    };
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string=edt_code.getText().toString();
                Intent intent=new Intent();
                intent.setAction(intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,string);
                intent.setType("text/plain");
                startActivity(intent);
            }
        });


    }

    private void startmeeting() {
        
        try {
            URL serverURL = new URL("https://meet.jit.si");

            JitsiMeetConferenceOptions.Builder builder  = new JitsiMeetConferenceOptions.Builder();
            builder.setServerURL(serverURL);
            builder.setRoom("test123");

            JitsiMeetUserInfo userInfo = new JitsiMeetUserInfo();
            userInfo.setDisplayName("test");
            builder.setUserInfo(userInfo);

            JitsiMeetActivity.launch(meeting_screen.this,builder.build());
            finish();

        }
        catch (Exception ex){
            Toast.makeText(meeting_screen.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    }

