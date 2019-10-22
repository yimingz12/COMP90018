package com.group59.studentCourseHelper.data.ui.post;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.group59.studentCourseHelper.R;

public class PostFragment extends Fragment {

    private PostViewModel notificationsViewModel;
    LinearLayout m_layout;
    Button m_button;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(PostViewModel.class);
        View root = inflater.inflate(R.layout.fragment_post, container, false);

        m_button=(Button)root.findViewById(R.id.question_submit);
        m_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.i("post","activate activity");
                Intent intent = new Intent();
                intent.setClass(getActivity(), PostActivity.class);
                startActivity(intent);
            }

        });
        final TextView textView = root.findViewById(R.id.text_post);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}