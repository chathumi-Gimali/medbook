<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Book Appointment</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-slate-50 text-slate-800 p-8">
  <div class="max-w-md mx-auto bg-white rounded-xl shadow p-6">
    <h1 class="text-2xl font-bold text-teal-700 mb-4">Book an Appointment</h1>

    <% if (request.getAttribute("error") != null) { %>
      <p class="bg-red-100 text-red-700 p-3 rounded mb-4"><%= request.getAttribute("error") %></p>
    <% } %>

    <form method="post" action="book" class="space-y-4">
      <div>
        <label class="block text-sm font-medium">Patient ID</label>
        <input name="patientId" required class="w-full border rounded p-2" placeholder="P001">
      </div>
      <div>
        <label class="block text-sm font-medium">Doctor ID</label>
        <input name="doctorId" required class="w-full border rounded p-2" placeholder="D001">
      </div>
      <div>
        <label class="block text-sm font-medium">Date</label>
        <input type="date" name="date" required class="w-full border rounded p-2">
      </div>
      <div>
        <label class="block text-sm font-medium">Time</label>
        <input type="time" name="time" required class="w-full border rounded p-2">
      </div>
      <div>
        <label class="block text-sm font-medium">Consultation Type</label>
        <select name="type" class="w-full border rounded p-2">
          <option value="GENERAL">General Consultation (LKR 1500)</option>
          <option value="SPECIALIST">Specialist (LKR 3500)</option>
        </select>
      </div>
      <button type="submit" class="w-full bg-teal-600 text-white rounded p-2 font-semibold">
        Book Appointment
      </button>
    </form>
  </div>
</body>
</html>