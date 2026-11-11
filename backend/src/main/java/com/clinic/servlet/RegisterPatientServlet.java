package com.clinic.servlet;

import com.clinic.model.Patient;
import com.clinic.repository.PatientRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// CREATE: registers a new patient, then redirects back to the list
@WebServlet("/register-patient")
public class RegisterPatientServlet extends HttpServlet {

    private final PatientRepository repo = new PatientRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String name     = req.getParameter("name");
        String email    = req.getParameter("email");
        String password = req.getParameter("password");
        String phone    = req.getParameter("phone");

        // Validation: every field is required
        if (isBlank(name) || isBlank(email) || isBlank(password) || isBlank(phone)) {
            resp.sendRedirect(req.getContextPath() + "/patients?error=missing");
            return;
        }

        String id = "P" + System.currentTimeMillis();
        repo.save(new Patient(id, name.trim(), email.trim(), password, phone.trim()));

        // Redirect so a refresh doesn't register the patient twice
        resp.sendRedirect(req.getContextPath() + "/patients");
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}