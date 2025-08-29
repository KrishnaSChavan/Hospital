package com.hms.hospital.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.hms.hospital.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.hospital.entity.Appointment;
import com.hms.hospital.entity.Bill;
import com.hms.hospital.repository.BillRepository;

@Service
public class BillService {
    @Autowired
    BillRepository billRepository;

    @Autowired
    AppointmentService appointmentService;

    public Bill createBill(Bill bill) {
        return billRepository.save(bill);
    }

    public Bill getBillById(Long id) {
        return billRepository.findById(id).orElse(null);
    }
    public List<Bill> getBillsByDoctorId(Long doctorId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);

        // Extract a unique set of patient IDs from all appointments
        Set<Long> patientIds = appointments.stream()
                .map(appointment -> appointment.getPatient().getPatientId())
                .collect(Collectors.toSet());

        // Find all bills for all patients in the unique set of IDs
        return billRepository.findByPatient_PatientIdIn(patientIds);
    }
    public List<Bill> getBillsByPatientId(Long patientId) {
        return billRepository.findByPatient_PatientIdIn(Set.of(patientId));
    }

    public void payBill(Long billId) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid appointment ID: " + billId));;
        bill.setPaymentStatus(Bill.PaymentStatus.PAID);
        billRepository.save(bill);
    }
}
