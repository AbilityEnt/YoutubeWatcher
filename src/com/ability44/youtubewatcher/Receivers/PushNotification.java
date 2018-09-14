package com.ability44.youtubewatcher.Receivers;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.ability44.youtubewatcher.MainActivity;
import com.parse.ParsePushBroadcastReceiver;

public class PushNotification extends ParsePushBroadcastReceiver {
	private static final String TAG = "MyCustomReceiver";
	private SharedPreferences prefs;
	private String alert, title;
	
	@Override
	protected void onPushReceive(final Context context, Intent intent) {
		super.onPushReceive(context, intent);
	}

	@Override
	public void onPushOpen(Context context, Intent intent) {
		prefs = context.getSharedPreferences("com.ability44.budgeting",
				Context.MODE_PRIVATE);
		Log.e(TAG, "Clicked");
		Intent i = new Intent(context, MainActivity.class);
		i.putExtras(intent.getExtras());
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(i);
		try {
			JSONObject json = new JSONObject(intent.getExtras().getString(
					"com.parse.Data"));

			title = json.getString("channel");
			alert = json.getString("vidname");
			prefs.edit().putString("pusht", title).commit();
			prefs.edit().putString("pusha", alert).commit();

		} catch (JSONException e) {
			Log.d(TAG, "JSONException: " + e.getMessage());

		}
	}

}
