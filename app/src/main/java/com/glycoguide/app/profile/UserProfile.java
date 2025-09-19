package com.glycoguide.app.profile;

public class UserProfile {
	public String fullName;
	public String username;
	public String email;
	public String phone;
	public String specialization;
	public String clinicName;
	public String clinicAddress;

	// Additional medical profile fields
	public String qualifications; // e.g., MBBS, MD (Medicine), DM (Endocrinology)
	public String registrationNumber; // Medical registration/license number
	public String yearsOfExperience; // Stored as string for simplicity
	public String languages; // comma-separated
	public String consultationHours; // e.g., Mon-Fri 10:00-13:00, 17:00-20:00
	public String affiliations; // hospital/association affiliations
	public String photoUri; // persisted URI string for profile photo
}
