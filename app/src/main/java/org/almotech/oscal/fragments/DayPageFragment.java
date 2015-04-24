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
import org.almotech.oscal.api.Communicator;
import org.almotech.oscal.model.DayModel;
import org.almotech.oscal.model.EventModel;
import org.almotech.oscal.utils.BusProvider;

import java.util.ArrayList;

/**
 * Created by Armando Shkurti on 2015-04-25.
 */
public class DayPageFragment extends Fragment {

    ArrayList<EventModel> modelArrayList;
    EventRVAdapter mRvAdapter;
    public static final String pos="pos";
    int pageNumber;
    public static DayPageFragment newInstance(int page){
        DayPageFragment mDayPageFragment = new DayPageFragment();
        Bundle mBundle = new Bundle();
        mBundle.putInt(pos, page);
        mDayPageFragment.setArguments(mBundle);
        return  mDayPageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pageNumber = getArguments().getInt(pos);

        Communicator mCommunicator =new Communicator();
        mCommunicator.getSomeEvents(pageNumber); // cojm tipin e faqe qe kemi main track, sidetrack1 etj
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.day_page, container,false);

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
    public void onSpeakersLoaded(DayModel model){

        if (model.getPageNumber() == pageNumber) {//shikojm te jete i njeti tip faqeje

            ArrayList<EventModel> modelArrayList = model.getServerResponse();
            if (modelArrayList != null && modelArrayList.size() > 0) {
                this.modelArrayList.clear();
                this.modelArrayList.addAll(modelArrayList);
                mRvAdapter.notifyDataSetChanged();
            }
        }

    }


}
