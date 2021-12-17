package src.roles;

import src.Course;
import src.FileInfoReader;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Professor extends User{
    ArrayList<Course> courses = new ArrayList<>();

    public Professor(String str) {
        String[] professor = str.split(";");
        this.id = professor[1].trim();
        this.name = professor[0].trim();
        this.username = professor[2].trim();
        this.password = professor[3].trim();

    }

    public Professor() {

    }

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

    // set the information for courses they teach
    public void setTeach(FileInfoReader file) {
        ArrayList<Course> courses = file.getCourses();
        this.courses = new ArrayList<>();
        for (Course cor : courses) {
            if (cor.getLecturer().equals(this.name)) {
                this.courses.add(cor);
            }
        }
    }

    //
    // get the information for courses they teach
    public void courseTeach(FileInfoReader file) {
        setTeach(file);
        ArrayList<Course> courses = file.getCourses();
        System.out.println("------------The course list------------");
        for (Course cor : this.courses) {
            cor.printCourse();
        }
        System.out.println();
    }


    // view student list
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

    public ArrayList<Course> getCourses() {

        return this.courses;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getUsername() {return this.username;}


}
