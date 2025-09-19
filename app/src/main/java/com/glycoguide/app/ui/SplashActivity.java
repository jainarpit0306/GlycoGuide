package com.glycoguide.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;

import com.glycoguide.app.R;
import com.glycoguide.app.auth.SessionManager;

public class SplashActivity extends BaseActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		new Handler(Looper.getMainLooper()).postDelayed(() -> {
			SessionManager sm = new SessionManager(this);
			if (sm.isLoggedIn()) {
				startActivity(new Intent(this, DashboardActivity.class));
			} else {
				startActivity(new Intent(this, LoginActivity.class));
			}
			finish();
		}, 1500);
	}
}
