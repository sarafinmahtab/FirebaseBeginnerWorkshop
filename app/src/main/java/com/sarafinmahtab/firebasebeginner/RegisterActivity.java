package com.sarafinmahtab.firebasebeginner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

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


        // Click Listeners
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String userName = userNameEditText.getText().toString();
                final String email = emailEditText.getText().toString();
                final String password = passwordEditText.getText().toString();


            }
        });
    }

    private EditText userNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;

    private Button registerButton;
}
