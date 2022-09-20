package com.example.visitormanagementapp.ui.faqs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FaqsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FaqsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is faqs fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}