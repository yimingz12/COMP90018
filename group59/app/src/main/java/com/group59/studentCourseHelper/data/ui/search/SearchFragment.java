package com.group59.studentCourseHelper.data.ui.search;


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

    DatabaseReference myRef = database.getReference("users");
    List<Search> searchList;

    private  ListView listView;
    TextView textView;

    private String TAG = getClass().getName();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(SearchViewModel.class);
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        textView = view.findViewById(R.id.text_search);
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

   public void Event(){
        searchList = new ArrayList();
       final String input = InputSearch.getText().toString();
       myRef.orderByChild("email").addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                   final String title = postSnapshot.child(("email")).getValue().toString();
                   final String psw = postSnapshot.child("password").getValue().toString();
                   char[] c = input.toCharArray();
                   int j = 0;
                   for(char cc:c) {
                       if (psw.indexOf(cc)>-1) {
                           Log.i(TAG, "onSearch:title:"+ title);
                           Log.i(TAG, "onSearch:psw:"+ psw);
                           j = 1;
                       }
                   }
                   Log.i(TAG, "onSearch::" +j);
                   if (j == 1){
                       searchList.add(new Search(title, psw));
                   }
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
       final SearchAdapter adapter = new SearchAdapter(getContext(), R.layout.search_list,searchList);
       listView = getView().findViewById(R.id.search_list);
       listView.setAdapter(adapter);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Search search = (Search) adapterView.getItemAtPosition(i);
               Toast.makeText(getContext(),search.getTitle(),Toast.LENGTH_SHORT).show();
               Log.i(TAG, "onClick::" +search.getTitle());

               Intent intent = new Intent();
               intent.setAction("AWARD");
               intent.putExtra("title",search.getTitle());
               startActivity(intent);
           }
       });

   }

    private void setContentView(int activity_register) {
    }
}