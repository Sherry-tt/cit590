package src.roles;

import src.Course;
import src.FileInfoReader;

import java.util.ArrayList;

public class Admin extends User {

    /**
     * constructor
     * @param str given string
     */
    public Admin(String str) {
        String[] admin = str.split(";");
        this.id = admin[0].trim();
        this.name = admin[1].trim();
        this.username = admin[2].trim();
        this.password = admin[3].trim();
    }

    /**
     * constructor
     */
    public Admin() {

    }


    /**
     * login in
     * @param username of admin
     * @param password of admin
     * @param file file
     * @return true of login in successfully
     */
    @Override
    public boolean login(String username, String password, FileInfoReader file) {
        ArrayList<Admin> admins = file.getAdmins();
        for (Admin admin : admins) {
            if (admin.username.equals(username) && admin.password.equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * get object
     * @param username of admin
     * @param password od admin
     * @param file file
     * @return Admin object
     */
    @Override
    public Admin getLogin (String username, String password, FileInfoReader file) {
        ArrayList<Admin> admins = file.getAdmins();
        for (Admin admin : admins) {
            if (admin.username.equals(username) && admin.password.equals(password)) {
                return admin;
            }
        }
        return null;
    }


    /**
     * 1- view all courses
     * @param file file
     */
    public void viewAllCourse(FileInfoReader file) {
        ArrayList<Course> courses = file.getCourses();
        for (Course cour : courses) {
            cour.printCourse();
        }
    }


    /**
     * 2- add new courses
     * @param file
     */
    public void addCourses(String strCourse, FileInfoReader file, String lecturerId, String courseId) {
        // get prof info based on id
        Professor prof = file.getOneProf(lecturerId);
        //check if prof has conflict

        prof.setTeach(file);
        ArrayList<Course> profCourses = prof.getCourses();
        Course str = new Course(strCourse);
        for (Course course : profCourses) {
            if (checkProfConflict(course, str)) {
                printMessage(course, str);
                return;
            }
        }
        //CIT595; Computer Systems Programming; Insup Lee; TR; 15:00; 16:30; 72
        file.setCourses(strCourse);
        Course thisCourse = file.getOneCourse(courseId);
        System.out.print("Successfully added the course: ");
        thisCourse.printCourse();
    }

    /**
     * check if the course has already exists
     * @param id of course
     * @param file name
     * @return true if it can be added
     */
    public boolean beforeAddCourse(String id, FileInfoReader file) {
        boolean isExist = file.checkCourseExist(id);
        if (isExist) {
            System.out.println("The course already exist");
            return false;
        }
        return true;
    }


    /**
     * check if the professor has already exists
     * @param lecturerId id of lecturer
     * @param file file
     * @return true if professor exists
     */
    public boolean checkProfExist(String lecturerId, FileInfoReader file) {
        // if not exist, add prof to list first
        ArrayList<Professor> profs = file.getProfessors();
        for (Professor prof : profs) {
            if (prof.getId().equals(lecturerId)) {
                return true;
            }
        }
        return false;
    }




    /**
     * add the student to system
     * @param strStu student's information
     * @param file name
     */
    public void addStudent(String strStu, FileInfoReader file) {
        file.setStudents(strStu);
    }


    /**
     * add the professor to system
     * @param strProf
     * @param file
     */
    public void addProf(String strProf, FileInfoReader file) {
        file.setProfessors(strProf);

    }


    /**
     * delete professor with given id
     * @param id of prof
     * @param file name
     */

    public void deleteProf(String id, FileInfoReader file) {
        if (checkProfExist(id, file)) {
            file.removeProfessor(id);
            System.out.println("Delete successful");
        } else{
            System.out.println("Delete failed, lecturer does not exists.");
        }
    }

    /**
     * delete student based on id
     * @param id student id
     * @param file file
     */
    public void deleteStu(String id, FileInfoReader file) {
        if (beforeDelStu(id, file)) {
            file.removeStudent(id);
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete failed, lecturer does not exists.");
        }

    }

    /**
     * delete course based on id
     * @param id of course
     * @param file file
     */
    public void deleteCourse(String id, FileInfoReader file) {
        if (beforeDelCourse(id, file)) {
            file.removeCourse(id);
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete failed, course does not exists.");
        }

    }


    /**
     * check if course id in system
     * @param id of course
     * @param file name
     * @return true if exist
     */

    public boolean beforeDelStu (String id, FileInfoReader file) {
        ArrayList<Student> students = file.getStudents();
        for(Student stu : students) {
            if (stu.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }


    /**
     * check if course exists
     * @param id of course
     * @param file name
     * @return true if course exists
     */

    public boolean beforeDelCourse(String id, FileInfoReader file) {
        ArrayList<Course> courses = file.getCourses();
        for(Course course : courses) {
            if (course.getId().equals(id)) {
                return true;
            }
        }
        System.out.println("Delete failed, course does not exists.");
        return false;
    }


    /**
     * chack if the new course is conflict with exiting classes
     * @param course1 exiting courses
     * @param course2 new course
     * @return true if there has a conflict
     */

    private boolean checkProfConflict(Course course1, Course course2 ) {
        int start2 = course2.getIntStart();
        int end2 = course2.getIntEnd();
        char[] days2 = course2.getDayList();

        char[] days1 = course1.getDayList();
        int start1 = course1.getIntStart();
        int end1 = course1.getIntEnd();

        for(char d1 : days1) {
            for(char d2 : days2) {
                if (d1 == d2 && (! (start2 >= end1 || end2 <= start1))) {
                    return true;
                }
            }
        }
        // no conflict
        return false;
    }


    /**
     * pring message when there is a conflict
     * @param course have conflict with
     * @param str new course
     */
    private void printMessage(Course course, Course str){
        System.out.print("The new added course has time conflict with course: " );
        course.printCourse();
        System.out.print("Unable to add new course ");
        str.printCourse();
    }

    /**
     * check id before add students
     * @param id of student
     * @param file file
     * @return true if this id can be used
     */

    public boolean beforeAddStuId (String id, FileInfoReader file) {
        ArrayList<Student> students = file.getStudents();
        for(Student stu : students) {
            if (stu.getId().equals(id)) {
                System.out.println("The ID already exists.");
                return false;
            }
        }
        return true;
    }

    /**
     * check username before add students
     * @param username of students
     * @param file file
     * @return true if this name can be used
     */

    public boolean beforeAddStuUsername (String username, FileInfoReader file) {
        ArrayList<Student> students = file.getStudents();
        for(Student stu : students) {
            if (stu.getUsername().equals(username)) {
                System.out.println("The username you entered is not available.");
                return false;
            }
        }
        return true;
    }


    /**
     * check id before add students
     * @param id of profedssor
     * @param file name
     * @return true if this id can be used
     */

    public boolean beforeAddProfId (String id, FileInfoReader file) {
        ArrayList<Professor> professors = file.getProfessors();
        for(Professor prof : professors) {
            if (prof.getId().equals(id)) {
                System.out.println("The ID already exists.");
                return false;
            }
        }
        return true;
    }

    /**
     * check name before add professor
     * @param username of professor
     * @param file file
     * @return true if this name can be used
     */

    public boolean beforeAddProfUsername (String username, FileInfoReader file) {
        ArrayList<Professor> professors = file.getProfessors();
        for(Professor prof : professors) {
            if (prof.getUsername().equals(username)) {
                System.out.println("The username you entered is not available.");
                return false;
            }
        }
        return true;
    }

}
