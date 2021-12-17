package src;

import src.roles.Admin;
import src.roles.Professor;
import src.roles.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    Scanner scan = new Scanner(System.in);




    public static void main(String[] str) {
//        System.out.println("Please enter your username, or type 'q' to quit ");
        Controller system = new Controller();
        FileInfoReader file = new FileInfoReader();

        while (true) {
            int choice = system.managementSystem();
            // name and password
            String[] np;
            Student stu = new Student();
            Professor prof = new Professor();
            Admin admin = new Admin();


            // login in as student
            if (choice == 1) {
                Student s;
                np = system.askNameAndPassWord();
                // to quit
                if (np[0].equals("q") || np[1].equals("q")) continue;
                // when user name or password not valid
                while (!stu.login(np[0], np[1], file)) {
                    System.out.println("Invalid username or password");
                    np = system.askNameAndPassWord();
                }
                s = stu.getLogin(np[0], np[1], file);
                system.dealWithStudent(s, file);
            } else if(choice == 2) {
                // login as professor
                Professor p;
                np = system.askNameAndPassWord();
                // to quit
                if (np[0].equals("q") || np[1].equals("q")) continue;

                while (!prof.login(np[0], np[1], file)) {
                    System.out.println("Invalid username or password");
                    np = system.askNameAndPassWord();
                }

                p = prof.getLogin(np[0], np[1], file);
                system.dealWithProfessor(p, file);
            } else if (choice == 3) {
                // login in as administrator
                Admin ad;
                np = system.askNameAndPassWord();
                // to quit
                if (np[0].equals("q") || np[1].equals("q")) continue;

                while (!admin.login(np[0], np[1], file)) {
                    System.out.println("Invalid username or password");
                    np = system.askNameAndPassWord();
                }
                ad = admin.getLogin(np[0], np[1], file);
                system.dealWithAdmin(ad, file);
            } else if(choice == 4) break;
        }
    }


    /**
     * when login as a student
     * @param student object
     * @param file
     */
    private void dealWithStudent(Student student, FileInfoReader file) {

        while(true) {
            int option = printStudentInterface(student);
            // 1- view all courses
            if (option == 1) {
                student.viewAllCourse(file);
            } else if (option == 2) {
                // 2 -- Add courses to your list
                String option2 = stuTwoAddCourse();
                if (option2.equals("q")) continue;
                else {
                    while(true) {
                        student.addCourse(option2, file);
                        option2 = stuTwoAddCourse();
                        if (option2.equals("q")) break;
                    }
                }
            } else if(option == 3) {
                // 3 -- View enrolled courses
                student.checkCourseSchedule();
            } else if(option == 4) {
                // 4 -- Drop courses in your list
                String option4 = stuFourDropCourse(student, file);
                if (option4.equals("q")) continue;
                else {
                    while(true) {
                        student.dropCourse(option4);
                        option4 = stuFourDropCourse(student, file);
                        if (option4.equals("q")) break;
                    }
                }
            } else if(option == 5) {
                student.viewGrades(file);
            } else if(option == 6) {
                return;
            }
        }
    }

    /**
     * when login as professor
     * @param professor object
     * @param file name
     */

    private void dealWithProfessor(Professor professor, FileInfoReader file) {
        while(true) {
            int option = printProfessorInterface(professor);
            // 1- View given courses
            if(option == 1) {
                professor.courseTeach(file);
            } else if(option == 2) {
                // 2 -- View student list of the given course
                while(true) {
                    String courseId = profTwoViewStudent(professor, file);
                    professor.studentInCourse(courseId, file);
                    if (courseId.equals("q")) break;
                }
            } else if (option == 3) return;
        }
    }

    private void dealWithAdmin(Admin admin, FileInfoReader file) {
        while(true) {
            int option = printAdminInterface();
            // 1- View given courses
            if(option == 1) {
                admin.viewAllCourse(file);
            } else if(option == 2) {
                // 2 -- Add new courses
                adminTwoAddCourse(admin, file);
            } else if(option == 3) {
                // 3- Delete courses
                System.out.println("Please enter the course ID, or type 'q' to end");
                String id = scan.next();
                if (!id.equals("q")) {
                    admin.deleteCourse(id, file);
                }
            } else if(option == 4) {
                // 4- Add new prof
                adminFourAddProf(admin, file);
            } else if(option == 5) {
                // 5- delete professor
                System.out.println("Please enter the professor ID, or type 'q' to end");
                String profId = scan.next();
                if (!profId.equals("q")) {
                    admin.deleteProf(profId, file);
                }
            } else if (option == 6) {
                // 6- Add new student
                adminFourAddProf(admin, file);
            } else if(option == 7) {
                // 7 - Delete student
                System.out.println("Please enter the Student ID, or type 'q' to end");
                String stuId = scan.next();
                if (!stuId.equals("q")) {
                    admin.deleteStu(stuId, file);
                }
            } else if(option == 8) return;
        }
    }

//---------------------------Student----------------------------------
    /**
     * student choose
     * 2 -- Add courses to your list
     * @return the choice
     */
    private String stuTwoAddCourse() {
        System.out.println("Please select the course ID you want to add to your list, eg. 'CIT590'");
        System.out.println("Or enter 'q' to the previous menu");
        String option = scan.next();
        return option;
    }

    /**
     * student
     * 4- Drop courses in your list
     * @param student object
     * @param file name
     * @return option
     */

    private String stuFourDropCourse(Student student, FileInfoReader file) {
        System.out.println("The courses in your list:");
        student.checkCourseSchedule();
        System.out.println();

        System.out.println("Please enter the ID of the course which you want to add to drop");
        System.out.println("Or enter 'q' to the previous menu");
        String option = scan.next();
        return option;
    }

//---------------------------Professor----------------------------------
    private String profTwoViewStudent(Professor professor, FileInfoReader file) {
        professor.courseTeach(file);
        System.out.println("Please enter the course ID, eg. 'CIS519'.");
        System.out.println("Or type 'q' to quit.");
        String courseId = scan.next();
        return courseId;
    }


//---------------------------Administrator----------------------------------
    private void adminTwoAddCourse(Admin admin, FileInfoReader file) {
        // get course ID
        System.out.println("Please enter the course ID, or type 'q' to end.");
        String id = scan.next();
        if (id.equals("q")) return;
        while (! admin.beforeAddCourse(id, file)) {
            System.out.println("Please enter the course ID, or type 'q' to end.");
            id = scan.next();
            if (id.equals("q")) return;
        }
        // get course name
        System.out.println("Please enter the course name, or type 'q' to end.");
        String name = scan.next();
        if (name.equals("q")) return;
        // get start date
        System.out.println("Please enter the course start time, or type 'q' to end. eg. '19:00'");
        String start = scan.next();
        if (start.equals("q")) return;
        // get end date
        System.out.println("Please enter the course end time, or type 'q' to end. eg. '20:00'");
        String end = scan.next();
        if (end.equals("q")) return;
        // get course date
        System.out.println("Please enter the course date, or type 'q' to end. eg. 'MW'");
        String days = scan.next();
        if (days.equals("q")) return;
        // get capacity
        System.out.println("Please enter the course capacity, or type 'q' to end. ed. '72'");
        String capacity = scan.next();
        if (capacity.equals("q")) return;
        // get lecturer's id
        System.out.println("Please enter the course lecturer's id, or type 'q' to end. eg. '001'");
        String lecturerId = scan.next();
        if (lecturerId.equals("q")) return;

        Professor prof;
        Course temp;

        // check if professor exist
        if (admin.checkProfExist(lecturerId, file)) {
            // exists
            prof = file.getOneProf(lecturerId);
            String strCourse = id + ";" + name + ";" + prof.getName() +";" +
                    days + ";" + start + ";" +  end + ";" + capacity;
            temp = new Course(strCourse);
            admin.addCourses(strCourse, file, lecturerId, id);
        } else {
            System.out.println("The professor isn't in the system, please add this professor first");

            adminFourAddProf(admin, file);
            // add new professor
            if(admin.checkProfExist(lecturerId, file)) {
                prof = file.getOneProf(lecturerId);
                String strCourse = id + ";" + name + ";" + prof.getName() +";" +
                        days + ";" + start + ";" +  end + ";" + capacity;
                admin.addCourses(strCourse, file, lecturerId, id);
            }
        }

        // check if time conflict

    }


    private void adminFourAddProf(Admin admin, FileInfoReader file) {
        System.out.println("Please enter the professor's ID, or type 'q' to quit ");
        String id = scan.next();
        if (id.equals("q")) return;
        // check id valid
        while(! admin.beforeAddProfId (id, file)) {
            System.out.println("Please enter the professor's ID, or type 'q' to quit ");
            id = scan.next();
            if (id.equals("q")) return;
        }

        // name
        System.out.println("Please enter the professor's name, or type 'q' to quit ");
        String name = scan.next();
        if (name.equals("q")) return;

        // username
        System.out.println("Please enter a username ");
        String username = scan.next();
        if (username.equals("q")) return;
        while(! admin.beforeAddProfUsername(username, file)) {
            System.out.println("Please enter the professor's ID, or type 'q' to quit ");
            id = scan.next();
            if (id.equals("q")) break;
        }

        // password
        System.out.println("Please enter a password ");
        String password = scan.next();
        if (password.equals("q")) return;

        //Clayton Greenberg; 001; Greenberg; password590
        String strProf = name + ";" + id + ";" + username + ";" + password;
        admin.addProf(strProf, file);
        System.out.println("Successfully add the new professor: " + id +" "+ name);
    }

    private void adminSixAddStu(Admin admin, FileInfoReader file) {
        System.out.println("Please enter the student's ID, or type 'q' to quit ");
        String id = scan.next();
        if (id.equals("q")) return;
        // check id valid
        while(! admin.beforeAddStuId (id, file)) {
            System.out.println("Please enter the professor's ID, or type 'q' to quit ");
            id = scan.next();
            if (id.equals("q")) return;
        }
        //001; StudentName1; testStudent01; password590; CIS191: A, CIS320: A

        // name
        System.out.println("Please enter the student's name, or type 'q' to quit ");
        String name = scan.next();
        if (name.equals("q")) return;

        // username
        System.out.println("Please enter a username ");
        String username = scan.next();
        if (username.equals("q")) return;
        while(! admin.beforeAddStuUsername(username, file)) {
            System.out.println("Please enter the professor's ID, or type 'q' to quit ");
            id = scan.next();
            if (id.equals("q")) return;
        }

        // password
        System.out.println("Please enter a password ");
        String password = scan.next();
        if (password.equals("q")) return;

        String str = id + ";" + name + ";" + username + ";" + password;

        // course & grade
        String courseId;
        String grade;
        int count = 0;
        // course
        while(true) {
            System.out.println("Please enter ID of a course which this student already took, one in a time ");
            System.out.println("type 'q' to quit, type 'n' to stop adding.");
            courseId = scan.next();
            if (courseId.equals("q")) return;
            if (courseId.equals("n")) break;

            if (count == 0) str += ";";
            else str += ",";
            //grade
            System.out.println("Please enter the grade, eg, 'A' ");
            grade = scan.next();
            str += courseId + ":" + grade;
            count++;
        }

        admin.addStudent(str, file);
    }


// ---------------Interface part--------------------------------------

    /**
     * print the system interface
     */
    private int managementSystem() {
        System.out.println("----------------------------");
        System.out.println("Students Management System");
        System.out.println("----------------------------");
        System.out.println("1 -- Login as a student");
        System.out.println("2 -- Login as a professor");
        System.out.println("3 -- Login as an admin");
        System.out.println("4 -- Quit the system");
        System.out.println();
        System.out.println("Please enter your option, eg. '1'.");
        String choice = scan.next();
        return Integer.valueOf(choice);
    }

    /**
     * ask user's username and password
     * @return
     */

    private String[] askNameAndPassWord() {
        System.out.println("Please enter your username, or type 'q' to quit");
        String username = scan.next();
        if (username.equals("q")) return new String[] {"q", "q"};
        System.out.println("Please enter your password, or type 'q' to quit");
        String password = scan.next();
        if (password.equals("q")) return new String[] {username, "q"};
        return new String[] {username, password};
    }

    /**
     * print the student interface
     * @param s student
     * @return option
     */
    private int printStudentInterface(Student s) {
        System.out.println("--------------------");
        System.out.println("Welcome, " + s.getName());
        System.out.println("--------------------");
        System.out.println("1 -- View all courses");
        System.out.println("2 -- Add courses to your list");
        System.out.println("3 -- View enrolled courses");
        System.out.println("4 -- Drop courses in your list");
        System.out.println("5 -- View grades");
        System.out.println("6 -- Return to previous menu");
        System.out.println();
        System.out.println("Please enter your option, eg. '1'.");
        String option = scan.next();
        return Integer.valueOf(option);
    }

    /**
     * print the professor interface
     * @param p professor
     * @return option
     */

    private int printProfessorInterface(Professor p) {
        System.out.println("--------------------");
        System.out.println("Welcome, " + p.getName());
        System.out.println("--------------------");
        System.out.println("1 -- View given courses");
        System.out.println("2 -- View student list of the given course");
        System.out.println("3 -- Return to previous menu");
        System.out.println();
        System.out.println("Please enter your option, eg. '1'.");
        String option = scan.next();
        return Integer.valueOf(option);
    }

    private int printAdminInterface() {
        System.out.println("--------------------");
        System.out.println("Welcome, admin" );
        System.out.println("--------------------");
        System.out.println("1 -- View all courses");
        System.out.println("2 -- Add new courses");
        System.out.println("3 -- Delete courses");
        System.out.println("4 -- Add new professor");
        System.out.println("5 -- Delete professor");
        System.out.println("6 -- Add new student");
        System.out.println("7 -- Delete student");
        System.out.println("8 -- Return to previous menu");

        System.out.println();
        System.out.println("Please enter your option, eg. '1'.");
        String option = scan.next();
        return Integer.valueOf(option);
    }

}
