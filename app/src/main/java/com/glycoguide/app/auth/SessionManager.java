package com.glycoguide.app.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class SessionManager {
	private static final String PREFS = "glycoguide_prefs";
	private static final String KEY_LOGGED_IN = "logged_in";
	private static final String KEY_DOCTOR_NAME = "doctor_name";
	private static final String KEY_USERNAME = "username";
	private static final String KEY_PASSWORD = "password";

	private final SharedPreferences prefs;

	public SessionManager(Context context) {
		this.prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
	}

	public void setLoggedIn(boolean loggedIn) {
		prefs.edit().putBoolean(KEY_LOGGED_IN, loggedIn).apply();
	}

	public boolean isLoggedIn() {
		return prefs.getBoolean(KEY_LOGGED_IN, false);
	}

	public void setDoctorName(String name) {
		prefs.edit().putString(KEY_DOCTOR_NAME, name).apply();
	}

	public String getDoctorName() {
		return prefs.getString(KEY_DOCTOR_NAME, "Dr. John Doe");
	}

	public void setUsername(String username) {
		prefs.edit().putString(KEY_USERNAME, username).apply();
	}

	public String getUsername() {
		return prefs.getString(KEY_USERNAME, "doctor");
	}

	public void setPassword(String password) {
		prefs.edit().putString(KEY_PASSWORD, password).apply();
	}

	public String getPassword() {
		return prefs.getString(KEY_PASSWORD, "");
	}

	public void ensureDefaultCredentials() {
		if (TextUtils.isEmpty(getPassword())) {
			setUsername("doctor");
			setPassword("password123");
			setDoctorName("Dr. " + getUsername());
		}
	}

	public void logout() {
		prefs.edit().clear().apply();
	}
}
