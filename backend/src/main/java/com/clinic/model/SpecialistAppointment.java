package com.clinic.model;

public class SpecialistAppointment extends Appointment {

    public SpecialistAppointment(String id, String patientId, String doctorId, String date, String time) {
        super(id, patientId, doctorId, date, time);
    }

    @Override
    public double getConsultationFee() {
        return 3500.00; // LKR
    }
        @Override
    public String getType() {
        return "SPECIALIST";
    }
}