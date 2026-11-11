<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.clinic.model.Appointment" %>
<%
  Appointment a = (Appointment) request.getAttribute("appointment");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Booking Confirmed</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-slate-50 text-slate-800 p-8">
  <div class="max-w-md mx-auto bg-white rounded-xl shadow p-6 text-center">
    <h1 class="text-2xl font-bold text-green-600 mb-4">Booking Confirmed!</h1>
    <table class="w-full text-left">
      <tr><td class="py-1 text-slate-500">Appointment ID</td><td><%= a.getId() %></td></tr>
      <tr><td class="py-1 text-slate-500">Patient</td><td><%= a.getPatientId() %></td></tr>
      <tr><td class="py-1 text-slate-500">Doctor</td><td><%= a.getDoctorId() %></td></tr>
      <tr><td class="py-1 text-slate-500">Date</td><td><%= a.getDate() %></td></tr>
      <tr><td class="py-1 text-slate-500">Time</td><td><%= a.getTime() %></td></tr>
      <tr><td class="py-1 text-slate-500">Status</td><td><%= a.getStatus() %></td></tr>
      <tr><td class="py-1 text-slate-500">Fee</td><td>LKR <%= a.getConsultationFee() %></td></tr>
    </table>
    <a href="book.jsp" class="inline-block mt-6 text-teal-700 underline">Book another</a>
  </div>
</body>
</html>