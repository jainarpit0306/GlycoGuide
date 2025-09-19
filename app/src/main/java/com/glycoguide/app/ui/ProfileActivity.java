package com.glycoguide.app.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.glycoguide.app.R;
import com.glycoguide.app.auth.SessionManager;
import com.glycoguide.app.profile.ProfileRepository;
import com.glycoguide.app.profile.UserProfile;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends BaseActivity {
	private ProfileRepository repo;
	private UserProfile profile;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		setupHeader();
		if (getSupportActionBar() != null) {
			getSupportActionBar().setTitle(R.string.title_profile);
		}

		repo = new ProfileRepository(this);
		profile = repo.loadProfile();
		bindOverview();
	}

	private void bindOverview() {
		CircleImageView photo = findViewById(R.id.imgProfilePhoto);
		TextView txtName = findViewById(R.id.txtDoctorName);
		TextView txtSpec = findViewById(R.id.txtSpecialty);
		TextView txtEmail = findViewById(R.id.txtEmail);
		TextView valFullName = findViewById(R.id.valFullName);
		TextView valPhone = findViewById(R.id.valPhone);
		TextView valDept = findViewById(R.id.valDepartment);
		TextView valEmp = findViewById(R.id.valEmployeeId);

		if (!TextUtils.isEmpty(profile.photoUri)) { photo.setImageURI(Uri.parse(profile.photoUri)); }
		txtName.setText(profile.fullName);
		txtSpec.setText(profile.specialization);
		txtEmail.setText(profile.email);
		valFullName.setText(profile.fullName);
		valPhone.setText(profile.phone);
		valDept.setText(profile.specialization);
		valEmp.setText(profile.registrationNumber);

		((TextView) findViewById(R.id.statPatients)).setText("156");
		((TextView) findViewById(R.id.statAppointments)).setText("89");
		((TextView) findViewById(R.id.statRecords)).setText("234");

		Button btnEdit = findViewById(R.id.btnEditProfile);
		Button btnChangePass = findViewById(R.id.btnChangePassword);
		Button btnLogout = findViewById(R.id.btnLogout);

		btnEdit.setOnClickListener(v -> startActivity(new Intent(this, ProfileEditActivity.class)));
		btnChangePass.setOnClickListener(v -> startActivity(new Intent(this, ChangePasswordActivity.class)));
		btnLogout.setOnClickListener(v -> {
			new SessionManager(this).logout();
			startActivity(new Intent(this, LoginActivity.class));
			finish();
		});
	}
}
