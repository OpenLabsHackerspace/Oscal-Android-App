package org.almotech.oscal.model;

import java.util.ArrayList;

/**
 * Created by Armando Shkurti on 2015-03-23.
 */
public class ServerResponse {

    public ArrayList<SpeakerModel> speakerModelArrayList;

    public ArrayList<SpeakerModel> getSpeakerModelArrayList() {
        return speakerModelArrayList;
    }

    public void setSpeakerModelArrayList(ArrayList<SpeakerModel> speakerModelArrayList) {
        this.speakerModelArrayList = speakerModelArrayList;
    }
}
