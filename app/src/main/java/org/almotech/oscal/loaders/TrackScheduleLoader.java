package org.almotech.oscal.loaders;

import android.content.Context;
import android.database.Cursor;
import org.almotech.oscal.db.DatabaseManager;
import org.almotech.oscal.model.Day;
import org.almotech.oscal.model.Track;

public class TrackScheduleLoader extends SimpleCursorLoader {

	private final Day day;
	private final Track track;

	public TrackScheduleLoader(Context context, Day day, Track track) {
		super(context);
		this.day = day;
		this.track = track;
	}

	@Override
	protected Cursor getCursor() {
		return DatabaseManager.getInstance().getEvents(day, track);
	}
}
