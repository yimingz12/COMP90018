package com.group59.studentCourseHelper.data.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group59.studentCourseHelper.R;
import com.group59.studentCourseHelper.data.ui.post.Question;

public class DetailsActivity extends AppCompatActivity {
    //TextView answer;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    Button ans_submit;
    Button ans_back;
    TextView title, name, des;
    DatabaseReference myRef = database.getReference("questions");
    private String TAG = getClass().getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ans_back=(Button)findViewById(R.id.back_btn);
        ans_submit=(Button)findViewById(R.id.ans_btn);
        title = findViewById(R.id.detail_title);
        name = findViewById(R.id.detail_name);
        des = findViewById(R.id.detail_des);
        ans_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        String a = intent.getStringExtra("title");
//        answer.setText(a);
        showView(a);

    }

    private void showView(String a) {
        myRef.child(a).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
