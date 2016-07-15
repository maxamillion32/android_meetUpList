package com.epicodus.meetuplist.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.meetuplist.Constants;
import com.epicodus.meetuplist.R;
import com.epicodus.meetuplist.adapters.FirebaseEventViewHolder;
import com.epicodus.meetuplist.models.Meetup;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedEventListActivity extends AppCompatActivity {
    private DatabaseReference mEventReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_upcoming_events);
        ButterKnife.bind(this);

        mEventReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_EVENTS);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Meetup, FirebaseEventViewHolder>
                (Meetup.class, R.layout.event_list_item, FirebaseEventViewHolder.class,
                        mEventReference) {

            @Override
            protected void populateViewHolder(FirebaseEventViewHolder viewHolder,
                                              Meetup model, int position) {
                viewHolder.bindEvent(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
