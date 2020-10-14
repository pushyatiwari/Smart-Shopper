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
    String guide  = "Hi, this app focuses on the product awareness\n" +
            "Directions to use the app:\n" +
            "Take picture of ingredients of the product and \n" +
            "get the details of ingredients used in product, \n" +
            "and decide whether to buy a product or not";
String imgUrl = "https://image.freepik.com/free-vector/healthy-foodstuff-paper-package-basket-food-item-milk-green-herb-isolated-white-cartoon-illustration-supermarket-shopping-vegetable-fruit-meal_169479-927.jpg";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_homefragment_base, container, false);

        TextView txt = (TextView) view.findViewById(R.id.txthome_guide);
        txt.setText(guide);
        Button btn = (Button) view.findViewById(R.id.home_takepic_btn);
        ImageView imageView = (ImageView) view.findViewById(R.id.home_pic);

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
                        .replace(R.id.fragment_container_home, fragmentTakePicContainer)
                        .commit();
            }
        });


        return view;
    }

}
