package com.example.madproject4.Fragments;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.madproject4.Activities.CaptureResult;
import com.example.madproject4.Activities.showIndItemFromHistory;
import com.example.madproject4.Activities.ShowDetailsCapture;
import com.example.madproject4.Adapters.MyAdapter;
import com.example.madproject4.Database.DatabaseHelper;
import com.example.madproject4.Database.DatabaseHistory;
import com.example.madproject4.MainActivity;
import com.example.madproject4.Model.Ingredients;
import com.example.madproject4.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentSearch extends Fragment {


    private View view;
    int j = 0;

    ListView listView_rs;
    DatabaseHistory db;
    String[] mTitle;
    String[] mdesc;
    String[] mimages;
    int fbArray, size;
    ArrayList<String> tempFb_title = new ArrayList<>();
    HashMap<String, Integer> hashMap = new HashMap<>();
    ArrayList<String> title_arrlist = new ArrayList<>();
    ArrayList<String> desc_arrlist = new ArrayList<>();
    ArrayList<String> image_arrlist = new ArrayList<>();

    public RecentSearch() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view  = inflater.inflate(R.layout.fragment_send_email, container, false);
        listView_rs = view.findViewById(R.id.list_rs);
        db = new DatabaseHistory(getActivity());

         size = db.getMax();

         fbArray = MainActivity.tempData.size();
        Log.d("recentsearch2", "onCreateView: " + MainActivity.tempData.size());



        for(int i = 0; i < MainActivity.tempData.size();i++)
        {
            tempFb_title.add(MainActivity.tempData.get(i).getTitle());
            hashMap.put(MainActivity.tempData.get(i).getTitle(), i);
            Log.d("descriptionRS111111" , "" + MainActivity.tempData.get(i).getTitle());

        }

        registerForContextMenu(listView_rs);
        load();



        return view;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.context_history, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int listPosition = info.position;
        switch(item.getItemId()) {
            case R.id.delete:
                deleteItemfromdb(mTitle[listPosition]);
                Toast.makeText(getActivity(), "to delete this in history", Toast.LENGTH_SHORT).show();                return true;

        }
        return super.onContextItemSelected(item);

    }

    private void deleteItemfromdb(String Title) {
        db.deleteitem(Title);
        title_arrlist.clear();
        desc_arrlist.clear();
        image_arrlist.clear();
        listView_rs.setVisibility(View.INVISIBLE);
        load();
        listView_rs.setVisibility(View.VISIBLE);



    }
    void load()
    {
        if(size > 0) {



            {
                Cursor res = db.getAllData();
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    String title = res.getString(1);
                    Log.d("descriptionRS" , "" + title);
                    int i = -1;
                    if (tempFb_title.contains(title)) {
                        title_arrlist.add(res.getString(1));
                        if(hashMap.get(title) != null){
                            i = hashMap.get(title);
                            desc_arrlist.add(MainActivity.tempData.get(i).getDescription());
                            image_arrlist.add(MainActivity.tempData.get(i).getImageUrl());
                        }

                    }
//                buffer.append("Id :" + res.getString(0) + "\n");
//                buffer.append("title :" + res.getString(1) + "\n");
//                buffer.append("count :" + res.getString(2) + "\n");


                }
                //showMessage("title : ",buffer.toString());
            }
            mTitle = new String[title_arrlist.size()];
            mdesc = new String[title_arrlist.size()];
            mimages = new String[title_arrlist.size()];
            for(int i = 0;i < title_arrlist.size(); i++)
            {
                mTitle[i] = title_arrlist.get(i);
                mdesc[i] = desc_arrlist.get(i);
                mimages[i] = image_arrlist.get(i);

            }
            MyAdapter adapter = new MyAdapter(getActivity(), mTitle, mdesc, mimages);
            listView_rs.setAdapter(adapter);

            listView_rs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                    Intent i_newshowdetails = new Intent(getActivity(), showIndItemFromHistory.class);
                    i_newshowdetails.putExtra("title", mTitle[position]);
                    i_newshowdetails.putExtra("index", position);
                    //Toast.makeText(CaptureResult.this, mTitle[position], Toast.LENGTH_SHORT).show();
                    startActivity(i_newshowdetails);
                }
            });
        }
    }


}
