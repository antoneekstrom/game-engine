package core;

import java.io.File;

/**
 * ObjectPair
 */
public class FileObjectPair<O> {

    /**
     * 
     */
    O obj;

    /**
     * 
     */
    File file;

    /**
     * @param a
     * @param b
     */
    public FileObjectPair(File file, O obj) {
        super();

        this.file = file;
        this.obj = obj;
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * @return the obj
     */
    public O getObject() {
        return obj;
    }
    
}