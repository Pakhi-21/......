const API_BASE_URL = "http://localhost:8080/api/admin";

// Load employees when the page loads
document.addEventListener("DOMContentLoaded", fetchEmployees);

async function fetchEmployees() {
    const response = await fetch(`${API_BASE_URL}/employees`);
    const employees = await response.json();

    const tableBody = document.getElementById("employee-table-body");
    tableBody.innerHTML = "";

    employees.forEach(employee => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${employee.id}</td>
            <td>${employee.name}</td>
            <td>${employee.email}</td>
            <td>${employee.department}</td>
            <td>${employee.location}</td>
            <td>${employee.isAdmin ? "Admin" : "Employee"}</td>
            <td>
                <button onclick="editEmployee(${employee.id})">Edit</button>
                <button onclick="deleteEmployee(${employee.id})">Delete</button>
                ${!employee.isAdmin ? `<button onclick="promoteEmployee(${employee.id})">Promote</button>` : ""}
            </td>
        `;
        tableBody.appendChild(row);
    });
}

async function deleteEmployee(id) {
    if (!confirm("Are you sure you want to delete this employee?")) return;

    await fetch(`${API_BASE_URL}/employees/${id}`, { method: "DELETE" });
    fetchEmployees();
}

async function promoteEmployee(id) {
    if (!confirm("Are you sure you want to promote this employee to admin?")) return;

    await fetch(`${API_BASE_URL}/employees/${id}/promote`, { method: "PUT" });
    fetchEmployees();
}

function editEmployee(id) {
    window.location.href = `employee-form.html?id=${id}`;
}

function openEmployeeForm() {
    window.location.href = "employee-form.html";
}

function goBack() {
    window.location.href = "admin-dashboard.html";
}
