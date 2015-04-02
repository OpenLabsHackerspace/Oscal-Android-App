package org.almotech.oscal.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.almotech.oscal.R;
import org.almotech.oscal.activities.PersonDetailActivity;
import org.almotech.oscal.model.EventModel;
import org.almotech.oscal.model.SpeakerModel;
import org.almotech.oscal.utils.LoadImageUtil;

import java.util.ArrayList;

/**
 * Created by Armando Shkurti on 2015-04-02.
 */
public class EventRVAdapter extends RecyclerView.Adapter<EventRVAdapter.EventVH> {

    private static ArrayList<EventModel> modelArrayList;
    private LoadImageUtil mLoadImageUtil;

    public EventRVAdapter(ArrayList<EventModel> modelArrayList) {

        this.modelArrayList=modelArrayList;
    }

    @Override
    public EventVH onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_item,viewGroup,false);

        EventVH mSpeakerVH = new EventVH(mView);
        return mSpeakerVH;
    }

    @Override
    public void onBindViewHolder(EventVH speakerVH, int position) {


    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public static class EventVH extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView speakerName;
        public TextView speakerCompany;
        public ImageView speakerIamge;

        public EventVH(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

        }
    }
}
