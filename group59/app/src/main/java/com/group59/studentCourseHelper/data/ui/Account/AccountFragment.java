package com.group59.studentCourseHelper.data.ui.Account;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group59.studentCourseHelper.R;
import com.group59.studentCourseHelper.data.ui.search.Search;
import com.group59.studentCourseHelper.data.ui.search.SearchAdapter;
import com.group59.studentCourseHelper.data.ui.search.SearchViewModel;

import java.util.ArrayList;
import java.util.List;

public class AccountFragment extends Fragment {

    private String TAG = getClass().getName();
    private ListView listView;
    private TextView title, qid, tag;
    List<questionList> mylist;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference myRef = database.getReference("users");
    private AccountViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(AccountViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);

        //title = root.findViewById(R.id.list_title);
        //qid = root.findViewById(R.id.list_qid);
        //tag = root.findViewById(R.id.list_tag);

        showList();
        return root;
    }

    private void showList() {
        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        myRef.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    final String title1 = postSnapshot.child("title").getValue().toString();
                    final String tag1 = postSnapshot.child("tag").getValue().toString();
                    final String qid1 = postSnapshot.child("qid").getValue().toString();
                    mylist.add(new questionList(title1,qid1,tag1));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final myListAdapter adapter = new myListAdapter(getContext(), R.layout.myquestion_list, mylist
        );
        listView = getView().findViewById(R.id.question_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                questionList list = (questionList) adapterView.getItemAtPosition(i);
                Toast.makeText(getContext(), list.getTitle(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onClick::" + list.getTitle());

                Intent intent = new Intent();
                intent.setAction("Account");
                intent.putExtra("title", list.getTitle());
                startActivity(intent);
            }
        });

    }
}

