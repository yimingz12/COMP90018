package com.group59.studentCourseHelper.data.ui.answer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group59.studentCourseHelper.R;

public class answerActivity extends AppCompatActivity {
    TextView query;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference myRef = database.getReference("users");
    private String TAG = getClass().getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        query = findViewById(R.id.text_query);
        Intent intent = getIntent();
        String a = intent.getStringExtra("title");
        Log.i(TAG, "onCreateView: :"+a);
        query.setText(a);
        showView(a);
    }

    private void showView(String a) {

    }

}
