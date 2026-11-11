package com.clinic.servlet;

import com.clinic.model.Appointment;
import com.clinic.repository.AppointmentRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// DELETE: removes an appointment from the file, but only if it was cancelled
@WebServlet("/delete")
public class DeleteAppointmentServlet extends HttpServlet {

    private final AppointmentRepository repo = new AppointmentRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String id = req.getParameter("id");
        Appointment a = (id == null) ? null : repo.findById(id);

        // Business rule: only cancelled bookings can be deleted
        if (a != null && a.getStatus().equals("CANCELLED")) {
            repo.delete(id);
        }

        resp.sendRedirect(req.getContextPath() + "/appointments");
    }
}