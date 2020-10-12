package com.example.madproject4.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.madproject4.Activities.CaptureResult;
import com.example.madproject4.Activities.ShowDetailsCapture;
import com.example.madproject4.Adapters.MyAdapter;
import com.example.madproject4.R;

public class FragmentHome extends Fragment {
    View view;
    ListView listView;
    String acetic = "https://images.unsplash.com/photo-1572635148818-ef6fd45eb394?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2000&q=80";
    String url = "https://images.unsplash.com/photo-1519996529931-28324d5a630e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1868&q=80";
    String alomonds = "https://images.unsplash.com/photo-1508061253366-f7da158b6d46?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80";
     String alum= "https://images.unsplash.com/photo-1574163783773-39f4681829b0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80";
    String mTitle[] = {"Acetic Acid", "Almond", "Aluminium", "Amino acid", "Jalapeno","Acetic Acid", "Almond", "Aluminium", "Amino acid", "Jalapeno"};
    String mDescription[] = {"Substance is harmless...", "good source of uns...", "can cause kidney...", "basic elements ...", "Variety of pepper...",
            "Substance is harmless...", "good source of uns...", "can cause kidney...", "basic elements ...", "Variety of pepper..."};
    String images[] = {acetic,alomonds,url,alum,url,acetic,alomonds,url,alum,url };

    public FragmentHome() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);

        listView = view.findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter(getActivity(), mTitle, mDescription, images);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent i = new Intent(getActivity(), ShowDetailsCapture.class);
                startActivity(i);
            }
        });

        return view;


    }

}
