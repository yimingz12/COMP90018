package com.group59.studentCourseHelper.data.ui.award;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.group59.studentCourseHelper.R;

public class AwardFragment extends Fragment {
    private String TAG = getClass().getName();

    TextView query;
    private AwardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(AwardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_award, container, false);

        query = root.findViewById(R.id.text_query);


        return root;
    }


}