package com.theopentutorial;

import com.theopentutorial.expandablelist.MainActivity;

import android.app.Application;

public class MyApplication extends Application {

	public MyApplication() {
		super();
		fixClassLoaderIssue();
	}

	private static void fixClassLoaderIssue() {
		ClassLoader myClassLoader = MainActivity.class.getClassLoader();
		Thread.currentThread().setContextClassLoader(myClassLoader);
	}
}
