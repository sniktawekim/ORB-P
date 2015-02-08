/*
 * SkirmishSetupMessage - Handles players logging into the lobby of a server
 * 
 *
 * ARRAY VALUES
 * index[0] = Person
 * 1 - 1 (Person to Person Position)
 * index[1] = Person position
 * 
 */
package orb.p.network.messages;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Blainezor
 */
public class SkirmishSetupMessage extends Message {

    private String[] persons;
    private int[] personsPositions;
    private static int VARS = 2;
    
    private SkirmishSetupMessage() {
    }

    public SkirmishSetupMessage(String[] persons, int[] personsPositions) {
        this.persons = persons;
        this.personsPositions = personsPositions;
    }

    /**
     * @param message
     * @return
     */
    public static SkirmishSetupMessage fromString(String message) {
        SkirmishSetupMessage newMessage = new SkirmishSetupMessage();
        String[] values = message.split(DELIMITER);

        if (values.length >= VARS) {
            try {
                newMessage.setPersons((String[]) objFromString(values[0]));
                newMessage.setPersonsPositions((int[]) objFromString(values[1]));
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(SkirmishSetupMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return newMessage;
    }

    @Override
    /**
     * Generates a String to represent the data stored in this class
     */
    public String toString() {
        String returnString = "";

        try {
            returnString += objToString(getPersons()) + DELIMITER + objToString(getPersonsPositions());
        } catch (IOException ex) {
            Logger.getLogger(SkirmishSetupMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnString;
    }

    /**
     * @return the persons
     */
    public String[] getPersons() {
        return persons;
    }

    /**
     * @param persons the persons to set
     */
    public void setPersons(String[] persons) {
        this.persons = persons;
    }

    /**
     * @return the personsPositions
     */
    public int[] getPersonsPositions() {
        return personsPositions;
    }

    /**
     * @param personsPositions the personsPositions to set
     */
    public void setPersonsPositions(int[] personsPositions) {
        this.personsPositions = personsPositions;
    }
}
