-- Create forms table

CREATE TABLE forms (
    id INT AUTO_INCREMENT PRIMARY KEY,

    first_name      VARCHAR(255) NOT NULL,
    last_name       VARCHAR(255) NOT NULL,
    gender          VARCHAR(50) NOT NULL,
    immigrant_id    VARCHAR(50) NOT NULL,
    dependent       VARCHAR(255),
    date_of_birth   DATE NOT NULL,
    email           VARCHAR(255) NOT NULL,
    document_requested VARCHAR(255) NOT NULL,

    status VARCHAR(50) DEFAULT 'notSent',

    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
