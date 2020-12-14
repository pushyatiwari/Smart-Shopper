package com.example.madproject4.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.madproject4.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class homefragment_base extends Fragment {


    public homefragment_base() {
        // Required empty public constructor
    }
    String guide  = "Choose products smartly -  for a healthy lifestyle.\n";
    //https://cdn.trendhunterstatic.com/thumbs/smart-shopping-cart.jpeg
   // https://image.shutterstock.com/image-photo/invest-your-health-slate-blackboard-600w-289592738.jpg
String imgUrl = "https://cdn.trendhunterstatic.com/thumbs/smart-shopping-cart.jpeg";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_homefragment_base, container, false);

        TextView txt = view.findViewById(R.id.txthome_guide);
        txt.setText(guide);
        Button btn = view.findViewById(R.id.home_takepic_btn);
        Button search_btn = view.findViewById(R.id.home_takepic_searchfood);
        ImageView imageView = view.findViewById(R.id.home_pic);

//        Picasso.with(getActivity())// Context
//                .load(imgUrl).fit().centerCrop() // URL or file
//                .into(imageView); // An ImageView object to show the loaded image
        Picasso.with(getContext()).load(imgUrl).fit()
                .placeholder(R.drawable.ic_beach)
                .error(R.drawable.ic_error)
                .into(imageView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTakePicture fragmentTakePicContainer = new FragmentTakePicture();
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null)
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                        .replace(R.id.fragment_container_home, fragmentTakePicContainer)
                        .commit();
            }
        });
        //android.R.anim.slide_in_left
        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFoodByNutritionix searchFoodByNutritionix = new SearchFoodByNutritionix();
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null)
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                        .replace(R.id.fragment_container_home, searchFoodByNutritionix)
                        .commit();

            }
        });


        return view;
    }

}
