package com.clinic.servlet;

import com.clinic.model.Patient;
import com.clinic.repository.PatientRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// READ: shows all patients, optionally filtered by ?q=
@WebServlet("/patients")
public class PatientsServlet extends HttpServlet {

    private final PatientRepository repo = new PatientRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String q = req.getParameter("q");
        String query = (q == null) ? "" : q.trim().toLowerCase();

        List<Patient> result = new ArrayList<>();
        for (Patient p : repo.findAll()) {
            // Search matches name, email, or phone
            if (query.isEmpty()
                    || p.getName().toLowerCase().contains(query)
                    || p.getEmail().toLowerCase().contains(query)
                    || p.getPhone().contains(query)) {
                result.add(p);
            }
        }

        req.setAttribute("patients", result);
        req.setAttribute("query", query);
        req.getRequestDispatcher("/patients.jsp").forward(req, resp);
    }
}