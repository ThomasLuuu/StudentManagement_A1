import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
public class Main implements StudentsEnrolmentManager {
    static Scanner obj = new Scanner(System.in);
    static ArrayList<Courses> coursesList = new ArrayList();
    static ArrayList<Students> studentsList = new ArrayList();
    static ArrayList<StudentsEnrolment> studentsEnrolmentList = new ArrayList<>();

    static String[] semester = {"2020A", "2020B", "2020C","2021A","2021B","2021C","2022A","2022B","2022C","2023A","2023B","2023C","2024A","2024B","2024C"};
    static ArrayList semesters = new ArrayList(Arrays.asList(semester));

    public static void welcome(){
        System.out.println("welcome to Student Management Program");
        System.out.println("please choose the section you want to do");
        System.out.println("1: display student information");
        System.out.println("2: add student information");
        System.out.println("3: delete student information");
        System.out.println("4: update student information");
        System.out.println("5: exit");
    }

    public static int option(){
        Scanner obj = new Scanner(System.in);
        int option = obj.nextInt();
        return option;
    }

//    public static void readFile(){
//        try {
//            Scanner sc = new Scanner(new File("src//default.csv"));
//            sc.useDelimiter(", ");
//            while(sc.hasNext()){
//                System.out.print(sc.next());
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//
//    }

    public static String readFile(){
        boolean done = false;
        String fileName = "";
        while (!done) {
            System.out.print("Please enter File Name located in src folder. Leave null to load default file: ");
            fileName = obj.nextLine();
            String defaultFile = "src/default.csv";
            if (!fileName.equals("")) {
                defaultFile = "src/"+fileName;
            }
            String line;
            String splitBy = ",";
            try {
                BufferedReader in = new BufferedReader(new FileReader(defaultFile));

                while ((line = in.readLine()) != null) {

                    boolean sAdded = false, cAdded = false;
                    String[] data = line.split(splitBy);
                    for (Students student : studentsList) {
                        if (student.getId().equals(data[0])) {
                            sAdded = true;
                            break;
                        }
                    }
                    for (Courses course : coursesList) {
                        if (course.getCourseId().equals(data[3])) {
                            cAdded = true;
                            break;
                        }
                    }
                    if (!sAdded) {
                        studentsList.add(new Students(data[0], data[1], data[2]));
                    }
                    if (!cAdded) {
                        coursesList.add(new Courses(data[3], data[4], data[5]));
                    }
                    studentsEnrolmentList.add(new StudentsEnrolment((new Students(data[0], data[1], data[2])), new Courses(data[3], data[4], data[5]), data[6]));
                }
                System.out.println("Successfully read from file.");
                done = true;

            } catch (IOException e) {
                System.out.println("An error occurred. Cannot find File.");
                done=false;
            }
        }
        return fileName;
    }






    public void Update(){

    }
    public void Delete(){

    }
    public void GetOne(){
        boolean flag = false;
//        Scanner obj1 = new Scanner(System.in);
//        System.out.println("Give me the ID you want to find: ");
//        String findId = obj1.nextLine();
        for (StudentsEnrolment std:studentsEnrolmentList){
            if(std.students.getId() == "1223") {
                flag = true;
            }
            else{ flag=false;}
        }
        if(flag){
            System.out.println("The list does not have that student you input");
        }else {
            System.out.println("!");
        }
    }
    public void GetAll(){
    if(studentsEnrolmentList.isEmpty()){
        System.out.println("not thing in here");
    }else {
    System.out.println("Here is the report of Students");
    System.out.println(studentsEnrolmentList.toString());
    }
    }
    public static void main(String[] args){

        String defaultAction = readFile();
        int option;
        Main system = new Main();
        do {
            welcome();
            option = option();
            switch (option){
                case 1: {
                    System.out.println("Do you want to display all students ( type 1 ) or specific student( type 2): ");
                    Scanner obj = new Scanner(System.in);
                    int choice = obj.nextInt();
                    switch (choice){
                        case 1: {
                            system.GetAll();
                            break;
                        }
                        case 2:{
                            system.GetOne();
                            break;
                        }

                    }
                    break;
                }
                case 2:{
                    system.Add();
                    break;
                }
                case 3:{
                    break;
                }
                case 4:{
                    System.out.println("4");
                    break;
                }
                default:{
                    System.out.println("Your choice not found");
                    break;
                }
            }
        }while (option !=0);


    }
    public static void Add(){
        Students result1 = null;
        Courses result2 = null;

        System.out.println("List of available students:");
        for (Students student : studentsList) {
            System.out.println(student.getId() + " " + student.getName());
        }
        System.out.println("Enter the student ID: ");
        String studentID = obj.nextLine();

        for (Students student : studentsList) {
            if (student.getId().equals(studentID))
                result1 = student;
        }
        if (result1 == null) {
            System.out.println("No student available with given ID.");
            return;
        }

        System.out.println("List of available courses:");
        for (StudentsEnrolment studentEnrolment : studentsEnrolmentList) {
            System.out.println(studentEnrolment.getCourses().getCourseId() + " " + studentEnrolment.getCourses().getCourseName() + " " + studentEnrolment.getCourses().getNumberOfCredit());
        }

        System.out.println("Enter the course ID: ");
        String courseID = obj.nextLine();

        for (Courses course : coursesList) {
            if (course.getCourseId().equals(courseID))
                result2 = course;
        }
        if (result2 == null) {
            System.out.println("No course available with given ID.");
            return;
        }

        System.out.println("Enter the semester: ");
        String Sem = obj.nextLine();
        if (!(semesters.contains(Sem))) {
            System.out.println("Not an available semester.");
            return;
        }

        StudentsEnrolment record = new StudentsEnrolment(result1, result2, Sem);
        if (studentsEnrolmentList.contains(record)){
            System.out.println("Enrolment already existed");
            return;
        }
        studentsEnrolmentList.add(record);
        System.out.println("Success");
        System.out.println(result1.getId() + " " + result1.getName() + ", " + result2.getCourseId()+ " " + result2.getCourseName() + ", " + Sem);
    }


}
