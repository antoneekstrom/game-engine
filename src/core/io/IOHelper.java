package core.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;

/**
 * FileHelper
 */
public class IOHelper {

    /**
     * Load classes from filesystem.
     * @param files the files
     * @param classes the name of the classes to load
     * @throws ClassNotFoundException if a class is not found
     * @throws IOException something bad happened
     */
    public static void loadClasses(File[] files, String... classes) throws ClassNotFoundException, IOException {

        URL[] urls = Arrays.stream(files).map(File::toURI).map(uri -> {
            try {
                return uri.toURL();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }).filter(uri -> uri != null).toArray(URL[]::new);

        URLClassLoader loader = new URLClassLoader(urls, System.class.getClassLoader());

        for (String c : classes) {
            loader.loadClass(c);
        }

        loader.close();
    }

    /**
     * Read the lines of a file and return a string of the entire file.
     * 
     * @param file the file to read
     * @return the data in the file
     * @throws FileNotFoundException if the filepath is incorrect or the file is not found
     * @throws IOException if something else happens I think
     */
    public static String readLines(File file) throws FileNotFoundException, IOException {

        // Bufferedreader used to read lines
        BufferedReader reader = new BufferedReader(new FileReader(file));

        // Strinbuilder to concatenate the lines more efficiently
        StringBuilder sBuilder = new StringBuilder();
        
        // use streams to append each line to the builder
        reader.lines().forEach(sBuilder::append);

        // get the string from the builder
        String lines = sBuilder.toString();

        // close stream and return
        reader.close();
        return lines;
    }
    
}