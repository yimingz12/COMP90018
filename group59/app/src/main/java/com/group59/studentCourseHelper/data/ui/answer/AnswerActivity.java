

        package com.group59.studentCourseHelper.data.ui.answer;

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

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;
        import com.group59.studentCourseHelper.R;
        import com.group59.studentCourseHelper.data.ui.post.Question;

        public class AnswerActivity extends AppCompatActivity {
        //TextView answer;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Button ans_submit;
        Button ans_back;
        EditText answer;
        String name;
        String qid;
        DatabaseReference myRef = database.getReference("answers");
        DatabaseReference userRef=database.getReference("users");
        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        private String TAG = getClass().getName();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userRef.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        String a=dataSnapshot.child("name").getValue(String.class);
        name=a;
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
        });;
        setContentView(R.layout.activity_answer);
        answer = findViewById(R.id.answ);
        ans_back=(Button)findViewById(R.id.ans_back);
        ans_submit=(Button)findViewById(R.id.ans_submit);
        Intent intent = getIntent();
        String a = intent.getStringExtra("qid");
        qid=a;
        Log.i(TAG, "onAnswer: :"+qid);


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



        }

        private void showView(String a) {

        }
        private boolean addans(){
        String m_answer=answer.getText().toString().trim();
        String aid= myRef.push().getKey();

        Answer m_ans=new Answer(m_answer,aid,qid,name);
        myRef.child(qid).child(aid).setValue(m_ans);
        Toast.makeText(this,"You have posted an answer succefully",Toast.LENGTH_LONG).show();
        return true;

        }
        }

