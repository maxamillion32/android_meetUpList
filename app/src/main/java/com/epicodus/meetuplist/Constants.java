package com.epicodus.meetuplist;

/**
 * Created by Guest on 7/7/16.
 */
public class Constants {
    public static final String MEETUP_API_KEY = BuildConfig.MEETUP_API_KEY;

    public static final String BASE_URL = "https://api.meetup.com/2/open_events";
    public static final String API_QUERY_PARAMETER = "key";
    public static final String SEARCH_QUERY_PARAMETER = "topic";
    public static final String TIME_QUERY_PARAMETER = "time=,1w";

    // https://api.meetup.com/2/open_events?topic=dancing&time=,1w&key=263238565a442e1967147767292e2462
}
