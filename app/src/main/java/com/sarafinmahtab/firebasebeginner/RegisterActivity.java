package com.sarafinmahtab.firebasebeginner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.register_toolbar);
        setSupportActionBar(toolbar);

        // Find Views
        userNameEditText = findViewById(R.id.username_edit_text);
        emailEditText = findViewById(R.id.email__edit_text);
        passwordEditText = findViewById(R.id.password__edit_text);

        registerButton = findViewById(R.id.register__button);


        // Init Views and Objects
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference();


        // Click Listeners
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String userName = userNameEditText.getText().toString();
                final String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = firebaseAuth.getCurrentUser();

                                    if (user != null) {

                                        DatabaseReference userChild =
                                                databaseReference.child("user").child(user.getUid());


                                        Map<String, String> userDataMap = new HashMap<>();

                                        userDataMap.put("username", userName);
                                        userDataMap.put("email", email);

                                        userChild.setValue(userDataMap);


                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        intent.putExtra("uid", user.getUid());
                                        startActivity(intent);
                                        finish();
                                    }
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(RegisterActivity.this, "Authentication failed",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    private EditText userNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;

    private Button registerButton;
}
