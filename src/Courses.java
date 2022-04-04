public class Courses{
    private final String CourseId;
    private final String CourseName;
    private final String numberOfCredit;

    public Courses(String CourseID, String CourseName, String numberOfCredit){

        this.CourseId = CourseID;
        this.CourseName = CourseName;
        this.numberOfCredit = numberOfCredit;
    }

    public String getCourseId(){
        return CourseId;
    }

    public String getCourseName(){
        return CourseName;
    }

    public String getNumberOfCredit(){
        return numberOfCredit;
    }

}
