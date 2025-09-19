package com.glycoguide.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.glycoguide.app.R;

public class DashboardActivity extends BaseActivity implements View.OnClickListener {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		setupHeader();
		if (getSupportActionBar() != null) {
			getSupportActionBar().setTitle(R.string.title_dashboard);
		}

		findViewById(R.id.cardPatients).setOnClickListener(this);
		findViewById(R.id.cardAppointments).setOnClickListener(this);
		findViewById(R.id.cardRecords).setOnClickListener(this);
		findViewById(R.id.cardMedication).setOnClickListener(this);
		findViewById(R.id.cardProfile).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.cardPatients) {
			startActivity(new Intent(this, PatientsActivity.class));
		} else if (id == R.id.cardAppointments) {
			startActivity(new Intent(this, AppointmentsActivity.class));
		} else if (id == R.id.cardRecords) {
			startActivity(new Intent(this, RecordsActivity.class));
		} else if (id == R.id.cardMedication) {
			startActivity(new Intent(this, MedicationActivity.class));
		} else if (id == R.id.cardProfile) {
			startActivity(new Intent(this, ProfileActivity.class));
		}
	}
}
