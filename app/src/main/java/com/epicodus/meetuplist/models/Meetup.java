package com.epicodus.meetuplist.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 7/8/16.
 */

@Parcel
public class Meetup {
    String name;
    String description;
    String eventUrl;
    int rsvpCount;
    double latitude;
    double longitude;
    String address1;
    String address2;
    String city;
    String state;
    String who;
    String nameGroup;
    private String pushId;

    String index;

    public Meetup() {}

public Meetup (String name, String description, String eventUrl, int rsvpCount, double latitude, double longitude, String address1, String address2, String city, String state, String who, String nameGroup) {
    this.name = name;
    this.description = description;
    this.eventUrl = eventUrl;
    this.rsvpCount = rsvpCount;
    this.latitude = latitude;
    this.longitude = longitude;
    this.address1 = address1;
    this.address2 = address2;
    this.city = city;
    this.state = state;
    this.who = who;
    this.nameGroup = nameGroup;
    this.index = "not_specified";
    }

    public String getName() {
        return name;
    }

    public String getDescription() { return description.replaceAll("<[^>]*>", ""); }

    public String getEventUrl() {
        return eventUrl;
    }

    public int getRsvpCount() { return rsvpCount; }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getWho() {
        return who;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
