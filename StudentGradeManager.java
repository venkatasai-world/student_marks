import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Student {
    String name;
    double grade;

    Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class StudentGradeManager {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        int choice;

        do {
            displayMenu();
            choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent(students);
                    break;
                case 2:
                    displayStudents(students);
                    break;
                case 3:
                    showSummaryReport(students);
                    break;
                case 4:
                    System.out.println("\n📌 Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("⚠ Invalid choice. Please try again.");
            }
        } while (choice != 4);

        sc.close();
    }

    private static void displayMenu() {
        System.out.println("\n========= 📚 Student Grade Manager 📚 =========");
        System.out.println("1️⃣  Add Student");
        System.out.println("2️⃣  Display All Students");
        System.out.println("3️⃣  Show Summary Report");
        System.out.println("4️⃣  Exit");
        System.out.println("==============================================");
    }

    private static void addStudent(ArrayList<Student> students) {
        System.out.print("Enter student name: ");
        String name = sc.nextLine().trim();

        double grade;
        while (true) {
            grade = getDoubleInput("Enter student grade (0-100): ");
            if (grade < 0 || grade > 100) {
                System.out.println("❌ Invalid grade! Must be between 0 and 100.");
            } else {
                break;
            }
        }

        students.add(new Student(name, grade));
        System.out.println("✅ Student added successfully!");
    }

    private static void displayStudents(ArrayList<Student> students) {
        System.out.println("\n--- 📝 Student List ---");
        if (students.isEmpty()) {
            System.out.println("⚠ No student records available.");
        } else {
            System.out.printf("%-20s | %-6s\n", "Name", "Grade");
            System.out.println("------------------------------");
            for (Student s : students) {
                System.out.printf("%-20s | %.2f\n", s.name, s.grade);
            }
        }
    }

    private static void showSummaryReport(ArrayList<Student> students) {
        if (students.isEmpty()) {
            System.out.println("⚠ No data to summarize.");
            return;
        }

        double total = 0, max = Double.MIN_VALUE, min = Double.MAX_VALUE;
        String topStudent = "", lowStudent = "";

        for (Student s : students) {
            total += s.grade;
            if (s.grade > max) {
                max = s.grade;
                topStudent = s.name;
            }
            if (s.grade < min) {
                min = s.grade;
                lowStudent = s.name;
            }
        }

        double average = total / students.size();
        System.out.println("\n--- 📊 Summary Report ---");
        System.out.printf("Total Students : %d\n", students.size());
        System.out.printf("Average Grade  : %.2f\n", average);
        System.out.printf("Highest Grade  : %.2f (%s)\n", max, topStudent);
        System.out.printf("Lowest Grade   : %.2f (%s)\n", min, lowStudent);
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int num = Integer.parseInt(sc.nextLine().trim());
                return num;
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double num = Double.parseDouble(sc.nextLine().trim());
                return num;
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number.");
            }
        }
    }
}