package com.codepath.apps.restclienttemplate;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class ComposeDialogFragment extends DialogFragment {

    public static final int MAX_TWEET_LENGTH = 140;

    TextInputLayout TILayout;
    EditText etCompose;
    Button btnTweet;

    public ComposeDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static ComposeDialogFragment newInstance(String title) {
        ComposeDialogFragment frag = new ComposeDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_compose, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        etCompose = (EditText) view.findViewById(R.id.etCompose);
        btnTweet = (Button) view.findViewById(R.id.btnTweet);
        TILayout = (TextInputLayout) view.findViewById(R.id.TILayout);

        // Set click listener on button
//        btnTweet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String tweetContent = etCompose.getText().toString();
//                if (tweetContent.isEmpty()){
//                    Toast.makeText(TimelineActivity.this, "Sorry, your tweet cannot be empty", Toast.LENGTH_LONG).show();
//                    return;
//                }
//                if (tweetContent.length() > MAX_TWEET_LENGTH){
//                    Toast.makeText(TimelineActivity.this, "Sorry, your tweet is too long", Toast.LENGTH_LONG).show();
//                    return;
//                }
//                Toast.makeText(TimelineActivity.this, tweetContent, Toast.LENGTH_LONG).show();
//                client.publishTweet(tweetContent, new JsonHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(int statusCode, Headers headers, JSON json) {
//                        Log.i(TAG, "onSuccess to publish tweet");
//                        try {
//                            Tweet tweet = Tweet.fromJson(json.jsonObject);
//                            Log.i(TAG, "Published tweet says: " + tweet.body);
//                            Intent intent = new Intent();
//                            intent.putExtra("tweet", Parcels.wrap(tweet));
//                            setResult(RESULT_OK, intent); // set result code and bundle data for response
//                            finish(); //closes the activity, pass data to parent
//                        } catch (JSONException e) {
//                            Log.e(TAG, "Json exception thrown", e);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
//                        Log.e(, "onFailure to publish tweet", throwable);
//                    }
//                });
//            }
//        });

        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        TILayout.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }
}