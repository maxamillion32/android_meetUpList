package com.epicodus.meetuplist;




import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guest on 7/7/16.
 */
public class MeetupService {
    public static final String TAG = UpcomingEventsActivity.class.getSimpleName();

    public static void findMeetups(String topic, String location, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.SEARCH_QUERY_PARAMETER, topic);
        urlBuilder.addQueryParameter(Constants.LOCATION_QUERY_PARAMETER, location);
        urlBuilder.addQueryParameter(Constants.TIME_QUERY_PARAMETER, Constants.TIME_QUERY_PARAMETER);
        urlBuilder.addQueryParameter(Constants.API_QUERY_PARAMETER, Constants.MEETUP_API_KEY);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Meetup> processResults(Response response) {
        ArrayList<Meetup> meetups = new ArrayList<>();

        try {
            String jsonData = response.body().string();

            if (response.isSuccessful()) {
                JSONObject meetupJSON = new JSONObject(jsonData);
                JSONArray meetupsJSON = meetupJSON.getJSONArray("results");
                for (int i = 0; i < meetupsJSON.length(); i++) {
                    JSONObject eventJSON = meetupsJSON.getJSONObject(i);
                    String name = eventJSON.getString("name");
                    String description = eventJSON.optString("description", "not provided");
                    String eventUrl = eventJSON.optString("event_url", "not provided");
                    int rsvpCount = eventJSON.optInt("yes_rsvp_count");
                    JSONObject venue = eventJSON.optJSONObject("venue");
                    double latitude = 0;
                    double longitude = 0;
                    String address1 = "not provided";
                    String address2 = "not provided";
                    if(venue != null){
                        latitude = venue.optDouble("lat");
                        longitude = venue.optDouble("lon");
                        address1 = venue.optString("address_1", "not provided");
                        address2 = venue.optString("address_2", "not provided");
                    }
                    String city = eventJSON.optString("city", "not provided");
                    String state = eventJSON.optString("state", "not provided");
                    String who = eventJSON.getJSONObject("group").getString("who");
                    String nameGroup = eventJSON.getJSONObject("group").getString("name");


                    Meetup meetup = new Meetup(name, description, eventUrl, rsvpCount, latitude, longitude, address1, address2, city, state, who, nameGroup);
                    meetups.add(meetup);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return meetups;
    }
}
