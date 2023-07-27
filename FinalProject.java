import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FinalProject {
    //used for formatting the menu when taking input
    public static void print_lines() {
        System.out.println();
        for (int i = 0; i<15; i++){
        System.out.print(" ");
    }
    }
    //use id_checker wherever you need to check the input for valid id format
    public static boolean id_checker(String id){
        Pattern pattern = Pattern.compile("%c%c%d%d%d%d\n");
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }
    public static void enterFacultyInformation(ArrayList<Person> array) {
        Scanner scanner = new Scanner(System.in);
        Faculty temp_holder = new Faculty();
        System.out.print("Enter the faculty info:\n");
        print_lines();
        System.out.print("Name of the faculty:");
        temp_holder.setFull_name(scanner.nextLine());
        print_lines();
        System.out.print("ID:");
        String temp_id = scanner.nextLine();
        //while the id doesn't match the pattern we will continue to ask
        if (id_checker(temp_id)){
            temp_holder.setId(temp_id);
        }
        else {
            while (!id_checker(temp_id)) {
                System.out.println("Invalid IF format. Must be LetterLetterDigitDigitDigitDigit");
            }
        }
        }


    public static void main(String[] args) {
        //Test code goes here
        Scanner scanner = new Scanner(System.in);

        //testing code
        ArrayList<Person> people_list = new ArrayList<>(100);
        people_list.add(new Student("earl", "id9084", 2.4, 1));
        people_list.add(new Student("Johanna", "id9484", 4.0, 6));
        people_list.add(new Student("Davis", "id9544", 3.1, 9));


        /*for (Person P: people_list){
            if (P.getClass().equals(Student.class)){
                student_list.add((Student) P);
            }
        }*/
        int choices;
        do {
            System.out.println("Choose one of the options: ");
            System.out.println("1- Enter the information of a faculty");
            System.out.println("2- Enter the information of a student");
            System.out.println("3- Print tuition invoice for a student");
            System.out.println("4- Print faculty information");
            System.out.println("5- Enter the information of a staff member");
            System.out.println("6- Print the information of a staff member");
            System.out.println("7- Delete a person");
            System.out.println("8- Exit Program");
            choices = scanner.nextInt();
            switch (choices) {
                case 1:
                    enterFacultyInformation(people_list);
                    break;
            /*case 2:
                enterStudentInformation();
                break;
            case 3:
                printTuitionInvoice();
                break;
            case 4:
                printFacultyInformation();
                break;
            case 5:
                enterStaffInformation();
                break;
            case 6:
                printStaffInformation();
                break;
            case 7:
                deletePerson();
                break;
                case 8:
                    exitSequence();
                    break;*/
                default:
                    System.out.println("Invalid choice. Please enter a valid option (1-8).");
            }
            scanner.reset();
        }
        while (choices != 8);
    }
    }


    //---------------------------
    abstract class Person {
        public String getFull_name() {
            return full_name;
        }

        public void setFull_name(String full_name) {
            this.full_name = full_name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        private String id;
        private String full_name;

        public abstract void print();

        public static void dash_attack() {
            for (int i = 0; i < 70; i++) {
                System.out.print("-");
            }
        }

    }

    class Student extends Person{
        private double gpa;
        private int credits;

        public Student(String NAME, String ID, double GPA, int credits) {
            setFull_name(NAME);
            setId(ID);
            setcredits(credits);
            setGpa(GPA);
        }

        public Student() {
        }

        public double find_discount() {
            if (getGpa() >= 3.85) {
                return 0.25;
            } else return 0.00;
        }

        public double getGpa() {
            return gpa;
        }

        public void setGpa(double gpa) {
            this.gpa = gpa;
        }

        public int getcredits() {
            return credits;
        }

        public void setcredits(int credits) {
            this.credits = credits;
        }

        @Override
        public void print() {
            double discount = this.find_discount();
            if (discount > 0) {
                discount = Math.round(discount * 100.0) / 100.0;
            }
            double net_total = getcredits() * 236.45 + 52;
            double total_price = net_total - (discount * net_total);
            double rounded_total = Math.round(total_price * 100) / 100.0;
            System.out.println("\n\n" + "Here is the tuition invoice for " + getFull_name() + ":\n\n");
            dash_attack();
            System.out.println();
            System.out.printf("%s", getFull_name());
            System.out.printf("%28s", getId());
            System.out.println("\nCredit credits: " + getcredits() + " ($236.45/credit hour)");
            System.out.print("Fees: $52\n\n\n");
            System.out.print("Total payment (after discount): $");
            System.out.printf("%,.2f", rounded_total);
            System.out.printf("%28s", "($" + discount + " discount applied)");
            System.out.println();
            dash_attack();
        }

        static Comparator<Student> gpa_compare = new Comparator<>(){
            @Override
            public int compare(Student student1, Student student2) {
                return Double.compare(student2.getGpa(),student1.getGpa());
        }
        };
        static Comparator<Student>  name_compare = new Comparator<>(){
            @Override
            public int compare(Student student1, Student student2) {
                return student1.getFull_name().compareTo(student2.getFull_name());
            }
        };
    }

    //---------------------------
    abstract class Employee extends Person {
        public String getDepartment() {
            return department;
        }
        public void setDepartment(String department) {
            this.department = department;
        }
        private String department;
    }

    class Staff extends Employee {

        public Staff(String NAME, String ID, String DEPTMT, String STATUS) {
            setFull_name(NAME);
            setId(ID);
            setDepartment(DEPTMT);
            setStatus(STATUS);
        }
        public Staff(){

        }

        public String getStatus() {
            return status;
        }
        public void setStatus(String status) {
            this.status = status;
        }
        private String status;
        @Override
        public void print() {
            dash_attack();
            System.out.print(getFull_name());
            System.out.printf("%28s\n", getId());
            System.out.print(getDepartment()+ ", "+getStatus());
            dash_attack();
        }
    }

    class Faculty extends Employee {
        public Faculty(String NAME, String ID, String DEPTMT, String RANK) {
            setFull_name(NAME);
            setId(ID);
            setDepartment(DEPTMT);
            setRank(RANK);
        }
        public Faculty(){

        }



        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        private String rank;
        @Override
        public void print() {
            dash_attack();
            System.out.print(getFull_name());
            System.out.printf("%28s\n", getId());
            System.out.print(getDepartment()+ ", "+getRank());
            dash_attack();
        }
    }



