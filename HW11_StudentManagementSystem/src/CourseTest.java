package src;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.roles.Student;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    Course course;

    @BeforeEach
    void setUp() {
        String str2 = "CIT000; Programming Languages ; Brandon L Krakowsky; MW; 01:30; 03:00; 110";
        course = new Course(str2);
    }

    @Test
    void setTotal() {
        assertEquals(course.getTotal(), 0);
        course.setTotal();
        assertEquals(course.getTotal(), 1);
    }
}