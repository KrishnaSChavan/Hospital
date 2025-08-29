package com.hms.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hms.hospital.entity.Appointment;
import com.hms.hospital.entity.Bill;
import com.hms.hospital.service.AppointmentService;
import com.hms.hospital.service.BillService;
import com.hms.hospital.service.DoctorService;

@Controller
@RequestMapping("/doctor/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/create/{appointmentId}")
    public String showCreateBillForm(@PathVariable Long appointmentId, Model model) {
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);

        model.addAttribute("appointment", appointment);
        model.addAttribute("bill", new Bill());
        return "doctor/create-bill";
    }

    // Handle bill creation
    @PostMapping("/create")
    public String createBill(@RequestParam Long appointmentId,
                             @RequestParam Double totalAmount,
                             @RequestParam Bill.PaymentStatus paymentStatus) {
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        Bill bill = new Bill();
        bill.setPatient(appointment.getPatient());
        bill.setTotalAmount(totalAmount);
        bill.setPaymentStatus(paymentStatus);
        billService.createBill(bill);
        return "redirect:/doctors/";
    }

    @PostMapping("/pay/{billId}")
    public String payBill(@PathVariable Long billId) {
        billService.payBill(billId);
        return "redirect:/patient/";
    }
}
