package com.group59.studentCourseHelper.data;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group59.studentCourseHelper.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.group59.studentCourseHelper.data.ui.login.LoginActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;


    public static String name="2222 ";


    User user1= new User();
    private FirebaseAuth mAuth;
    //ImageView profile;
    Button quit;
    //TextView name;
    ProgressBar loadingProgressBar;


    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference=database.getReference("users");




        final TextView username = findViewById(R.id.tv_username);
        final ImageView profile = findViewById(R.id.iv_profile);

        loadingProgressBar = findViewById(R.id.loading);

        String a = getIntent().getStringExtra("key");
        username.setText(a);

        quit= findViewById(R.id.b_signout);

        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // sign in or changed
//                   = user.getDisplayName();
                     String email = user.getEmail();
                    user.getUid();


                   // Uri photoUri = user.getPhotoUrl();
                    reference.child(user.getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String a=dataSnapshot.child("name").getValue(String.class);
                            Log.i("tag","This is the name");
                            Log.i("Tag",a);
                            Home.name=a;
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    Log.i("home name","this is the name00");
                    Log.i("home name" ,Home.name);
                    // If the above were null, iterate the provider data
                    // and set with the first non null data
                    for (UserInfo userInfo : user.getProviderData()) {

//                        if (name == null && userInfo.getDisplayName() != null) {
//                            name = userInfo.getDisplayName();
//                            System.out.println(name);
//                        }
//                        if (photoUri == null && userInfo.getPhotoUrl() != null) {
//                            photoUri = userInfo.getPhotoUrl();
//                        }
                    }
                   // username.setText(name);
                    //profile.setImageURI(photoUri);
                } else {
                    // sign out
                }
            }
        };


        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Home.this, LoginActivity.class));
                signOut();
                finish();


            }
        });
/////////////////////////////////////////////////////////////////////
       /*
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            //Name, email address, and profile photo Url
            //String name = user.getDisplayName();
            String Name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUri = user.getPhotoUrl();

            // If the above were null, iterate the provider data
            // and set with the first non null data
            for (UserInfo userInfo : user.getProviderData()) {
                if (Name == null && userInfo.getDisplayName() != null) {
                    Name = userInfo.getDisplayName();
                }
                if (photoUri == null && userInfo.getPhotoUrl() != null) {
                    photoUri = userInfo.getPhotoUrl();
                }
            }

            username.setText(email);
            //emailTextView.setText(user.getEmail());
            ///////
            if (photoUri != null) {
                Glide.with(this)
                        .load(photoUri)
                        .fitCenter()
                        .into(Image);
            }
            //////////

        }*/


////////////////////////////////////////////////////////
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_award, R.id.navigation_post)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener); }

    private void signOut() {
        mAuth.signOut();
        //updateUI(null);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
