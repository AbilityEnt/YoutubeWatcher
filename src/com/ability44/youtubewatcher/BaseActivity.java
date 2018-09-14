package com.ability44.youtubewatcher;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.ability44.youtubewatcher.R;
import com.parse.Parse;
import com.parse.ParseCrashReporting;

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class BaseActivity extends Activity {
	private static final String APP_ID = "phPdaGd889Z9SiIN5aXcIJGnYCbgIkdlrQLzeI7h",
			CLIENT_KEY = "UblanI8WddIGUpDqRQX5yYTry4yr2H78nwdthlEp";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Enable Local Datastore.
		ParseCrashReporting.enable(this);
		Parse.enableLocalDatastore(this);
		Parse.initialize(this, APP_ID, CLIENT_KEY);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		if (itemId == R.id.menu_about) {
			new AlertDialog.Builder(this)
					.setTitle("About")
					.setMessage(
							"This application is for use of keeping up with Youtube videos from your favorite channels!/n Developed By: Harley Dishon")
					.setNeutralButton("OK",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {

								}
							})
					.setPositiveButton("Review This App",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									String playuri = "market://details?id=com.ability44.youtubewatcher";
									if (appInstalledOrNot(playuri) == true) {
										Intent intent = new Intent(
												Intent.ACTION_VIEW, Uri
														.parse(playuri));
										startActivity(intent);
									} else {
										Intent intent = new Intent(
												Intent.ACTION_VIEW,
												Uri.parse("https://play.google.com/store/apps/details?id=com.ability44.youtubewatcher"));
										startActivity(intent);
									}
								}
							}).show();
		}

		return true;
	}

	private boolean appInstalledOrNot(String uri) {
		PackageManager pm = getPackageManager();
		boolean app_installed = false;
		try {
			pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
			app_installed = true;
		} catch (PackageManager.NameNotFoundException e) {
			app_installed = false;
		}
		return app_installed;
	}

}
