package com.example.madproject4.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.madproject4.Activities.showNutritionDetails;
import com.example.madproject4.Adapters.RecyclerViewAdapter;
import com.example.madproject4.Model.Food;
import com.example.madproject4.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFoodByNutritionix extends Fragment  implements RecyclerViewAdapter.onFoodListener {


    private View view;
    //https://trackapi.nutritionix.com/v2/search/instant?query=grilled cheese
    private final String JSON_URL1 = "https://trackapi.nutritionix.com/v2/search/instant?query=";
    private RequestQueue requestQueue;
    private List<Food> listFood;
    private RecyclerView recyclerView;
    private EditText search_edt;
    private Button search_food;
    String search_query;
    String thmb;
    ImageView voice_btn;
    ArrayList<String> individualItem_aotu  = new ArrayList<>();

    String individualItem[];
    public SearchFoodByNutritionix() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_helpful_products, container, false);
        listFood = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerviewid);
        search_edt = view.findViewById(R.id.edit_searchfood);
        voice_btn = view.findViewById(R.id.btnSpeak);
        search_food  = view.findViewById(R.id.search_food_btn);
        search_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listFood.clear();
                search_query = search_edt.getText().toString();
                String url = JSON_URL1.concat(search_query);
                Log.d("url ", "onClick: "+ url+ " , "+search_query);
                jsonrequest(url);
            }
        });
        voice_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(intent, 10);
                } else {
                    Toast.makeText(getActivity(), "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    listFood.clear();
                    search_query =result.get(0);
                    String url = JSON_URL1.concat(search_query);
                    Log.d("url ", "onClick: "+ url+ " , "+search_query);
                    jsonrequest(url);
                }
                break;
        }
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
                        Food food = new Food() ;
                        food.setName(jsonObject.getString("food_name"));
                        food.setNf_calories(jsonObject.getString("nf_calories"));
                        food.setBrand_name(jsonObject.getString("brand_name"));
                        JSONObject photo = jsonObject.getJSONObject("photo");
                        thmb = photo.getString("thumb");
                        food.setThumb(thmb);
                        food.setNix_item_id(jsonObject.getString("nix_item_id"));
                        listFood.add(food);
                    }
                    setuprecyclerview(listFood);

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
        Food p = listFood.get(position);
        Intent i = new Intent(getActivity(), showNutritionDetails.class);
        i.putExtra("nix_id",p.getNix_item_id());
        i.putExtra("nix_name",p.getName());
        i.putExtra("nix_thumb",thmb);
        startActivity(i);
        Log.d("clicked: ", "onClick: " + p.getName()+
                " -- "+p.getBrand_name() + "id" + p.getNix_item_id());

    }
}