package com.glycoguide.app.ui;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.glycoguide.app.R;
import com.glycoguide.app.auth.SessionManager;
import com.glycoguide.app.profile.ProfileRepository;
import com.glycoguide.app.profile.UserProfile;

public abstract class BaseActivity extends AppCompatActivity {
	protected SessionManager sessionManager;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sessionManager = new SessionManager(this);
	}

	protected void setupHeader() {
		androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		TextView doctorName = findViewById(R.id.txtDoctorName);
		if (doctorName != null) {
			doctorName.setText(sessionManager.getDoctorName());
		}
		ImageView imgDoctor = findViewById(R.id.imgDoctor);
		if (imgDoctor != null) {
			UserProfile p = new ProfileRepository(this).loadProfile();
			if (p.photoUri != null && !p.photoUri.isEmpty()) {
				imgDoctor.setImageURI(Uri.parse(p.photoUri));
			}
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			onBackPressed();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
