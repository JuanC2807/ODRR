package edu.gmu.cs321;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Test;  // Changed from org.junit.Test
import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    //
    private Date yyyymmDate(int y, int m, int d){
        return new Date();
    }

    //Tests
    @Test
    void createPerson_validInputs(){
        Person p = new Person();
        String id = p.createPerson("Juan", "Solis", yyyymmDate(2003, 9, 28), "male");
        assertNotNull(id);
        assertFalse(id.isEmpty(),"Filled all inputs should not result in an empty ID");
    }

    @Test
    void createPerson_invalidInput(){
        Person p = new Person();
        String id = p.createPerson("", "", null, "");
        assertTrue(id == null || id.isEmpty(), "Invalid inputs should not make an ID");
    }


    @Test
    void updatePerson_validInput_returnsTrue(){
        Person p = new Person();
        boolean updatePerson = p.updatePerson("0001", "Juan Carlos", "Solis", yyyymmDate(2003, 10, 28), "Male");
        assertTrue(updatePerson, "valid updatePerson should return true");
    }

    @Test
    void updatePerson_invalidInput_returnsFalse(){
        Person p = new Person();
        boolean updatePerson = p.updatePerson(null, null, null, null, null);
        assertFalse(updatePerson, "Invalid update should return false");
    }

    @Test
    void getPerson_validId_returnPresent(){
        Person p = new Person();
        Optional<Person> result = p.getPerson("0001");
        assertTrue(result.isPresent(), "Valid ID should return a record");
    }

    @Test
    void getPerson_invalidId_returnsEmpty(){
        Person p = new Person();
        Optional<Person> result = p.getPerson("");
        assertTrue(result.isEmpty(), "Invalid ID should return empty");
    }



}
