package com.example.madproject4.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.madproject4.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_home, container, false);


        homefragment_base hfragment_base = new homefragment_base();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_home, hfragment_base)
                .commit();
        return view;
    }
//    public void addNewFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.add(R.id.fragment_container_home, fragment);
//        transaction.commit();
//
//    }

}
