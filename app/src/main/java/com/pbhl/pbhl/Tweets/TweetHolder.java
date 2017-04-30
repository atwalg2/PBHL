//package com.pbhl.pbhl.Tweets;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//
//import com.google.gson.Gson;
//
//import java.io.BufferedReader;
//import java.io.ByteArrayOutputStream;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//
//import static android.content.Context.MODE_PRIVATE;
//
///**
// * Created by Home on 2017-04-29.
// *
// *  An ArrayList of our Tweets to be used in creating
// *
// *
// */
//
//public class TweetHolder {
//    ArrayList<TweetInfo> tweets;
//
//    public TweetHolder() {
//        this.tweets = new ArrayList<>();
//    }
//
//    public void addTweet(TweetInfo tweet){
//        tweets.add(tweet);
//    }
//
//    public ArrayList<TweetInfo> getTweets(){
//        return this.tweets;
//    }
//
//    public void loadStoredTweets(Context ctx) throws IOException {
//        Gson gson = new Gson();
//
//        FileInputStream fis = ctx.openFileInput("PermStore");
//        InputStreamReader isr = new InputStreamReader(fis);
//        BufferedReader bufferedReader = new BufferedReader(isr);
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = bufferedReader.readLine()) != null) {
//            sb.append(line);
//        }
//    }
//
////    public class DbBitmapUtility {
////
////        // convert from bitmap to byte array
////        public static byte[] getBytes(Bitmap bitmap) {
////            ByteArrayOutputStream stream = new ByteArrayOutputStream();
////            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
////            return stream.toByteArray();
////        }
////
////        // convert from byte array to bitmap
////        public static Bitmap getImage(byte[] image) {
////            return BitmapFactory.decodeByteArray(image, 0, image.length);
////        }
////    }
////    public void storifyTweets(Context ctx){
//
////        try{
////            SQLiteDatabase permStore = ctx.openOrCreateDatabase("Tweets", MODE_PRIVATE, null);
////            permStore.execSQL("CREATE TABLE IF NOT EXISTS tweets();
////        }
////        catch(Exception e){
////            e.printStackTrace();
////        }
//
//
//
////        SQLiteDatabase mydatabase = ctx.openOrCreateDatabase("PermStore.db",MODE_PRIVATE,null);
////        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS tweets (sname VARCHAR, content VARCHAR, tweetPic  );");
////
////
////        // First turn out tweets into a Json String
////        Gson gson = new Gson();
////        String jsonString = gson.toJson(tweets);
////
////        try {
////            FileOutputStream fos = ctx.openFileOutput("PermStore", MODE_PRIVATE);
////
////            fos.write(jsonString.getBytes());
////            fos.close();
////        }
////        catch(IOException ignored){
////        }
////    }
//}
