package com.ability44.youtubewatcher.Utilities;

import java.util.List;

import com.ability44.youtubewatcher.List.PubDateFeed;
import com.ability44.youtubewatcher.List.YtRSSFeed;

/**
 * @author Harley Dishon(ability44@ymail.com)
 * 
 */

public class StringBuilder {
	
	public static String pubDates(List<PubDateFeed> items) {
		String itemsPub = "";
		for (int i = 0; i < items.size(); i++) {
			String time = items.get(i).getPubdate() + "`";
			itemsPub = itemsPub + time;
		}
		return itemsPub;
	}
	
	public static String ytPubDates(List<YtRSSFeed> items) {
		String itemsPub = "";
		for (int i = 0; i < items.size(); i++) {
			String time = items.get(i).getPubdate() + "`";
			itemsPub = itemsPub + time;
		}
		return itemsPub;
	}

	public static String ytTitles(List<YtRSSFeed> items) {
		String itemsTitle = "";
		for (int i = 0; i < items.size(); i++) {
			String title = items.get(i).getTitle() + "`";
			itemsTitle = itemsTitle + title;
		}
		return itemsTitle;
	}

	public static String ytDescriptions(List<YtRSSFeed> items) {
		String itemsDec = "";
		for (int i = 0; i < items.size(); i++) {
			String dec = items.get(i).getDescription() + "`";
			itemsDec = itemsDec + dec;
		}
		return itemsDec;
	}

	public static String ytLinks(List<YtRSSFeed> items) {
		String itemsLink = "";
		for (int i = 0; i < items.size(); i++) {
			String link = items.get(i).getLink() + "`";
			itemsLink = itemsLink + link;
		}
		return itemsLink;
	}

	public static String ytGuids(List<YtRSSFeed> items) {
		String itemsGuid = "";
		for (int i = 0; i < items.size(); i++) {
			String guid = items.get(i).getGuid() + "`";
			itemsGuid = itemsGuid + guid;
		}
		return itemsGuid;
	}

	public static String ytAuthor(List<YtRSSFeed> items) {
		String itemsAuthor = "";
		for (int i = 0; i < items.size(); i++) {
			String author = items.get(i).getGuid() + "`";
			itemsAuthor = itemsAuthor + author;
		}
		return itemsAuthor;
	}
}
