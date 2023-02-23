package com.sharkBytesLab.superNotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {



    private FirebaseDatabase database;
    private FirebaseAuth auth;
    private EditText signUpEmail,signUpPassword;
    private Button signUpButton;
    private TextView signUpText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        signUpEmail = findViewById(R.id.signUpEmail);
        signUpPassword = findViewById(R.id.signUpPassword);
        signUpButton = findViewById(R.id.signUpButton);
        signUpText = findViewById(R.id.signUpText);



        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                auth.createUserWithEmailAndPassword(signUpEmail.getText().toString(), signUpPassword.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()){
                                    Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                                    Toast.makeText(SignUpActivity.this, "Sign Up Success.", Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });


    }
}