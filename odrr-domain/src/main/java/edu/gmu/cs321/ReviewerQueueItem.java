package edu.gmu.cs321;
import java.time.LocalDateTime;;

public class ReviewerQueueItem {
    private int formId;
    private String applicantName;
    private String submittedBy;
    private LocalDateTime submittedDate;
    private String status;

    public int getFormId(){
        return formId;
    }

    public void setFormId(int formId){
        this.formId = formId;
    }

    public String getApplicantName(){
        return applicantName;
    }

    public void setApplicantName(String applicantName){
        this.applicantName = applicantName;
    }

    public String getSubmittedBy(){
        return submittedBy;
    }

    public void setSubmittedBy(String submittedBy){
        this.submittedBy = submittedBy;
    }

    public LocalDateTime getSubmittedDate(){
        return submittedDate;
    }

    public void setSubmittedDate(LocalDateTime submittedDate){
        this.submittedDate = submittedDate;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }
    
}
