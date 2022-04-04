public class StudentsEnrolment extends Main {
    Students students;
    Courses courses;
    String semester;


    public Students getStudents(){
        return students;
    }

    public Courses getCourses(){
        return courses;
    }

    public String getSemester(){
        return  semester;
    }

    public StudentsEnrolment(Students students, Courses courses, String semester){
        this.students = students;
        this.courses = courses;
        this.semester = semester;
    }

    @Override
    public String toString(){
        return students.getId()+
                "," + students.getName()+
                "," + students.getId()+
                "," + courses.getCourseId()+
                "," + courses.getCourseName()+
                "," + courses.getNumberOfCredit()+
                "," + semester + "\n";

    }

}
