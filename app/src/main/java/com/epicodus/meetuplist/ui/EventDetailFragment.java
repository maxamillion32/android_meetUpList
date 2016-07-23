package com.epicodus.meetuplist.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.meetuplist.Constants;
import com.epicodus.meetuplist.R;
import com.epicodus.meetuplist.models.Meetup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EventDetailFragment extends Fragment implements View.OnClickListener{
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;
    @Bind(R.id.eventImageView) ImageView mImageLabel;
    @Bind(R.id.eventNameTextView) TextView mNameLabel;
    @Bind(R.id.rsvpTextView) TextView mRsvpLabel;
    @Bind(R.id.descriptionTextView) TextView mDescriptionLabel;
    @Bind(R.id.websiteTextView) TextView mEventWebsiteLabel;
    @Bind(R.id.groupTextView) TextView mGroupLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.sendtextTextView) TextView mSendTextLabel;
    @Bind(R.id.saveEventButton) TextView mSaveEventButton;

    private Meetup mEvents;

    public static EventDetailFragment newInstance(Meetup event) {
        EventDetailFragment eventDetailFragment = new EventDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("event", Parcels.wrap(event));
        eventDetailFragment.setArguments(args);
        return eventDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEvents = Parcels.unwrap(getArguments().getParcelable("event"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        ButterKnife.bind(this, view);

//        Picasso.with(view.getContext()).load(mEvents.getImageUrl()).resize(MAX_WIDTH, MAX_HEIGHT).centerCrop().into(mImageLabel);

        mNameLabel.setText(mEvents.getName());
        mRsvpLabel.setText(mEvents.getRsvpCount() + " are going");
        mDescriptionLabel.setText(mEvents.getDescription());
        mGroupLabel.setText(mEvents.getNameGroup());
        mAddressLabel.setText(mEvents.getAddress1());
        mEventWebsiteLabel.setText(mEvents.getEventUrl());

        mEventWebsiteLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);
        mSendTextLabel.setOnClickListener(this);
        mSaveEventButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mEventWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mEvents.getEventUrl()));
            startActivity(webIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + mEvents.getLatitude()
                            + "," + mEvents.getLongitude()));
            startActivity(mapIntent);
        }
        if (v == mSendTextLabel) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out this Meetup: " + mEvents.getEventUrl());
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        if (v == mSaveEventButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference eventRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_EVENTS)
                    .child(uid);
            DatabaseReference pushRef = eventRef.push();
            String pushId = pushRef.getKey();
            mEvents.setPushId(pushId);
            pushRef.setValue(mEvents);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }

}

//+ "?q=(" + mEvents.getName() + ")"