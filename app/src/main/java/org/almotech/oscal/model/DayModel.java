package org.almotech.oscal.model;

import java.util.ArrayList;

/**
 * Created by Armando Shkurti on 2015-04-25.
 */
public class DayModel {

    public ArrayList<EventModel> serverResponse;
    public int pageNumber;


    public DayModel(ArrayList<EventModel> serverResponse, int pageNumber) {
        this.serverResponse = serverResponse;
        this.pageNumber = pageNumber;
    }


    public ArrayList<EventModel> getServerResponse() {
        return serverResponse;
    }


    public void setServerResponse(ArrayList<EventModel> serverResponse) {
        this.serverResponse = serverResponse;
    }


    public int getPageNumber() {
        return pageNumber;
    }


    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
