package com.epicodus.meetuplist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UpcomingEventsActivity extends AppCompatActivity {
    private TextView mInterestTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_events);
        mInterestTextView = (TextView) findViewById(R.id.InterestTextView);
        Intent intent = getIntent();
        String interest = intent.getStringExtra("interest");
        mInterestTextView.setText("Here are all the results for: " + interest);
    }
}
