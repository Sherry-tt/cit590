package src.roles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.FileInfoReader;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    Student stu;
    Professor prof;
    Admin ad;
    FileInfoReader file;

    @BeforeEach
    void setUp() {
        stu = new Student();
        prof = new Professor();
        ad = new Admin();
        file = new FileInfoReader();
    }

    @Test
    void login() {
        assertTrue(prof.login("Smith", "password590", file));
        assertFalse(prof.login("sherry", "password591", file));
    }

    @Test
    void getLogin() {
        Student stu2 = stu.getLogin("testStudent01", "password590", file);
        assertEquals("001", stu2.getId());
        assertEquals("StudentName1", stu2.getName());
    }
}