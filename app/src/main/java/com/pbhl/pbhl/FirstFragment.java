package com.pbhl.pbhl;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    LinearLayout item;

    Team T1 = new Team("HENRY SINGER");
    Team T2 = new Team("ALL INDIA");
    Team T3 = new Team("SUNNY VALLEY HOMES");
    Team T4 = new Team("PIZZA 38");
    Team T5 = new Team("ROYAL STAR PROPERTIES");
    Team T6 = new Team("VACUUMS R US");
    Team T7 = new Team("EYES ON 34TH");
    Team T8 = new Team("SOUTHTOWN HYUNDAI");

    Team[] teams = {T1,T2,T3,T4,T5,T6,T7,T8};

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FirstFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstFragment newInstance(String param1, String param2) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        FrameLayout myBox = (FrameLayout) getLayoutInflater();
//        FrameLayout myBox = (FrameLayout) getView().findViewById(R.id.league_box_frame_layout);
//        TextView text = (TextView) myBox.findViewById(R.id.left_team);
//        text.setText("FUCK");

//        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/abc.ttf");
//        tx.setTypeface(custom_font);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_first, container, false);

        item = (LinearLayout) root.findViewById(R.id.games_container);

        for(int i=0; i<teams.length;i++) {
            View child = getActivity().getLayoutInflater().inflate(R.layout.league_box, null);
            TextView left = (TextView) child.findViewById(R.id.left_team);
            TextView right = (TextView) child.findViewById(R.id.right_team);

            left.setText(teams[i].getTeamName());
            i++;
            right.setText(teams[i].getTeamName());

            item.addView(child);
        }
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
