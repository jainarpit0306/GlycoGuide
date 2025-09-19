package com.glycoguide.app.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.glycoguide.app.R;

public class ResetPasswordActivity extends BaseActivity {
	private EditText etIdentifier;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reset_password);
		setupHeader();
		if (getSupportActionBar() != null) {
			getSupportActionBar().setTitle(R.string.title_reset_password);
		}

		etIdentifier = findViewById(R.id.etIdentifier);
		Button btnSubmit = findViewById(R.id.btnSubmit);

		btnSubmit.setOnClickListener(v -> {
			String id = etIdentifier.getText().toString();
			if (TextUtils.isEmpty(id)) {
				etIdentifier.setError("Enter username or email");
				return;
			}
			Toast.makeText(this, "Password reset link sent (mock)", Toast.LENGTH_LONG).show();
			finish();
		});
	}
}
