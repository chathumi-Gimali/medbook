<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.clinic.model.Appointment" %>
<%
    // The servlet puts the list here; it is never null when reached via /appointments
    List<Appointment> appointments = (List<Appointment>) request.getAttribute("appointments");
    String pageTitle = "Appointments";
    String activePage = "appointments";
%>
<%@ include file="/WEB-INF/includes/header.jspf" %>

  <div class="max-w-4xl mx-auto">
    <h1 class="text-2xl font-bold text-teal-700 mb-6">All Appointments</h1>

    <div class="bg-white rounded-xl shadow overflow-hidden">
      <table class="w-full text-left">
        <thead class="bg-slate-100 text-sm text-slate-600">
          <tr>
            <th class="p-3">ID</th>
            <th class="p-3">Patient</th>
            <th class="p-3">Doctor</th>
            <th class="p-3">Date</th>
            <th class="p-3">Time</th>
            <th class="p-3">Fee</th>
            <th class="p-3">Status</th>
            <th class="p-3">Action</th>
          </tr>
        </thead>
        <tbody>
<%
    if (appointments != null) {
        for (Appointment a : appointments) {
%>
          <tr class="border-t">
            <td class="p-3"><%= a.getId() %></td>
            <td class="p-3"><%= a.getPatientId() %></td>
            <td class="p-3"><%= a.getDoctorId() %></td>
            <td class="p-3"><%= a.getDate() %></td>
            <td class="p-3"><%= a.getTime() %></td>
            <td class="p-3">LKR <%= a.getConsultationFee() %></td>
            <td class="p-3"><%= a.getStatus() %></td>
            <td class="p-3">
<%
                if (!a.getStatus().equals("CANCELLED") && !a.getStatus().equals("COMPLETED")) {
%>
              <form method="post" action="cancel">
                <input type="hidden" name="id" value="<%= a.getId() %>">
                <button type="submit" class="text-red-600 underline">Cancel</button>
              </form>
<%
                } else if (a.getStatus().equals("CANCELLED")) {
%>
              <form method="post" action="delete">
                <input type="hidden" name="id" value="<%= a.getId() %>">
                <button type="submit" class="text-slate-500 underline">Delete</button>
              </form>
<%
                }
%>
            </td>
          </tr>
<%
        }
    }
%>
        </tbody>
      </table>
    </div>

    <a href="book.jsp" class="inline-block mt-6 text-teal-700 underline">Book a new appointment</a>
  </div>

<%@ include file="/WEB-INF/includes/footer.jspf" %>