package com.group59.studentCourseHelper.data;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group59.studentCourseHelper.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.group59.studentCourseHelper.data.ui.login.LoginActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class Home extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;

    private FirebaseAuth mAuth;
    //ImageView profile;
    //Button quit;
    //TextView name;
    ProgressBar loadingProgressBar;


    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        //final TextView username = findViewById(R.id.tv_username);
        final ImageView profile = findViewById(R.id.iv_profile);

        loadingProgressBar = findViewById(R.id.loading);

        String a = getIntent().getStringExtra("key");
        //username.setText(a);

        //quit= findViewById(R.id.b_signout);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // sign in or changed
                    String name = user.getDisplayName();
                    String email = user.getEmail();
                    //Uri photoUri = user.getPhotoUrl();

                    // If the above were null, iterate the provider data
                    // and set with the first non null data
                    for (UserInfo userInfo : user.getProviderData()) {
                        if (name == null && userInfo.getDisplayName() != null) {
                            name = userInfo.getDisplayName();
                        }
                        /*
                        if (photoUri == null && userInfo.getPhotoUrl() != null) {
                            photoUri = userInfo.getPhotoUrl();
                        }*/
                    }
                    //username.setText(name);
                    //profile.setImageURI(photoUri);
                } else {
                    // sign out
                }
            }
        };

/*
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Home.this, LoginActivity.class));
                signOut();
                finish();


            }
        });
        */
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
                R.id.navigation_home, R.id.navigation_award,R.id.navigation_search, R.id.navigation_post)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_logout_btn:
                logOut();
                return true;



            default:
                return false;


        }

    }

    private void logOut() {


        mAuth.signOut();
        sendToLogin();
    }

    private void sendToLogin() {

        Intent loginIntent = new Intent(Home.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();

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
