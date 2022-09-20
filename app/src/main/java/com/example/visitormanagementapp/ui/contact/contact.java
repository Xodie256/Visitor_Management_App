package com.example.visitormanagementapp.ui.contact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import com.example.visitormanagementapp.databinding.FragmentContactBinding;
import com.example.visitormanagementapp.databinding.FragmentHomeBinding;
import com.example.visitormanagementapp.R;
import com.example.visitormanagementapp.ui.home.HomeViewModel;


public class contact extends Fragment {
    private FragmentContactBinding binding;

    private ContactViewModel contactsViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ContactViewModel contactsViewModel =
                new ViewModelProvider(this).get(ContactViewModel.class);

        binding = FragmentContactBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textContact;
        contactsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
}