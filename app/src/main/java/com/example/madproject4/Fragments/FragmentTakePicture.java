package com.example.madproject4.Fragments;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.madproject4.Activities.CaptureResult;
import com.example.madproject4.Database.DatabaseHelper;
import com.example.madproject4.MainActivity;
import com.example.madproject4.Model.Ingredients;
import com.example.madproject4.R;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class FragmentTakePicture extends Fragment {

    View view;
    ImageView imageView;

    private static final int CAMERA_REQUEST_CODE = 100;
    // DatabaseHelper myDb;
    String[] cameraPermission;
    String[] storagePermission;
    Uri image_uri;
   public static ArrayList<Ingredients> itemsArraylist;
     String finalTemp ;
    public FragmentTakePicture() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.takepicture_fragment, container, false);
        cameraPermission = new String[]{Manifest.permission.CAMERA};
        imageView = view.findViewById(R.id.imageView);
      //  myDb = new DatabaseHelper(getActivity());
        itemsArraylist = new ArrayList<>();
        cameraPermission = new String[]{Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};

        storagePermission = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        final Button button = view.findViewById(R.id.capture);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                switch (v.getId()) {
                    case R.id.capture:
                        if (!checkCameraPermission()) {
                            requestCameraPermission();
                            Log.i("onClick", "onClick: ");
                        } else {
                            pickCamera();
                            Log.i("onClick", "picking: ");
                        }
                        break;
                }
            }
        });



        return view;
    }


    private void requestCameraPermission() {
        requestPermissions(cameraPermission, CAMERA_REQUEST_CODE);
    }

    private boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);

        return result && result1;
    }

    private void pickCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "NewPic");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image To Text");
        image_uri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

//        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//       // cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,image_uri);
//        startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE);

        CropImage.activity()
                .start(getContext(), this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean writeStorageAccepted = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted && writeStorageAccepted) {
                        pickCamera();
                    } else {
                        // Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                    }

                }
                break;


        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            Log.i("crop", "onActivityResult: ");
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                imageView.setImageURI(resultUri);
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();

                TextRecognizer recognizer = new TextRecognizer.Builder(getActivity().getApplicationContext()).build();
                 itemsArraylist.clear();
                if (!recognizer.isOperational()) {
                    //Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                } else {
                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> items = recognizer.detect(frame);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < items.size(); i++) {
                        TextBlock myItem = items.valueAt(i);
                        sb.append(myItem.getValue());
                    }

                    Log.d("string buider ", "onActivityResult: " + sb.length());
                    if(itemsArraylist.size() > 0)
                    {

                        itemsArraylist.clear();
                    }
                    itemsArraylist.clear();
                    String temp = "";
                    for (int i = 0; i < sb.length(); i++) {
                        finalTemp = temp;
                        char ch = sb.charAt(i);
                        if (ch != ',') {
                            temp += ch;
                             if(temp.contains("INGREDIENTS") || temp.contains("INGREDIENTS:"))
                               {
                                temp = "";
                                continue;
                               }
                            finalTemp = temp;
                        } else {
                            finalTemp = temp;
                            //if that similar item exists in sql, take the title and push to arraylist.
//                            Cursor res = myDb.getAllData();
//                            if (res.getCount() == 0) {
//                                // show message
//                                showMessage("Error", "Nothing found");
//                                return;
//                            }

//                            while (res.moveToNext()) {
//
//                                String title = res.getString(1);
//                                Log.d("tempSQL", "onActivityResult: "+title+" , temp - "+temp);
//                                if(temp.toLowerCase().contains(title.toLowerCase())){
//                                    itemsArraylist.add(title);
//                                    break;
//                                }
//                             }

                            for (int j = 0; j < MainActivity.tempData.size(); j++) {
                                // Log.d("arraylist", "onActivityResult: " + itemsArraylist.get(i));
                                if(temp.toLowerCase().contains(MainActivity.tempData.get(j).getTitle().toLowerCase()))
                                    itemsArraylist.add(MainActivity.tempData.get(j));


                            }
                            Log.d("temp val", "onActivityResult: "+ temp);
                            temp = "";
                        }


                    }

                    for (int j = 0; j < itemsArraylist.size(); j++) {
                        Log.d("arraylist", "onActivityResult: " + itemsArraylist.get(j).getTitle());

                    }
                    {

                        Intent intent = new Intent(getActivity(), CaptureResult.class);
                        startActivity(intent);

                    }


                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                // Toast.makeText(this, "" + error, Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}
