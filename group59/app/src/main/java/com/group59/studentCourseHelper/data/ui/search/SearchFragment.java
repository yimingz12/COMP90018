package com.group59.studentCourseHelper.data.ui.search;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group59.studentCourseHelper.R;

import java.util.ArrayList;
import java.util.List;



public class SearchFragment extends Fragment {

    private SearchViewModel notificationsViewModel;

    EditText InputSearch;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference myRef = database.getReference("questions");
    List<Search> searchList;

    private ListView listView;
    private ImageView image;
    TextView textView;
    int j = 0;
    private String TAG = getClass().getName();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(SearchViewModel.class);
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        textView = view.findViewById(R.id.search_input);
        searchList = new ArrayList();
        InputSearch = view.findViewById(R.id.search_input);

        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        Button btn = (Button) view.findViewById(R.id.search_btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Event();
            }
        });


        return view;
    }

    public void Event() {
        searchList = new ArrayList();
        final String input = InputSearch.getText().toString();
        myRef.orderByChild("questionId").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Log.i(TAG, "onSearch::" + postSnapshot.child("tag"));
                    final String title = postSnapshot.child("questionTitle").getValue().toString();
                    final String tag = postSnapshot.child("tag").getValue().toString();
                    final String qid = postSnapshot.child("questionId").getValue().toString();
                    char[] c = input.toCharArray();
                    j = 0;
                    image.setVisibility(View.GONE);

                    for (char cc : c) {
                        if (title.indexOf(cc) > -1 || tag.indexOf(cc) > -1) {
                            j = 1;
                        }
                    }
                    if (j == 1) {
                        searchList.add(new Search(title, tag, qid));
                    }
                }
                Log.i(TAG, "onSearch1::" + j);
                if(j==0){
                        Toast.makeText(getContext(), "No results", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        SearchAdapter adapter = new SearchAdapter(getContext(), R.layout.search_list, searchList);
        listView = getView().findViewById(R.id.search_list);
        listView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        image = getView().findViewById(R.id.imageView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Search search = (Search) adapterView.getItemAtPosition(i);
                Toast.makeText(getContext(), search.getTitle(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.setAction("android.intent.action.DETAILS");
                intent.putExtra("qid", search.getQid());
                startActivity(intent);
            }
        });

    }

    private void setContentView(int activity_register) {
    }
}