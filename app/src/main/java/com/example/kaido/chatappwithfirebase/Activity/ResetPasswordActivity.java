package com.example.kaido.chatappwithfirebase.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kaido.chatappwithfirebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class ResetPasswordActivity extends AppCompatActivity {
    MaterialEditText  metEmail;
    Button btnReset;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Reset Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        metEmail = findViewById(R.id.edtEmail);
        btnReset = findViewById(R.id.btnReset);
        auth = FirebaseAuth.getInstance();
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = metEmail.getText().toString();
                if (email.equals("")){
                    Toast.makeText(ResetPasswordActivity.this, "Field is required ", Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ResetPasswordActivity.this, "Please check your email", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ResetPasswordActivity.this,LoginActivity.class));
                            }
                            else {
                                String error = task.getException().getMessage();
                                Toast.makeText(ResetPasswordActivity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
