package com.group59.studentCourseHelper.data.ui.post;

import android.widget.Button;
import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PostViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    EditText title;
    EditText desc;
    Button mButton;
    public PostViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is post fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}