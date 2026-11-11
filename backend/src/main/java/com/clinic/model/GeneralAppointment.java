package com.clinic.model;

public class GeneralAppointment extends Appointment {

    public GeneralAppointment(String id, String patientId, String doctorId, String date, String time) {
        super(id, patientId, doctorId, date, time);
    }

    @Override
    public double getConsultationFee() {
        return 1500.00; // LKR
    }
        @Override
    public String getType() {
        return "GENERAL";
    }
}