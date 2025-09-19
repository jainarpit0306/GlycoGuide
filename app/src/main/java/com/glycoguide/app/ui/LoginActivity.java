package com.glycoguide.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.glycoguide.app.R;
import com.glycoguide.app.auth.AuthService;
import com.glycoguide.app.auth.SessionManager;

public class LoginActivity extends BaseActivity {
	private EditText etUsername;
	private EditText etPassword;
	private AuthService authService;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		authService = new AuthService();
		setupHeader();
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(false);
			getSupportActionBar().setTitle(R.string.title_login);
		}

		etUsername = findViewById(R.id.etUsername);
		etPassword = findViewById(R.id.etPassword);
		Button btnLogin = findViewById(R.id.btnLogin);
		TextView tvReset = findViewById(R.id.tvResetPassword);

		btnLogin.setOnClickListener(v -> attemptLogin());
		tvReset.setOnClickListener(v -> startActivity(new Intent(this, ResetPasswordActivity.class)));
	}

	private void attemptLogin() {
		String username = etUsername.getText().toString();
		String password = etPassword.getText().toString();

		if (TextUtils.isEmpty(username)) {
			etUsername.setError("Username required");
			return;
		}
		if (TextUtils.isEmpty(password)) {
			etPassword.setError("Password required");
			return;
		}

		if (authService.isValidCredentials(this, username, password)) {
			SessionManager sm = new SessionManager(this);
			sm.setLoggedIn(true);
			sm.setDoctorName("Dr. " + username);
			sm.setUsername(username);
			// do not store entered password permanently here; SessionManager is used by change password
			startActivity(new Intent(this, DashboardActivity.class));
			finish();
		} else {
			Toast.makeText(this, R.string.error_invalid_login, Toast.LENGTH_SHORT).show();
		}
	}
}
