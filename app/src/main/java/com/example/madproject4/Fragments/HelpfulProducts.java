package com.example.madproject4.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.madproject4.Adapters.MyAdapter;
import com.example.madproject4.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpfulProducts extends Fragment {


    private View view;
    ListView listView;
    String acetic = "https://images.unsplash.com/photo-1572635148818-ef6fd45eb394?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=2000&q=80";
    String url = "https://images.unsplash.com/photo-1519996529931-28324d5a630e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1868&q=80";
    String alomonds = "https://images.unsplash.com/photo-1508061253366-f7da158b6d46?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80";
    String alum= "https://images.unsplash.com/photo-1574163783773-39f4681829b0?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1950&q=80";
    String mTitle[] = {"Acetic Acid", "Almond", "Aluminium", "Amino acid", "Jalapeno","Acetic Acid", "Almond", "Aluminium", "Amino acid", "Jalapeno"};
    String mDescription[] = {"Substance is harmless...", "good source of uns...", "can cause kidney...", "basic elements ...", "Variety of pepper...",
            "Substance is harmless...", "good source of uns...", "can cause kidney...", "basic elements ...", "Variety of pepper..."};
    String images[] = {acetic,alomonds,url,alum,url,acetic,alomonds,url,alum,url };

    public HelpfulProducts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view =  inflater.inflate(R.layout.fragment_helpful_products, container, false);
        listView = view.findViewById(R.id.helpfulP_listview);
        MyAdapter adapter = new MyAdapter(getActivity(), mTitle, mDescription, images);
        listView.setAdapter(adapter);
        return view;
    }

}
