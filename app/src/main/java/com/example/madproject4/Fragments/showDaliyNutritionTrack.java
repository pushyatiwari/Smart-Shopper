package com.example.madproject4.Fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.madproject4.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class showDaliyNutritionTrack extends Fragment {
    TextView p_txtsp, carb_txtsp, vita_txtsp,vitd_txtsp,vite_txtsp,
            vitc_txtsp,sugar_txtsp,calc_txtsp,cal_txtsp,
            p_txtsp_left, carb_txtsp_left, vita_txtsp_left,vitd_txtsp_left,vite_txtsp_left,
            vitc_txtsp_left,sugar_txtsp_left,calc_txtsp_left,cal_txtsp_left;
    public  final String MyPREFERENCES = "myFoodNutritionPrefs" ;
    public  final String MyPREFERENCES2 = "MyPrefs_date" ;
    SharedPreferences sharedpreferences;
    SharedPreferences sharedpreferences2;
    public String age, gender;

    SharedPreferences.Editor editor2;
    Button change_pref;

    public static final String carb_pref = "Carbohydrate";
    public static final String enrgy_pref = "Energy";
    public static final String protein_pref = "Protein";
    public static final String vita_pref = "Vitamin A";
    public static final String vitd_pref = "Vitamin D";
    public static final String vite_pref = "Vitamin E";
    public static final String vitc_pref = "Vitamin C";
    public static final String sugar_pref = "Sugar";
    public static final String calcium_pref = "Calcium";
    public static final String calories_pref = "Calories";

    public showDaliyNutritionTrack() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_show_daliy_nutrition_track, container, false);
        p_txtsp = view.findViewById(R.id.protein_sp);
        carb_txtsp = view.findViewById(R.id.carb_sp);
        vita_txtsp = view.findViewById(R.id.vita_sp);
        vitd_txtsp = view.findViewById(R.id.vitd_sp);
        vite_txtsp = view.findViewById(R.id.vite_sp);
        vitc_txtsp = view.findViewById(R.id.vitc_sp);
        sugar_txtsp = view.findViewById(R.id.sugar_sp);
        calc_txtsp = view.findViewById(R.id.calcium_sp);
        cal_txtsp = view.findViewById(R.id.cal_sp);

        p_txtsp_left = view.findViewById(R.id.protein_sp_left);
        carb_txtsp_left = view.findViewById(R.id.carb_sp_left);
        vita_txtsp_left = view.findViewById(R.id.vita_sp_left);
        vitd_txtsp_left = view.findViewById(R.id.vitd_sp_left);
        vite_txtsp_left = view.findViewById(R.id.vite_sp_left);
        vitc_txtsp_left = view.findViewById(R.id.vitc_sp_left);
        sugar_txtsp_left = view.findViewById(R.id.sugar_sp_left);
        calc_txtsp_left = view.findViewById(R.id.calcium_sp_left);
        cal_txtsp_left = view.findViewById(R.id.cal_sp_left);
        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        sharedpreferences2 = getActivity().getSharedPreferences(MyPREFERENCES2, Context.MODE_PRIVATE);
        change_pref = view.findViewById(R.id.change_pref);
        editor2 = sharedpreferences2.edit();
        load();
        Button b = view.findViewById(R.id.clear);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getActivity().getSharedPreferences(MyPREFERENCES, 0);
                preferences.edit().clear().commit();
                load();
            }
        });
        change_pref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              showAlertDialogButtonClicked();

            }
        });
        Toast.makeText(getActivity(), "Showing for \n age "+sharedpreferences2.getString("age", "")
                +", "+ sharedpreferences2.getString("gender", ""), Toast.LENGTH_SHORT).show();

        return  view;
    }


    public void load(){

        Float temp_carb_pref = sharedpreferences.getFloat(carb_pref,0);
        Float temp_prot_pref = sharedpreferences.getFloat(protein_pref,0);
        Float temp_vita_pref = sharedpreferences.getFloat(vita_pref,0);
        Float temp_vitd_pref = sharedpreferences.getFloat(vitd_pref,0);
        Float temp_vite_pref = sharedpreferences.getFloat(vite_pref,0);
        Float temp_vitc_pref = sharedpreferences.getFloat(vitc_pref,0);
        Float temp_sugar_pref = sharedpreferences.getFloat(sugar_pref,0);
        Float temp_calcium_pref = sharedpreferences.getFloat(calcium_pref,0);
        Float temp_cal_pref = sharedpreferences.getFloat(calories_pref,0);

        carb_txtsp.setText(temp_carb_pref+"");
        p_txtsp.setText(temp_prot_pref+"");
        vita_txtsp.setText(temp_vita_pref+"");
        vitd_txtsp.setText(temp_vitd_pref+"");
        vite_txtsp.setText(temp_vite_pref+"");
        vitc_txtsp.setText(temp_vitc_pref+"");
        sugar_txtsp.setText(temp_sugar_pref+"");
        calc_txtsp.setText(temp_calcium_pref+"");
        cal_txtsp.setText(temp_cal_pref+"");
        sharedpreferences2 = getActivity().getSharedPreferences(MyPREFERENCES2, Context.MODE_PRIVATE);
        String age = sharedpreferences2.getString("age", "");
        int age_int = Integer.parseInt(age);
        String gender = sharedpreferences2.getString("gender", "");

        p_txtsp_left.setText((56 - temp_prot_pref)+" g");
        carb_txtsp_left.setText(325 - temp_carb_pref + " g");
        if(gender.equals("male"))
        vita_txtsp_left.setText(900 - temp_vita_pref+" mcg");
        if(gender.equals("female"))
            vita_txtsp_left.setText(700 - temp_vita_pref+" mcg");
        if(age_int < 1)
        vitd_txtsp_left.setText(400 - temp_vitd_pref+" IU");
        else if(age_int < 70)
            vitd_txtsp_left.setText(600 - temp_vitd_pref+" IU");
        else if(age_int > 70)
            vitd_txtsp_left.setText(700 - temp_vitd_pref+" IU");
        vite_txtsp_left.setText(15 - temp_vite_pref+" mg");
        vitc_txtsp_left.setText(90 - temp_vitc_pref+" mg");
        sugar_txtsp_left.setText(25 - temp_sugar_pref+" g");
        calc_txtsp_left.setText(2500 - temp_calcium_pref+" mg");
        cal_txtsp_left.setText(2000 - temp_cal_pref+" kcal");

    }
    public void showAlertDialogButtonClicked()
    {

        // Create an alert builder
        AlertDialog.Builder builder
                = new AlertDialog.Builder(getActivity());
        builder.setTitle("Details");

        // set the custom layout
        final View customLayout
                = getLayoutInflater()
                .inflate(
                        R.layout.user_details,
                        null);
        builder.setView(customLayout);

        // add a button
        builder
                .setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(
                                    DialogInterface dialog,
                                    int which)
                            {

                                // send data from the
                                // AlertDialog to the Activity
                                EditText editText
                                        =customLayout.findViewById(
                                        R.id.userage_edt);
                                RadioButton r1   =(RadioButton) customLayout.findViewById(
                                        R.id.male_r);
                                RadioButton r2   =customLayout.findViewById(
                                        R.id.female_r);

                                sendDialogDataToActivity(
                                        editText.getText().toString(), r1.isChecked(),r2.isChecked());
                            }
                        });

        // create and show
        // the alert dialog
        AlertDialog dialog
                = builder.create();
        dialog.show();
    }

    private void sendDialogDataToActivity(String data, Boolean m, Boolean f)
    {
       // Toast.makeText(getActivity(), "data sent", Toast.LENGTH_SHORT).show();
        age = data;
        if(m)
        {
            gender = "male";
        }
        else{
            gender = "female";
        }

            editor2.putString("age", age);
            editor2.putString("gender", gender);
            editor2.commit();
        Toast.makeText(getActivity(), "Showing for \n age "+sharedpreferences2.getString("age", "")
                +", "+ sharedpreferences2.getString("gender", ""), Toast.LENGTH_SHORT).show();
        load();
    }

}
