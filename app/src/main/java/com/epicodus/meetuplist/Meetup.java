package com.epicodus.meetuplist;

/**
 * Created by Guest on 7/8/16.
 */
public class Meetup {
    private String mName;
    private String mDescription;
    private String mEventUrl;
    private String mRsvpCount;
    private double mLatitude;
    private double mLongitude;
    private String mAddress1;
    private String mAddress2;
    private String mCity;
    private String mState;
    private String mWho;
    private String mNameGroup;

public Meetup (String name, String description, String eventUrl, String rsvpCount, double latitude, double longitude, String address1, String address2, String city, String state, String who, String nameGroup) {
    this.mName = name;
    this.mDescription = description;
    this.mEventUrl = eventUrl;
    this.mRsvpCount = rsvpCount;
    this.mLatitude = latitude;
    this.mLongitude = longitude;
    this.mAddress1 = address1;
    this.mAddress2 = address2;
    this.mCity = city;
    this.mState = state;
    this.mWho = who;
    this.mNameGroup = nameGroup;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getEventUrl() {
        return mEventUrl;
    }

    public String getRsvpCount() {
        return mRsvpCount;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public String getAddress1() {
        return mAddress1;
    }

    public String getAddress2() {
        return mAddress2;
    }

    public String getCity() {
        return mCity;
    }

    public String getState() {
        return mState;
    }

    public String getWho() {
        return mWho;
    }

    public String getNameGroup() {
        return mNameGroup;
    }
}
