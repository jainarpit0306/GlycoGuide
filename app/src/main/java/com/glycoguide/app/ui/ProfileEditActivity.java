package com.glycoguide.app.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.glycoguide.app.R;
import com.glycoguide.app.auth.SessionManager;
import com.glycoguide.app.profile.ProfileRepository;
import com.glycoguide.app.profile.UserProfile;

public class ProfileEditActivity extends BaseActivity {
	private EditText etFullName, etUsername, etEmail, etPhone, etSpecialization, etQualifications, etRegNumber, etExperience, etLanguages, etConsultationHours, etAffiliations, etClinicName, etClinicAddress;
	private ProfileRepository repo;
	private UserProfile profile;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_edit);
		setupHeader();
		if (getSupportActionBar() != null) {
			getSupportActionBar().setTitle("Edit Profile");
		}
		repo = new ProfileRepository(this);
		bindViews();
		loadProfile();
	}

	private void bindViews() {
		etFullName = findViewById(R.id.etFullName);
		etUsername = findViewById(R.id.etUsername);
		etEmail = findViewById(R.id.etEmail);
		etPhone = findViewById(R.id.etPhone);
		etSpecialization = findViewById(R.id.etSpecialization);
		etQualifications = findViewById(R.id.etQualifications);
		etRegNumber = findViewById(R.id.etRegNumber);
		etExperience = findViewById(R.id.etExperience);
		etLanguages = findViewById(R.id.etLanguages);
		etConsultationHours = findViewById(R.id.etConsultationHours);
		etAffiliations = findViewById(R.id.etAffiliations);
		etClinicName = findViewById(R.id.etClinicName);
		etClinicAddress = findViewById(R.id.etClinicAddress);
		Button btnSave = findViewById(R.id.btnSaveProfile);
		btnSave.setOnClickListener(v -> saveProfile());
	}

	private void loadProfile() {
		profile = repo.loadProfile();
		etFullName.setText(profile.fullName);
		etUsername.setText(profile.username);
		etEmail.setText(profile.email);
		etPhone.setText(profile.phone);
		etSpecialization.setText(profile.specialization);
		etQualifications.setText(profile.qualifications);
		etRegNumber.setText(profile.registrationNumber);
		etExperience.setText(profile.yearsOfExperience);
		etLanguages.setText(profile.languages);
		etConsultationHours.setText(profile.consultationHours);
		etAffiliations.setText(profile.affiliations);
		etClinicName.setText(profile.clinicName);
		etClinicAddress.setText(profile.clinicAddress);
	}

	private void saveProfile() {
		String fullName = etFullName.getText().toString().trim();
		String username = etUsername.getText().toString().trim();
		String email = etEmail.getText().toString().trim();
		if (TextUtils.isEmpty(fullName)) { etFullName.setError("Required"); return; }
		if (TextUtils.isEmpty(username)) { etUsername.setError("Required"); return; }
		if (TextUtils.isEmpty(email)) { etEmail.setError("Required"); return; }

		profile.fullName = fullName;
		profile.username = username;
		profile.email = email;
		profile.phone = etPhone.getText().toString().trim();
		profile.specialization = etSpecialization.getText().toString().trim();
		profile.qualifications = etQualifications.getText().toString().trim();
		profile.registrationNumber = etRegNumber.getText().toString().trim();
		profile.yearsOfExperience = etExperience.getText().toString().trim();
		profile.languages = etLanguages.getText().toString().trim();
		profile.consultationHours = etConsultationHours.getText().toString().trim();
		profile.affiliations = etAffiliations.getText().toString().trim();
		profile.clinicName = etClinicName.getText().toString().trim();
		profile.clinicAddress = etClinicAddress.getText().toString().trim();

		repo.saveProfile(profile);
		SessionManager sm = new SessionManager(this);
		sm.setDoctorName(fullName);
		sm.setUsername(username);
		Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show();
		finish();
	}
}
