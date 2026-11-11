package com.clinic.repository;

import com.clinic.model.Patient;
import java.io.*;
import java.util.*;

// The only class that reads and writes patients.txt.
// File format: id|name|email|password|phone
public class PatientRepository {

    private static final String FILE = "C:/medbook-data/patients.txt";

    // CREATE: add one line at the end of the file
    public void save(Patient p) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE, true))) {
            bw.write(p.toFileLine());
            bw.newLine();
        }
    }

    // READ: load every patient
    public List<Patient> findAll() throws IOException {
        List<Patient> list = new ArrayList<>();
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

    // READ one patient by ID (null if not found)
    public Patient findById(String id) throws IOException {
        for (Patient p : findAll()) {
            if (p.getId().equals(id)) return p;
        }
        return null;
    }

    // DELETE: remove one patient, then rewrite the file
    public void delete(String id) throws IOException {
        List<Patient> all = findAll();
        all.removeIf(p -> p.getId().equals(id));
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
            for (Patient p : all) {
                bw.write(p.toFileLine());
                bw.newLine();
            }
        }
    }

    // Turns one text line back into a Patient.
    // split with -1 keeps empty trailing fields.
    private Patient parse(String line) {
        String[] p = line.split("\\|", -1);
        return new Patient(p[0], p[1], p[2], p[3], p[4]);
    }
}