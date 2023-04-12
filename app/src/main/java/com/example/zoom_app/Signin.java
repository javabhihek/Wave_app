package com.example.zoom_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class Signin extends AppCompatActivity {

    Button btn_sigin;
    Button sing_in_google;

    EditText edt_emailid,edt_password,edt_mobup,edt_genderup,edt_dobup;
    TextView txt_forgotpassword,txt_register;
    String email,pass,mobile,gender,dob;
    FirebaseAuth auth;
    FirebaseDatabase database;
    
    GoogleSignInClient mGoogleSignInClient;
    ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        findViewById(R.id.sing_in_google);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        progressDialog=new ProgressDialog(Signin.this);
        progressDialog.setTitle("Creating account");
        progressDialog.setMessage("we are creating your account.");

        GoogleSignInOptions gso= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail().build();
        mGoogleSignInClient=GoogleSignIn.getClient(this,gso);
//        sing_in_google.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                singIn();
//            }
//        });
        
        progressDialog=new ProgressDialog(Signin.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage(" Please wait for Login account");

        btn_sigin=findViewById(R.id.btn_signin);
        txt_register=findViewById(R.id.txt_register);

        edt_emailid=findViewById(R.id.edt_email);
        edt_password=findViewById(R.id.edt_password);

        txt_forgotpassword=findViewById(R.id.txt_forgotpassword);

        txt_forgotpassword.setOnClickListener((view -> {
            startActivity(new Intent(Signin.this,ForotScreen.class));
            finish();
        }));

        txt_register.setOnClickListener((view -> {
            startActivity(new Intent(Signin.this,signup.class));
            finish();
        }));
       btn_sigin.setOnClickListener((view -> {validateUser();}));
    }

    int RC_SING_IN=40;
    private void singIn() {
        Intent intent=mGoogleSignInClient.getSignInIntent();
        startActivityForResult(intent,RC_SING_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==RC_SING_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

                    try {
                        GoogleSignInAccount account= task.getResult(ApiException.class);
                        firbaseAuth(account.getIdToken());
                    } catch (ApiException e) {
                        throw new RuntimeException(e);
                    }
        }

    }

    private void firbaseAuth(String idToken) {
        AuthCredential credential= GoogleAuthProvider.getCredential(idToken,null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user= auth.getCurrentUser();

                            User users = new User();
                            users.setUserId(user.getUid());
                            users.setUserId(user.getDisplayName());
                            users.setUserId(user.getPhotoUrl().toString());

                            database.getReference().child("Users").child(user.getUid()).setValue(users);
                            Intent intent = new Intent(Signin.this,Welcome_page.class);
                            startActivity(intent);

                        }else {
                            Toast.makeText(Signin.this, "Error :", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() !=null){
            startActivity(new Intent(this,Welcome_page.class));
            finish();
        }
    }
    private void validateUser() {
        email=edt_emailid.getText().toString();
        pass=edt_password.getText().toString();
        if (email.length()==0){
            edt_emailid.requestFocus();
            edt_emailid.setError("Filed cannot be empty.");
            //Toast.makeText(this, "Please enter email id.", Toast.LENGTH_SHORT).show();
        } else if (email.matches("[a-zA-Z0-9]")) {
            edt_emailid.requestFocus();
            edt_emailid.setError("Enter valid email");
        } else if (pass.length()==0) {
            edt_password.requestFocus();
            edt_password.setError("Filed cannnot be empty.");
            //Toast.makeText(this, "Please enter password.", Toast.LENGTH_SHORT).show();
        } else if (pass.matches("[a-zA-Z0-9@]")) {
            edt_password.requestFocus();
            edt_password.setError("Enter password like this: Abcd@2 ");
        } else {
            loginUser();
            progressDialog.show();
        }
    }

    private <activity> void loginUser() {
        auth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Signin.this, "Login success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Signin.this,MainActivity.class));
                            finish();
                        }else {
                            Toast.makeText(Signin.this, "Error:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}