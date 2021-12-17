package src;

import src.roles.Student;

import java.util.ArrayList;
import java.util.Locale;

public class Course {
    String id;
    String courseName;
    String lecturer;
    String day;
    String start;
    String end;
    int capacity;
    int total = 0;
    ArrayList<Student> studentsIn = new ArrayList<>();


    char[] dayList;

    int intStart;
    int intEnd;

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

    // get ID of course
    public String getId() {
        return this.id;
    }

    // get capacity of course
    public int getCapacity() {
        return this.capacity;
    }

    public int getTotal() {
        return this.total;
    }

    public char[] getDayList() {
        return this.dayList;
    }

    public int getIntStart() {
        return this.intStart;
    }

    public int getIntEnd() {
        return this.intEnd;
    }

    public String getName() {
        return this.courseName;
    }

    public String getLecturer() {
        return this.lecturer;
    }

    public void setTotal() {this.total++;}

    public void setStudentsIn(Student student) {
        this.studentsIn.add(student);
    }






}
