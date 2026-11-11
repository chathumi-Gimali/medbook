package com.clinic.servlet;

import com.clinic.repository.AppointmentRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// READ: loads every appointment and shows them in a table
@WebServlet("/appointments")
public class AppointmentsServlet extends HttpServlet {

    private final AppointmentRepository repo = new AppointmentRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("appointments", repo.findAll());
        req.getRequestDispatcher("/appointments.jsp").forward(req, resp);
    }
}