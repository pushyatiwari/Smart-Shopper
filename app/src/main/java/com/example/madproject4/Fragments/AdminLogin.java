package com.example.madproject4.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.madproject4.Activities.AddDataFB;
import com.example.madproject4.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminLogin extends Fragment {

Button reg, log, addfb;
    reg_frag regf = new reg_frag();
    login_frag login_f = new login_frag();
    public AdminLogin() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_login, container, false);
        reg = view.findViewById(R.id.reg_admin);
        log = view.findViewById(R.id.login_admin);
       // addfb = (Button) view.findViewById(R.id.adddata_fb);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadFragment(regf);
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadFragment(login_f);
            }
        });
//        addfb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), AddDataFB.class));
//            }
//        });


      return view;
    }
    public Fragment loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.log_regcontainer, fragment).
                setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        transaction.commit();
        return fragment;
    }

}
