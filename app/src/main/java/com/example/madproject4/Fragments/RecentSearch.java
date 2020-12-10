package com.example.madproject4.Fragments;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.example.madproject4.Activities.ShowDetailsCapture;
import com.example.madproject4.Adapters.MyAdapter;
import com.example.madproject4.Database.DatabaseHelper;
import com.example.madproject4.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentSearch extends Fragment {


    private View view;
    ListView listView;
    DatabaseHelper db;
    public RecentSearch() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view  = inflater.inflate(R.layout.fragment_send_email, container, false);
        listView = view.findViewById(R.id.list_rs);
        db = new DatabaseHelper(getContext());
        ArrayList<String> mTitle,  mDescription, images;
        mTitle = new ArrayList();
        mDescription =  new ArrayList();
        images = new ArrayList();

        {
            Cursor res = db.getAllData();
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("Id :" + res.getString(0) + "\n");
                buffer.append("title :" + res.getString(1) + "\n");
                buffer.append("description :" + res.getString(2) + "\n");
                buffer.append("effects :" + res.getString(3) + "\n");
                buffer.append("harmful :" + res.getString(4) + "\n");
                buffer.append("added in :" + res.getString(5) + "\n\n");
                buffer.append("image url" + res.getString(6) + "\n\n");
                mTitle.add(res.getString(1));
                mDescription.add(res.getString(2));
                images.add(res.getString(6));

            }

        }
        String mTitleString[] = new String[mTitle.size()];
        for(int j =0;j<mTitle.size();j++){
            mTitleString[j] = mTitle.get(j);
        }
        String mdescString[] = new String[mDescription.size()];
        for(int j =0;j<mDescription.size();j++){
            mTitleString[j] = mDescription.get(j);
        }
        String mimageString[] = new String[images.size()];
        for(int j =0;j<images.size();j++){
            mTitleString[j] = images.get(j);
        }

        MyAdapter adapter = new MyAdapter(getActivity().getApplicationContext(), mTitleString,
                mdescString, mimageString);
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
