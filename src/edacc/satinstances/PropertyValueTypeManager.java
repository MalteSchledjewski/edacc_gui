/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edacc.satinstances;

import edacc.model.DatabaseConnector;
import edacc.model.NoConnectionToDBException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 *
 * @author dgall
 */
public class PropertyValueTypeManager {

    private static PropertyValueTypeManager instance;
    private Hashtable<String, PropertyValueType<?>> propertyTypes;

    public static PropertyValueTypeManager getInstance() throws IOException, NoConnectionToDBException, SQLException {
        if (instance == null) {
            instance = new PropertyValueTypeManager();
        }
        return instance;
    }

    public PropertyValueType<?> getPropertyValueTypeByName(String name) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Constructor of the PropertyValueTypeManager
     *
     * @throws IOException
     */
    private PropertyValueTypeManager() throws IOException, NoConnectionToDBException, SQLException {
        this.propertyTypes = loadPropertyValueTypes();
    }

    /**
     * Returns all LBVKeys from the LBVKeyManager.
     *
     * @return 
     */
    public Vector<PropertyValueType<?>> getAll() {
        return new Vector<PropertyValueType<?>>(propertyTypes.values());
    }

    /**
     * Loads all PropertyValueType classes from the db and returns a
     * Hashtable with the name as key and an object of the PropertyValueType as value.
     *
     * @throws IOException
     *             if an error occurs while loading the class files from the db to the filesystem.
     */
    private Hashtable<String, PropertyValueType<?>> loadPropertyValueTypes() throws IOException, NoConnectionToDBException, SQLException {
        final String query = "SELECT * FROM PropertyValueType";
        PreparedStatement ps = DatabaseConnector.getInstance().getConn().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        LinkedList<File> files = new LinkedList<File>();
        LinkedList<Class<PropertyValueType<?>>> propertyValueTypes = new LinkedList<Class<PropertyValueType<?>>>();
        while (rs.next()) {
            File f = new File(rs.getString("name"));
            FileOutputStream out = new FileOutputStream(f);
            InputStream in = rs.getBinaryStream("typeClass");
            int len = 0;
            byte[] buffer = new byte[256 * 1024];
            while ((len = in.read(buffer)) > -1) {
                out.write(buffer, 0, len);
            }
            out.close();
            in.close();
            files.add(f);
        }

        // Create a new URLClassLoader for loading the PropertyValueTypes with the
        // ClassLoader of the PropertyValueTypes.class as parent (which is important because
        // the Classcast won't work with any other ClassLoader)
        ClassLoader cl = new URLClassLoader(fileListToURLArray(files),
                PropertyValueType.class.getClassLoader());
        List<Class<PropertyValueType<?>>> classes = getClassesFromFiles(files, cl);
        // clean up temporary files
        for (File f : files)
            f.delete();
        return createProeprtyValueTypeObjects(classes);
    }

    /**
     * Small utility method to convert a File array to an URL array.
     *
     * @param files
     * @return the new {@link URL} array
     * @throws MalformedURLException
     */
    private URL[] fileListToURLArray(List<File> files)
            throws MalformedURLException {
        URL[] urls = new URL[files.size()];
        int i = 0;
        for (File f : files) {
            urls[i++] = f.toURI().toURL();
        }
        return urls;
    }

    /**
     * Extracts all pluggable PropertyValueType classes from a list of files.
     *
     * @param files
     * @param cl
     * @return List
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    private List<Class<PropertyValueType<?>>> getClassesFromFiles(List<File> files, ClassLoader cl)
            throws IOException {
        List<Class<PropertyValueType<?>>> classes = new ArrayList<Class<PropertyValueType<?>>>();
        for (File f : files) {
            if (f.getName().toLowerCase().endsWith(".class")) {
                try {
                    Class<?> cls = cl.loadClass(f.getName().substring(0,
                            f.getName().length() - 6).replace('/', '.'));
                    if (isPluggableClass(cls)) {
                        classes.add((Class<PropertyValueType<?>>) cls);
                    }
                } catch (ClassNotFoundException e) {
                    System.err.println("Can't load Class " + f.getName());
                    e.printStackTrace();
                }
            }
        }
        return classes;
    }

    /**
     * Checks if a given class is pluggable, which means: has the type PropertyValueType.
     *
     * @param cls
     * @return true if class is pluggable
     */
    private boolean isPluggableClass(Class<?> cls) {
        return cls.getSuperclass().equals(PropertyValueType.class);
    }

    /**
     * This method creates the PropertyValueType objects of all found ProeprtyValueTypes and adds them
     * to the list.
     */
    private Hashtable<String, PropertyValueType<?>> createProeprtyValueTypeObjects(
            List<Class<PropertyValueType<?>>> propertyTypeClasses) {
        Hashtable<String, PropertyValueType<?>> propertyTypes = new Hashtable<String, PropertyValueType<?>>(
                propertyTypeClasses.size());
        for (Class<PropertyValueType<?>> propertyType : propertyTypeClasses) {
            try {
                PropertyValueType<?> key = propertyType.newInstance();
                propertyTypes.put(key.getName(), key);
            } catch (InstantiationException e) {
                System.err.println("Can't instantiate PropertyValueType: "
                        + propertyType.getName());
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                System.err.println("IllegalAccess for PropertyValueType: "
                        + propertyType.getName());
                e.printStackTrace();
            }
        }
        return propertyTypes;
    }
}
