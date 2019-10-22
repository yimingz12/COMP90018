package com.group59.studentCourseHelper.data.ui.post;



import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group59.studentCourseHelper.R;

public class PostActivity extends AppCompatActivity {

    EditText title;
    EditText desc;
    Button  submitButton;
    FirebaseDatabase database;
    DatabaseReference mDB_ref;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        mDB_ref=database.getReference("questions");
        mAuth=FirebaseAuth.getInstance();

        setContentView(R.layout.fragment_post);
        title=(EditText)findViewById(R.id.ques_title);
        desc=(EditText)findViewById(R.id.ques_desc);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            addques();
            }
        });

    }
    private void addques(){
        String m_title=title.getText().toString().trim();
        String m_desc=title.getText().toString().trim();

        if(!TextUtils.isEmpty(m_title)){
        String id= mDB_ref.push().getKey();
        }else{
            Toast.makeText(this,"You should write a title",Toast.LENGTH_LONG).show();
        }

    }
}
