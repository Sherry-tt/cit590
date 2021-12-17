package src;

import src.roles.Admin;
import src.roles.Professor;
import src.roles.Student;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileInfoReader {

    public static List<String> studentList;
    public static List<String> profList;
    public static List<String> adminList;
    public static List<String> courseList;

    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Admin> admins = new ArrayList<>();
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

//            for (String line : allLines_4) {
//                System.out.println(line);
//            }
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


    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public ArrayList<Admin> getAdmins() {
        return this.admins;
    }

    public ArrayList<Professor> getProfessors() {
        return this.professors;
    }

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

    public void setCourses(String course) {
        this.courses.add(new Course(course));
    }

    public void setStudents(String student) {
        this.students.add(new Student(student));
    }

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
     * remove the course based on ID
     * @param id
     */

    public void removeCourse(String id) {
        for (Course course : courses) {
            if (course.getId().equals(id)) courses.remove(course);
        }
    }

    public void removeProfessor(String id) {
        for (Professor prof : professors) {
            if (prof.getId().equals(id)) courses.remove(prof);
        }
    }

    public void removeStudent(String id) {
        for (Student stu : students) {
            if (stu.getId().equals(id)) courses.remove(stu);
        }
    }




}
