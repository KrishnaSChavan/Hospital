package com.hms.hospital.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.hospital.entity.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {
    List<Bill> findByPatient_PatientIdIn(Set<Long> patientIds);

}
