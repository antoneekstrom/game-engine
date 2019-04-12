package core.io.config;

import java.io.File;
import java.io.IOException;

import com.google.gson.Gson;

import core.io.IOHelper;

/**
 * Configurations-file-handler Use {@link Config#loadConfig(File)} to create a {@code Config} instance.
 * 
 * <p>The {@link Config} instance houses an object that contains the properties of the config file.
 * The properties object extends {@link IConfigProperties}. An implementation of the properties interface should have the properties of the json file and might also have other methods and properties to aid the usage of the configuration file.
 */
public class Config <P extends IConfigProperties> {

    /**
     * Load a configuration file.
     * 
     * @param file the config-file to load
     * @param propertiesClass the class of the properties object
     * @return the config instance
     */
    public static <P extends IConfigProperties> Config<P> loadConfig(File file, Class<P> propertiesClass) throws IOException {

        Config<P> config = new Config<P>(file, propertiesClass);

        return config;
    }


    private final File file;
    private final Class<P> propertiesClass;
    private P properties;

    protected Config(File file, Class<P> propertiesClass) throws IOException {
        this.file = file;
        this.propertiesClass = propertiesClass;

        loadProperties();
    }

    protected void loadProperties() throws IOException {
        Gson g = new Gson();

        String lines = IOHelper.readLines(getFile());

        properties = g.fromJson(lines, propertiesClass);
    }

    /**
     * Create a file from a path relative to this config files path.
     * @param path the path
     */
    public File getRelativeTo(String path) {
        return new File(getFile().getParent(), path);
    }

    /**
     * @return the propertiesClass
     */
    public Class<P> getPropertiesClass() {
        return propertiesClass;
    }

    /**
     * @return the file
     */
    public File getFile() {
        return file;
    }

    /**
     * 
     * @return the properties
     */
    public P getProperties() {
        return properties;
    }

}