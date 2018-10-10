# Firebase Beginner Workshop

Hello, hope you are fine. I am preparing this documentation for you all to get started with Firebase Authentication and Database. Follow step by step. I hope you will be a specialist in Firebase.

## Prerequisites
* Internet Connection.
* Any Android 4.0 (Ice Cream Sandwich) or newer device.
* The latest version of Android Studio with Successful Gradle Build.
* Create a project on Android Studio.

## Add Firebase to your app

* Click **Tools > Firebase** on Top Toolbar to open the **Assistant** window.
* Now you will find the categories you can work with. At first, we will work with **Firebase Authentication**. So click on Authentication. Then click *Email and Password Authentication*. Then you will find the steps to go on. 
* The first job is *Connect to Firebase*. So just click to connect your app with the firebase. You will see some gradle building. When you finish, you can see the the connection is successful.

Now you have to check that the connection is successful. So just go to the **Gradle Scripts**. Find `build.gradle (Project)`. you will see that this line is added for Firebase in the dependencies section.
```
        classpath 'com.google.gms:google-services:4.0.1'
```
And find `build.gradle (app)`, you will see that this line is added.
```
        apply plugin: 'com.google.gms.google-services'
```

Besides when your app is connected, Firebase put an auto-generated file name `google-services.json`. You can find it in the Project directory, then click on the *app* folder.

## Add Firebase Authentication to your app.

* To use an authentication provider, you need to enable it. Go to [Firebase console](https://console.firebase.google.com/). You can see the project Firebase created to for you. Go to the project, then to the **Sign-in Method** page in the Firebase **Authentication** section to *enable Email/Password sign-in* and any other identity providers you want for your app.
* On Android Studio, on `app/build.gradle`, add these following line on  **dependencies** section.
```
        implementation 'com.google.firebase:firebase-core:16.0.4'
        implementation 'com.google.firebase:firebase-auth:16.0.4'
```

Now first of all, create 3 Activities in Android Studio. *LoginActivity*, *RegisterActivity*, *MainActivity*. Implement the design and initialize all the views.

Before starting please watch on your database rules. Without rules, your database permission will be denied.

### Implement Register (Sign up an user with Firebase)
In *RegisterActivity*, You just need to get the username, email and password from the user. Then add the following code in your register function or listener.

```
        // init FirebaseAuth
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        // Create an user with Email and Password
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            if (user != null) {
                                // User is Created
                                // Now you need to update the user data to the Realtime Database.
                            
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Authentication failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
```

If the user is created, then you need to insert the user data with user id to the Realtime Database else you will get the Toast. To insert data to Realtime Database, add the following line.
```
        // init database reference
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        
        // If user is not null then update the data to the database
        DatabaseReference userChild =
                databaseReference.child("user").child(user.getUid());


        Map<String, String> userDataMap = new HashMap<>();

        userDataMap.put("username", userName);
        userDataMap.put("email", email);

        userChild.setValue(userDataMap);
        
        // Do next task
```

### Implement Login (Sign in an user with Firebase)
In *LoginActivity*, You just need to give the valid email and password from user to login. Then add the following line in your login function or listener.

```
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        
                        if (user != null) {
                           // Do next task
                           
                        }
                    }
                });
```

### Implement Query
In *MainActivity*, you have to use Firebase Query to fetch user data of the current user id. Here is the code.
```
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        // Database Query
        Query query = databaseReference.child("user").child(firebaseUser.getUid());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        User user = dataSnapshot.getValue(User.class);

                        if (user != null) {
                            // Update UI
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                        Log.d("DatabaseError", databaseError.getMessage());

                    }
                });

```


So, overall these are the basic of Firebase. But if you continue, you will definitely become good in Firebase. Thank you.
