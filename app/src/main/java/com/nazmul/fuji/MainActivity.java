package com.nazmul.fuji;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nazmul.fuji.databinding.ActivityMainBinding;

import android.support.annotation.NonNull;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView( this, R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        button = (Button) findViewById(R.id.signup_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignup();
            }

        });
    }

    private void openSignup() {
        Intent intent = new Intent(this,SignupActivity.class);
        startActivity(intent);
    }

    public void loginUser(View view) {
        String email = binding.emailET.getText().toString();
        String pass = binding.passwordET.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        currentUser = firebaseAuth.getCurrentUser();
                        updateUI("Logged in as "+currentUser.getEmail());
                    }
                })
                /*.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        currentUser = firebaseAuth.getCurrentUser();
                        updateUI("Logged in as "+currentUser.getEmail());
                    }
                })*/.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                updateUI(e.getLocalizedMessage());
            }
        });


    }
    /*public void regristerUser(View view) {
        String email = binding.emailET.getText().toString();
        String pass = binding.passwordET.getText().toString();

        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        currentUser = firebaseAuth.getCurrentUser();
                        updateUI("Logged in as "+currentUser.getEmail());
                    }
                })
                *//*.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        currentUser = firebaseAuth.getCurrentUser();
                        updateUI("Logged in as "+currentUser.getEmail());
                    }
                })*//*.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                updateUI(e.getLocalizedMessage());
            }
        });

    }*/

    private void updateUI(String msg){
        binding.statusTV.setText(msg);
    }




}