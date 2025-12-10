import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        StudentManager manager = new StudentManager();

        // تشغيل خدمة الحفظ التلقائي
        AutoSaveThread autoSave = new AutoSaveThread(manager);
        autoSave.start();

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("===== Student Record Management System (SRMS) =====");

        while (running) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Undergraduate Student");
            System.out.println("2. Add Graduate Student");
            System.out.println("3. Update GPA");
            System.out.println("4. Remove Student");
            System.out.println("5. Show All Students");
            System.out.println("6. Save to CSV");
            System.out.println("7. Load from CSV");
            System.out.println("8. Generate Report");
            System.out.println("9. Exit");
            System.out.print(">> ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume extra newline

            switch (choice) {

                case 1: {
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("First Name: ");
                    String fn = sc.nextLine();
                    System.out.print("Last Name: ");
                    String ln = sc.nextLine();
                    System.out.print("GPA: ");
                    double gpa = sc.nextDouble(); sc.nextLine();
                    System.out.print("Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Year: ");
                    int year = sc.nextInt(); sc.nextLine();

                    Address addr = new Address("Street", "City", "000", "Country");

                    try {
                        Student u = new UndergraduateStudent(id, fn, ln, gpa, dept, addr, year);
                        manager.addStudent(u);
                        System.out.println("Added Successfully!");
                    } 
                    catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }

                case 2: {
                    System.out.print("ID: ");
                    String id = sc.nextLine();
                    System.out.print("First Name: ");
                    String fn = sc.nextLine();
                    System.out.print("Last Name: ");
                    String ln = sc.nextLine();
                    System.out.print("GPA: ");
                    double gpa = sc.nextDouble(); sc.nextLine();
                    System.out.print("Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Program: ");
                    String program = sc.nextLine();
                    System.out.print("Supervisor: ");
                    String sup = sc.nextLine();

                    Address addr = new Address("Street", "City", "000", "Country");

                    try {
                        Student g = new GraduateStudent(id, fn, ln, gpa, dept, addr, program, sup);
                        manager.addStudent(g);
                        System.out.println("Added Successfully!");
                    } 
                    catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }

                case 3: {
                    System.out.print("Enter ID: ");
                    String id = sc.nextLine();
                    System.out.print("New GPA: ");
                    double gpa = sc.nextDouble(); sc.nextLine();

                    if (manager.updateStudentGpa(id, gpa)) {
                        System.out.println("Updated Successfully!");
                    } else {
                        System.out.println("Student Not Found.");
                    }
                    break;
                }

                case 4: {
                    System.out.print("Enter ID: ");
                    String id = sc.nextLine();
                    if (manager.removeStudent(id)) {
                        System.out.println("Removed Successfully!");
                    } else {
                        System.out.println("Student Not Found.");
                    }
                    break;
                }

                case 5: {
                    System.out.println("\n--- All Students ---");
                    for (Student s : manager.getAllStudents()) {
                        System.out.println(s);
                    }
                    break;
                }

                case 6: {
                    FileHandler.saveToCSV("students.csv", manager.getAllStudents());
                    System.out.println("Saved to students.csv");
                    break;
                }

                case 7: {
                    manager.getAllStudents().clear();
                    manager.getAllStudents().addAll(FileHandler.loadFromCSV("students.csv"));
                    System.out.println("Loaded from students.csv");
                    break;
                }

                case 8: {
                    ReportThread report = new ReportThread(manager);
                    report.start();
                    break;
                }

                case 9: {
                    running = false;
                    autoSave.stopService();
                    System.out.println("Exiting system...");
                    break;
                }

                default:
                    System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
