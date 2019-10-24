package com.group59.studentCourseHelper.data.ui.award;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class mylistViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public mylistViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is your questions");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
