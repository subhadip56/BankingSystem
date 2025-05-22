// Function to safely encode HTML (XSS protection)
function encodeHTML(s) {
    return s.replace(/&/g, '&amp;')
            .replace(/</g, '&lt;')
            .replace(/>/g, '&gt;')
            .replace(/"/g, '&quot;')
            .replace(/'/g, '&#39;');
}

// Render the employee table rows
function populateEmployeeTable(employeesData) {
    const tableBody = document.getElementById('employeeTableBody');
    let html = '';

    if (employeesData.length > 0) {
        employeesData.forEach(e => {
            html += '<tr>' +
                        `<td>${encodeHTML(e.id)}</td>` +
                        `<td>${encodeHTML(e.fullName)}</td>` +
                        `<td>${encodeHTML(e.email)}</td>` +
                        `<td>${encodeHTML(e.username)}</td>` +
                        `<td>${encodeHTML(e.role)}</td>` +
                        `<td>${encodeHTML(e.createdAt)}</td>` +
                        '<td class="actions">' +
                            `<button type="button" class="btn-edit" data-id="${encodeHTML(e.id)}">Edit</button> ` +
                            `<button type="button" class="btn-delete" data-id="${encodeHTML(e.id)}">Delete</button>` +
                        '</td>' +
                    '</tr>';
        });
        tableBody.innerHTML = html;
    } else {
        document.getElementById('noEmployeesMessage').style.display = 'block';
    }
}

// Set up event handlers after DOM is ready
window.addEventListener('DOMContentLoaded', () => {
    // 1. Render table from global `employees` array
    const employeesData = Array.isArray(window.employees) ? window.employees : [];
    populateEmployeeTable(employeesData);

    // 2. Delegate Edit button clicks
    document.body.addEventListener('click', e => {
        if (e.target.matches('.btn-edit')) {
            const id = e.target.getAttribute('data-id');
            window.location.href = `EditEmployeeServlet?id=${id}`;
        }
    });

    // 3. Delegate Delete button clicks
    document.body.addEventListener('click', async e => {
        if (e.target.matches('.btn-delete')) {
            const id = e.target.getAttribute('data-id');
            if (!confirm('Are you sure you want to delete this employee?')) return;

            try {
                const resp = await fetch(`DeleteEmployeeServlet?id=${id}`, { method: 'POST' });
                if (resp.ok) {
                    // Remove locally and re-render
                    const idx = employeesData.findIndex(x => x.id == id);
                    if (idx > -1) {
                        employeesData.splice(idx, 1);
                        populateEmployeeTable(employeesData);
                    }
                } else {
                    alert('Failed to delete employee.');
                }
            } catch (err) {
                console.error('Delete error:', err);
                alert('An error occurred while deleting.');
            }
        }
    });
});
