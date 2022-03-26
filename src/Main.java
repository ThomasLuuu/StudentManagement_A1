import java.io.*;
import java.util.*;

public class Main implements StudentsEnrolmentManager {
    //Attribute
    static Scanner obj = new Scanner(System.in);
    static ArrayList<Courses> coursesList = new ArrayList();
    static ArrayList<Students> studentsList = new ArrayList();
    static ArrayList<StudentsEnrolment> studentsEnrolmentList = new ArrayList<>();
    static String[] semester = {"2020A", "2020B", "2020C","2021A","2021B","2021C","2022A","2022B","2022C","2023A","2023B","2023C","2024A","2024B","2024C"};
    static ArrayList semesters = new ArrayList(Arrays.asList(semester));

    //TODO
    public static void welcome(){
        //Welcome method print out all the options that user can pick to run the program
        System.out.println("welcome to Student Management Program");
        System.out.println("please choose the section you want to do");
        System.out.println("1: display information");
        System.out.println("2: add student information");
        System.out.println("3: delete student information");
        System.out.println("4: update student information");
        System.out.println("5: exit");
    }
    public static int option(){
        //The method that check user pick at welcome()
        Scanner obj = new Scanner(System.in);
        int option = obj.nextInt();
        return option;
    }
    public static boolean verification(String sID, String Course){
        //this is the method to verify if student have course inputted by user
        boolean check  = true;
        for(StudentsEnrolment studentsEnrolment: studentsEnrolmentList){
            if(studentsEnrolment.getStudents().getId().equals(sID)){
                if(studentsEnrolment.getCourses().getCourseId().equals(Course)){
                    //return false if student have the course that inputted => cancel "add() method"
                        check = false;
                        break;
                }
            }
        }
        return check;

    }
    public int getIndex(String sID, String Course){
        //A method to get index of student with inputted course that user want to delete
        int i = 0;
        for(StudentsEnrolment studentsEnrolment: studentsEnrolmentList){
            if(studentsEnrolment.getStudents().getId().equals(sID)){
                if(studentsEnrolment.getCourses().getCourseId().equals(Course)){
                    break;
                }
            }
            //a loop to count. Then get index and return
            i = i + 1;
        }
//        System.out.println(i);
        return i;
    }
    public static String readFile(){
        //A method that whenever launch the program, the system will clone the file "default" to studentsEnrolmentList
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
            try {
                BufferedReader in = new BufferedReader(new FileReader(defaultFile));

                while ((line = in.readLine()) != null) {

                    boolean sAdded = false, cAdded = false;
                    String[] data = line.split(",");
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
    private static void saveFile(String type, String fileName, List data) throws IOException {
        //This is the method that can help user to save the file
        String name = "src/"+fileName+".csv";
        File csvFile = new File(name);
        FileWriter fileWriter = new FileWriter(csvFile, true);
        //Append : true allow to save override to the exit file
        List<List<String>> al = new ArrayList<>();
        al = data;
        //Cases of saving file in this system
        switch (type){
            case"student":{
                fileWriter.write("Course");
                fileWriter.append(',');
                fileWriter.write("Course id");
                fileWriter.write("\n");
                for (int i=0; i<al.size();i++){
                    fileWriter.write((al.get(i)).get(0).split(",")[0]);
                    fileWriter.append(',');
                    fileWriter.write((al.get(i)).get(0).split(",")[1]);
                    fileWriter.write("\n");
                }
                break;
            }
            case"course":{
                fileWriter.write("Student");
                fileWriter.append(',');
                fileWriter.write("Student id");
                fileWriter.write("\n");
                for (int i=0; i<al.size();i++){
                    fileWriter.write((al.get(i)).get(0).split(",")[0]);
                    fileWriter.append(',');
                    fileWriter.write((al.get(i)).get(0).split(",")[1]);
                    fileWriter.write("\n");
                }
                break;
            }
            case"sem":{
                fileWriter.write("Courses in this sem: ");
                fileWriter.write("\n");
                fileWriter.write("Course");
                fileWriter.append(',');
                fileWriter.write("Course id");
                fileWriter.write("\n");
                for (int i=0; i<al.size();i++){
                    fileWriter.write((al.get(i)).get(0).split(",")[0]);
                    fileWriter.append(',');
                    fileWriter.write((al.get(i)).get(0).split(",")[1]);
                    fileWriter.write("\n");
                }
                break;
            }
            case"update":{
                System.out.println(al);
                fileWriter.write("StudentID");
                fileWriter.append(',');
                fileWriter.write("Course ID");
                fileWriter.append(',');
                fileWriter.write("Sem");
                fileWriter.write("\n");
                for (int i=0; i<al.size(); i++){
                    fileWriter.write((al.get(i)).get(0).split(",")[0]);
                    fileWriter.append(',');
                    fileWriter.write((al.get(i)).get(0).split(",")[1]);
                    fileWriter.append(',');
                    fileWriter.write((al.get(i)).get(0).split(",")[2]);
                    fileWriter.write("\n");

                }
            }
        }


//        String re = (al.get(0)).get(0).split(",")[0];
//        String re1 = (al.get(0)).get(0).split(",")[1];
//        System.out.println(re);
//        System.out.println(re1);
        fileWriter.flush();
        fileWriter.close();


    }
    public boolean checkFileExist(String fileName){
        //A method to check whenever user input filename for saving, this method will check if that file exits in the system
        boolean check = false;
        File tempFile =   new File("src/"+fileName+".csv");
        if(tempFile.exists()){
            //return true if exits
            check = true;
        }
        return check;
    }
    public static void Add(){
        //this is Add method, users have to input Id, course and sem
        Students result1 = null;
        Courses result2 = null;
        System.out.println(studentsEnrolmentList);
        System.out.println("List of available students:");
        for (Students student : studentsList) {
            System.out.println(student.getId() + " " + student.getName());
        }
        System.out.println("Enter the student ID: ");
        String studentID = obj.nextLine();

        for (Students student : studentsList) {
            if (student.getId().equalsIgnoreCase(studentID))
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
            if (course.getCourseId().equalsIgnoreCase(courseID))
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
        }
        if((verification(studentID,courseID))){
            StudentsEnrolment record = new StudentsEnrolment(result1, result2, Sem);
            studentsEnrolmentList.add(record);
            System.out.println("Success");
            System.out.println(result1.getId() + " " + result1.getName() + ", " + result2.getCourseId()+ " " + result2.getCourseName() + ", " + Sem);
        }else {
            System.out.println("Your input is already exits");
        }
    }
    public void Update() throws IOException {
        //this is the update method that helps user to update course of one specific student
        List<List<String>> al = new ArrayList<>();
        Students result1 = null;
        Courses result2 = null;
        Scanner obj2 = new Scanner(System.in);
        System.out.println("Here is the list of Student: ");
        for(StudentsEnrolment studentsEnrolment: studentsEnrolmentList){
            System.out.println(studentsEnrolment.getStudents().getId() + " || " + studentsEnrolment.getStudents().getName());
        }
        String studentSearch = obj.nextLine();
        for (StudentsEnrolment studentsEnrolment: studentsEnrolmentList){
            if(studentsEnrolment.getStudents().getId().equalsIgnoreCase(studentSearch)){
                System.out.println(studentsEnrolment.getStudents().getName() + " || " + studentsEnrolment.getStudents().getBirthDate()
                        + " ||" + studentsEnrolment.getCourses().getCourseId() + " || " + studentsEnrolment.getCourses().getCourseName()
                        + " || " + studentsEnrolment.getCourses().getNumberOfCredit() + " || " + studentsEnrolment.getSemester());
            }
        }
        for (Students student : studentsList) {
            if (student.getId().equals(studentSearch))
                result1 = student;
        }

        if (result1 == null) {
            System.out.println("No student available with given ID.");
            return;
        }
        System.out.println("Enter the course code you want to update: ");
        String courseSearch = obj.nextLine();

        for (Courses course : coursesList) {
            if (course.getCourseId().equals(courseSearch))
                result2 = course;
        }
        if (result2 == null) {
            System.out.println("No course available with given ID.");
            return;
        }

        System.out.println("Enter the semester");
        String semSearch = obj2.nextLine();
        System.out.println(studentsEnrolmentList);
        if(!(verification(studentSearch,courseSearch))){
            StudentsEnrolment record = new StudentsEnrolment(result1, result2, semSearch);
            if (studentsEnrolmentList.contains(record)){
                System.out.println("Enrolment already existed");
                return;
            }
            al.add(Arrays.asList(studentSearch+ ", " + courseSearch + " , " + semSearch));
            studentsEnrolmentList.add(record);
            System.out.println("Do you want to save this course addition?" +
                    "[1] YES" +
                    "[2] NO ");
            String choice = obj.nextLine();
            switch (choice){
                case "1":{
                    System.out.println("Please enter file name");
                    String fileNameInputed = obj.nextLine();
                    saveFile("update", fileNameInputed, al);
                    break;
                }
                case "2": System.exit(0);
            }


    }}
    public void Delete() throws IOException {
        //this is Delete method that help user delete 1 specific course of student
        Students result1 = null;
        Courses result2 = null;
        Scanner obj2 = new Scanner(System.in);
        System.out.println(studentsEnrolmentList);
        System.out.println("Here is the list of Student: ");
        for(StudentsEnrolment studentsEnrolment: studentsEnrolmentList){
            System.out.println(studentsEnrolment.getStudents().getId() + " || " + studentsEnrolment.getStudents().getName());
        }
        String studentSearch = obj.nextLine();
        for (StudentsEnrolment studentsEnrolment: studentsEnrolmentList){
            if(studentsEnrolment.getStudents().getId().equalsIgnoreCase(studentSearch)){
                System.out.println(studentsEnrolment.getStudents().getName() + " || " + studentsEnrolment.getStudents().getBirthDate()
                        + " ||" + studentsEnrolment.getCourses().getCourseId() + " || " + studentsEnrolment.getCourses().getCourseName()
                        + " || " + studentsEnrolment.getCourses().getNumberOfCredit() + " || " + studentsEnrolment.getSemester());
            }
        }
        for (Students student : studentsList) {
            if (student.getId().equals(studentSearch))
                result1 = student;
        }

        if (result1 == null) {
            System.out.println("No student available with given ID.");
        }
        System.out.println("Enter the course code you want to update: ");
        String courseSearch = obj.nextLine();

        for (Courses course : coursesList) {
            if (course.getCourseId().equals(courseSearch))
                result2 = course;
        }
        if (result2 == null) {
            System.out.println("No course available with given ID.");
        }

        System.out.println("Enter the semester");

        String semSearch = obj2.nextLine();
        int i = 0;
        if(!(verification(studentSearch,courseSearch))){
            i = i+1;
            StudentsEnrolment record = new StudentsEnrolment(result1, result2, semSearch);
                studentsEnrolmentList.remove(getIndex(studentSearch,courseSearch));
                System.out.println("Remove Completely");

        }

//        System.out.println(record);


    }
    public void Display(String type){
        //this is display method, thí will popup when user want to print out the information of the enrollment
        String displayType = type;
        switch (displayType){
            case "students":{
                System.out.println("Here is the list of students: ");
                for(StudentsEnrolment studentsEnrolment: studentsEnrolmentList){
                    System.out.println(studentsEnrolment.getStudents().getId() + " || " + studentsEnrolment.getStudents().getName());
                }
                break;
            }
            case "courses":{
                System.out.println("Here is the list of courses: ");
                for (StudentsEnrolment studentsEnrolment: studentsEnrolmentList){
                    System.out.println(studentsEnrolment.getCourses().getCourseId() + " || " + studentsEnrolment.getCourses().getCourseName());
                }
                break;
            }
            case "sem":{
                System.out.println("Here is the list of semester: ");
                for( String sem: semester){
                    System.out.println(sem);
                }
                break;
            }
        }
    }
    public void GetOne() throws IOException {
        //This is the method which can help user to print specific information
        boolean flag =false;
        Scanner obj2 = new Scanner(System.in);
        System.out.println("Do you want to " +
                "[1] Print all courses for 1 student in 1 semester. " +
                "[2] Print all students of 1 course in 1 semester. " +
                "[3] Prints all courses offered in 1 semester");
        String result = obj2.nextLine();
        List<List<String>> al = new ArrayList<>();
        switch (result){
            case "1":{
                Display("students");
                System.out.println("Please enter Sid you want to print all courses ");
                String studentSearch = obj2.nextLine();
                for(StudentsEnrolment studentsEnrolment: studentsEnrolmentList){
                    if(studentsEnrolment.getStudents().getId().equals(studentSearch)){
                        String dataInput = studentsEnrolment.getCourses().getCourseName() + ", " + studentsEnrolment.getCourses().getCourseId();
                        System.out.println("This student has enrolled " + dataInput);
//                        saveInfo.add(dataInput);

                        al.add(Arrays.asList(dataInput));
                        flag = true;
                    }
                }

                if (!flag){
                    System.out.println("Your input is invalid");
                    break;
                }
                System.out.println("Do you want to save file?" +
                        "[1] Yes" +
                        "[2] No");
                String choice =obj2.nextLine();
                switch (choice){
                    case "1":{
                        System.out.println("Type the file name you want to save: ");
                        String csvFileName = obj2.nextLine();
                        if(checkFileExist(csvFileName)){
                            System.out.println("This file is exit in the system, do you want to override it? " +
                                    "[1] YES" +
                                    "[2] NO");
                            String overRideChoice = obj2.nextLine();
                            switch (overRideChoice){
                                case "1":{
                                    saveFile("student",csvFileName, al);
                                    System.out.println("Save Successfully Override to the file: " + csvFileName +".csv");
                                    break;
                                }
                                case "2":{
                                    System.exit(0);
                                    break;
                                }

                            }
                        }else {
                            saveFile("student",csvFileName, al);
                            System.out.println("Save Successfully to the file: " + csvFileName +".csv");
                        }

                    }
                }


                break;
            }
            case "2": {
                Display("courses");
                System.out.println("Please enter Course code you want to print all students enrolled: ");
                String courseSearch = obj2.nextLine();
                for(StudentsEnrolment studentsEnrolment: studentsEnrolmentList){
                    if(studentsEnrolment.getCourses().getCourseId().equals(courseSearch)){
                        String dataInput = studentsEnrolment.getStudents().getName() + ", " + studentsEnrolment.getStudents().getId();
                        System.out.println("Student enrolled: " + dataInput);
                        flag = true;
                        al.add(Arrays.asList(dataInput));

                    }
                }

                if (!flag){
                    System.out.println("Your input is invalid");
                    System.exit(0);
                }
                System.out.println("Do you want to save file?" +
                        "[1] Yes" +
                        "[2] No");
                String choice =obj2.nextLine();
                switch (choice){
                    case "1":{
                        System.out.println("Type the file name you want to save: ");
                        String csvFileName = obj2.nextLine();
                        if(checkFileExist(csvFileName)){
                            System.out.println("This file is exit in the system, do you want to override it? " +
                                    "[1] YES" +
                                    "[2] NO");
                            String overRideChoice = obj2.nextLine();
                            switch (overRideChoice){
                                case "1":{
                                    saveFile("student",csvFileName, al);
                                    System.out.println("Save Successfully Override to the file: " + csvFileName +".csv");
                                    break;
                                }
                                case "2":{
                                    System.exit(0);
                                    break;
                                }

                            }
                        }else {
                            saveFile("course",csvFileName, al);
                            System.out.println("Save Successfully to the file: " + csvFileName +".csv");
                        }

                    }
                }
                break;
            }
            case "3":{
                Display("sem");
                System.out.println("Please enter Semester you want to print all courses: ");
                String semSearch = obj2.nextLine();
                for (StudentsEnrolment studentsEnrolment: studentsEnrolmentList){
                    if(studentsEnrolment.getSemester().equals(semSearch)){
                        String dataInput = studentsEnrolment.getCourses().getCourseId() + ", " +studentsEnrolment.getCourses().getCourseName();
                        System.out.println("Courses in this sem: "+ dataInput);
                        al.add(Arrays.asList(dataInput));
                        flag = true;
                    }
                }

                if(!flag){
                    System.out.println("Your input is invalid");
                    System.exit(0);
                }
                System.out.println("Do you want to save file?" +
                        "[1] Yes" +
                        "[2] No");
                String choice =obj2.nextLine();
                switch (choice){
                    case "1":{
                        System.out.println("Type the file name you want to save: ");
                        String csvFileName = obj2.nextLine();
                        if(checkFileExist(csvFileName)){
                            System.out.println("This file is exit in the system, do you want to override it? " +
                                    "[1] YES" +
                                    "[2] NO");
                            String overRideChoice = obj2.nextLine();
                            switch (overRideChoice){
                                case "1":{
                                    saveFile("student",csvFileName, al);
                                    System.out.println("Save Successfully Override to the file: " + csvFileName +".csv");
                                    break;
                                }
                                case "2":{
                                    System.exit(0);
                                    break;
                                }

                            }
                        }else {
                            saveFile("sem",csvFileName, al);
                            System.out.println("Save Successfully to the file: " + csvFileName +".csv");
                        }

                    }
                }
                break;
            }
        }




    }
    public void GetAll(){
        //this is display method, thí will popup when user want to print out the information of the enrollment
        if(studentsEnrolmentList.isEmpty()){
        System.out.println("not thing in here");
    }else {
    System.out.println("Here is the report of Students");
    System.out.println(studentsEnrolmentList.toString());
    }
    }
    public static void main(String[] args) throws IOException {

        readFile();
        int option;
        Main system = new Main();
        do {
            welcome();
            option = option();
            switch (option){
                case 1: {
                    System.out.println("Do you want to display all students ( type 1 ) or specific information( type 2): ");
                    Scanner obj = new Scanner(System.in);
                    int choice = obj.nextInt();
                    switch (choice){
                        case 1: {
                            system.GetAll();
                            break;
                        }
                        case 2:{
                            system.GetOne();
                        }


                    }
                    break;
                }
                case 2:{
                    system.Add();
                    break;
                }
                case 3:{
                    system.Delete();
                    break;
                }
                case 4:{
                    system.Update();
                    break;
                }
                case 5:{
                    System.exit(0);
                }
                default:{
                    System.out.println("Your choice not found");
                    break;
                }
            }
        }while (option !=0);


    }
}
