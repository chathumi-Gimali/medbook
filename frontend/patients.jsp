<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.clinic.model.Patient" %>
<%
    List<Patient> patients = (List<Patient>) request.getAttribute("patients");
    String query = (String) request.getAttribute("query");
    if (query == null) query = "";
    String error = request.getParameter("error");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Patients</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-slate-50 text-slate-800 p-8">
  <div class="max-w-4xl mx-auto">
    <h1 class="text-2xl font-bold text-teal-700 mb-6">Patients</h1>

    <% if ("missing".equals(error)) { %>
      <p class="bg-red-100 text-red-700 p-3 rounded mb-4">Please fill in every field.</p>
    <% } %>

    <!-- Search -->
    <form method="get" action="patients" class="flex gap-2 mb-6">
      <input name="q" value="<%= query %>" placeholder="Search by name, email or phone"
             class="flex-1 border rounded p-2">
      <button type="submit" class="bg-teal-600 text-white rounded px-4">Search</button>
    </form>

    <!-- List -->
    <div class="bg-white rounded-xl shadow overflow-hidden mb-8">
      <table class="w-full text-left">
        <thead class="bg-slate-100 text-sm text-slate-600">
          <tr>
            <th class="p-3">ID</th>
            <th class="p-3">Name</th>
            <th class="p-3">Email</th>
            <th class="p-3">Phone</th>
          </tr>
        </thead>
        <tbody>
<%
    if (patients != null) {
        for (Patient p : patients) {
%>
          <tr class="border-t">
            <td class="p-3"><%= p.getId() %></td>
            <td class="p-3"><%= p.getName() %></td>
            <td class="p-3"><%= p.getEmail() %></td>
            <td class="p-3"><%= p.getPhone() %></td>
          </tr>
<%
        }
    }
%>
        </tbody>
      </table>
    </div>

    <!-- Register -->
    <div class="bg-white rounded-xl shadow p-6 max-w-md">
      <h2 class="text-lg font-semibold mb-4">Register a new patient</h2>
      <form method="post" action="register-patient" class="space-y-3">
        <input name="name" placeholder="Full name" required class="w-full border rounded p-2">
        <input name="email" type="email" placeholder="Email" required class="w-full border rounded p-2">
        <input name="password" type="password" placeholder="Password" required class="w-full border rounded p-2">
        <input name="phone" placeholder="Phone number" required class="w-full border rounded p-2">
        <button type="submit" class="w-full bg-teal-600 text-white rounded p-2 font-semibold">Register</button>
      </form>
    </div>

    <a href="appointments" class="inline-block mt-6 text-teal-700 underline">View appointments</a>
  </div>
</body>
</html>