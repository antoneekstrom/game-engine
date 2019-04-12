package core.state;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Can deserialize and serialize {@link State} objects.
 */
public class StateHandler {

    public StateHandler() {}

    /**
     * Save the state to a file.
     * @param state the state to serialize
     * @param file the file to output to
     * @exception IOException
     */
    public void serialize(IState state, File file) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(state);
        out.flush();
        out.close();
    }

    /**
     * Read a state object from a file.
     * @param <S> the type of state
     * @param file the file that contains the serialized state
     * @return the state
     * @exception ClassCastException Attempting to cast the state object to invalid class
     */
    @SuppressWarnings("unchecked")
    public <S extends IState>S deserialize(File file) throws IOException, ClassNotFoundException, ClassCastException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        S state = (S) in.readObject();
        in.close();
        return state;
    }
    
}