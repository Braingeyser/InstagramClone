package com.example.android.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
	@Override
	public void onCreate() {
		super.onCreate();

		Parse.initialize(new Parse.Configuration.Builder(this)
				.applicationId("fRPtXs4fvWuaSkMXZT5IE5no8fCH5kw68aIq3XkH")
				// if defined
				.clientKey("KI4fxYlv2afiOIVce7gWpMigB3aNSM4qn51FbBGE")
				.server("https://parseapi.back4app.com/")
				.build()
		);
	}
}
