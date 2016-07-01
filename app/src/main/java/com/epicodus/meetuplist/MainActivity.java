package com.epicodus.meetuplist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Button mFindEventsButton;
    private EditText mInterestEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInterestEditText = (EditText) findViewById(R.id.InterestEditText);

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
