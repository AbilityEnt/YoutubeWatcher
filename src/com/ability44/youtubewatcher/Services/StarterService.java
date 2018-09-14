package com.ability44.youtubewatcher.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class StarterService extends Service {

	private static final String TAG = "StarterService";

	@Override
	public void onStart(Intent intent, int startid) {
		Intent service = new Intent(this, StarterService.class);
        this.startService(service);
		Log.d(TAG, "onStart");
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "My Service stopped", Toast.LENGTH_LONG).show();
		Log.d(TAG, TAG + " onDestroy");
	}
}
