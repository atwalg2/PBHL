package com.pbhl.pbhl;

import android.content.Context;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import twitter4j.MediaEntity;
import twitter4j.Status;

/**
 * Created by Home on 2017-04-29.
 */

public class TweetInfo {
    private String tweetName;
    private String tweetContent;
    private Bitmap tweetImage;

    public TweetInfo(Status stat) throws IOException {
        MediaEntity[] media = stat.getMediaEntities();

        if(media.length != 0){
            try {
                tweetImage = BitmapFactory.decodeStream((InputStream) new URL(media[0].getMediaURL()).getContent());
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

        this.tweetName = stat.getUser().getScreenName();
        this.tweetContent = stat.getText();

    }

    public TweetInfo(String tweetName, String tweetContent, String tweetImageBase64){

        if(tweetName == "PBHL_EDM"){
            tweetName = "PBHL";
        }
        this.tweetName = tweetName;

        this.tweetContent = tweetContent;
//        this.tweetImage = StringToBitMap(tweetImageBase64);
    }

    public void setTweetImage(Bitmap tweetImage) {
        this.tweetImage = tweetImage;
    }

    public String getTweetName() {
        return tweetName;
    }

    public String getTweetContent() {
        return tweetContent;
    }

    public Bitmap getTweetImage() {
        return tweetImage;
    }


    public void addToDatabase(Context ctx) {

        try {
//            Log.i(" ------ LAST: ", "DATABASE CREATED");
            SQLiteDatabase permStore = ctx.openOrCreateDatabase("Tweets", Context.MODE_PRIVATE, null);
            permStore.execSQL("DROP TABLE IF EXISTS tweets");
            permStore.execSQL("CREATE TABLE IF NOT EXISTS tweets (screen_name VARCHAR, tweet_text VARCHAR, tweet_image VARCHAR)");
//            Log.i(" ------ RESULT: ", "DATABASE CREATED");
            Log.i(" ------ RESULT: ", "DATABASE CREATED");

            DatabaseUtils.sqlEscapeString(this.tweetContent);
            if(this.tweetImage!=null){
                String tweetImageBase64 = BitMapToString(this.tweetImage);
                permStore.execSQL( "INSERT INTO tweets (screen_name, tweet_text, tweet_image) VALUES ('" + this.tweetName + "', " + DatabaseUtils.sqlEscapeString(this.tweetContent) + ", '" + tweetImageBase64 + "')" );
            }
            else {
                permStore.execSQL("INSERT INTO tweets (screen_name, tweet_text, tweet_image) VALUES ('" + this.tweetName + "'," + DatabaseUtils.sqlEscapeString(this.tweetContent) + ", null)");
            }

            permStore.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    // CONVERT BITMAPS TO BASE64 ENCODED STRING
    public String BitMapToString(Bitmap bitmap){

        ByteArrayOutputStream baos = new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,1, baos);
        byte [] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);

        return temp;
    }

    public Bitmap StringToBitMap(String encodedString){

        try {
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

}