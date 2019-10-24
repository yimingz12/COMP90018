package com.group59.studentCourseHelper.data.ui.answer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group59.studentCourseHelper.R;
import com.group59.studentCourseHelper.data.ui.post.Question;

public class AnswerActivity extends AppCompatActivity {
    //TextView answer;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    Button ans_submit;
    Button ans_back;
    EditText answer;
    DatabaseReference myRef = database.getReference("answers");
    private String TAG = getClass().getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        answer = findViewById(R.id.answ);
        ans_back=(Button)findViewById(R.id.ans_back);
        ans_submit=(Button)findViewById(R.id.ans_submit);
        ans_submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               if( addans()){
                   finish();
               }
            }
        });
        ans_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        String a = intent.getStringExtra("title");
        Log.i(TAG, "onCreateView: :"+a);
        answer.setText(a);
        showView(a);

    }

    private void showView(String a) {

    }
    private boolean addans(){
    String m_answer=answer.getText().toString().trim();
        String aid= myRef.push().getKey();

        //Answer m_ans=new Answer(m_answer,aid,qid,name);
        //myRef.child(qid).child(aid).setValue(m_ans);
        String id= myRef.push().getKey();
        Answer m_ans=new Answer(m_answer);
        myRef.child(id).setValue(m_ans);
        Toast.makeText(this,"You have posted an answer succefully",Toast.LENGTH_LONG).show();
        return true;

    }
}
