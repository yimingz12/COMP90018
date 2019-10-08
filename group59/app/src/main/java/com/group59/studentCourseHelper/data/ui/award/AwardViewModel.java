package com.group59.studentCourseHelper.data.ui.award;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AwardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AwardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is award fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}