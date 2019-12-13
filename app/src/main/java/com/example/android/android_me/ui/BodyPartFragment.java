package com.example.android.android_me.ui;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {
    private List<Integer> mImageIds;
    private int mIndex;

    //final variables to store the info aboout the list of images and index
    public static final String IMAGE_ID_LIST = "image_ids";
    public static final String LIST_INDEX = "list_index";


    /**
     * This empty constructor is necessary or what we can say is required to call the method
     */
    public BodyPartFragment() {
    }

    /**
     * Now override the predefined function(method) to set a view which inflates the desired layout using
     * the available image resources in the drawable folder
     */
    @Override



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        //Now inflate the Android_Me Fragment Layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);


        //Now find out the image that is supposed to be displayed on this layout
        final ImageView imageview = (ImageView) rootView.findViewById(R.id.body_part_image_view);


        /**Now set this image on the screen(the image is ready to be displayed
         *       * but it won't be displayed until the corresponding command is not found)
         *      * (by performing only the code given below image is not going to be displayed)
         *
         imageview.setImageResource(AndroidImageAssets.getBodies().get(0));
         //by this code all the imageviews(selected so far) will be displayed on the screen */


        //if a list of images exists and then set it to the correct image-index
        if (mImageIds != null) {
            imageview.setImageResource(mImageIds.get(mIndex));          //Set an onClickListener on the imageviews
            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //increase the index of the image as long as it is less than or equal to the size of image ids list
                    if (mIndex < mImageIds.size() - 1) {
                        mIndex++;
                        //if the index reaches to its maximum then, on next click set j it to zero
                    } else {
                        mIndex = 0;
                    }
                    imageview.setImageResource(mImageIds.get(mIndex));
                }
            });
            //else generate a toast
        } else {
            Toast.makeText(getContext(), "File not Found!!!", Toast.LENGTH_SHORT).show();
        }

        return rootView;
    }

    public void setImageIds(List<Integer> ImageIds) {
        mImageIds = ImageIds;
    }

    public void setListIndex(int Index) {
        mIndex = Index;
    }

    /**
     * By doing so we are saving the current state of the fragment
     * so that it does not get restored when orientation is changed from vertical to horizontal or vice versa
     * @Bundle it refers that the current state reached so far is being bundled
     */
    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX, mIndex);
    }
}