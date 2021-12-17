package src.roles;

import src.Course;
import src.FileInfoReader;

import java.util.*;

public class Student extends User{

    //String[] courses;
    HashMap<String, String> courseGrades = new HashMap<>();
    List<Course> schedule = new LinkedList<>();

    /**
     * constructor
     */
    public Student() {

    }

    /**
     * constructor
     * @param str of student information
     */
    public Student(String str) {
        String[] student = str.split(";");
        this.id = student[0].trim();
        this.name = student[1].trim();
        this.username = student[2].trim();
        this.password = student[3].trim();
        String course = student[4].trim();
        String[] courses = course.split(",");
        for (String cour : courses) {
            String[] temp = cour.split(":");
            this.courseGrades.put(temp[0].trim(), temp[1].trim());
        }
    }

    /**
     * for students to login in
     * @param username of student
     * @param password of student
     * @param file file
     * @return true if username and password are both right
     */
    @Override
    public boolean login(String username, String password, FileInfoReader file) {
        ArrayList<Student> students = file.getStudents();
        for (Student student : students) {
            if (student.username.equals(username) && student.password.equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * get the student
     * @param username of student
     * @param password of student
     * @param file file
     * @return the student
     */

    @Override
    public Student getLogin (String username, String password, FileInfoReader file) {
        ArrayList<Student> students = file.getStudents();
        for (Student student : students) {
            if (student.username.equals(username) && student.password.equals(password)) {
                return student;
            }
        }
        return null;
    }

    /**
     * view courses --1
     */
    public void viewAllCourse(FileInfoReader file) {
        ArrayList<Course> courses = file.getCourses();

        for (Course cour : courses) {
            cour.printCourse();
        }
    }


    /**
     * add courses to list --2
     * @param courseId is course's ID
     */
    public void addCourse(String courseId, FileInfoReader file) {
        // check whether in schedule

        if(courseValid(courseId, file)) {
            System.out.println("Course added successfully");
            System.out.println();
        }
    }

    /**
     * check if the course is valid
     * @param courseId od course
     * @param file file
     * @return true if valid
     */
    private boolean courseValid(String courseId, FileInfoReader file) {
        // check if in schedule
        if (courseInSchedule(courseId)) {
            System.out.println("The course you selected is already in your list");
            return false;
        }
        // check if in system
        Course course = courseInSystem(courseId, file);
        if (course == null) {
            System.out.println("The course you selected is not in system");
            return false;
        }
        // check capacity
        if (course.getCapacity() == course.getTotal()) return false;

        // check time conflict
        if (courseConflict(courseId, file, course)) {
            return false;
        }
        this.schedule.add(course);
        course.setTotal();
        course.setStudentsIn(this);
        return true;
    }

    /**
     * check whether course is in schedule
     * @param courseId of course
     * @return true of in schedule
     */
    private boolean courseInSchedule(String courseId) {
        for(Course cour : this.schedule) {
            if (cour.getId().equals(courseId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check whether the course is in system
     * @param courseId of course
     * @param file file
     * @return course if it in system; else, return null
     */
    private Course courseInSystem(String courseId, FileInfoReader file) {
        ArrayList<Course> courses = file.getCourses();
        for (Course course : courses) {
            if (course.getId().equals(courseId)) return course;
        }
        return null;
    }

    // check time conflict
    private boolean courseConflict(String courseId, FileInfoReader file, Course course) {
        for (Course sch : schedule) {
            if (oneConflict(sch, course)) {
                System.out.println("The course you selected has time conflict with " + sch.getId() + " " + sch.getName() );
                return true;
            }
        }
        return false;
    }

    // check if conflict with one course
    private boolean oneConflict(Course course1, Course course2) {
        char[] days1 = course1.getDayList();
        char[] days2 = course2.getDayList();
        int start1 = course1.getIntStart();
        int end1 = course1.getIntEnd();
        int start2 = course2.getIntStart();
        int end2 = course2.getIntEnd();

        for(char d1 : days1) {
            for(char d2 : days2) {
                if (d1 == d2 && (! (start2 >= end1 || end2 <= start1))) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * drop courses --4
     * @param courseId is course's name
     */
    public void dropCourse(String courseId) {

        if(!courseInSchedule(courseId)) {
            System.out.println("The course isn't in your schedule");
            System.out.println();
        }

        for(Course cour : this.schedule) {
            if (cour.getId().equals(courseId)) {
                this.schedule.remove(cour);
                System.out.println("Course dropped successfully");
                System.out.println();
                break;
            }
        }
    }

    /**
     * 3 - check course schedule
     */
    public void checkCourseSchedule() {
        for (Course cour : this.schedule) {
            cour.printCourse();
        }


    }

    /**
     * view grades --5
     */
    public void viewGrades(FileInfoReader file) {
        HashMap<String, String> courseGrades = this.courseGrades;

        for (String key : courseGrades.keySet()) {
            String tempGrade = courseGrades.get(key);
            ArrayList<Course> courses = file.getCourses();
            String name = "";
            for (Course co : courses) {
                if (co.getId().equals(key)) {
                    name = co.getName();
                    break;
                }
            }

            System.out.println("Grade of " + key + " " + name + ": " + tempGrade);
        }

    }

    public List<Course> getSchedule() {
        return this.schedule;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }


    public boolean planCourse(String courseId) {
        for (Course co : this.schedule) {
            if (co.getId().equals(courseId)) return true;
        }
        return false;
    }

    public String getUsername() {
        return this.username;
    }

}
