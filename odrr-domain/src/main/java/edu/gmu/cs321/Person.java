package edu.gmu.cs321;
import java.util.Date;
import java.util.Optional;

public class Person {
    /**
     * Persons first name.
     */
    private String firstName;
    /**
     * Persons last name.
     */
    private String lastName;
    /**
     * Persons date of birth.
     */
    private Date dobDate;
    /**
     * Persons gender.
     */
    private String gender;

    //firstName get and set
    /**
     * Gets the persons first name.
     * @return first name.
     */
    public String getFirstName(){
        return firstName;
    }

    /**
     * Sets Persons first name.
     * @param name name passed into set.
     */
    public void setFirstName(String name){
        this.firstName = name;
    }

    //lastName get and set
    /**
     * Gets persons last name. 
     * @return last name.
     */
    public String getLastName(){
        return lastName;
    }
    /**
     * Sets persons last name.
     * @param name passed in last name.
     */
    public void setLastName(String name){
        this.lastName = name;
    }

    //dob get and set
    /**
     * Gets the persons date of birth.
     * @return Persons dob.
     */
    public Date getDOB(){
        return dobDate;
    }

    /**
     * Sets persons dob.
     * @param d date passed in.
     */
    public void setDOB(Date d){
        this.dobDate = d;
    }

    //gender get and set
    /**
     * Get person gender.
     * @return person gender.
     */
    public String getGender(){
        return gender;
    }
    /**
     * Sets persons gender.
     * @param g passed in person gender.
     */
    public void setGender(String g){
        this.gender = g;
    }

    /**
     * Creates a new person with data provided.
     * @param firstName persons first name.
     * @param lastName persons last name.
     * @param dobDate persons date of birth.
     * @param gender persons gender.
     * @return a person ID(a empty string for this iteration).
     */
    public String createPerson(String firstName, String lastName, Date dobDate, String gender){
        return "";
    }

    /**
     * Updates a person.
     * @param personId id or person to be updated.
     * @param newFirstName first name of person to be updated.
     * @param newLastName last name of person to be updated.
     * @param newDobDate dob of person to be updated.
     * @param newGender gender of person to be updated.
     * @return true if was able to update (for this iteration returns false).
     */
    public boolean updatePerson(String personId, String newFirstName, String newLastName, Date newDobDate, String newGender){
        return false;
    }

    /**
     * Get a person by their ID
     * @param personId the id of the person to be found.
     * @return a optional holding the person if found or an empty optional if not.
     */
    public Optional<Person> getPerson(String personId){
        return Optional.empty();
    }


    
}

