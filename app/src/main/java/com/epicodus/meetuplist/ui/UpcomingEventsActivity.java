package com.epicodus.meetuplist.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.meetuplist.Constants;
import com.epicodus.meetuplist.R;
import com.epicodus.meetuplist.adapters.EventListAdapter;
import com.epicodus.meetuplist.models.Meetup;
import com.epicodus.meetuplist.services.MeetupService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class UpcomingEventsActivity extends AppCompatActivity {

//    private SharedPreferences mSharedPreferences;
//    private String mRecentAddress;

    public static final String TAG = UpcomingEventsActivity.class.getSimpleName();

    @Bind(R.id.InterestTextView) TextView mInterestTextView;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private EventListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_events);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String topic = intent.getStringExtra("topic");
        String location = intent.getStringExtra("location");
        mInterestTextView.setText("Here are all the results for: " + topic + " near " + location);

        getMeetups(topic, location);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
//        Log.d("Shared Pref Location", mRecentAddress);
    }

    public ArrayList<Meetup> mEvents = new ArrayList<>();

    private void getMeetups(String topic, String location) {
            final MeetupService meetupService = new MeetupService();
            meetupService.findMeetups(topic, location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                mEvents = meetupService.processResults(response);

                UpcomingEventsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new EventListAdapter(getApplicationContext(), mEvents);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(UpcomingEventsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
