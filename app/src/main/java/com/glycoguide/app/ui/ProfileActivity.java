package com.glycoguide.app.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.glycoguide.app.R;
import com.glycoguide.app.auth.SessionManager;
import com.glycoguide.app.profile.ProfileRepository;
import com.glycoguide.app.profile.UserProfile;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends BaseActivity {
	private static final int REQ_PICK_IMAGE = 1001;
	private EditText etFullName, etUsername, etEmail, etPhone, etSpecialization, etClinicName, etClinicAddress;
	private EditText etQualifications, etRegNumber, etExperience, etLanguages, etConsultationHours, etAffiliations;
	private Button btnEditSave, btnChangePassword, btnChangePhoto;
	private boolean editMode = false;
	private ProfileRepository repo;
	private UserProfile profile;
	private CircleImageView imgProfilePhoto;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		setupHeader();
		if (getSupportActionBar() != null) {
			getSupportActionBar().setTitle(R.string.title_profile);
		}
		repo = new ProfileRepository(this);
		bindViews();
		loadProfile();
		setEditMode(false);
	}

	private void bindViews() {
		imgProfilePhoto = findViewById(R.id.imgProfilePhoto);
		btnChangePhoto = findViewById(R.id.btnChangePhoto);

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
		btnEditSave = findViewById(R.id.btnEditSave);
		btnChangePassword = findViewById(R.id.btnChangePassword);

		btnEditSave.setOnClickListener(v -> {
			if (editMode) {
				if (saveProfile()) {
					setEditMode(false);
				}
			} else {
				setEditMode(true);
			}
		});

		btnChangePassword.setOnClickListener(v -> startActivity(new Intent(this, ChangePasswordActivity.class)));
		btnChangePhoto.setOnClickListener(v -> pickImage());
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
		if (!TextUtils.isEmpty(profile.photoUri)) {
			imgProfilePhoto.setImageURI(Uri.parse(profile.photoUri));
		}
	}

	private boolean saveProfile() {
		String fullName = etFullName.getText().toString().trim();
		String username = etUsername.getText().toString().trim();
		String email = etEmail.getText().toString().trim();
		String phone = etPhone.getText().toString().trim();
		String specialization = etSpecialization.getText().toString().trim();
		String qualifications = etQualifications.getText().toString().trim();
		String regNo = etRegNumber.getText().toString().trim();
		String exp = etExperience.getText().toString().trim();
		String languages = etLanguages.getText().toString().trim();
		String hours = etConsultationHours.getText().toString().trim();
		String affiliations = etAffiliations.getText().toString().trim();
		String clinicName = etClinicName.getText().toString().trim();
		String clinicAddress = etClinicAddress.getText().toString().trim();

		if (TextUtils.isEmpty(fullName)) { etFullName.setError("Required"); return false; }
		if (TextUtils.isEmpty(username)) { etUsername.setError("Required"); return false; }
		if (TextUtils.isEmpty(email)) { etEmail.setError("Required"); return false; }

		profile.fullName = fullName;
		profile.username = username;
		profile.email = email;
		profile.phone = phone;
		profile.specialization = specialization;
		profile.qualifications = qualifications;
		profile.registrationNumber = regNo;
		profile.yearsOfExperience = exp;
		profile.languages = languages;
		profile.consultationHours = hours;
		profile.affiliations = affiliations;
		profile.clinicName = clinicName;
		profile.clinicAddress = clinicAddress;

		repo.saveProfile(profile);

		SessionManager sm = new SessionManager(this);
		sm.setDoctorName(fullName);
		sm.setUsername(username);
		Toast.makeText(this, "Profile saved", Toast.LENGTH_SHORT).show();
		return true;
	}

	private void setEditMode(boolean enable) {
		editMode = enable;
		etFullName.setEnabled(enable);
		etUsername.setEnabled(enable);
		etEmail.setEnabled(enable);
		etPhone.setEnabled(enable);
		etSpecialization.setEnabled(enable);
		etQualifications.setEnabled(enable);
		etRegNumber.setEnabled(enable);
		etExperience.setEnabled(enable);
		etLanguages.setEnabled(enable);
		etConsultationHours.setEnabled(enable);
		etAffiliations.setEnabled(enable);
		etClinicName.setEnabled(enable);
		etClinicAddress.setEnabled(enable);
		btnEditSave.setText(enable ? "Save" : "Edit");
		btnChangePhoto.setEnabled(enable);
	}

	private void pickImage() {
		Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intent, REQ_PICK_IMAGE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQ_PICK_IMAGE && resultCode == RESULT_OK && data != null) {
			Uri uri = data.getData();
			if (uri != null) {
				imgProfilePhoto.setImageURI(uri);
				if (profile != null) {
					profile.photoUri = uri.toString();
					repo.saveProfile(profile);
				}
			}
		}
	}
}
