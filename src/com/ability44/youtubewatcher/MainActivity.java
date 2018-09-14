package com.ability44.youtubewatcher;

import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ability44.youtubewatcher.AsyncTask.GetRssPubDate;
import com.ability44.youtubewatcher.AsyncTask.LoadReceivers;
import com.ability44.youtubewatcher.List.PubDateFeed;
import com.parse.ParseObject;

public class MainActivity extends BaseActivity {

	SharedPreferences runVerPref, pubDatePref, titlePref, categoryPref,
			descriptionPref, linkPref, guidPref, authorPref, ytPref,
			ytChannelPref;
	String runVerson = "RunVersion", ytp = "YtChan",
			ytChannelsp = "YoutubeChannel", pubDatep = "VidPubDate",
			titlep = "VidTitle", descriptionp = "VidDescription",
			linkp = "VidLink", guidp = "VidGuid", authorp = "VidAuthor",
			AD_UNIT_ID = "ca-app-pub-6918182161737584/1670131951";
	int rvint = 1; // 1
	public static Context context;
	private Activity activity;
	private LinearLayout layout;
	TextView noChanMessage, noChanTitle;
	ListView chanListView;
	List<PubDateFeed> items;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		activity = this;
		context = this;

		runVerPref = this.getSharedPreferences(runVerson, Context.MODE_PRIVATE);
		ytPref = this.getSharedPreferences(ytp, Context.MODE_PRIVATE);
		ytChannelPref = this.getSharedPreferences(ytChannelsp,
				Context.MODE_PRIVATE);
		if (runVerPref.getInt(runVerson, 0) < rvint) {
			new LoadReceivers().execute();
			runVerPref.edit().putInt(runVerson, rvint).commit();
		}
		if (ytChannelPref.getString(ytChannelsp, null) == null) {
			noChanMessage = (TextView) this.findViewById(R.id.tvNoChan);
			noChanTitle = (TextView) this.findViewById(R.id.tvNoChanTitle);
			chanListView = (ListView) this.findViewById(R.id.lvChannels);

			noChanMessage.setVisibility(View.VISIBLE);
			noChanTitle.setVisibility(View.VISIBLE);
			chanListView.setVisibility(View.GONE);
		}
	}

	public void AddChannel(View v) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
		alertDialog.setTitle("Channel Name");
		alertDialog.setMessage("Enter YouTube Channel Name.");
		alertDialog.setNeutralButton("Cancel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});
		alertDialog.setPositiveButton("Add Channel",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}
				});
		LayoutInflater inflater = LayoutInflater.from(activity);
		View dialog_layout = inflater.inflate(R.layout.dialog_yt_chan, null);
		final EditText etYtChan = (EditText) dialog_layout
				.findViewById(R.id.etytchan);
		alertDialog.setView(dialog_layout);
		final AlertDialog dialog = alertDialog.create();
		dialog.show();
		dialog.getButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						ParseObject channelObject = new ParseObject(
								"YoutubeChannel");
						String ytChan;
						String ytChanString = ytChannelPref.getString(
								ytChannelsp, null);
						String etChan = etYtChan.getText().toString();
						if (ytChanString == null || ytChanString == "") {
							ytChan = etChan;
						} else {
							ytChan = ytChanString + "`" + etChan;
						}
						GetRssPubDate getrss = new GetRssPubDate();
						AsyncTask<String, Void, List<PubDateFeed>> rssexecute = getrss
								.execute("http://www.youtube.com/rss/user/"
										+ etChan + "/videos.rss");
						items = null;
						try {
							items = rssexecute.get();
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (ExecutionException e) {
							e.printStackTrace();
						}
						if (ytChanString == null) {
							if (items.isEmpty() == true || etChan == "blank") {
								Toast.makeText(context,
										"That channel does not exist.",
										Toast.LENGTH_LONG).show();
							} else if (items.size() < 25) {
								Toast.makeText(
										context,
										"That channel does not have enough videos uploaded to display it on this app.",
										Toast.LENGTH_LONG).show();
							} else {
								channelObject.put("channel_name", etChan);
								channelObject.put("rss_feed",
										"http://www.youtube.com/rss/user/"
												+ etChan + "/videos.rss");
								channelObject.saveInBackground();
								ytChannelPref.edit()
										.putString(ytChannelsp, ytChan)
										.commit();
								dialog.dismiss();
							}
						} else {
							if (ytChanString.contains(etChan)) {
								Toast.makeText(
										context,
										"You already have this channel added..",
										Toast.LENGTH_LONG).show();

							} else if (items.isEmpty() == true
									|| etChan == "blank") {
								Toast.makeText(context,
										"That channel does not exist.",
										Toast.LENGTH_LONG).show();
							} else if (items.size() < 25) {
								Toast.makeText(
										context,
										"That channel does not have enough videos uploaded to display it on this app.",
										Toast.LENGTH_LONG).show();
							} else {
								ytChannelPref.edit()
										.putString(ytChannelsp, ytChan)
										.commit();
								dialog.dismiss();
							}
						}
					}
				});
		Toast.makeText(context, ytChannelPref.getString(ytChannelsp, null),
				Toast.LENGTH_LONG).show();
	}

	// Intent intent = new Intent(this, YtVids.class);
	// ytPref.edit().putString(ytChannelp, "WhatsUpWithRs").commit();
	// startActivity(intent);
	// onPause();
}
