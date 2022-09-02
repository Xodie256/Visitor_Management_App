package com.example.visitormanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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

public class Register extends AppCompatActivity implements View.OnClickListener {
    //firebase code for checking auth state
    private FirebaseAuth mAuth;

    private TextView banner, Register;
    private EditText editText1, editText2,editPassword,editEmail;

    //returning to log in page
    private TextView have;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initialize firebase auth
        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.banner);
                banner.setOnClickListener(this);

               Register =(Button) findViewById(R.id.register);
               Register.setOnClickListener(this);

               editText1 = (EditText) findViewById(R.id.editText1);
               editText2 = (EditText) findViewById(R.id.editText2);
               editPassword = (EditText) findViewById(R.id.editPassword);
               editEmail = (EditText) findViewById(R.id.editEmail);

        have = (TextView) findViewById(R.id.have);
        have.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch(view.getId()) {
            case R.id.have:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
        switch(view.getId()){
            case R.id.banner:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.register:
                Register();
                break;
        }
    }

    private void Register(){
        String fname = editText1.getText().toString().trim();
        String lname = editText2.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        String email = editEmail.getText().toString().trim();

        if(fname.isEmpty()){
            editText1.setError("first name is required!");
            editText1.requestFocus();
        return;
        }
        if(lname.isEmpty()){
            editText2.setError("last name is required!");
            editText2.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editPassword.setError("Password is required!");
            editPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            editPassword.setError("minimum length is 6 characters!");
            editPassword.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editPassword.setError("Email is required!");
            editEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Please provide valid email!");
            editEmail.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(fname,lname,password,email);

                            FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>(){
                                        @Override
                                        public void onComplete(@NonNull Task <Void> task){
                                            if(task.isSuccessful()){
                                                Toast.makeText(Register.this, "User has been registered successfully!!", Toast.LENGTH_SHORT).show();
                                            }
                                            //redirect to log in page
                                            else{
                                                Toast.makeText(Register.this, "Register failed, try again", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }else{
                            Toast.makeText(Register.this, "Register failed, try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                    });

    }

}