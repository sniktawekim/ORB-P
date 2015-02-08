/*
 * Message - Basic layout of a message. A message is essentially a collection
 * of information relating to a specific operation. 
 */
package orb.p.network.messages;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author blainezor
 */
public abstract class Message {

    //This is how parts of a message are split
    public static final String DELIMITER = "%%%_0_%%";

    /**
     * Read the object from Base64 string.
     */
    public static Object objFromString(String s) throws IOException,
            ClassNotFoundException {
        byte[] data = Base64Coder.decode(s);
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(data));
        Object o = ois.readObject();
        ois.close();
        return o;
    }

    /**
     * Write the object to a Base64 string.
     */
    public static String objToString(Serializable o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(o);
        oos.close();
        return new String(Base64Coder.encode(baos.toByteArray()));
    }
}
