package org.almotech.oscal.model;

/**
 * Created by Armando Shkurti on 2015-04-02.
 */
public class EventModel {



    //http://e-cuni.com/oscal/service/index.php?module=oscal&action=getEvents
    public String id;
    public String title;
    public String speaker_name;
    public String tag;
    public String time_description;
    public String location;
    public String description;
    public String link;
    public String time_event;
    public String create_date;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSpeaker_name() {
        return speaker_name;
    }

    public String getTag() {
        return tag;
    }

    public String getTime_description() {
        return time_description;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getTime_event() {
        return time_event;
    }

    public String getCreate_date() {
        return create_date;
    }

}
