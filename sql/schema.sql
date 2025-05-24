
-- 1. Create and switch to the database
CREATE DATABASE IF NOT EXISTS bank_db;
USE bank_db;

-- 2. Core user tables

-- Approved customers
CREATE TABLE customer (
  id               INT AUTO_INCREMENT PRIMARY KEY,
  customer_id      VARCHAR(20)    NOT NULL UNIQUE,
  name             VARCHAR(100)   NOT NULL,
  father_name      VARCHAR(100)   NOT NULL,
  gender           ENUM('Male','Female','Other') NOT NULL,
  dob              DATE           NOT NULL,
  email            VARCHAR(100)   NOT NULL UNIQUE,
  marital_status   ENUM('Single','Married')      NOT NULL,
  address          VARCHAR(255)   NOT NULL,
  city             VARCHAR(50)    NOT NULL,
  pin_code         VARCHAR(10)    NOT NULL,
  state            VARCHAR(50)    NOT NULL,
  religion         VARCHAR(50)    NOT NULL,
  category         ENUM('General','OBC','SC','ST') NOT NULL,
  income           VARCHAR(50)    NOT NULL,
  education        ENUM('Non-Graduate','Graduate','Post-Graduate','Doctorate','Others') NOT NULL,
  occupation       ENUM('Salaried','Self-Employed','Business','Student','Retired','Others') NOT NULL,
  pan              VARCHAR(20)    NOT NULL UNIQUE,
  aadhar           VARCHAR(20)    NOT NULL UNIQUE,
  senior_citizen   BOOLEAN        NOT NULL DEFAULT FALSE,
  existing_account BOOLEAN        NOT NULL DEFAULT FALSE,
  password         VARCHAR(255),
  card_number      VARCHAR(20)    UNIQUE,
  card_pin         CHAR(7),
  created_at       TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Pending customer sign-ups
CREATE TABLE pending_customers (
  pending_id        INT AUTO_INCREMENT PRIMARY KEY,
  reference_number  VARCHAR(20)    NOT NULL UNIQUE,
  name              VARCHAR(100),
  father_name       VARCHAR(100),
  gender            ENUM('Male','Female','Other'),
  dob               DATE,
  email             VARCHAR(100),
  marital_status    ENUM('Single','Married'),
  address           VARCHAR(255),
  city              VARCHAR(50),
  pin_code          VARCHAR(10),
  state             VARCHAR(50),
  religion          VARCHAR(50),
  category          ENUM('General','OBC','SC','ST'),
  income            VARCHAR(50)    NOT NULL,
  education         ENUM('Non-Graduate','Graduate','Post-Graduate','Doctorate','Others'),
  occupation        ENUM('Salaried','Self-Employed','Business','Student','Retired','Others'),
  account_type      ENUM('Savings Account','Current Account','Fixed Deposit Account','Recurring Deposit Account'),
  services          VARCHAR(255),
  created_at        TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Status history for pending workflows
CREATE TABLE status_history (
  history_id       INT AUTO_INCREMENT PRIMARY KEY,
  reference_number VARCHAR(20)   NOT NULL,
  status           ENUM('Pending','Approved','Rejected') NOT NULL DEFAULT 'Pending',
  created_at       TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE(reference_number)
);

-- Staff tables

-- Bank employees
CREATE TABLE employee (
  employee_id INT AUTO_INCREMENT PRIMARY KEY,
  full_name   VARCHAR(100)   NOT NULL,
  email       VARCHAR(100)   NOT NULL UNIQUE,
  username    VARCHAR(50)    NOT NULL UNIQUE,
  password    VARCHAR(255)   NOT NULL,
  role        VARCHAR(20)    NOT NULL,
  created_at  TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Administrators
CREATE TABLE admin (
  admin_id      INT AUTO_INCREMENT PRIMARY KEY,
  username      VARCHAR(50)    NOT NULL UNIQUE,
  password_hash VARCHAR(255)   NOT NULL,
  full_name     VARCHAR(100)   NOT NULL,
  email         VARCHAR(100)   NOT NULL UNIQUE,
  role          ENUM('Admin','Super Admin') NOT NULL,
  created_at    TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Financial tables

-- Customer accounts
CREATE TABLE account (
  account_id     INT AUTO_INCREMENT PRIMARY KEY,
  customer_id    VARCHAR(20)    NOT NULL,
  account_number VARCHAR(20)    NOT NULL UNIQUE,
  account_type   VARCHAR(30)    NOT NULL,
  balance        DECIMAL(15,2)  NOT NULL DEFAULT 0.00,
  created_at     TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

-- Transactions (mini-statements)
CREATE TABLE transaction_history (
  transaction_id   INT AUTO_INCREMENT PRIMARY KEY,
  account_number   VARCHAR(20)   NOT NULL,
  type             ENUM('Credit','Debit') NOT NULL,
  amount           DECIMAL(15,2) NOT NULL,
  description      VARCHAR(255),
  transaction_date TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (account_number) REFERENCES account(account_number)
);

-- Deposit requests
CREATE TABLE deposit_request (
  request_id     INT AUTO_INCREMENT PRIMARY KEY,
  customer_id    VARCHAR(20)    NOT NULL,
  account_number VARCHAR(20)    NOT NULL,
  amount         DECIMAL(15,2)  NOT NULL,
  status         ENUM('Pending','Approved','Rejected') NOT NULL DEFAULT 'Pending',
  created_at     TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (customer_id)    REFERENCES customer(customer_id),
  FOREIGN KEY (account_number) REFERENCES account(account_number)
);

-- Withdrawal requests
CREATE TABLE withdrawal_request (
  request_id     INT AUTO_INCREMENT PRIMARY KEY,
  customer_id    VARCHAR(20)    NOT NULL,
  account_number VARCHAR(20)    NOT NULL,
  amount         DECIMAL(15,2)  NOT NULL,
  status         ENUM('Pending','Approved','Rejected') NOT NULL DEFAULT 'Pending',
  created_at     TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (customer_id)    REFERENCES customer(customer_id),
  FOREIGN KEY (account_number) REFERENCES account(account_number)
);

-- Fixed Deposit / Recurring Deposit requests
CREATE TABLE fd_rd_request (
  request_id    INT AUTO_INCREMENT PRIMARY KEY,
  customer_id   VARCHAR(20)    NOT NULL,
  deposit_type  ENUM('FD','RD') NOT NULL,
  amount        DECIMAL(15,2)  NOT NULL,
  tenure_months INT            NOT NULL,
  interest_rate DECIMAL(5,2)   NOT NULL,
  status        ENUM('Pending','Approved','Rejected') NOT NULL DEFAULT 'Pending',
  created_at    TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

-- Loan applications
CREATE TABLE loan_application (
  application_id INT AUTO_INCREMENT PRIMARY KEY,
  customer_id    VARCHAR(20)    NOT NULL,
  loan_type      VARCHAR(20)    NOT NULL,
  amount         DECIMAL(15,2)  NOT NULL,
  tenure_months  INT            NOT NULL,
  status         ENUM('Pending','Approved','Rejected') NOT NULL DEFAULT 'Pending',
  decision_date  DATE,
  created_at     TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

-- Support tables

-- Grievances / Complaints
CREATE TABLE grievance (
  grievance_id INT AUTO_INCREMENT PRIMARY KEY,
  customer_id  VARCHAR(20)    NOT NULL,
  subject      VARCHAR(255)   NOT NULL,
  description  TEXT           NOT NULL,
  status       ENUM('Pending','In Progress','Resolved','Rejected') NOT NULL DEFAULT 'Pending',
  created_at   TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at   TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

-- Beneficiaries for transfers
CREATE TABLE beneficiary (
  beneficiary_id          INT AUTO_INCREMENT PRIMARY KEY,
  customer_id             VARCHAR(20)    NOT NULL,
  beneficiary_customer_id VARCHAR(20)    NOT NULL,
  created_at              TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (customer_id)             REFERENCES customer(customer_id),
  FOREIGN KEY (beneficiary_customer_id) REFERENCES customer(customer_id),
  UNIQUE KEY unique_pair (customer_id, beneficiary_customer_id)
);

-- Pending fund transfers (approval workflow)
CREATE TABLE pending_fund_transfers (
  request_id              INT AUTO_INCREMENT PRIMARY KEY,
  sender_customer_id      VARCHAR(20)     NOT NULL,
  sender_account_number   VARCHAR(20)     NOT NULL,
  beneficiary_customer_id VARCHAR(20)     NOT NULL,
  amount                  DECIMAL(15,2)   NOT NULL,
  description             VARCHAR(255),
  status                  ENUM('Pending','Approved','Rejected') NOT NULL DEFAULT 'Pending',
  created_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  decision_date           TIMESTAMP,
  admin_comments          VARCHAR(255),
  FOREIGN KEY (sender_customer_id)     REFERENCES customer(customer_id),
  FOREIGN KEY (sender_account_number)  REFERENCES account(account_number),
  FOREIGN KEY (beneficiary_customer_id) REFERENCES customer(customer_id)
);

