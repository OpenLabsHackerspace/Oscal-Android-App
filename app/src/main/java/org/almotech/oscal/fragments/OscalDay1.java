package org.almotech.oscal.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import org.almotech.oscal.R;

/**
 * Created by ErionDell on 3/25/2015.
 */
public class OscalDay1 extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Communicator mCommunicator =new Communicator();
        mCommunicator.getSomeEvents();*/
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.speakers_fragment, container,false);

        ViewPager mViewPager = (ViewPager) mView.findViewById(R.id.viewpager);
        PagerSlidingTabStrip mPagerSlidingTabStrip = (PagerSlidingTabStrip) mView.findViewById(R.id.tabs);

        mViewPager.setAdapter(new DayFragmentsAdapter(getChildFragmentManager(), 1));
        mPagerSlidingTabStrip.setViewPager(mViewPager);

        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public static class DayFragmentsAdapter extends FragmentPagerAdapter {

        int day;

        public DayFragmentsAdapter(FragmentManager fm, int day) {
            super(fm);
            this.day = day;
        }


        @Override
        public Fragment getItem(int position) {
            return DayPageFragment.newInstance(position, day);
        }


        @Override
        public int getCount() {
            return 3;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Main Track";
                case 1:
                    return "Side Track 1";
                case 2:
                    return "Side Track 2";
                default:
                    return "Main Track";
            }
        }
    }
}
