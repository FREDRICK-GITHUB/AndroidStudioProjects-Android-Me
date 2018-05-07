package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {
    //Final strings to store state information about the list of images and list index
    public static final String IMAGE_ID_LIST = "image_ids";
    public static final String LIST_INDEX = "list_index";

    //Tag for logging
    private static  final String TAG = "BodyPartFragment";
    //Dynamic calling of images in fragments
    private List<Integer> mImageIds;
    private int mListIndex;
    //Mandatory constructor for instantiating the fragment
    public BodyPartFragment(){

    }
/*
  Inflates the fragment layout and sets any image resources
  */
@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    //Load the saved state (the list of images and list index) if there is one
    if (savedInstanceState != null){
    mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
    mListIndex = savedInstanceState.getInt(LIST_INDEX);
    }


    //Inflate the Android-Me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);
        //Get a reference to the ImageView in the fragment layout
    final ImageView imageView =  rootView.findViewById(R.id.body_part_image_view);
    //Set the image resource to display
    if(mImageIds != null){
        //Set the image resource to the list item at the stored index
            imageView.setImageResource(mImageIds.get(mListIndex));
        //Set a click listener on the image view
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListIndex < mImageIds.size()-1){
                    mListIndex++;
                } else {
                    //The end of the list has been reached, so return to the beginning index
                    mListIndex = 0;
                }
                //Set the image resource to the new list item
                imageView.setImageResource(mImageIds.get(mListIndex));
            }
        });
    }
    else {
//Log a message saying that the image id list is null
        Log.v(TAG,"This fragment has a null list of image id's");
    }


//    imageView.setImageResource(AndroidImageAssets.getHeads().get(0));
    //Return root view
    return rootView;
    }

    public void setImageIds(List<Integer> imageIds){
    mImageIds = imageIds;

    }
    public  void setListIndex(int index){
    mListIndex = index;

    }
    //Save the current state of this fragment
    public void onSaveInstanceState(Bundle currentState){
    currentState.putIntegerArrayList(IMAGE_ID_LIST,(ArrayList<Integer>) mImageIds);
    currentState.putInt(LIST_INDEX, mListIndex);
    }
}
