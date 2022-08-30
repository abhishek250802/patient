package com.patient.demo.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.patient.demo.exception.MareezAlreadyExist;
import com.patient.demo.model.Patient;
import com.patient.demo.model.PatientModel;
import com.patient.demo.repository.PatientRepository;
import com.patient.demo.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	private final PatientRepository patientRepository;
	
	PatientServiceImpl(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	@Override
	public List<PatientModel> getAllPatients() {
		List<Patient> patients = new ArrayList<Patient>();
		patientRepository.findAll().forEach((patient) -> patients.add(patient));
		List<PatientModel> patientsModel = new ArrayList<PatientModel>();
		
		for (Patient p : patients) {
			PatientModel pm = new PatientModel();
			pm.setId(p.getId());
			pm.setPatient_name(p.getPatient_name());
			pm.setPatient_phone(p.getPatient_phone());
			pm.setPatient_disease(p.getPatient_disease());
			pm.setBed_no(p.getBed_no());
			pm.setAddress(p.getAddress());
			patientsModel.add(pm);
		
		}
		return patientsModel;
	}
	@Override
	public PatientModel getPatientsById(int id) {
		Patient patient = patientRepository.findById(id).get();
		
		PatientModel pm = new PatientModel();
		pm.setId(patient.getId());
		pm.setPatient_name(patient.getPatient_name());
		pm.setPatient_phone(patient.getPatient_phone());
		pm.setPatient_disease(patient.getPatient_disease());
		pm.setBed_no(patient.getBed_no());
		pm.setAddress(patient.getAddress());
		return pm;
	}
	
	@Override
	public PatientModel createPatient(@Valid PatientModel patientModel) {
		Patient p = patientRepository.findFirstById(patientModel.getId());
		if(Objects.nonNull(p)) {
			throw new MareezAlreadyExist("Mareez Already Exist with id : "+p.getId());
				}
		Patient patient = new Patient();
		patient.setPatient_name(patientModel.getPatient_name());
		patient.setPatient_phone(patientModel.getPatient_phone());
	    patient.setPatient_disease(patientModel.getPatient_disease());
	    patient.setBed_no(patientModel.getBed_no());
	    patient.setAddress(patientModel.getAddress());
	    patientRepository.save(patient);
		return patientModel;
	}
	
	public PatientServiceImpl() {
		this.patientRepository = null;}
	


	@Override
	public PatientModel updatePatient(int id, PatientModel patientDetails) {
		Patient updatePatient = null;
	
		updatePatient = patientRepository.findById(id).get();
		
		updatePatient.setPatient_name(patientDetails.getPatient_name());
		updatePatient.setPatient_phone(patientDetails.getPatient_phone());
		updatePatient.setPatient_disease(patientDetails.getPatient_disease());
		updatePatient.setBed_no(patientDetails.getBed_no());
		updatePatient.setAddress(patientDetails.getAddress());
		
		patientRepository.save(updatePatient);
		return patientDetails;
	}
	@Override
	public int deletePatientsById(int id) {
		patientRepository.deleteById(id);
		return id;
	}
	@Override
	public PatientModel getAllPatientsById() {
	
		return null;
	
}
	}