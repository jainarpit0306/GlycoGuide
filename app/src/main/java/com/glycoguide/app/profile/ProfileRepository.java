package com.glycoguide.app.profile;

import android.content.Context;
import android.content.SharedPreferences;

public class ProfileRepository {
	private static final String PREFS = "glycoguide_profile";
	private static final String KEY_FULL_NAME = "full_name";
	private static final String KEY_USERNAME = "username";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_PHONE = "phone";
	private static final String KEY_SPECIALIZATION = "specialization";
	private static final String KEY_CLINIC_NAME = "clinic_name";
	private static final String KEY_CLINIC_ADDRESS = "clinic_address";
	private static final String KEY_QUALIFICATIONS = "qualifications";
	private static final String KEY_REG_NO = "registration_number";
	private static final String KEY_EXPERIENCE = "years_experience";
	private static final String KEY_LANGUAGES = "languages";
	private static final String KEY_HOURS = "consultation_hours";
	private static final String KEY_AFFILIATIONS = "affiliations";
	private static final String KEY_PHOTO_URI = "photo_uri";

	private final SharedPreferences prefs;

	public ProfileRepository(Context context) {
		prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
	}

	public UserProfile loadProfile() {
		UserProfile p = new UserProfile();
		p.fullName = prefs.getString(KEY_FULL_NAME, "Dr. John Doe");
		p.username = prefs.getString(KEY_USERNAME, "doctor");
		p.email = prefs.getString(KEY_EMAIL, "doctor@example.com");
		p.phone = prefs.getString(KEY_PHONE, "+1 555-123-4567");
		p.specialization = prefs.getString(KEY_SPECIALIZATION, "Diabetology");
		p.clinicName = prefs.getString(KEY_CLINIC_NAME, "GlycoGuide Clinic");
		p.clinicAddress = prefs.getString(KEY_CLINIC_ADDRESS, "123 Health St, Wellness City");
		p.qualifications = prefs.getString(KEY_QUALIFICATIONS, "MBBS, MD (Medicine), DM (Endocrinology)");
		p.registrationNumber = prefs.getString(KEY_REG_NO, "REG-123456");
		p.yearsOfExperience = prefs.getString(KEY_EXPERIENCE, "10");
		p.languages = prefs.getString(KEY_LANGUAGES, "English, Hindi");
		p.consultationHours = prefs.getString(KEY_HOURS, "Mon-Fri 10:00-13:00, 17:00-20:00");
		p.affiliations = prefs.getString(KEY_AFFILIATIONS, "Endocrine Society, ADA");
		p.photoUri = prefs.getString(KEY_PHOTO_URI, "");
		return p;
	}

	public void saveProfile(UserProfile p) {
		prefs.edit()
			.putString(KEY_FULL_NAME, p.fullName)
			.putString(KEY_USERNAME, p.username)
			.putString(KEY_EMAIL, p.email)
			.putString(KEY_PHONE, p.phone)
			.putString(KEY_SPECIALIZATION, p.specialization)
			.putString(KEY_CLINIC_NAME, p.clinicName)
			.putString(KEY_CLINIC_ADDRESS, p.clinicAddress)
			.putString(KEY_QUALIFICATIONS, p.qualifications)
			.putString(KEY_REG_NO, p.registrationNumber)
			.putString(KEY_EXPERIENCE, p.yearsOfExperience)
			.putString(KEY_LANGUAGES, p.languages)
			.putString(KEY_HOURS, p.consultationHours)
			.putString(KEY_AFFILIATIONS, p.affiliations)
			.putString(KEY_PHOTO_URI, p.photoUri)
			.apply();
	}
}
