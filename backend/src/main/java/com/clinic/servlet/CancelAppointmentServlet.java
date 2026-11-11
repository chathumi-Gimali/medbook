package com.clinic.servlet;

import com.clinic.model.Appointment;
import com.clinic.repository.AppointmentRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// UPDATE: changes an appointment's status to CANCELLED
@WebServlet("/cancel")
public class CancelAppointmentServlet extends HttpServlet {

    private final AppointmentRepository repo = new AppointmentRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");
        Appointment a = (id == null) ? null : repo.findById(id);

        // Only cancel appointments that still count as active
        if (a != null
                && !a.getStatus().equals("CANCELLED")
                && !a.getStatus().equals("COMPLETED")) {
            repo.updateStatus(id, "CANCELLED");
        }

        // Redirect instead of forward: a refresh won't resend the cancel
        resp.sendRedirect(req.getContextPath() + "/appointments");
    }
}