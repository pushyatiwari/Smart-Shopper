package com.example.madproject4.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.madproject4.Activities.CaptureResult;
import com.example.madproject4.Activities.ShowDetailsCapture;
import com.example.madproject4.Activities.showNutritionDetails;
import com.example.madproject4.Adapters.MyAdapter;
import com.example.madproject4.Adapters.RecyclerViewAdapter;
import com.example.madproject4.MainActivity;
import com.example.madproject4.Model.Food;
import com.example.madproject4.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class SafestProducts extends Fragment  implements RecyclerViewAdapter.onFoodListener {


    private View view;
    //https://trackapi.nutritionix.com/v2/search/instant?query=grilled cheese
    private final String JSON_URL1 = "https://trackapi.nutritionix.com/v2/search/instant?query=";
    private RequestQueue requestQueue;
    private List<Food> lstAnime;
    private RecyclerView recyclerView;
    private EditText search_edt;
    private Button search_food;
    String search_query;
    public SafestProducts() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_helpful_products, container, false);
        lstAnime = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerviewid);
        search_edt = view.findViewById(R.id.edit_searchfood);

        search_food  = view.findViewById(R.id.search_food_btn);
        search_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lstAnime.clear();
                search_query = search_edt.getText().toString();
                String url = JSON_URL1.concat(search_query);
                Log.d("url ", "onClick: "+ url+ " , "+search_query);
                jsonrequest(url);
            }
        });

        return view;

    }
        private void jsonrequest (String url) {


            JsonObjectRequest req =  new JsonObjectRequest(Request.Method.GET, url,
                    null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("branded");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                           String name = jsonObject.getString("food_name");
                            Log.d("food name", name);
                            Food anime = new Food() ;
                            anime.setName(jsonObject.getString("food_name"));
                            anime.setNf_calories(jsonObject.getString("nf_calories"));
                            anime.setBrand_name(jsonObject.getString("brand_name"));
                            JSONObject photo = jsonObject.getJSONObject("photo");
                            String thmb = photo.getString("thumb");
                            anime.setThumb(thmb);
                            anime.setNix_item_id(jsonObject.getString("nix_item_id"));
                            lstAnime.add(anime);
                        }
                        setuprecyclerview(lstAnime);

                        Log.d("Response food", response.getString("branded"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.d("Error", "Error: " + error.getMessage());
                    Log.d("error food", "error");
                    Toast.makeText(getActivity(), "" + error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            })
            {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("x-app-id ", "fc2dc68b");
                    headers.put("x-app-key", "655b6f137722571fb6a530c658656595");
                    return headers;
                }
            };

            requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(req);


        }


        private void setuprecyclerview (final List < Food > lstAnime) {


            RecyclerViewAdapter myadapter = new RecyclerViewAdapter(getActivity(), lstAnime, this);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            recyclerView.setAdapter(myadapter);

        }

    @Override
    public void onFoodClick(int position) {
        Food p = lstAnime.get(position);
        Intent i = new Intent(getActivity(), showNutritionDetails.class);
        i.putExtra("nix_id",p.getNix_item_id());
        startActivity(i);
            Log.d("clicked: ", "onClick: " + p.getName()+
                 " -- "+p.getBrand_name() + "id" + p.getNix_item_id());

    }
}

