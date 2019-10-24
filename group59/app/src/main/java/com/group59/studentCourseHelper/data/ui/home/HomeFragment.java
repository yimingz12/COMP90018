package com.group59.studentCourseHelper.data.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group59.studentCourseHelper.R;
import com.group59.studentCourseHelper.data.Home;
import com.group59.studentCourseHelper.data.ui.login.LoginActivity;

public class HomeFragment extends Fragment {

    //private HomeViewModel homeViewModel;
    String email;
    String name;
    private Button quit;
    private FirebaseAuth mAuth;
    DatabaseReference mDB_user;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        //homeViewModel.getText().observe(this, new Observer<String>() {
        // @Override
        //   public void onChanged(@Nullable String s) {
        //textView.setText(s);
        //    }
        //});
        mAuth = FirebaseAuth.getInstance();
       email= mAuth.getCurrentUser().getEmail();
       mDB_user=FirebaseDatabase.getInstance().getReference("users").child(mAuth.getCurrentUser().getUid());
        mDB_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String a=dataSnapshot.child("name").getValue(String.class);
                name=a;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        quit = root.findViewById(R.id.b_signout);
        return root;
        /*
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Home.this, LoginActivity.class));
                signOut();
                finish();


            }
        });*/
    }


    private void signOut() {
        mAuth.signOut();
        //updateUI(null);
    }


}