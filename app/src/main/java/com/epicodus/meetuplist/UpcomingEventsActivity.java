package com.epicodus.meetuplist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class UpcomingEventsActivity extends AppCompatActivity {
    private TextView mInterestTextView;
    private ListView mListView;
    private String[] events = new String[] {"The Portland Hiking Meetup Group", "Docker Portland, OR",
            "New-to-Portland Moms", "Free Yoga for Adults in Portland Area", "Activities and Sports for the Awesomely Mediocre", "New Relic FutureTalks PDX", "Art Geeks"};
    private Button mLogInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_events);

        mListView = (ListView) findViewById(R.id.listView);
        mInterestTextView = (TextView) findViewById(R.id.InterestTextView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, events);
        mListView.setAdapter(adapter);

        Intent intent = getIntent();
        String interest = intent.getStringExtra("interest");
        mInterestTextView.setText("Here are all the results for: " + interest);

        mLogInButton = (Button) findViewById(R.id.LogInButton);
        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpcomingEventsActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });
    }
}
