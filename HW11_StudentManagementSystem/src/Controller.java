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
            }
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
            } else if(option == 3) {
                return;
            }
        }
    }

    private void dealWithAdmin(Admin admin, FileInfoReader file) {
        while(true) {
            int option = printAdminInterface(admin);
            // 1- View given courses
        }
    }


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

    private String profTwoViewStudent(Professor professor, FileInfoReader file) {
        professor.courseTeach(file);
        System.out.println("Please enter the course ID, eg. 'CIS519'.");
        System.out.println("Or type 'q' to quit.");
        String courseId = scan.next();
        return courseId;
    }


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

    private int printAdminInterface(Admin admin) {

    }

}
