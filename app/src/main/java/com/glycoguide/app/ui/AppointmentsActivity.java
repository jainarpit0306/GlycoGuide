package com.glycoguide.app.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.glycoguide.app.R;

public class AppointmentsActivity extends BaseActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_section);
		setupHeader();
		if (getSupportActionBar() != null) {
			getSupportActionBar().setTitle(R.string.title_appointments);
		}
	}
}
