package com.example.madproject4.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.madproject4.Adapters.MyAdapter;
import com.example.madproject4.R;

public class HistoryFragment extends Fragment {
    View view;
    ListView listView;
    String acetate = "https://img.rolandberger.com/content_assets/content_images/captions/rb_pub_17_011_foc_us_chemical_winners_image_image_caption_none.jpg";
    String url = "https://s.alicdn.com/@sc01/kf/HTB1IwrUd25G3KVjSZPxq6zI3XXag.jpg_300x300.jpg";
    String AluminiumSodiumsulfte = "https://5.imimg.com/data5/BP/AF/MY-26952425/textile-auxiliaries-chemical-500x500.jpg";
    String benzoateurl= "https://d37ky63zmmmzfj.cloudfront.net/production/itemimages/packed_food/sauces_ketchup/other_sauces/cookwell_sodiumbenzoate_25gm.jpg";
    String[] mTitle = {"Calcium Acetate", "Aluminium sodium sulfate(E521)", "Benzoate",
            "Amino acid", "Calcium Diglutamate",
            "Calcium Acetate", "Aluminium sodium sulfate(E521)", "Benzoate", "Amino acid", "Acetylated oxidized starch(E1451)"};
    String[] mDescription = {"Substance is harmless...", "health affecting, found...",
            "may cause asthma and irritation of the lungs...", "basic elements ...", "May cause adverse effects...",
            "Substance is harmless...", "health affecting, found...",
            "may cause asthma and irritation of the lungs...", "basic elements ...", "Thickener, emulsifier, added to food for babies..."};
    String[] images = {acetate, AluminiumSodiumsulfte, benzoateurl, acetate, url, acetate, AluminiumSodiumsulfte, benzoateurl, acetate, url};

    public HistoryFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.history_fragment, container, false);

        listView = view.findViewById(R.id.history_listview);
        MyAdapter adapter = new MyAdapter(getActivity(), mTitle, mDescription, images);
        listView.setAdapter(adapter);
        return view;


    }
}