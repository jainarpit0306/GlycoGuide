package com.glycoguide.app.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.glycoguide.app.R;
import com.glycoguide.app.auth.SessionManager;

public class ChangePasswordActivity extends BaseActivity {
	private EditText etCurrent, etNew, etConfirm;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		setupHeader();
		if (getSupportActionBar() != null) {
			getSupportActionBar().setTitle("Change Password");
		}

		etCurrent = findViewById(R.id.etCurrentPassword);
		etNew = findViewById(R.id.etNewPassword);
		etConfirm = findViewById(R.id.etConfirmPassword);
		Button btnUpdate = findViewById(R.id.btnUpdatePassword);

		btnUpdate.setOnClickListener(v -> attemptChange());
	}

	private void attemptChange() {
		SessionManager sm = new SessionManager(this);
		String current = etCurrent.getText().toString();
		String newP = etNew.getText().toString();
		String confirm = etConfirm.getText().toString();

		if (TextUtils.isEmpty(current)) { etCurrent.setError("Required"); return; }
		if (TextUtils.isEmpty(newP)) { etNew.setError("Required"); return; }
		if (newP.length() < 6) { etNew.setError("Min 6 characters"); return; }
		if (!newP.equals(confirm)) { etConfirm.setError("Passwords do not match"); return; }

		String stored = sm.getPassword();
		if (!TextUtils.isEmpty(stored) && !stored.equals(current)) {
			etCurrent.setError("Incorrect current password");
			return;
		}

		sm.setPassword(newP);
		Toast.makeText(this, "Password updated", Toast.LENGTH_SHORT).show();
		finish();
	}
}
