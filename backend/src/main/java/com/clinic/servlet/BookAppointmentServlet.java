package com.clinic.servlet;

import com.clinic.model.Appointment;
import com.clinic.model.GeneralAppointment;
import com.clinic.model.SpecialistAppointment;
import com.clinic.repository.AppointmentRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// Controller: receives the form, checks the slot, saves, and forwards to a page.
@WebServlet("/book")
public class BookAppointmentServlet extends HttpServlet {

    private final AppointmentRepository repo = new AppointmentRepository();

    // GET: just show the empty form
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/book.jsp").forward(req, resp);
    }

    // POST: the form submits here
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String patientId = req.getParameter("patientId");
        String doctorId  = req.getParameter("doctorId");
        String date      = req.getParameter("date");
        String time      = req.getParameter("time");
        String type      = req.getParameter("type");

        // 1. Validate: every field must have a value
        if (isBlank(patientId) || isBlank(doctorId) || isBlank(date) || isBlank(time)) {
            showError(req, resp, "Please fill in every field.");
            return;
        }

        // 2. Check availability (abstraction: the rule is hidden inside this method)
        try {
            if (!isSlotFree(doctorId, date, time)) {
                showError(req, resp, "That time slot is already booked for this doctor.");
                return;
            }
        } catch (IOException e) {
            showError(req, resp, "Could not read appointments. Please try again.");
            return;
        }

        // 3. Create the right subclass (polymorphism)
        String id = "A" + System.currentTimeMillis();
        Appointment a = "SPECIALIST".equals(type)
                ? new SpecialistAppointment(id, patientId, doctorId, date, time)
                : new GeneralAppointment(id, patientId, doctorId, date, time);

        // 4. Save through the repository (CREATE)
        repo.save(a);

        // 5. Pass the object to the confirmation page and forward
        req.setAttribute("appointment", a);
        req.getRequestDispatcher("/confirm.jsp").forward(req, resp);
    }

    // A slot is taken if the same doctor, date and time has a non-cancelled appointment
    private boolean isSlotFree(String doctorId, String date, String time) throws IOException {
        for (Appointment a : repo.findAll()) {
            if (a.getDoctorId().equals(doctorId)
                    && a.getDate().equals(date)
                    && a.getTime().equals(time)
                    && !a.getStatus().equals("CANCELLED")) {
                return false;
            }
        }
        return true;
    }

    private void showError(HttpServletRequest req, HttpServletResponse resp, String message)
            throws ServletException, IOException {
        req.setAttribute("error", message);
        req.getRequestDispatcher("/book.jsp").forward(req, resp);
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}