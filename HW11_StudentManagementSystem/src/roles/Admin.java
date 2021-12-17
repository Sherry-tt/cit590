package src.roles;

import src.Course;
import src.FileInfoReader;

import java.util.ArrayList;

public class Admin extends User {

    public Admin(String str) {
        String[] admin = str.split(";");
        this.id = admin[0].trim();
        this.name = admin[1].trim();
        this.username = admin[2].trim();
        this.password = admin[3].trim();
    }

    public Admin() {

    }


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

    public boolean beforeAdd(String id, FileInfoReader file) {
        boolean isExist = file.checkCourseExist(id);
        if (isExist) {
            System.out.println("The course already exist");
            return false;
        }
        return true;
    }

    public void checkProfExist(String lecturerId) {
        // if not exist, add prof to list first
    }

    /**
     * 2- add new courses
     * @param id
     * @param name
     * @param start
     * @param end
     * @param days
     * @param capacity
     * @param lecturerId
     * @param file
     */

    public void addCourses(String id, String name, String start, String end,
                            String days, String capacity, String lecturerId, FileInfoReader file) {
        // get prof info based on id
        Professor prof = file.getOneProf(lecturerId);
        //check if prof has conflict
        String strCourse = id + ";" + name + ";" + prof.getName() +" " +
                days + ";" + start + ";" +  end + ";" + capacity;
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

    public void deleteProf(String id, FileInfoReader file) {
        file.removeProfessor(id);
    }

    public void deleteStu(String id, FileInfoReader file) {
        file.removeStudent(id);

    }

    public void deleteCourse(String id, FileInfoReader file) {
        file.removeCourse(id);
    }

    /**
     * check if prof id in system
     * @param id
     * @param file
     * @return true if exist
     */
    public boolean beforeDelProf(String id, FileInfoReader file) {
        ArrayList<Professor> professors = file.getProfessors();
        for(Professor prof : professors) {
            if (prof.getId().equals(id)) {
                return true;
            }
        }
        return false;
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

    public boolean beforeDelCourse(String id, FileInfoReader file) {
        ArrayList<Course> courses = file.getCourses();
        for(Course course : courses) {
            if (course.getId().equals(id)) {
                return true;
            }
        }
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
     * check name before add students
     * @param name of students
     * @param file file
     * @return true if this name can be used
     */

    public boolean beforeAddStuName (String name, FileInfoReader file) {
        ArrayList<Student> students = file.getStudents();
        for(Student stu : students) {
            if (stu.getName().equals(name)) {
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
     * check name before add students
     * @param name of students
     * @param file file
     * @return true if this name can be used
     */

    public boolean beforeAddProfName (String name, FileInfoReader file) {
        ArrayList<Professor> professors = file.getProfessors();
        for(Professor prof : professors) {
            if (prof.getName().equals(name)) {
                System.out.println("The username you entered is not available.");
                return false;
            }
        }
        return true;
    }



}
