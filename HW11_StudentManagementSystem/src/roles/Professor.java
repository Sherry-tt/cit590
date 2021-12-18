package src.roles;

import src.Course;
import src.FileInfoReader;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Represent professor object
 */
public class Professor extends User{
    /**
     * course list
     */
    ArrayList<Course> courses = new ArrayList<>();

    /**
     * constructor
     * @param str professor information
     */
    public Professor(String str) {
        String[] professor = str.split(";");
        this.id = professor[1].trim();
        this.name = professor[0].trim();
        this.username = professor[2].trim();
        this.password = professor[3].trim();

    }

    /**
     * constructor
     */
    public Professor() {

    }

    /**
     * login as professor
     * @param username of professor
     * @param password of professor
     * @param file file
     * @return true if login in successfully
     */
    @Override
    public boolean login(String username, String password, FileInfoReader file) {
        ArrayList<Professor> professors = file.getProfessors();
        for (Professor prof : professors) {
            if (prof.username.equals(username) && prof.password.equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * get login information
     * @param username of professor
     * @param password of professor
     * @param file file
     * @return professor object
     */
    @Override
    public Professor getLogin (String username, String password, FileInfoReader file) {
        ArrayList<Professor> professors = file.getProfessors();
        for (Professor prof : professors) {
            if (prof.username.equals(username) && prof.password.equals(password)) {
                return prof;
            }
        }
        return null;
    }

    /**
     * set the information for courses they teach
     * @param file file
     */
    public void setTeach(FileInfoReader file) {
        ArrayList<Course> courses = file.getCourses();
        this.courses = new ArrayList<>();
        for (Course cor : courses) {
            if (cor.getLecturer().equals(this.name)) {
                this.courses.add(cor);
            }
        }
    }

    /**
     * get the information for courses they teach
     * @param file file
     */
    public void courseTeach(FileInfoReader file) {
        setTeach(file);
        ArrayList<Course> courses = file.getCourses();
        System.out.println("------------The course list------------");
        for (Course cor : this.courses) {
            cor.printCourse();
        }
        System.out.println();
    }


    /**
     * view student list
     * @param courseId course id
     * @param file file
     */
    public void studentInCourse(String courseId, FileInfoReader file) {
        ArrayList<Student> students = file.getStudents();
        ArrayList<Course> courses = file.getCourses();
        String name = null;
        for (Course course : courses) {
            if (course.getId().equals(courseId)) {
                name = course.getName();
                break;
            }
        }
        System.out.println("Students in your course " + " " + courseId + " " + name + ":");
        for (Student stu : students) {
            if (stu.planCourse(courseId)) {
                System.out.println(stu.getId() + " " + stu.getName());
            }
        }
        System.out.println();
    }

    /**
     * get the course list the professor teach
     * @return course list
     */
    public ArrayList<Course> getCourses() {
        return this.courses;
    }

//    /**
//     * get professor id
//     * @return professor id
//     */
//    public String getId() {
//        return this.id;
//    }

//    /**
//     * get the professor name
//     * @return professor name
//     */
//    public String getName() {
//        return this.name;
//    }

//    /**
//     * get professor username
//     * @return professor username
//     */
//    public String getUsername() {return this.username;}


}
