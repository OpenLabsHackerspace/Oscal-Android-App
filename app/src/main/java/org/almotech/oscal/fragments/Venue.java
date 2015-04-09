package org.almotech.oscal.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import org.almotech.oscal.R;
import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by ErionDell on 4/6/2015.
 */
public class Venue extends Fragment {


    private MapView mMapView;
    private ResourceProxy mResourceProxy;
    ArrayList<OverlayItem> items;
    private ItemizedIconOverlay<OverlayItem> mMyLocationOverlay;
    //set the starting point for the city
    private static final GeoPoint TIRANA = new GeoPoint(41.31928d, 19.83237d);

    private static final double DESTINATION_LATITUDE = 41.3191981;
    private static final double DESTINATION_LONGITUDE = 19.8321331;
    private static final String DESTINATION_NAME = "godina iliria";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.venue, container,false);
        mMapView = (MapView)mView.findViewById(R.id.mapview);

        setupMap();
        setMarkerToMap();
        onMarkerClick();

        return mView;
    }
    private void setupMap() {

        mMapView.setClickable(true);
        mMapView.setTileSource(TileSourceFactory.MAPNIK);
        //mMapView.setMapFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/sdcard/osmdroid/albania.map");

        mMapView.getController().setZoom(19);
        mMapView.getController().setCenter(TIRANA);
        mMapView.setUseDataConnection(true);
        mMapView.setMultiTouchControls(true);
    }

    /**
     * Add markers to the specific coordinates
     *
     */
    private void setMarkerToMap() {
        items = new ArrayList<OverlayItem>();

        //TODO duhet te ndryshohen vetem lat dhe long te ktyre
        // markerave dhe do dali aty ku don ti te dali markeri
        // vec kesaj te metoda add marker percaktohet dhe imazhi qe do vendoset
        // aktualisht perdoret ic_launcher por mund ta nderrosh sipas qefit
        //addMarker("03", "Qemal Stafa Stadium", 41.3185333, 19.8238861);
        //addMarker("04", "Universiteti Politeknik", 41.3167333, 19.8215528);
        //addMarker("05", "Mother Teresa Statue", 41.3170306, 19.8220417);
        //addMarker("06", "The Arceological Museum", 41.3181278, 19.8219417);
        addMarker("01", "Godina Lisia", 41.31928, 19.83237);
    }



    private void addMarker(String tag, String description, double lat, double lng){
        GeoPoint marker = new GeoPoint(lat, lng);
		/*Drawable newMarker = this.getResources().getDrawable(getResources().getIdentifier("com.naccon.audioguide:raw/ic_" + "headphones",
				"raw", "com.naccon.audioguide"));*/
        Drawable newMarker = this.getResources().getDrawable(R.drawable.pin_oscal);
        OverlayItem myOverlayItem = new OverlayItem(tag, description, marker);
        myOverlayItem.setMarker(newMarker);
        items.add(myOverlayItem);
    }

    /**
     * create action when marker is clicked
     * and
     */
    private void onMarkerClick() {
        mResourceProxy = new DefaultResourceProxyImpl(getActivity());
        this.mMyLocationOverlay = new ItemizedOverlayWithFocus<OverlayItem>(items,
                new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>(){
                    @Override
                    public boolean onItemSingleTapUp(final int index,final OverlayItem item) {
                        return true;
                    }
                    @Override
                    public boolean onItemLongPress(int arg0, OverlayItem arg1) {
                        // TODO Auto-generated method stub
                        return false;
                    }

                }
                , mResourceProxy);

        this.mMapView.getOverlays().add(this.mMyLocationOverlay);
        mMapView.invalidate();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.map, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.directions:
                launchDirections();
                return true;
        }
        return false;
    }
    private void launchDirections() {
        // Build intent to start Google Maps directions
        String uri = String.format(Locale.US,
                "http://maps.google.com/maps?f=d&daddr=%1$f,%2$f(%3$s)&dirflg=r",
                DESTINATION_LATITUDE, DESTINATION_LONGITUDE, DESTINATION_NAME);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));

        startActivity(intent);
    }
}