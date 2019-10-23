package com.group59.studentCourseHelper.data.ui.post;



import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group59.studentCourseHelper.R;

import java.util.regex.*;

public class PostActivity extends AppCompatActivity {

    EditText title;
    EditText desc;
    EditText tag;
    Button  submitButton;
    Button backButton;
    FirebaseDatabase database;
    DatabaseReference mDB_ref;
    DatabaseReference mDB_user;
    FirebaseAuth mAuth;
    String name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("activity","activity is using");
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        mDB_ref=database.getReference("questions");
        mDB_user=database.getReference("users");
        mAuth=FirebaseAuth.getInstance();
        mDB_user=mDB_user.child(mAuth.getUid());
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
        setContentView(R.layout.activity_post);
        title=(EditText)findViewById(R.id.ques_title);
        desc=(EditText)findViewById(R.id.ques_desc);
        tag=(EditText)findViewById(R.id.ques_tag) ;
        submitButton=(Button)findViewById(R.id.ques_submit) ;
        backButton=(Button)findViewById(R.id.ques_back);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if( addques()){
                  finish();
               };

            }

        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    private boolean addques(){
        String m_title=title.getText().toString().trim();
        String m_desc=desc.getText().toString().trim();
        String m_tag=tag.getText().toString().trim();
        boolean match=parsetag(m_tag);
        if(match){
            if(!TextUtils.isEmpty(m_title)){
                String id= mDB_ref.push().getKey();
                Question newques= new Question(id,m_title,m_desc,m_tag,name,mAuth.getUid());
                mDB_ref.child(mAuth.getUid()).child(id).setValue(newques);
                Toast.makeText(this,"You have posted a question succefully",Toast.LENGTH_LONG).show();
                return true;
            }else{
                Toast.makeText(this,"You should write a title",Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this,"You should write a legal subject code",Toast.LENGTH_LONG).show();
        }

        return false;
    }
    private boolean parsetag(String tag){
        String pattern="^([a-z]|[A-Z]){4}[0-9]{5}$";
        return Pattern.matches(pattern,tag);
    }
}
