package com.example.android_app;

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

public class MainActivity extends AppCompatActivity {
    EditText emailid,password;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;

    {
        mFirebaseAuth = FirebaseAuth.getInstance();
    }

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailid = findViewById(R.id.editText6);
        password = findViewById(R.id.editText7);
        btnSignUp = findViewById(R.id.button2);
        tvSignIn = findViewById(R.id.textView2);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailid.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()){
                    emailid.setError("Please enter email id");
                    emailid.requestFocus();
                }
                else if (pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else if (email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(MainActivity.this,"Fields are Empty!",Toast.LENGTH_SHORT).show();
                }
                else if (!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"SignUp Unsuccessful, Please try Again",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(MainActivity.this,"Error Ocurred!",Toast.LENGTH_SHORT).show();

                }

            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
