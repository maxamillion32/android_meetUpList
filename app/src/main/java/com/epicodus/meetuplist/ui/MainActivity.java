package com.epicodus.meetuplist.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.meetuplist.R;
import com.epicodus.meetuplist.ui.UpcomingEventsActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.FindEventsButton) Button mFindEventsButton;
    @Bind(R.id.InterestEditText) EditText mInterestEditText;
    @Bind(R.id.AppNameTextView) TextView mAppNameTextView;
    @Bind(R.id.LocationEditText) EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface pacificoFont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        mAppNameTextView.setTypeface(pacificoFont);
        mFindEventsButton.setOnClickListener(this);

    }

            @Override
            public void onClick(View v) {
                if (v == mFindEventsButton) {
                String topic = mInterestEditText.getText().toString();
                String location = mLocationEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, UpcomingEventsActivity.class);
                intent.putExtra("topic", topic);
                intent.putExtra("location", location);
                startActivity(intent);
            }
    }
}
