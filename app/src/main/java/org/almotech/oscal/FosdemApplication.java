package org.almotech.oscal;

import android.app.Application;
import android.preference.PreferenceManager;
import org.almotech.oscal.alarms.FosdemAlarmManager;
import org.almotech.oscal.db.DatabaseManager;

public class FosdemApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		DatabaseManager.init(this);
		// Initialize settings
		PreferenceManager.setDefaultValues(this, R.xml.settings, false);
		// Alarms (requires settings)
		FosdemAlarmManager.init(this);
	}
}
