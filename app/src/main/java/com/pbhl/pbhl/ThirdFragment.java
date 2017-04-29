package com.pbhl.pbhl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.ImageReader;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.services.concurrency.AsyncTask;
import twitter4j.MediaEntity;
import twitter4j.Status;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageButton full;
    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "j1qW8lZhpTqxnfO7HSq9eUHKF";
    private static final String TWITTER_SECRET = "Ah1AKbpxbdGy0O5aAjfTuxoTq8G8QoSJqNaYeWfopcA8xTZviK";
    LinearLayout item;

    private OnFragmentInteractionListener mListener;

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this.getActivity(), new Twitter(authConfig));
        // TODO: Use a more specific parent



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_CONSUMER_KEY, TWITTER_CONSUMER_SECRET);
//        Fabric.with(getActivity(), new com.twitter.sdk.android.Twitter(authConfig));
        // TODO: Use a more specific parent
        View root = inflater.inflate(R.layout.fragment_third, container, false);
        full = (ImageButton) root.findViewById(R.id.full_screen_tweet);

        TwitterHandles handles = TwitterHandles.getInstance();
        List<Status> Mack = handles.getBobMackenzie();
        List<Status> Elliot = handles.getElliotFriedman();
        Bitmap[] EPics = handles.getElliotArr();
        List<Status> PBHL = handles.getPBHLOfficial();
        Bitmap[] PPics = handles.getPbhlArr();
//        Log.v("BITMAP ARR SIZE:-----:",Integer.toString(PPics.length));


        item = (LinearLayout) root.findViewById(R.id.twitter_container);

            for(int i=0; i<PBHL.size();i++) {
                View child = getActivity().getLayoutInflater().inflate(R.layout.tweet_box, null);
                TextView name = (TextView) child.findViewById(R.id.twitter_name);
                TextView desc = (TextView) child.findViewById(R.id.twitter_text);

                MediaEntity[] media = PBHL.get(i).getMediaEntities(); //get the media entities from the status

                // LOAD PROFILE PICS
                RoundedImageView prof = (RoundedImageView) child.findViewById(R.id.profile);
                Bitmap pbhlpic =  handles.getPbhlProfile();
                pbhlpic.setDensity(Bitmap.DENSITY_NONE);
                prof.setImageDrawable(new BitmapDrawable(pbhlpic));


                if(media.length != 0){
                    ImageButton pic = (ImageButton) child.findViewById(R.id.tweet_image);

//                    Log.v("PICTURE:-----:",Integer.toString(i));

//                    pic.setImageBitmap(EPics[i]);
//                    Log.v("CHECKER  :::",Integer.toString(PPics[i].getWidth()));
                    pic.setPadding(0,500,0,0);
//                    PPics[i]
//                    pic.setBack
                    PPics[i].setDensity(Bitmap.DENSITY_NONE);
                    Drawable d = new BitmapDrawable(PPics[i]);
//                    RoundedBitmapDrawable roundBit = RoundedBitmapDrawableFactory.create(getResources(), )
                    pic.getLayoutParams().height = 950;
                    pic.getLayoutParams().width = 800;
                    pic.setBackground(d);

                    pic.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ImageButton but = (ImageButton) v;

                            full.setImageDrawable(but.getDrawable());
                            full.setVisibility(View.VISIBLE);
                        }
                    });

                }

                name.setText(PBHL.get(i).getUser().getName());
                desc.setText(PBHL.get(i).getText());




                item.addView(child);
            }

        return root;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
