import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class test {
    private static Students student;
    private static Courses course;
    private static Main studentsEnrolmentList;

    @BeforeEach
    public void setUp(){
        studentsEnrolmentList = new Main();
    }
    @Test
    public void testEnrollIdWhenAdd(){
        Students result1 = new Students("s3777213","Toan","04/09/2000");
        Courses result2 = new Courses("COSC2440","Further Programming","12");
        String sem = "2022A";
        studentsEnrolmentList.equals( new StudentsEnrolment(result1,result2,sem));
        assertEquals("s3777213", result1.getId());

    }
    @Test
    public void testEnrollStudentNameWhenAdd(){
        Students result1 = new Students("s3777213","Toan","04/09/2000");
        Courses result2 = new Courses("COSC2440","Further Programming","12");
        String sem = "2022A";
        studentsEnrolmentList.equals( new StudentsEnrolment(result1,result2,sem));
        assertEquals("Toan", result1.getName());
    }
    @Test
    public void testEnrollCourseNameWhenAdd(){
        Students result1 = new Students("s3777213","Toan","04/09/2000");
        Courses result2 = new Courses("COSC2440","Further Programming","12");
        String sem = "2022A";
        studentsEnrolmentList.equals( new StudentsEnrolment(result1,result2,sem));
        assertEquals("Further Programming", result2.getCourseName());
    }
}

