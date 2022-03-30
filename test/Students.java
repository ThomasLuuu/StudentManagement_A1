public class Students {
    private final String Sid;
    private final String StudentName;
    private final String birthDate;

    //Setter Getter
    public  Students(String Sid, String StudentName, String birthDate){
        this.Sid = Sid;
        this.StudentName = StudentName;
        this.birthDate = birthDate;

    }
    public String getId(){
        return Sid;
    }

    public String getName(){
        return  StudentName;
    }

    public String getBirthDate(){
        return birthDate;
    }




}
