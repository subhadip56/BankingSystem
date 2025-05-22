package com.bank.servlet;

import com.bank.controller.DBUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ManageEmployeesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**  
     * A JavaBean representing one employee.  
     * JSTL EL will now find getId(), getFullName(), etc.
     */
    public static class Employee {
        private int id;
        private String fullName;
        private String email;
        private String username;
        private String role;
        private Timestamp createdAt;

        public Employee() { }

        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }

        public String getFullName() {
            return fullName;
        }
        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }

        public String getUsername() {
            return username;
        }
        public void setUsername(String username) {
            this.username = username;
        }

        public String getRole() {
            return role;
        }
        public void setRole(String role) {
            this.role = role;
        }

        public Timestamp getCreatedAt() {
            return createdAt;
        }
        public void setCreatedAt(Timestamp createdAt) {
            this.createdAt = createdAt;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Employee> list = new ArrayList<>();
        String sql = "SELECT employee_id, full_name, email, username, role, created_at FROM employee";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("employee_id"));
                e.setFullName(rs.getString("full_name"));
                e.setEmail(rs.getString("email"));
                e.setUsername(rs.getString("username"));
                e.setRole(rs.getString("role"));
                e.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(e);
            }

        } catch (SQLException ex) {
            throw new ServletException(ex);
        }

        req.setAttribute("employees", list);
        req.getRequestDispatcher("JSP/manage_employees.jsp")
           .forward(req, resp);
    }
}
