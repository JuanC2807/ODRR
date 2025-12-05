package edu.gmu.cs321;

import java.io.Serializable;
public class ImmigrantData implements Serializable {
    private String firstName;
    private String lastName;
    private String gender;
    private String immigrantID;
    private String dependent;
    private String dateOfBirth;
    private String email;
    private String documentRequested;
    private String notesFromReviewer = "";  // Default value is empty string

    //Empty Constructor
    public ImmigrantData(){}
    // Constructor
    public ImmigrantData(String firstName, String lastName, String gender, String immigrantID,
                         String dependent, String dateOfBirth, String email, String documentRequested) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.immigrantID = immigrantID;
        this.dependent = dependent;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.documentRequested = documentRequested;
        // notesFromReviewer is already initialized to "" by default
    }

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getGender() { return gender; }
    public String getImmigrantID() { return immigrantID; }
    public String getDependent() { return dependent; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getEmail() { return email; }
    public String getDocumentRequested() { return documentRequested; }
    public String getNotesFromReviewer() { return notesFromReviewer; }  // new getter

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setGender(String gender) { this.gender = gender; }
    public void setImmigrantID(String immigrantID) { this.immigrantID = immigrantID; }
    public void setDependent(String dependent) { this.dependent = dependent; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setEmail(String email) { this.email = email; }
    public void setDocumentRequested(String documentRequested) { this.documentRequested = documentRequested; }
    public void setNotesFromReviewer(String notesFromReviewer) { this.notesFromReviewer = notesFromReviewer; }  // new setter
}
