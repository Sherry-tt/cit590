package src;

import src.roles.Student;

import java.util.ArrayList;
import java.util.Locale;

public class Course {
    /**
     * course id
     */
    String id;
    /**
     * course name
     */
    String courseName;
    /**
     * lecturer name
     */
    String lecturer;
    /**
     * dates of course
     */
    String day;
    /**
     * start time of course
     */
    String start;
    /**
     * end time of date
     */
    String end;
    /**
     * capacity of course
     */
    int capacity;
    /**
     * total number od students of the course
     */
    int total = 0;
    /**
     * List of students of this course
     */
    ArrayList<Student> studentsIn = new ArrayList<>();

    /**
     * days
     */
    char[] dayList;

    /**
     * start time in int type
     */
    int intStart;
    /**
     * end time in int type
     */
    int intEnd;

    /**
     * constructor
     * @param str information of course
     */
    public Course (String str) {
        String[] course = str.split(";");
        this.id = course[0].trim();
        this.courseName = course[1].trim();
        this.lecturer = course[2].trim();
        this.day = course[3].trim();
        this.start = course[4].trim();
        this.end = course[5].trim();

        this.intStart = Integer.valueOf(this.start.replaceAll(":", ""));
        this.intEnd = Integer.valueOf(this.end.replaceAll(":", ""));

        this.dayList = this.day.toCharArray();


        this.capacity = Integer.valueOf(course[6].trim().toLowerCase());
    }

    /**
     * print course information
     */
    public void printCourse() {
        // // CIT590|Programming Languages and Techniques, 16:30-18:00 on MW, with course capacity: 110, students: 0, lecture: Professor Brandon
        System.out.println(this.id + "|" + this.courseName + ", " + this.start + "-" + this.end + " on " + this.day
                + ", " + "with course capacity: " + this.capacity + ", " + "students: " + this.total
                + ", " + "lecturer: Professor " + this.lecturer);
    }

    /**
     * get ID of course
     * @return course id
     */
    public String getId() {
        return this.id;
    }

    /**
     * get capacity of course
     * @return course capacity
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * get total number of students in the course
     * @return total number
     */
    public int getTotal() {
        return this.total;
    }

    /**
     * get days
     * @return days
     */
    public char[] getDayList() {
        return this.dayList;
    }

    /**
     * get start time
     * @return start time
     */
    public int getIntStart() {
        return this.intStart;
    }

    /**
     * get end time
     * @return end time
     */
    public int getIntEnd() {
        return this.intEnd;
    }

    /**
     * get course name
     * @return course name
     */
    public String getName() {
        return this.courseName;
    }

    /**
     * get lecturer name
     * @return lecturer name
     */
    public String getLecturer() {
        return this.lecturer;
    }

    /**
     * set total number
     */
    public void setTotal() {this.total++;}

    /**
     * add student to ths course
     * @param student object
     */
    public void setStudentsIn(Student student) {
        this.studentsIn.add(student);
    }
}
