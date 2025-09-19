package com.glycoguide.app.auth;

import android.content.Context;
import android.text.TextUtils;

public class AuthService {
	private static final String DEMO_USER = "doctor";
	private static final String DEMO_PASS = "password123";

	public boolean isValidCredentials(Context context, String username, String password) {
		if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
			return false;
		}
		SessionManager sm = new SessionManager(context);
		sm.ensureDefaultCredentials();
		String storedUser = sm.getUsername();
		String storedPass = sm.getPassword();
		if (TextUtils.isEmpty(storedUser) || TextUtils.isEmpty(storedPass)) {
			storedUser = DEMO_USER;
			storedPass = DEMO_PASS;
		}
		return username.trim().equals(storedUser) && password.equals(storedPass);
	}
}
