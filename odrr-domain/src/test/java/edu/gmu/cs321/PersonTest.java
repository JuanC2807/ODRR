package edu.gmu.cs321;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Test;  // Changed from org.junit.Test
import static org.junit.jupiter.api.Assertions.*;


/**
 * Using TDD aproach to test create, update and get for our Person
 */
public class PersonTest {
    
    /**
     * 
     * @param y used for year
     * @param m used for month
     * @param d used for day
     * @return date object (when fully implemented)
     */
    private Date yyyymmDate(int y, int m, int d){
        return new Date();
    }

    
    /**
     * This test is used to check if when given valid inputs when creating a person its sucessful.
     */
    @Test
    void createPerson_validInputs(){
        Person p = new Person();
        String id = p.createPerson("Juan", "Solis", yyyymmDate(2003, 9, 28), "male");
        assertNotNull(id, "ID should not be null for valid inputs");
        assertFalse(id.isEmpty(),"Filled all inputs should not result in an empty ID");
    }

    /**
     * Testing that if we create a person with invalid inputs returns null or an empty ID.
     */
    @Test
    void createPerson_invalidInput(){
        Person p = new Person();
        String id = p.createPerson("", "", null, "");
        assertTrue(id == null || id.isEmpty(), "Invalid inputs should not make an ID");
    }


    /**
     * This test checks if when updating a Person with valid inputs returns true.
     */
    @Test
    void updatePerson_validInput_returnsTrue(){
        Person p = new Person();
        boolean updatePerson = p.updatePerson("0001", "Juan Carlos", "Solis", yyyymmDate(2003, 10, 28), "Male");
        assertTrue(updatePerson, "valid updatePerson should return true");
    }

    /**
     * This test checks if when updating a person with invalid inputs it returns false.
     */
    @Test
    void updatePerson_invalidInput_returnsFalse(){
        Person p = new Person();
        boolean updatePerson = p.updatePerson(null, null, null, null, null);
        assertFalse(updatePerson, "Invalid update should return false");
    }

    /**
     * Tests the get person with a valid ID returns a non-empty Optional.
     */
    @Test
    void getPerson_validId_returnPresent(){
        Person p = new Person();
        Optional<Person> result = p.getPerson("0001");
        assertTrue(result.isPresent(), "Valid ID should return a record");
    }

    /**
     * Tests that get person with an invalid or empty ID returns an empty Optional.
     */
    @Test
    void getPerson_invalidId_returnsEmpty(){
        Person p = new Person();
        Optional<Person> result = p.getPerson("");
        assertTrue(result.isEmpty(), "Invalid ID should return empty");
    }



}
