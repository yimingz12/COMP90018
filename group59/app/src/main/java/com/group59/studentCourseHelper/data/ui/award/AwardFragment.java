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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group59.studentCourseHelper.R;

import java.util.List;

public class AwardFragment extends Fragment {
    private String TAG = getClass().getName();
    private ListView listView;
    private TextView title, time, tag;
    List<Listmy> mylist;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference myRef = database.getReference("users");
    private AwardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(AwardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_award, container, false);

//        title = root.findViewById(R.id.list_title);
//        time = root.findViewById(R.id.list_time);
//        tag = root.findViewById(R.id.list_tag);

       // showList();
        return root;
    }

    private void showList() {
        final String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        myRef.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    final String title1 = postSnapshot.child("title").getValue().toString();
                    final String time1 = postSnapshot.child("tag").getValue().toString();
                    final String tag1 = postSnapshot.child("time").getValue().toString();
                    mylist.add(new Listmy(title1,time1,tag1));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final ListAdapter adapter = new ListAdapter(getContext(), R.layout.myquestion_list, mylist
        );
        listView = getView().findViewById(R.id.myquestion_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Listmy list = (Listmy) adapterView.getItemAtPosition(i);
                Toast.makeText(getContext(), list.getTitle(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onClick::" + list.getTitle());

                Intent intent = new Intent();
                intent.setAction("AWARD");
                intent.putExtra("title", list.getTitle());
                startActivity(intent);
            }
        });


    }
}