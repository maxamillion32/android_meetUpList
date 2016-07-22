package com.epicodus.meetuplist.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.meetuplist.Constants;
import com.epicodus.meetuplist.R;
import com.epicodus.meetuplist.models.Meetup;
import com.epicodus.meetuplist.ui.EventDetailActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by Guest on 7/14/16.
 */
public class FirebaseEventViewHolder extends RecyclerView.ViewHolder{

    View mView;
    Context mContext;
    public ImageView mDragIcon;

    public FirebaseEventViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindEvent(Meetup event) {
        TextView eventTitleTextView = (TextView) mView.findViewById(R.id.eventTitleTextView);
        TextView descriptionTextView = (TextView) mView.findViewById(R.id.descriptionTextView);
        TextView rsvpTextView = (TextView) mView.findViewById(R.id.rsvpTextView);

        eventTitleTextView.setText(event.getName());
        descriptionTextView.setText(event.getDescription());
        rsvpTextView.setText(event.getRsvpCount() + " " + event.getWho() + " are going");

        mDragIcon = (ImageView) mView.findViewById(R.id.dragIcon);
    }
}
