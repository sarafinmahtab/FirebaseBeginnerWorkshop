package com.sarafinmahtab.firebasebeginner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sarafinmahtab.firebasebeginner.model.User;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    private FirebaseUser firebaseUser;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        String uid = getIntent().getStringExtra("uid");

        Log.d("uid", uid);

        userNameTextView = findViewById(R.id.username_text);

        schoolEditText = findViewById(R.id.school_edit_text);
        collegeEditText = findViewById(R.id.college_edit_text);
        homeTownEditText = findViewById(R.id.home_town_edit_text);

        updateProfileButton = findViewById(R.id.update_profile_button);


        // Init Views and Objects
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        firebaseUser = firebaseAuth.getCurrentUser();

        databaseReference = firebaseDatabase.getReference();


        // Database Query
        Query query = databaseReference.child("user").child(firebaseUser.getUid());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        User user = dataSnapshot.getValue(User.class);

                        if (user != null) {
                            userNameTextView.setText(user.getUsername());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                        Log.d("DatabaseError", databaseError.getMessage());

                    }
                });


        // Update User Details
        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String schoolName = schoolEditText.getText().toString();
                String collegeName = collegeEditText.getText().toString();
                String homeTown = homeTownEditText.getText().toString();

                if (firebaseUser != null) {

                    DatabaseReference userChild =
                            databaseReference.child("details").child(
                                    firebaseUser.getUid());


                    Map<String, String> userDataMap = new HashMap<>();

                    userDataMap.put("school", schoolName);
                    userDataMap.put("college", collegeName);
                    userDataMap.put("hometown", homeTown);

                    userChild.setValue(userDataMap);

                    Toast.makeText(MainActivity.this,
                            "Update Successful", Toast.LENGTH_LONG).show();
                }
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
