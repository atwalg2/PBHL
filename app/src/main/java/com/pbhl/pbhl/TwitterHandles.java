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

    public static TwitterHandles getInstance() {
        return ourInstance;
    }

    private TwitterHandles() {
    }

    public void setPBHLOfficial(final List<Status> PBHLOfficial) {
        this.PBHLOfficial = PBHLOfficial;

        new AsyncTask<Void,Void,Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
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

    private InputStream sendingGetRequest(String site) throws Exception {
            final String USER_AGENT = "Mozilla/5.0";


            String urlString = site;
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // By default it is GET request
            con.setRequestMethod("GET");

           //add request header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("Sending get request : " + url);
            System.out.println("Response code : " + responseCode);


            return con.getInputStream();
            // Reading response from input Stream
//            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//            String output;
//            StringBuffer response = new StringBuffer();
//
//            while ((output = in.readLine()) != null) {
//            response.append(output);
//            }
//            in.close();
//
//            //printing result from response
//            System.out.println(response.toString());
    }

}
