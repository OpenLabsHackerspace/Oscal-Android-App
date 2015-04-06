package org.almotech.oscal.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import org.almotech.oscal.R;

/**
 * Created by ErionDell on 4/6/2015.
 */
public class Venue extends Fragment {

    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.venue, container, false);


        //webView = (WebView) inflater.findViewById(R.id.webView);
        //webView.getSettings().setJavaScriptEnabled(true);
        //webView.loadUrl("http://www.google.com");

    }

}