package src.roles;

import src.FileInfoReader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represent a user
 */
public abstract class User {

    /**
     * user id
     */
    String id;
    /**
     * user name
     */
    String name;
    /**
     * username
     */
    String username;
    /**
     * password
     */
    String password;

    /**
     * login in method
     * @param username
     * @param password
     * @param file
     * @return true if login in successfully
     */
    abstract boolean login(String username, String password, FileInfoReader file);

    /**
     * get user information after login in
     * @param username
     * @param password
     * @param file
     * @return user information
     */
    abstract User getLogin(String username, String password, FileInfoReader file);

    /**
     * @return user id
     */
    public String getId() {
        return this.id;
    }

    /**
     * @return user name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return this.username;
    }
}
