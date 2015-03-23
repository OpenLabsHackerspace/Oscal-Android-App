package org.almotech.oscal.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import org.almotech.oscal.R;
import org.almotech.oscal.adapters.RVAdapter;
import org.almotech.oscal.api.Communicator;
import org.almotech.oscal.model.SpeakerModel;
import org.almotech.oscal.model.ServerResponse;
import org.almotech.oscal.utils.BusProvider;

import java.util.ArrayList;

public class PersonsListFragment extends Fragment  {

	private static final int PERSONS_LOADER_ID = 1;

    ArrayList<SpeakerModel> modelArrayList;
    RVAdapter mRvAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Communicator mCommunicator =new Communicator();
        mCommunicator.getSomeSpeakers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.speakers_fragment, container,false);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView mRecyclerView = (RecyclerView) mView.findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(manager);

        if(modelArrayList==null)
        modelArrayList = new ArrayList<>();

        if(mRvAdapter == null)
        mRvAdapter = new RVAdapter(modelArrayList);

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
    public void onSpeakersLoaded(ArrayList<SpeakerModel> modelArrayList){

        if(modelArrayList != null && modelArrayList.size()>0){
            this.modelArrayList.clear();
            this.modelArrayList.addAll(modelArrayList);
            mRvAdapter.notifyDataSetChanged();
        }

    }
}
