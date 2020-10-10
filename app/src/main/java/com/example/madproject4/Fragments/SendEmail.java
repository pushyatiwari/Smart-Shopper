package com.example.madproject4.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.madproject4.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SendEmail extends Fragment {


    private View view;
    AutoCompleteTextView autoCompleteTextView;

    public SendEmail() {
        // Required empty public constructor
    }
    String arr[] = {"Acetic Acid", "Almond", "Aluminium", "Amino acid", "Jalapeno","Acetic Acid", "Almond", "Aluminium", "Amino acid", "Jalapeno"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view  = inflater.inflate(R.layout.fragment_send_email, container, false);
        autoCompleteTextView = view.findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getContext(),android.R.layout.select_dialog_item,arr);

        autoCompleteTextView.setThreshold(2);
        autoCompleteTextView.setAdapter(adapter);

        return view;
    }

}
