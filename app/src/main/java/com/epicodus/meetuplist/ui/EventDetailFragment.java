package com.epicodus.meetuplist.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.meetuplist.R;
import com.epicodus.meetuplist.Meetup;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EventDetailFragment extends Fragment {
    @Bind(R.id.eventImageView) ImageView mImageLabel;
    @Bind(R.id.eventNameTextView) TextView mNameLabel;
    @Bind(R.id.rsvpTextView) TextView mRsvpLabel;
    @Bind(R.id.descriptionTextView) TextView mDescriptionLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.groupTextView) TextView mGroupLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
    @Bind(R.id.coordinateTextView) TextView mCoordinateLabel;
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

//        Picasso.with(view.getContext()).load(mEvents.getImageUrl()).into(mImageLabel);

        mNameLabel.setText(mEvents.getName());
        mRsvpLabel.setText(mEvents.getRsvpCount() + " are going");
        mDescriptionLabel.setText(mEvents.getDescription());
        mGroupLabel.setText(mEvents.getNameGroup());
        mAddressLabel.setText(mEvents.getAddress1() + ", " + mEvents.getAddress2() + ", " + mEvents.getCity() + ", " + mEvents.getState());
        mCoordinateLabel.setText(mEvents.getLatitude() + ", " + mEvents.getLongitude());

        return view;
    }
}