package com.group59.studentCourseHelper.data.ui.award;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import java.util.ArrayList;
import java.util.List;

public class awardFragment extends Fragment{
    private mylistViewModel notificationsViewModel;
    private ListView listView;
    TextView textView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    List<Question> myList = new ArrayList();
    DatabaseReference myRef = database.getReference("questions");


    private String TAG = getClass().getName();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(mylistViewModel.class);
        final View view = inflater.inflate(R.layout.fragment_award, container, false);
        textView = view.findViewById(R.id.myQ_hint);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        String uid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        listView =(ListView) view.findViewById(R.id.question_list);
        myRef.orderByChild("senderId").equalTo(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    final String title = postSnapshot.child("questionTitle").getValue().toString();
                    final String tag = postSnapshot.child("tag").getValue().toString();
                    final String desc = postSnapshot.child("questionDesc").getValue().toString();
                    final  String qId = postSnapshot.child("questionId").getValue().toString();
                    myList.add(new Question(title,desc,tag,qId));
                    myListAdapter adapter = new myListAdapter(getContext(), R.layout.my_list, myList);
                    listView =(ListView) view.findViewById(R.id.question_list);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Question search = (Question) adapterView.getItemAtPosition(i);
                Toast.makeText(getContext(),search.getqTitle(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.setAction("android.intent.action.DETAILS");
                intent.putExtra("qid", search.getqId());
                startActivity(intent);
            }
        });


        return view;
    }
    public void showList(){

    }

}
