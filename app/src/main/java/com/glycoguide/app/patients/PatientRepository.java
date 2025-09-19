package com.glycoguide.app.patients;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PatientRepository {
	private static List<Patient> cache;
	private static Map<String, Patient> idMap;

	public PatientRepository(Context context) {
		if (cache == null) {
			seed();
		}
	}

	public List<Patient> listPatients() {
		return new ArrayList<>(cache);
	}

	public Patient getById(String id) {
		return idMap.get(id);
	}

	private static void seed() {
		cache = new ArrayList<>();
		idMap = new HashMap<>();
		for (int i = 1; i <= 15; i++) {
			Patient p = new Patient();
			p.id = UUID.randomUUID().toString();
			p.fullName = "Patient " + i;
			p.age = String.valueOf(25 + (i % 30));
			p.gender = (i % 2 == 0) ? "Male" : "Female";
			p.phone = "+1 555-000-" + String.format("%04d", i);
			p.photoUri = ""; // placeholder; could be assigned later
			p.lastVisit = "2025-09-" + String.format("%02d", (i % 28) + 1);
			p.diagnosisSummary = (i % 3 == 0) ? "Type 2 Diabetes" : (i % 3 == 1) ? "Pre-diabetes" : "Gestational risk";
			cache.add(p);
			idMap.put(p.id, p);
		}
	}
}
