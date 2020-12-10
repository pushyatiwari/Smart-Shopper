package com.example.madproject4.Fragments;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madproject4.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class reg_frag extends Fragment {


    public reg_frag() {
        // Required empty public constructor
    }
EditText reg_e, reg_p;
    Button  reg_btn;
   private FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_reg_frag, container, false);
        reg_e = (EditText) view.findViewById(R.id.reg_email);
        reg_p = (EditText) view.findViewById(R.id.reg_password);
        reg_btn = (Button) view.findViewById(R.id.reg_btn);
        auth = FirebaseAuth.getInstance();

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String u_email = reg_e.getText().toString();
                final String u_pass = reg_p.getText().toString();
                if (isValidEmail(u_email)&&u_pass.length()>7){
                    if (validatePassword(reg_p.getText().toString())) {
                        Toast.makeText(getActivity(), "go ahead", Toast.LENGTH_SHORT).show();
                        registerUser(u_email,u_pass);

                    }
                    else{
                        Toast.makeText(getActivity(), "inval pass", Toast.LENGTH_SHORT).show();
                    }

                }else{

                    Toast.makeText(getActivity(), "inval email", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }

    private void registerUser(String u_email, String u_pass) {
          auth.createUserWithEmailAndPassword(u_email,u_pass).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                  if(task.isSuccessful())
                  {
                      Toast.makeText(getActivity(), "user registered", Toast.LENGTH_SHORT).show();
                  }
                  else{
                      Toast.makeText(getActivity(), "registration failed", Toast.LENGTH_SHORT).show();
                  }
              }
          });
    }


    public boolean validatePassword(final String password){
       return password.length()>6;
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

}
