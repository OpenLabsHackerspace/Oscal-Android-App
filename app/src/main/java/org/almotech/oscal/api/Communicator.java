package org.almotech.oscal.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.almotech.oscal.model.DayModel;
import org.almotech.oscal.model.EventModel;
import org.almotech.oscal.model.SpeakerModel;
import org.almotech.oscal.utils.BusProvider;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;

/**
 * Created by Armando Shkurti on 2015-03-23.
 * Modified by Erion
 */
public class Communicator {

    String URL = "http://e-cuni.com/oscal/service";

    public static Gson getGsonWithDateTypeAdapter() {
        return getGsonWithDateTypeAdapter("yyyy-MM-dd HH:mm:ss");
    }

    public static Gson getGsonWithDateTypeAdapter(String format) {
        return new GsonBuilder().setDateFormat(format).create();
    }

    public void getSomeSpeakers(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(URL)
                .setConverter(new GsonConverter(getGsonWithDateTypeAdapter()))
                .build();

        Speakers speakers = restAdapter.create(Speakers.class);

        Callback<ArrayList<SpeakerModel>> callback = new Callback<ArrayList<SpeakerModel>>() {
            @Override
            public void success(ArrayList<SpeakerModel> serverResponse, Response response) {
                BusProvider.getInstance().post(serverResponse);
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
                BusProvider.getInstance().post(new ArrayList<SpeakerModel>());
            }
        };

        speakers.getResponse(callback);
    }

    public interface Speakers {
        @GET("/index.php?module=oscal&action=getSpeakers")
        void getResponse(Callback<ArrayList<SpeakerModel>> cb);
    }


    public void getSomeEvents(final int page, final int day){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(URL)
                .setConverter(new GsonConverter(getGsonWithDateTypeAdapter()))
                .build();

        if(day == 2 && page == 0)
            Log.e("","");
        EventsMainTrackDay1 eventsMainTrackDay1 = null;
        EventsSideTrack1Day1 eventsSideTrack1Day1 = null;
        EventsSideTrack2Day1 eventsSideTrack2Day1 = null;
        EventsMainTrackDay2 eventsMainTrackDay2 = null;
        EventsSideTrack1Day2 eventsSideTrack1Day2 = null;
        EventsSideTrack2Day2 eventsSideTrack2Day2 = null;
        if(day == 1) {
            if (page == 0) {
                eventsMainTrackDay1 = restAdapter.create(EventsMainTrackDay1.class);
            } else if (page == 1) {
                eventsSideTrack1Day1 = restAdapter.create(EventsSideTrack1Day1.class);
            } else if (page == 2) {
                eventsSideTrack2Day1 = restAdapter.create(EventsSideTrack2Day1.class);
            } else {
                eventsMainTrackDay1 = restAdapter.create(EventsMainTrackDay1.class);
            }
        }else{
            if (page == 0) {
                eventsMainTrackDay2 = restAdapter.create(EventsMainTrackDay2.class);
            } else if (page == 1) {
                eventsSideTrack1Day2 = restAdapter.create(EventsSideTrack1Day2.class);
            } else if (page == 2) {
                eventsSideTrack2Day2 = restAdapter.create(EventsSideTrack2Day2.class);
            } else {
                eventsMainTrackDay2 = restAdapter.create(EventsMainTrackDay2.class);
            }
        }

        Callback<ArrayList<EventModel>> callback = new Callback<ArrayList<EventModel>>() {
            @Override
            public void success(ArrayList<EventModel> serverResponse, Response response) {
                if(day == 2 && page == 0)
                    Log.e("","");
                BusProvider.getInstance().post(new DayModel(serverResponse, page, day));
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
                BusProvider.getInstance().post(new ArrayList<SpeakerModel>());
            }
        };
        if(eventsMainTrackDay1 != null)
            eventsMainTrackDay1.getResponse(callback);
        else if(eventsSideTrack1Day1 != null)
            eventsSideTrack1Day1.getResponse(callback);
        else if(eventsSideTrack2Day1 != null)
            eventsSideTrack2Day1.getResponse(callback);
        else if(eventsMainTrackDay2 != null)
            eventsMainTrackDay2.getResponse(callback);
        else if(eventsSideTrack1Day2 != null)
            eventsSideTrack1Day2.getResponse(callback);
        else if(eventsSideTrack2Day2 != null)
            eventsSideTrack2Day2.getResponse(callback);
    }

    // TODO just change url here in each of these interfaces accordingly and than its all good as long as te content of the JSON is the same

    /**
     * used for EventsMainTrackDay1
     */
    public interface EventsMainTrackDay1 {
        @GET("/index.php?module=oscal&action=getmain_track1")
        void getResponse(Callback<ArrayList<EventModel>> cb);
    }

    /**
     * used for EventsSideTrack1Day1
     */
    public interface EventsSideTrack1Day1 {
        @GET("/index.php?module=oscal&action=getday1_track1")
        void getResponse(Callback<ArrayList<EventModel>> cb);
    }
    /**
     * used for EventsSideTrack2Day1
     */
    public interface EventsSideTrack2Day1 {
        @GET("/index.php?module=oscal&action=getday2_track1")
        void getResponse(Callback<ArrayList<EventModel>> cb);
    }
    /**
     * used for EventsMainTrackDay2
     */
    public interface EventsMainTrackDay2 {
        @GET("/index.php?module=oscal&action=getmain_track2")
        void getResponse(Callback<ArrayList<EventModel>> cb);
    }

    /**
     * used for EventsSideTrack1Day2
     */
    public interface EventsSideTrack1Day2 {
        @GET("/index.php?module=oscal&action=getday1_track2")
        void getResponse(Callback<ArrayList<EventModel>> cb);
    }
    /**
     * used for EventsSideTrack2Day2
     */
    public interface EventsSideTrack2Day2 {
        @GET("/index.php?module=oscal&action=getday2_track2")
        void getResponse(Callback<ArrayList<EventModel>> cb);
    }
}
