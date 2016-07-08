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
        mInterestTextView.setText("Here are all the results for: " + topic);

        mLogInButton = (Button) findViewById(R.id.LogInButton);

        getMeetups(topic);

        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpcomingEventsActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });
    }

    public ArrayList<Meetup> mEvents = new ArrayList<>();

    private void getMeetups(String topic) {
            final MeetupService meetupService = new MeetupService();
            meetupService.findMeetups(topic, new Callback() {

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
                    }
                });
            }
        });
    }
}
