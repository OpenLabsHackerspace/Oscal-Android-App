package org.almotech.oscal.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    EventRVAdapter mRvAdapter;
    RecyclerView mRecyclerView;
    ArrayList<EventModel> modelArrayList;

    public static final String pos="pos";
    public static final String dayKey="day";
    int pageNumber;
    int  pageDay;

    public static DayPageFragment newInstance(int page, int day){
        DayPageFragment mDayPageFragment = new DayPageFragment();
        Bundle mBundle = new Bundle();
        mBundle.putInt(pos, page);
        mBundle.putInt(dayKey, day);
        mDayPageFragment.setArguments(mBundle);
        return  mDayPageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(pageDay == 2 && pageNumber == 0)
            Log.e("", "");
        pageNumber = getArguments().getInt(pos);
        pageDay = getArguments().getInt(dayKey);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.day_page, container,false);



        if(pageDay == 2 && pageNumber == 0)
            Log.e("", "");
        Communicator mCommunicator =new Communicator();
        mCommunicator.getSomeEvents(pageNumber,pageDay); // cojm tipin e faqe qe kemi main track, sidetrack1 etj


        if (modelArrayList==null){
            modelArrayList = new ArrayList<>();
        }

        if (mRvAdapter == null){
            mRvAdapter = new EventRVAdapter(modelArrayList);
        }

        mRecyclerView = (RecyclerView) mView.findViewById(R.id.rv);

        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

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
        //if(pageDay == 2 && pageNumber == 0)
          //  Log.e("pageDay", "pageNumber: 0 , pageDay: 2");
        /*if (model.getPageNumber() == pageNumber && model.getPageDay() == pageDay) {//shikojm te jete i njeti tip faqeje
            if(pageDay == 2 && pageNumber == 0)
            Log.e("pageDay", "gotData = true");
            ArrayList<EventModel> modelArrayList = model.getServerResponse();
            if(mRvAdapter == null)
                mRvAdapter = new EventRVAdapter(modelArrayList);

            mRecyclerView.setAdapter(mRvAdapter);
        }*/

        if (model.getPageNumber() == pageNumber && model.getPageDay() == pageDay) {//shikojm te jete i njeti tip faqeje

            ArrayList<EventModel> modelArrayList = model.getServerResponse();
            if (modelArrayList != null && modelArrayList.size() >0){
                this.modelArrayList.clear();
                this.modelArrayList.addAll(modelArrayList);
                mRvAdapter.notifyDataSetChanged();
            }
        }

    }


}
