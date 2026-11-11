package com.clinic.repository;

import com.clinic.model.Appointment;
import com.clinic.model.GeneralAppointment;
import com.clinic.model.SpecialistAppointment;
import java.io.*;
import java.util.*;

// The only class that reads and writes appointments.txt.
// Servlets call this class and never touch the file directly.
public class AppointmentRepository {

    private static final String FILE = "C:/medbook-data/appointments.txt";

    // CREATE: add one line at the end of the file
    public void save(Appointment a) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {
            bw.write(a.toFileLine());
            bw.newLine();
        }
    }

    // READ: load every line and turn it into an object
    public List<Appointment> findAll() throws IOException {
        List<Appointment> list = new ArrayList<>();
        File f = new File(FILE);
        if (!f.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    list.add(parse(line));
                }
            }
        }
        return list;
    }

    // READ one appointment by ID (returns null if not found)
    public Appointment findById(String id) throws IOException {
        for (Appointment a : findAll()) {
            if (a.getId().equals(id)) return a;
        }
        return null;
    }

    // UPDATE: change the status of one appointment, then rewrite the file
    public void updateStatus(String id, String newStatus) throws IOException {
        List<Appointment> all = findAll();
        for (Appointment a : all) {
            if (a.getId().equals(id)) {
                a.setStatus(newStatus);
            }
        }
        rewriteAll(all);
    }

    // DELETE: remove one appointment, then rewrite the file
    public void delete(String id) throws IOException {
        List<Appointment> all = findAll();
        all.removeIf(a -> a.getId().equals(id));
        rewriteAll(all);
    }

    // Overwrites the whole file with the given list
    private void rewriteAll(List<Appointment> list) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
            for (Appointment a : list) {
                bw.write(a.toFileLine());
                bw.newLine();
            }
        }
    }

    // Turns one text line back into the correct subclass
    private Appointment parse(String line) {
        String[] p = line.split("\\|");
        // p[0]=id, p[1]=patientId, p[2]=doctorId, p[3]=date, p[4]=time, p[5]=status, p[6]=type
        Appointment a = p[6].equals("SPECIALIST")
                ? new SpecialistAppointment(p[0], p[1], p[2], p[3], p[4])
                : new GeneralAppointment(p[0], p[1], p[2], p[3], p[4]);
        a.setStatus(p[5]); // restore the saved status (the constructor sets BOOKED)
        return a;
    }
}