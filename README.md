# Banking Management System

This Java-based Banking Management System is a multi-tier web application (no Maven) built with Servlets and JSP, running on Apache Tomcat with MySQL as the backend database (`bank_db`). It simulates a realistic banking environment with **three user roles**: Customer, Employee, and Admin. Customers can open accounts and manage their funds, but certain actions (like large transfers or loans) require Employee or Admin approval. Employees have a dashboard to approve transactions and assist customers. Admins oversee the system: they can add/remove employees, and they approve major requests (fund transfers, loans, fixed/recurring deposits) and respond to customer grievances. The application emphasizes realistic banking workflows – for example, when a customer requests a fund transfer or loan, it sits in a pending queue until an Admin reviews and approves it. Each user has a role-based dashboard, ensuring proper data separation and access control.

## Features

* **Customer**

  * **Registration & Login**: Customers can sign up for a new account and securely log in.
  * **View Balance & Transactions**: Customers can see their account balance and transaction history.
  * **Deposits & Withdrawals**: Customers submit deposit or withdrawal requests. These requests go to the Employee for approval before affecting the balance.
  * **Manage Beneficiaries**: Customers can add or remove beneficiaries for fund transfers.
  * **Fund Transfers**: Customers can request money transfers to beneficiaries. Each transfer request requires Admin approval.
  * **Loans & FD/RD**: Customers can apply for loans or create Fixed Deposits (FD) / Recurring Deposits (RD). These applications are reviewed and approved by Admin.
  * **Grievance Submission**: Customers can file grievances (complaints). Admin users can view these grievances and respond.
  * **Password Management**: Customers can change their account password after logging in.

* **Employee**

  * **Login & Dashboard**: Employees log in to access their dashboard.
  * **Approve Transactions**: Employees see pending deposit/withdrawal requests from customers and can approve or reject them, updating customer balances accordingly.
  * **View Customer Info**: Employees can look up customer accounts and view details to assist in resolving issues.

* **Admin**

  * **Login & Dashboard**: Admins log in to a dedicated dashboard.
  * **Manage Employees**: Admins can add new employees, edit employee details, or remove employees.
  * **Approve Transfers & Loans**: Admins review pending fund transfer requests, loan applications, and FD/RD requests from customers and approve or reject them.
  * **Respond to Grievances**: Admins view customer grievances and can post responses.
  * **System Oversight**: Admins have visibility into overall system activity and reports (e.g. list of pending requests).

* **Workflow Highlights**

  * **Multi-step Approvals**: Sensitive actions (transfers, large transactions, loans) require multiple approvals, reflecting real bank processes.
  * **Role-Based Access**: Each user role sees only the data and actions relevant to them (e.g. customers can’t approve their own withdrawals).
  * **Session Management**: Secure session handling ensures users must log in to access any account features.

## Technology Stack

* **Java Servlets & JSP** – Core of the MVC web application; Servlets act as controllers and JSPs serve as views.
* **Apache Tomcat** – Servlet container for hosting the application.
* **MySQL (bank\_db)** – Relational database for storing all data (accounts, transactions, etc.).
* **JDBC with DAO Pattern** – Data Access Objects (e.g., `AccountDAO`, `GrievanceDAO`, etc.) interact with the database through JDBC. The `DBUtil` class handles establishing connections.
* **HTML/CSS/JavaScript** – Front-end for building interactive user interfaces (forms, tables, etc.).

## Project Structure

The project follows a standard Java web project layout (for example, in Eclipse):

* **Source code (`src/`)**

  * **Controllers/Servlets** (`com.bank.controller`): Java Servlet classes that handle HTTP requests (e.g. `LoginServlet`, `RegisterServlet`, `ApproveWithdrawalServlet`, etc.).
  * **Data Utilities**: `DBUtil.java` for managing the database connection (getting a `Connection` object).
  * **DAO Classes** (`com.bank.dao`): Classes like `AccountDAO`, `UserDAO`, `BeneficiaryDAO`, `LoanDAO`, `GrievanceDAO`, etc., each with methods to query/update the database tables.
  * **Model Classes** (`com.bank.model`): Plain Java objects representing entities (e.g. `User`, `Account`, `Beneficiary`, `Loan`, `FDAccount`, `Grievance`), each corresponding to a database table.

* **Web Content** (or `WebApp` folder)

  * **JSP Pages**: e.g. `login.jsp`, `register.jsp`, `customer_home.jsp`, `employee_home.jsp`, `admin_home.jsp`, and other pages for forms (deposit.jsp, withdraw\.jsp, transfer.jsp, etc.). Each JSP corresponds to a view for a specific feature or role.
  * **Static Resources**: CSS and JavaScript files used by JSP pages to style the application and add interactivity.

Overall, the codebase has dozens of Servlets and JSPs to cover all banking features. The design loosely follows MVC: Servlets (controllers) use DAOs (model/data) and forward to JSPs (views). The `DBUtil` class centralizes database configuration, so changes to connection settings are in one place.

## Prerequisites

Before setting up the project, ensure you have the following installed on your system:

* **Java Development Kit (JDK)** – version 8 or later.
* **Apache Tomcat** – version 8.x or 9.x (matching your Servlet API version).
* **MySQL Server** – running and accessible. Install MySQL Community Server if you don’t have it.
* **Eclipse IDE for Enterprise Java Developers** (or another Java IDE that supports dynamic web projects).
* **MySQL Connector/J** – the JDBC driver JAR for MySQL. (Ensure it’s added to the project’s build path or placed in Tomcat’s `lib` folder.)

## Installation & Setup

Follow these steps to run the project locally:

1. **Clone the Repository**
   Download or clone this GitHub repository to your local machine:

   ```bash
   git clone https://github.com/YourUsername/BankingManagementSystem.git
   ```

   (Replace `YourUsername` with the actual GitHub path if needed.)

2. **Create the Database**

   * Open MySQL (via command line or a GUI like MySQL Workbench).
   * Create a new database named `bank_db`:

     ```sql
     CREATE DATABASE IF NOT EXISTS bank_db;
     ```
   * If the project includes a SQL script (e.g. `schema.sql` or `bank_db.sql`), run that script to create all necessary tables and initial data. If no script is provided, you will need to create tables manually based on the model classes (`User`, `Account`, `Transaction`, `Beneficiary`, `Loan`, `FDAccount`, `Grievance`, etc.).
   * Example to create an `employee` table:

     ```sql
     CREATE TABLE employee (
       employee_id INT AUTO_INCREMENT PRIMARY KEY,
       full_name   VARCHAR(100) NOT NULL,
       email       VARCHAR(100) NOT NULL UNIQUE,
       username    VARCHAR(50)  NOT NULL UNIQUE,
       password    VARCHAR(255) NOT NULL,
       role        VARCHAR(20)  NOT NULL,
       created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
     );
     ```

     *Other tables include accounts, transactions, loans, beneficiaries, grievances, etc. Refer to `com.bank.model` for more.*

3. **Configure Database Connection**

   * Open the `DBUtil.java` file (usually under `src/com/bank/util/`). This class contains the JDBC connection URL, username, and password.
   * **Important:** Do not hard-code your actual credentials into source control. Instead:

     * Option A: Use **environment variables**. For example, you could set `BANK_DB_URL`, `BANK_DB_USER`, and `BANK_DB_PASS` in your OS, then modify `DBUtil.java` to read these with `System.getenv(...)`.
     * Option B: Use a separate `db.properties` file (not committed to Git). Store your credentials there and load them in `DBUtil`.
   * For example, an environment-variable approach in `DBUtil.java` might look like:

     ```java
     String url = System.getenv("BANK_DB_URL");
     String user = System.getenv("BANK_DB_USER");
     String pass = System.getenv("BANK_DB_PASS");
     Connection conn = DriverManager.getConnection(url, user, pass);
     ```
   * Or a properties file approach (if `db.properties` exists):

     ```java
     Properties props = new Properties();
     props.load(new FileInputStream("db.properties"));
     String url = props.getProperty("db.url");
     String user = props.getProperty("db.username");
     String pass = props.getProperty("db.password");
     ```
   * Ensure that the JDBC URL points to `jdbc:mysql://localhost:3306/bank_db` (or your host/port). Set the correct MySQL username and password with privileges on `bank_db`.

4. **Import into Eclipse**

   * Open Eclipse and choose *File → Import → Existing Projects into Workspace*. Select the cloned project folder.
   * Make sure the project is configured as a **Dynamic Web Project**. If not, you may need to create a new Dynamic Web Project and copy the source code into it.
   * Add the MySQL Connector/J JAR to the project’s build path (e.g. put it in `WEB-INF/lib`).
   * Add Apache Tomcat as a runtime library: Right-click the project → *Properties* → *Project Facets* or *Targeted Runtimes*, and select your Tomcat server.

5. **Build and Deploy**

   * In Eclipse, right-click the project and choose *Run As → Run on Server*. Select your configured Tomcat server.
   * Eclipse should compile the code, create a WAR, and deploy it to Tomcat.
   * If you're deploying manually, you can also export the project as a WAR file (`Right-click → Export → WAR file`) and drop that WAR into Tomcat’s `webapps/` folder, then start Tomcat.

6. **Run the Application**

   * Start (or restart) Apache Tomcat server.
   * Open a web browser and go to `http://localhost:8080/BankingManagementSystem` (replace `BankingManagementSystem` with the actual context path if different).
   * You should see the login page. You can register a new **Customer** account using the registration form.
   * **Admin/Employee Access**: If there is no seeded Admin user, you may need to create one directly in the database.

## Usage Notes

* After logging in, you will land on your role’s **Dashboard** page. From there, use the navigation menu or links to access features (e.g. deposit, withdraw, transfer).
* When a **Customer** makes a deposit or withdrawal request, it will not immediately reflect in the balance. Instead, an Employee must log in and approve that request. The same goes for transfers, loans, and FD/RD requests (Admins approve these). This simulates a real banking approval flow.
* Use the **Manage Beneficiaries** section to set up accounts you frequently transfer to. This lets you send funds without typing account numbers each time.
* The **Grievance** feature lets customers file complaints or queries. Admins should regularly check and respond to these.

## Configuration & Best Practices

* **Do not hard-code credentials**: Make sure your database credentials are **not** stored in plain text in the source code. Use environment variables or an external configuration file (`db.properties`) and exclude that file from version control (`.gitignore`).
* **JDBC Driver**: Ensure the MySQL JDBC driver (Connector/J) is available to Tomcat. You can place the JAR in `WEB-INF/lib` of the project or in Tomcat’s global `lib` folder.
* **Server Ports**: By default, Tomcat runs on port 8080. If that port is in use, change it in the Tomcat `server.xml` or use another port (update the URL accordingly).
* **Database Backups**: Regularly back up your `bank_db` database, especially if you modify the schema or have test data.
* **Logging & Debugging**: Check the Tomcat console or log files (`catalina.out`) for errors if something doesn’t work as expected. Common issues include misconfigured JDBC URL or missing tables.
* **Secure Password Handling**: In a production setting, passwords should be hashed (e.g. using BCrypt) rather than stored in plaintext. If this project stores plaintext passwords, consider integrating a hashing library for added security.

## Conclusion

This Banking Management System is designed to be a realistic, educational web application. It demonstrates a full cycle of banking operations with multiple user roles and approval workflows. Feel free to explore the code, add new features, or improve the UI/UX. If you have questions or encounter issues, review the code comments and configuration, or reach out in the project’s issue tracker.

