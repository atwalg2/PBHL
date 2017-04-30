package com.pbhl.pbhl;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.twitter.sdk.android.core.models.Tweet;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 2017-04-29.
 */

public class TwitterAdapter extends RecyclerView.Adapter<TwitterAdapter.TweetViewHolder> {

    private List<TweetInfo> tweetList;


    //    ---   VIEW HOLDER   ---
    public static class TweetViewHolder extends RecyclerView.ViewHolder {
        protected TextView vName;
        protected TextView vTweetText;
        protected RoundedImageView vProfile;
        protected ImageView vTweetImage;

        public TweetViewHolder(View v) {
            super(v);

            vName =(TextView) v.findViewById(R.id.twitter_name);
            vProfile = (RoundedImageView) v.findViewById(R.id.profile);
            vTweetText = (TextView)v.findViewById(R.id.twitter_text);
            vTweetImage = (ImageView) v.findViewById(R.id.tweet_image);
        }
    }                                               //    ---   VIEW HOLDER   ---



    //    ---    CONSTRUCTOR   ---
    public TwitterAdapter(Context ctx){
        tweetList = new ArrayList<>();
        buildTweetList(ctx);
//        this.tweetList = tweetList;
    }                                               //    ---    CONSTRUCTOR   ---



    @Override
    public TweetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // inflate custom layout
        View tweetView = inflater.inflate(R.layout.tweet_card, parent, false);

        TweetViewHolder tweetViewHolder = new TweetViewHolder(tweetView);
        return tweetViewHolder;
    }

    @Override
    public void onBindViewHolder(TweetViewHolder holder, int position) {
        TweetInfo tweetInfo = this.tweetList.get(position);

        //Set item view based on your views
        TextView vName = holder.vName;
        vName.setText(tweetInfo.getTweetName());
        TextView vTweetText = holder.vTweetText;
        vTweetText.setText(tweetInfo.getTweetContent());
        ImageView vTweetImage = holder.vTweetImage;
        vTweetImage.setImageDrawable(new BitmapDrawable(tweetInfo.getTweetImage()));
//        RoundedImageView vProfile = holder.vProfile;


    }

    @Override
    public int getItemCount() {
        return tweetList.size();
    }

    private void buildTweetList(Context ctx){

        SQLiteDatabase db = ctx.openOrCreateDatabase( "Tweets", Context.MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT screen_name, tweet_text FROM tweets LIMIT 22", null);


        int nameIndex = 0;
        int textIndex = 1;

        c.moveToFirst();
        while (c.getPosition() != c.getCount()){


            Log.i("TEST: ", c.getString(nameIndex) );
            Log.i("TEST: ", c.getString(textIndex) );

            TweetInfo tempTweetInfo = new TweetInfo(c.getString(nameIndex),  c.getString(textIndex), "hi"); //c.getString(imageIndex)
            Log.i("STRING : = ", c.getString(textIndex));
            this.tweetList.add(tempTweetInfo);

            c.moveToNext();
        }
        c.close();
        db.close();

    }


}
