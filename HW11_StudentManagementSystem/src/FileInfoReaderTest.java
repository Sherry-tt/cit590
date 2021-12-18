package src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.roles.Professor;
import src.roles.Student;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FileInfoReaderTest {
    FileInfoReader file;

    @BeforeEach
    void setUp() {
        file = new FileInfoReader();
    }

    @Test
    void checkCourseExist() {
        assertTrue(file.checkCourseExist("CIT590"));
        assertFalse(file.checkCourseExist("CIT000"));
    }

    @Test
    void removeCourse() {
        ArrayList<Course> courses = file.getCourses();
        assertEquals(50, courses.size());
        file.removeCourse("CIT590");
        assertEquals(49, courses.size());
    }

    @Test
    void removeProfessor() {
        ArrayList<Professor> professors = file.getProfessors();
        assertEquals(32, professors.size());
        file.removeProfessor("029");
        assertEquals(31, professors.size());
    }

    @Test
    void removeStudent() {
        ArrayList<Student> students = file.getStudents();
        assertEquals(2, students.size());
        file.removeStudent("001");
        assertEquals(1, students.size());
    }
}