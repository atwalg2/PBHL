package com.pbhl.pbhl;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.*;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.*;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

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

//    LinearLayout item;

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
        // TODO: Use a more specific parent


        TwitterAuthConfig authConfig = TwitterCore.getInstance().getAuthConfig();
//        TwitterAuthToken authToken = Twitter.getSessionManager().getActiveSession().getAuthToken();
//        OAuthSigning oauthSigning = new OAuthSigning(authConfig, authToken);
//
//
//        Map<String, String> authHeaders = oauthSigning.getOAuthEchoHeadersForVerifyCredentials();
//
//        try {
//            URL url = new URL("https://api.twitter.com/1.1/account/verify_credentials.json");
//            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            for (Map.Entry<String, String> entry : authHeaders.entrySet()) {
//                connection.setRequestProperty(entry.getKey(), entry.getValue());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        var options = {
//                url: 'https://api.twitter.com/1.1/account/verify_credentials.json',
//                headers: {
//            'Authorization': req.headers['x-verify-credentials-authorization']
//        }
//};
//
//// Make the request
//        request(options, function (error, response, body) {
//            if (!error && response.statusCode == 200) {
//
//                // If twitter responds with a 200, the echo call was authorized
//
//                // TODO: do stuff
//
//                next();
//            } else {
//                res.send(401, 'Unauthorized');
//                next();
//            }
//        });



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        URL url = new URL("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=twitterapi&count=2")
        // TODO: Use a more specific parent
        View root = inflater.inflate(R.layout.fragment_third, container, false);

//        final LinearLayout item = (LinearLayout) root.findViewById(R.id.standings_container);
        final ViewGroup parentView = (ViewGroup) root.findViewById(R.id.twitter_container);   //getDecorView().getRootView();
        // TODO: Base this Tweet ID on some data from elsewhere in your app
        long tweetId = 848043595827433472L;
//        long tweetId = 5;
        TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                TweetView tweetView = new TweetView(getContext(), result.data);
//                item.addView(tweetView);
                parentView.addView(tweetView);
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Load Tweet failure", exception);
            }
        });
        tweetId = 847935348697116673L;
//        long tweetId = 5;
        TweetUtils.loadTweet(tweetId, new Callback<Tweet>() {
            @Override
            public void success(Result<Tweet> result) {
                TweetView tweetView = new TweetView(getContext(), result.data);
//                item.addView(tweetView);
                parentView.addView(tweetView);
            }
            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Load Tweet failure", exception);
            }
        });


        // Inflate the layout for this fragment
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
