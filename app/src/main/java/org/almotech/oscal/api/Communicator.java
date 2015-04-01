package org.almotech.oscal.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.almotech.oscal.model.EventModel;
import org.almotech.oscal.model.SpeakerModel;
import org.almotech.oscal.model.ServerResponse;
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


    public void getSomeEvents(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(URL)
                .setConverter(new GsonConverter(getGsonWithDateTypeAdapter()))
                .build();

        Events speakers = restAdapter.create(Events.class);

        Callback<ArrayList<EventModel>> callback = new Callback<ArrayList<EventModel>>() {
            @Override
            public void success(ArrayList<EventModel> serverResponse, Response response) {
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

    public interface Events {
        @GET("/index.php?module=oscal&action=getEvents")
        void getResponse(Callback<ArrayList<EventModel>> cb);
    }
}
