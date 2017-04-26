package com.pbhl.pbhl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.renderscript.ScriptGroup;
import android.util.Log;

import com.twitter.sdk.android.core.models.Image;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import io.fabric.sdk.android.services.concurrency.AsyncTask;
import io.fabric.sdk.android.services.network.HttpRequest;
import twitter4j.MediaEntity;
import twitter4j.Status;

/**
 * Created by Home on 2017-04-24.
 */

public class TwitterHandles {
    private static final TwitterHandles ourInstance = new TwitterHandles();
    private List<twitter4j.Status> PBHLOfficial;  //PBHL_EDM
    private Bitmap[] pbhlArr;
    private List<twitter4j.Status> BobMackenzie;  //PBHL_BOB_MACKENZIE
    private List<twitter4j.Status> ElliotFriedman;
    private Bitmap[] elliotArr;

    private Bitmap[] profilePics; // PBHL, BOB, ELLIOT

    private int check = 0;

    public static TwitterHandles getInstance() {
        return ourInstance;
    }

    private TwitterHandles() {
    }

    public void setPBHLOfficial(final List<Status> PBHLOfficial) {
        this.PBHLOfficial = PBHLOfficial;
        if (check == 0){
            profilePics = new Bitmap[3];
        }
        check++;
        new AsyncTask<Void,Void,Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    profilePics[0] = BitmapFactory.decodeStream((InputStream)new URL(PBHLOfficial.get(0).getUser().getProfileImageURL()).getContent());
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                elliotArr = new Bitmap[ElliotFriedman.size()];
                pbhlArr = new Bitmap[PBHLOfficial.size()];
                for(int x=0; x<PBHLOfficial.size();x++){
                    MediaEntity[] media = PBHLOfficial.get(x).getMediaEntities();

                    if (media.length != 0){
                        try {
                            Bitmap bmp = BitmapFactory.decodeStream((InputStream)new URL(media[0].getMediaURL()).getContent());

                            Log.v("IMAGE URL ON BUILD:  ", media[0].getMediaURL());
                            pbhlArr[x] = bmp;
                            Log.v("IMAGE WIDTH:   ",Integer.toString(bmp.getWidth()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                return null;
            }
        }.execute(null,null,null);
    }


    public void setBobMackenzie(List<Status> bobMackenzie) {
        BobMackenzie = bobMackenzie;
    }

    public void setElliotFriedman(final List<Status> elliotFriedman) {
        ElliotFriedman = elliotFriedman;

        new AsyncTask<Void,Void,Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                elliotArr = new Bitmap[ElliotFriedman.size()];
                for(int x=0; x<ElliotFriedman.size();x++){
                    MediaEntity[] media = ElliotFriedman.get(x).getMediaEntities();
                    if (media.length != 0){
                        try {

                            Bitmap bmp = BitmapFactory.decodeStream((InputStream)new URL(media[0].getMediaURL()).getContent());

                            Log.v("HELLO", media[0].getMediaURL());
                            elliotArr[x] = bmp;
                            Log.v("LOCATION",Integer.toString(bmp.getWidth()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                return null;
            }
        }.execute(null,null,null);
    }

    public List<Status> getPBHLOfficial() {
        return PBHLOfficial;
    }

    public List<Status> getBobMackenzie() {
        return BobMackenzie;
    }

    public List<Status> getElliotFriedman() {
        return ElliotFriedman;
    }

    public Bitmap[] getElliotArr(){
        return elliotArr;
    }

    public Bitmap[] getPbhlArr() {
        return pbhlArr;
    }

    public Bitmap getPbhlProfile(){
        return profilePics[0];
    }

}
