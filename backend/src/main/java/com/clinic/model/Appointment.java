package com.clinic.model;

// Abstract base class: every appointment has these details,
// but the consultation fee depends on the type (General or Specialist).
public abstract class Appointment {

    // Private fields = encapsulation
    private String id;
    private String patientId;
    private String doctorId;
    private String date;    // format: yyyy-MM-dd, e.g. 2026-11-20
    private String time;    // format: HH:mm, e.g. 09:30
    private String status;  // BOOKED, CONFIRMED, COMPLETED, CANCELLED, NO_SHOW

    public Appointment(String id, String patientId, String doctorId, String date, String time) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.status = "BOOKED"; // every new appointment starts as BOOKED
    }

    public String getId() { return id; }
    public String getPatientId() { return patientId; }
    public String getDoctorId() { return doctorId; }
    public String getDate() { return date; }
    public String getTime() { return time; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    // Polymorphism: each subclass returns its own fee
    public abstract double getConsultationFee();
        // Polymorphism: each subclass reports its own type name for storage
    public abstract String getType();

       // Converts this object into one line of appointments.txt (7 fields)
    public String toFileLine() {
        return id + "|" + patientId + "|" + doctorId + "|" + date + "|" + time
             + "|" + status + "|" + getType();
    }
}