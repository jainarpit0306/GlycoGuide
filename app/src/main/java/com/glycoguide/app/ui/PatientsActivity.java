package com.glycoguide.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.glycoguide.app.R;
import com.glycoguide.app.patients.Patient;
import com.glycoguide.app.patients.PatientRepository;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PatientsActivity extends BaseActivity {
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patients);
		setupHeader();
		if (getSupportActionBar() != null) {
			getSupportActionBar().setTitle(R.string.title_patients);
		}

		RecyclerView recyclerView = findViewById(R.id.recyclerPatients);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		PatientRepository repo = new PatientRepository(this);
		List<Patient> patients = repo.listPatients();
		recyclerView.setAdapter(new PatientsAdapter(patients, id -> {
			Intent i = new Intent(this, PatientDetailActivity.class);
			i.putExtra("patient_id", id);
			startActivity(i);
		}));
	}

	private static class PatientsAdapter extends RecyclerView.Adapter<PatientsAdapter.VH> {
		interface OnPatientClick { void onClick(String patientId); }
		private final List<Patient> data;
		private final OnPatientClick onClick;
		PatientsAdapter(List<Patient> data, OnPatientClick onClick) { this.data = data; this.onClick = onClick; }

		@NonNull @Override public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
			View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient_card, parent, false);
			return new VH(v);
		}
		@Override public void onBindViewHolder(@NonNull VH h, int pos) {
			Patient p = data.get(pos);
			h.txtName.setText(p.fullName);
			h.txtMeta.setText("Age " + p.age + " • " + p.gender + " • Last: " + p.lastVisit);
			h.txtSummary.setText(p.diagnosisSummary);
			h.itemView.setOnClickListener(v -> onClick.onClick(p.id));
		}
		@Override public int getItemCount() { return data.size(); }
		static class VH extends RecyclerView.ViewHolder {
			CircleImageView img;
			TextView txtName; TextView txtMeta; TextView txtSummary;
			VH(@NonNull View itemView) {
				super(itemView);
				img = itemView.findViewById(R.id.imgPatient);
				txtName = itemView.findViewById(R.id.txtName);
				txtMeta = itemView.findViewById(R.id.txtMeta);
				txtSummary = itemView.findViewById(R.id.txtSummary);
			}
		}
	}
}
