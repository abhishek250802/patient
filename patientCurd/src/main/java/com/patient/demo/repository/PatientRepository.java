package com.patient.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.patient.demo.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{

	@Query("SELECT p FROM Patient p WHERE p.id= ?1")
	Patient findFirstById(int id);
}
