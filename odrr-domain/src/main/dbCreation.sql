-- Create forms table
CREATE TABLE forms (
    id INT AUTO_INCREMENT PRIMARY KEY,
    applicant_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(50),
    date_of_birth DATE NOT NULL,
    address TEXT,
    status VARCHAR(50) DEFAULT 'notSent',
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create data_entry_table
CREATE TABLE data_entry_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    form_id INT NOT NULL,
    clerk_id VARCHAR(100) NOT NULL,
    submission_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    validation_status VARCHAR(50) DEFAULT 'PENDING',
    FOREIGN KEY (form_id) REFERENCES forms(id) ON DELETE CASCADE
);

-- Create reviewer_table
CREATE TABLE reviewer_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    form_id INT NOT NULL,
    reviewer_id VARCHAR(100) NOT NULL,
    review_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    changes_made TEXT,
    notes TEXT,
    FOREIGN KEY (form_id) REFERENCES forms(id) ON DELETE CASCADE
);

-- Create approver_table
CREATE TABLE approver_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    form_id INT NOT NULL,
    approver_id VARCHAR(100) NOT NULL,
    decision VARCHAR(50) NOT NULL,
    reason TEXT,
    decision_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (form_id) REFERENCES forms(id) ON DELETE CASCADE
);

-- Add indexes for better performance
CREATE INDEX idx_form_status ON forms(status);
CREATE INDEX idx_data_entry_form ON data_entry_table(form_id);
CREATE INDEX idx_reviewer_form ON reviewer_table(form_id);
CREATE INDEX idx_approver_form ON approver_table(form_id);