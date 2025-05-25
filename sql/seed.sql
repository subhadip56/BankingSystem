-- Insert some demo data for admin
-- Insert default admin users
INSERT INTO admin (username, password_hash, full_name, email, role) VALUES
('arvind.kumar', 'admin@123', 'Arvind Kumar', 'arvind.kumar@securebank.in', 'Admin'),
('priya.sharma', 'super@456', 'Priya Sharma', 'priya.sharma@securebank.in', 'Super Admin');

-- Insert a sample employee
INSERT INTO employee (username, password, full_name, email, role) VALUES
('rahul.kumar', 'emp1@123', 'Rahul Kumar', 'rahul.kumar@securebank.in', 'Clerk');
