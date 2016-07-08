package com.epicodus.meetuplist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class UpcomingEventsActivity extends AppCompatActivity {
    public static final String TAG = UpcomingEventsActivity.class.getSimpleName();

    private TextView mInterestTextView;
    private ListView mListView;
    private Button mLogInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_events);

        mListView = (ListView) findViewById(R.id.listView);
        mInterestTextView = (TextView) findViewById(R.id.InterestTextView);

        Intent intent = getIntent();
        String topic = intent.getStringExtra("topic");
        String location = intent.getStringExtra("location");
        mInterestTextView.setText("Here are all the results for: " + topic + " near " + location);

        mLogInButton = (Button) findViewById(R.id.LogInButton);

        getMeetups(topic, location);

        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpcomingEventsActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });
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
                        String[] eventNames = new String[mEvents.size()];
                        for (int i = 0; i < eventNames.length; i++) {
                            eventNames[i] = mEvents.get(i).getName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(UpcomingEventsActivity.this, android.R.layout.simple_list_item_1, eventNames);
                        mListView.setAdapter(adapter);

                        for (Meetup meetup : mEvents) {
                            Log.d(TAG, "Name: " + meetup.getName());
                            Log.d(TAG, "Description: " + meetup.getDescription());
                            Log.d(TAG, "EventUrl: " + meetup.getEventUrl());
                            Log.d(TAG, "RsvpCount: " + meetup.getRsvpCount());
                            Log.d(TAG, "Latitude: " + meetup.getLatitude());
                            Log.d(TAG, "Longitude: " + meetup.getLongitude());
                            Log.d(TAG, "Address Line 1: " + meetup.getAddress1());
                            Log.d(TAG, "Address Line 2: " + meetup.getAddress2());
                            Log.d(TAG, "City: " + meetup.getCity());
                            Log.d(TAG, "State: " + meetup.getState());
                            Log.d(TAG, "Who: " + meetup.getWho());
                            Log.d(TAG, "Group Name: " + meetup.getNameGroup());
                        }
                    }
                });
            }
        });
    }
}
