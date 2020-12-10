package com.example.madproject4.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madproject4.Activities.AddDataFB;
import com.example.madproject4.Activities.AddDataToDatabase;
import com.example.madproject4.Activities.Guide;
import com.example.madproject4.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class login_frag extends Fragment {


    public login_frag() {
        // Required empty public constructor
    }
   EditText l_mail, l_pass;
    Button login_btn;
   FirebaseAuth auth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login_frag, container, false);
        l_mail = (EditText) view.findViewById(R.id.l_email);
        l_pass = (EditText) view.findViewById(R.id.l_password);
        login_btn = (Button) view.findViewById(R.id.login_btn);
        auth = FirebaseAuth.getInstance();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String l_email = l_mail.getText().toString();
                String l_psswrd = l_pass.getText().toString();
                loginUser(l_email,l_psswrd);
            }
        });


        return view;
    }

    private void loginUser(String l_email, String l_psswrd) {
        auth.signInWithEmailAndPassword(l_email,l_psswrd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(getActivity(), "login success, Welcome", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), AddDataFB.class );
                startActivity(intent);
                getActivity().finish();
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "login failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
