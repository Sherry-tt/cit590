package src;

import src.roles.Admin;
import src.roles.Professor;
import src.roles.Student;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * read the file in
 */
public class FileInfoReader {

    /**
     * student list
     */
    public static List<String> studentList;
    /**
     * professor list
     */
    public static List<String> profList;
    /**
     * administrator list
     */
    public static List<String> adminList;
    /**
     * course list
     */
    public static List<String> courseList;

    /**
     * all courses
     */
    private ArrayList<Course> courses = new ArrayList<>();
    /**
     * all students
     */
    private ArrayList<Student> students = new ArrayList<>();
    /**
     * all administrators
     */
    private ArrayList<Admin> admins = new ArrayList<>();
    /**
     * all professors
     */
    private ArrayList<Professor> professors = new ArrayList<>();


    /**
     * read file from .txt file
     */
    public FileInfoReader() {
        File myFile_studentInfo = new File("src/studentInfo.txt");
        File myFile_profInfo = new File("src/profInfo.txt");
        File myFile_adminInfo = new File("src/adminInfo.txt");
        File myFile_courseInfo = new File("src/courseInfo.txt");

        try {
            List<String> allLines_1 = Files.readAllLines(myFile_studentInfo.toPath());
            this.studentList = allLines_1;
            List<String> allLines_2 = Files.readAllLines(myFile_profInfo.toPath());
            this.profList = allLines_2;
            List<String> allLines_3 = Files.readAllLines(myFile_adminInfo.toPath());
            this.adminList = allLines_3;
            List<String> allLines_4 = Files.readAllLines(myFile_courseInfo.toPath());
            this.courseList = allLines_4;

            constructCourses();
            constructStudents();
            constructAdmins();
            constructProfessors();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * construct the courses
     */
    private void constructCourses() {
        for(int i = 0 ; i < this.courseList.size(); i++) {
           this.courses.add(new Course(this.courseList.get(i)));
        }
    }

    /**
     * construct the Admins
     */
    private void constructAdmins() {
        for(int i = 0 ; i < this.adminList.size(); i++) {
            this.admins.add(new Admin(this.adminList.get(i)));
        }
    }

    /**
     * construct the students
     */
    private void constructStudents() {
        for(int i = 0 ; i < this.studentList.size(); i++) {
            this.students.add(new Student(this.studentList.get(i)));
        }
    }

    /**
     * construct the Professors
     */
    private void constructProfessors() {
        for(int i = 0 ; i < this.profList.size(); i++) {
            this.professors.add(new Professor(this.profList.get(i)));
        }
    }

    /**
     * get the students list
     * @return students list
     */
    public ArrayList<Student> getStudents() {
        return this.students;
    }

    /**
     * get the admins list
     * @return admins list
     */
    public ArrayList<Admin> getAdmins() {
        return this.admins;
    }

    /**
     * get the Professors list
     * @return Professors list
     */
    public ArrayList<Professor> getProfessors() {
        return this.professors;
    }

    /**
     * get the courses list
     * @return courses list
     */
    public ArrayList<Course> getCourses() {
        return this.courses;
    }

    /**
     * give courseId, check if it has exists
     * @param courseId of course
     * @return true if it has exists
     */
    public boolean checkCourseExist(String courseId) {
        for (Course cou : this.courses) {
            if (cou.getId().equals(courseId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * set the course list
     * @param course given course information
     */
    public void setCourses(String course) {
        this.courses.add(new Course(course));
    }

    /**
     * set the student list
     * @param student given student information
     */
    public void setStudents(String student) {
        this.students.add(new Student(student));
    }

    /**
     * set the professor list
     * @param professor given student information
     */
    public void setProfessors(String professor) {
        this.professors.add(new Professor(professor));
    }

    /**
     * get professor information based on the id
     * @param profId of professor
     * @return professor information
     */
    public Professor getOneProf(String profId) {
        for (Professor prof : this.professors) {
            if (prof.getId().equals(profId)) return prof;
        }
        return null;
    }

    /**
     * get one course object
     * @param courseId id of course
     * @return Course obkect
     */
    public Course getOneCourse(String courseId){
        for (Course cour : this.courses) {
            if (cour.getId().equals(courseId)) return cour;
        }
        return null;
    }

    /**
     * remove the course based on ID
     * @param id
     */
    public void removeCourse(String id) {
//        for (Course course : courses) {
//            if (course.getId().equals(id)) courses.remove(course);
//        }

        Iterator<Course> iterator = this.courses.iterator();
        while (iterator.hasNext()){
            Course next = iterator.next();
            if(next.getId().equals(id)){
                iterator.remove();
                return;
            }
        }
    }

    /**
     * remove the Professor based on ID
     * @param id of Professor
     */
    public void removeProfessor(String id) {
//        for (Professor prof : professors) {
//            if (prof.getId().equals(id)) courses.remove(prof);
//        }
        Iterator<Professor> iterator = this.professors.iterator();
        while (iterator.hasNext()){
            Professor next = iterator.next();
            if(next.getId().equals(id)){
                iterator.remove();
                return;
            }
        }
    }

    /**
     * remove the Student based on ID
     * @param id of Student
     */
    public void removeStudent(String id) {
//        for (Student stu : students) {
//            if (stu.getId().equals(id)) courses.remove(stu);
//        }

        Iterator<Student> iterator = this.students.iterator();
        while (iterator.hasNext()){
            Student next = iterator.next();
            if(next.getId().equals(id)){
                iterator.remove();
                return;
            }
        }
    }
}
