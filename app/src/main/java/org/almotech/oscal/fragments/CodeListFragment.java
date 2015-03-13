package org.almotech.oscal.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.almotech.oscal.R;
import org.almotech.oscal.activities.EventDetailsActivity;
import org.almotech.oscal.adapters.EventsAdapter;
import org.almotech.oscal.db.DatabaseManager;
import org.almotech.oscal.loaders.SimpleCursorLoader;
import org.almotech.oscal.model.Event;
import org.almotech.oscal.widgets.BookmarksMultiChoiceModeListener;

/**
 * Bookmarks list, optionally filterable.
 *
 * @author Christophe Beyls
 */
public class CodeListFragment extends Fragment {

	private static final int BOOKMARKS_LOADER_ID = 1;
	private static final String PREF_UPCOMING_ONLY = "bookmarks_upcoming_only";

	private EventsAdapter adapter;
	private boolean upcomingOnly;

	private MenuItem filterMenuItem;
	private MenuItem upcomingOnlyMenuItem;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.code_of_conduct_fragment,container,false);


        return mView;
    }
}
