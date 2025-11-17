package edu.gmu.cs321;
import java.time.LocalDateTime;

public class FormSubmission {
    private int id;
    private int formId;
    private String submittedBy;
    private LocalDateTime submittedDate;

    public FormSubmission() {}

    public FormSubmission(int formId, String submittedBy){
        this.formId = formId;
        this.submittedBy = submittedBy;
    }

    public FormSubmission(int id, int formId, String submittedBy, LocalDateTime submittedDate){
        this.id = id;
        this.formId = formId;
        this.submittedBy = submittedBy;
        this.submittedDate = submittedDate;
    }

    public int getId() {return id;}
    public int getFormId() {return formId;}
    public String getSubmittedby() {return submittedBy;}
    public LocalDateTime getSubmittedDate() {return submittedDate;}

    public void setId(int id) {this.id = id;}
    public void setFormId(int formId) {this.formId = formId;}
    public void setSubmittedBy(String submittedBy) {this.submittedBy = submittedBy;}
    public void setSubmittedDate(LocalDateTime submittedDate) {this.submittedDate = submittedDate;}
    
}
