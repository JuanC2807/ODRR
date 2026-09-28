# Original Document Return Request (ODRR)

Original Document Return Request (ODRR) is a team software engineering project developed for **CS 321: Software Engineering** at George Mason University.

The application is designed to manage requests for the return of original documents. Users submit document-return requests, which then move through a multi-step workflow involving data entry, review, and approval.

## Project Overview

The goal of ODRR is to provide a structured workflow for processing document-return requests.

The system supports multiple roles:

- **Data Entry** – enters and submits applicant information and requested documents.
- **Reviewer** – reviews submitted requests, validates information, adds notes, and determines whether the request should continue to approval.
- **Approver** – performs the final review and determines whether the document-return request should be approved.

This project was created as part of a semester-long software engineering course and focused on requirements gathering, system design, implementation, testing, and team-based development.

## Features

- Submit original document-return requests
- Validate applicant information
- Maintain reviewer and approval workflow stages
- Review and update submitted requests
- Add reviewer notes
- Store request information in a MySQL database
- Web-based application using Jakarta Servlets
- Unit testing with JUnit 5
- Maven-based build and dependency management
- Checkstyle integration for code-quality checks

## Workflow

```text
Data Entry
    |
    v
Reviewer Queue
    |
    v
Review / Validation
    |
    v
Approval Queue
    |
    v
Final Approval
```

## Technologies Used

- **Java 21**
- **Jakarta Servlets**
- **Maven**
- **MySQL**
- **JUnit 5**
- **WAR packaging**
- **Checkstyle**
- **Git / GitLab / GitHub**

## Project Structure

```text
ODRR/
├── odrr-domain/
│   ├── src/
│   │   ├── main/
│   │   └── test/
│   └── pom.xml
├── .gitignore
└── README.md
```

The main application is contained in the `odrr-domain` Maven module.

## Building the Project

### Requirements

- Java 21
- Maven
- MySQL
- A Jakarta-compatible servlet container

### Build

From the `odrr-domain` directory:

```bash
cd odrr-domain
mvn clean package
```

This compiles the project, runs the configured test lifecycle, and produces a WAR package.

## Testing

Run the unit tests with:

```bash
mvn test
```

The project uses JUnit 5 for automated testing.

## Team Project

ODRR was developed as a team project for CS 321 at George Mason University.

This GitHub repository preserves the original project history and development branches migrated from the team's GMU GitLab repository.

## Contributions

Because this was a team project, the repository contains work from multiple contributors. Individual contributions and development history can be reviewed through the Git commit history and feature branches.

## Academic Context

This repository represents an academic software engineering project and is preserved as part of my software development portfolio.
