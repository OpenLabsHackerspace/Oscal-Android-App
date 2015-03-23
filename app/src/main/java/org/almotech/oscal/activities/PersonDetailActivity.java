package org.almotech.oscal.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.almotech.oscal.R;
import org.almotech.oscal.utils.LoadImageUtil;

/**
 * Created by Armando Shkurti on 2015-03-23.
 */
public class PersonDetailActivity extends ActionBarActivity {

    String url = "http://oscal.openlabs.cc/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            String name = getIntent().getExtras().getString("name");
        String url = getIntent().getExtras().getString("url");
        String company = getIntent().getExtras().getString("company");
        String descr = getIntent().getExtras().getString("descr");

        setContentView(R.layout.speaker_detail);

        LoadImageUtil mLoadImageUtil = new LoadImageUtil(this);
        mLoadImageUtil.loadBitmapToImageView((ImageView)findViewById(R.id.speakerImg),url);

        TextView nametxt = (TextView) findViewById(R.id.speakerName);
        nametxt.setText(name);

        TextView companytxt = (TextView) findViewById(R.id.speakerCompany);
        companytxt.setText(company);

        TextView descrtxt = (TextView) findViewById(R.id.descr);
        descrtxt.setText(descr);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setTitle(getString(R.string.menu_speakers));
    }

    @Override
    public boolean  onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.person, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.more_info:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
        }
        return false;
    }
}
