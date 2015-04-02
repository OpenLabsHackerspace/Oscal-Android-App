package org.almotech.oscal.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import org.almotech.oscal.R;
import org.almotech.oscal.adapters.EventRVAdapter;
import org.almotech.oscal.adapters.SpeakerRVAdapter;
import org.almotech.oscal.api.Communicator;
import org.almotech.oscal.model.EventModel;
import org.almotech.oscal.model.SpeakerModel;
import org.almotech.oscal.utils.BusProvider;

import java.util.ArrayList;

/**
 * Created by ErionDell on 3/25/2015.
 */
public class OscalDay1 extends Fragment {


    ArrayList<EventModel> modelArrayList;
    EventRVAdapter mRvAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Communicator mCommunicator =new Communicator();
        mCommunicator.getSomeEvents();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.speakers_fragment, container,false);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView mRecyclerView = (RecyclerView) mView.findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(manager);

        if(modelArrayList==null)
            modelArrayList = new ArrayList<>();

        if(mRvAdapter == null)
            mRvAdapter = new EventRVAdapter(modelArrayList);

        mRecyclerView.setAdapter(mRvAdapter);

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Subscribe
    public void onSpeakersLoaded(ArrayList<EventModel> modelArrayList){

        if(modelArrayList != null && modelArrayList.size()>0){
            this.modelArrayList.clear();
            this.modelArrayList.addAll(modelArrayList);
            mRvAdapter.notifyDataSetChanged();
        }

    }

}
