package com.patient.demo.service;

import java.util.List;

import com.patient.demo.model.PatientModel;

public interface PatientService {
	
	List<PatientModel> getAllPatients();
	
	PatientModel getAllPatientsById();
	
	PatientModel createPatient(PatientModel patientModel);
	
	PatientModel updatePatient(int id, PatientModel patientDetails);
	
	int deletePatientsById(int id);

	PatientModel getPatientsById(int id);


	

}
