package src.roles;

import src.FileInfoReader;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class User {


    String id;
    String name;
    String username;
    String password;

    abstract boolean login(String username, String password, FileInfoReader file);

    abstract User getLogin(String username, String password, FileInfoReader file);



}
