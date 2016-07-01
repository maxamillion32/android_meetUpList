package com.epicodus.meetuplist;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mFindEventsButton;
    private EditText mInterestEditText;
    private TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInterestEditText = (EditText) findViewById(R.id.InterestEditText);

        mAppNameTextView = (TextView) findViewById(R.id.AppNameTextView);
        Typeface pacificoFont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        mAppNameTextView.setTypeface(pacificoFont);
        mFindEventsButton = (Button) findViewById(R.id.FindEventsButton);
        mFindEventsButton.setOnClickListener(this);

    }

            @Override
            public void onClick(View v) {
                if (v == mFindEventsButton) {
                String interest = mInterestEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, UpcomingEventsActivity.class);
                intent.putExtra("interest", interest);
                startActivity(intent);
            }
    }
}
