package com.glycoguide.app.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.glycoguide.app.R;
import com.glycoguide.app.patients.Patient;
import com.glycoguide.app.patients.PatientRepository;

public class PatientDetailActivity extends BaseActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_detail);
		setupHeader();
		if (getSupportActionBar() != null) {
			getSupportActionBar().setTitle("Patient Details");
		}

		String id = getIntent().getStringExtra("patient_id");
		Patient p = new PatientRepository(this).getById(id);

		TextView txtHeader = findViewById(R.id.txtPatientHeader);
		if (p != null) {
			txtHeader.setText(p.fullName + " • Age " + p.age + " • " + p.gender);
		}

		Button btnPersonal = findViewById(R.id.btnPersonalInfo);
		Button btnHistory = findViewById(R.id.btnMedicalHistory);
		Button btnCurrent = findViewById(R.id.btnCurrentTreatment);
		Button btnPrescribe = findViewById(R.id.btnPrescribeNew);
		Button btnSubscription = findViewById(R.id.btnSubscription);

		btnPersonal.setOnClickListener(v -> showComingSoon());
		btnHistory.setOnClickListener(v -> showComingSoon());
		btnCurrent.setOnClickListener(v -> showComingSoon());
		btnPrescribe.setOnClickListener(v -> showComingSoon());
		btnSubscription.setOnClickListener(v -> showComingSoon());
	}

	private void showComingSoon() {
		android.widget.Toast.makeText(this, "Coming soon", android.widget.Toast.LENGTH_SHORT).show();
	}
}
