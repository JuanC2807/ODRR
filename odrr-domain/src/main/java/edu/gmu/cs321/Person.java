package edu.gmu.cs321;
//import java.lang.foreign.Linker.Option;
import java.util.Date;
import java.util.Optional;

public class Person {
    private String firstName;
    private String lastName;
    private Date dobDate;
    private String gender;

    //firstName get and set
    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String name){
        this.firstName = name;
    }

    //lastName get and set
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String name){
        this.lastName = name;
    }

    //dob get and set
    public Date getDOB(){
        return dobDate;
    }
    public void setDOB(Date d){
        this.dobDate = d;
    }

    //gender get and set
    public String getGender(){
        return gender;
    }
    public void setGender(String g){
        this.gender = g;
    }

    public String createPerson(String firstName, String lastName, Date dobDate, String gender){
        return "";
    }

    public boolean updatePerson(String personId, String newFirstName, String newLastName, Date newDobDate, String newGender){
        return false;
    }

    public Optional<Person> getPerson(String personId){
        return Optional.empty();
    }


    /*
    public boolean getFirstName(){
        return true;
    }
    public boolean getLastName(){
        return true;
    }
    public boolean getDOB(){
        return true;
    }
    */
}

