package main;

import lombok.extern.slf4j.Slf4j;
import main.utils.Utils;

import java.io.*;
import java.util.Properties;

@Slf4j
public class ConfigurationBuilder implements Cloneable {

    private static String PROPERTY_NAME_LANGUAGE = "LANGUAGE";
    private static String PROPERTY_NAME_FILEDIR = "FILEDIR";

    private static String CONFIGPATH = Utils.getInterfaceFolder() + "GazePlay.properties";

    private static String DEFAULT_VALUE_LANGUAGE = "fra";

    private static String getFileDirectoryDefaultValue() {
        return Utils.getInterfaceFolder() + "files" + Utils.FILESEPARATOR;
    }

    private static Properties loadProperties(String propertiesFilePath) throws IOException {
        try (InputStream inputStream = new FileInputStream(propertiesFilePath)) {
            final Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        }
    }

    public static ConfigurationBuilder createFromPropertiesResource() {
        Properties properties;
        try {
            properties = loadProperties(CONFIGPATH);
        } catch (FileNotFoundException e) {
            log.warn("Config file not found : {}", CONFIGPATH);
            properties = null;
        } catch (IOException e) {
            log.error("Failure while loading config file {}", CONFIGPATH, e);
            properties = null;
        }
        ConfigurationBuilder builder = new ConfigurationBuilder();
        if (properties != null) {
            log.info("Properties loaded : {}", properties);
            builder.populateFromProperties(properties);
        }
        return builder;
    }

    protected String language = DEFAULT_VALUE_LANGUAGE;

    // protected String filedir = getFileDirectoryDefaultValue();

    public ConfigurationBuilder() {

    }

    private ConfigurationBuilder copy() {
        /*
         * try { return (ConfigurationBuilder) super.clone(); } catch (CloneNotSupportedException e) { throw new
         * RuntimeException(e); }
         */
        return this;
    }

    public ConfigurationBuilder withLanguage(String value) {
        ConfigurationBuilder copy = copy();
        copy.language = value;
        return copy;
    }

    public void populateFromProperties(Properties prop) {
        String buffer;

        buffer = prop.getProperty(PROPERTY_NAME_LANGUAGE);
        if (buffer != null) {
            language = buffer.toLowerCase();
        }
/*
        buffer = prop.getProperty(PROPERTY_NAME_FILEDIR);
        if (buffer != null) {
            filedir = buffer;
        }

 */

    }

    private Properties toProperties() {
        Properties properties = new Properties() {

            @Override
            public Object setProperty(String key, String value) {
                if (value == null) {
                    return this.remove(key);
                }
                return super.setProperty(key, value);
            }

        };

        properties.setProperty(PROPERTY_NAME_LANGUAGE, this.language);
        // properties.setProperty(PROPERTY_NAME_FILEDIR, this.filedir);

        return properties;
    }

    public Configuration build() {
        Configuration configuration = new Configuration();
        return configuration;
    }

    public void saveConfig() throws IOException {
        Properties properties = toProperties();
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(CONFIGPATH))) {
            String fileComment = "Automatically generated by GazePlay";
            properties.store(fileOutputStream, fileComment);
        }
        log.info("Properties saved : {}", properties);
    }

    public void saveConfigIgnoringExceptions() {
        try {
            saveConfig();
        } catch (IOException e) {
            log.error("Exception while writing configuration to file {}", CONFIGPATH, e);
        }
    }

    @Override
    public ConfigurationBuilder clone() {
        try {
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return (ConfigurationBuilder) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}