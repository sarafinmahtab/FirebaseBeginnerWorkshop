package com.sarafinmahtab.firebasebeginner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);


        userNameTextView = findViewById(R.id.username_text);

        schoolEditText = findViewById(R.id.school_edit_text);
        collegeEditText = findViewById(R.id.college_edit_text);
        homeTownEditText = findViewById(R.id.home_town_edit_text);

        updateProfileButton = findViewById(R.id.update_profile_button);

        // Update User Details
        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String schoolName = schoolEditText.getText().toString();
                String collegeName = collegeEditText.getText().toString();
                String homeTown = homeTownEditText.getText().toString();


            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private TextView userNameTextView;

    private EditText schoolEditText;
    private EditText collegeEditText;
    private EditText homeTownEditText;

    private Button updateProfileButton;
}
