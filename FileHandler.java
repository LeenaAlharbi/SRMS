// هذا الكلاس يحفظ الطلاب بداخل ملف CSV ويقرأه
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    // حفظ الطلاب في ملف CSV
    public static void saveToCSV(String file, List<Student> list) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(file))) {

            for (Student s : list) {
                // تحويل الطالب إلى سطر CSV
                pw.println(
                        s.getId() + "," +
                        s.getFirstName() + "," +
                        s.getLastName() + "," +
                        s.getGpa() + "," +
                        s.getDepartment()
                );
            }

        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // تحميل الطلاب من CSV
    public static List<Student> loadFromCSV(String file) {
        List<Student> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] p = line.split(",");
                if (p.length < 5) continue; // لو ناقص بيانات نتخطى السطر

                String id = p[0];
                String fn = p[1];
                String ln = p[2];
                double gpa = Double.parseDouble(p[3]);
                String dept = p[4];

                // هنا نرجع الطالب كـ Undergraduate بشكل بسيط
                Address dummyAddress = new Address("N/A", "N/A", "000", "N/A");

                Student s = new UndergraduateStudent(id, fn, ln, gpa, dept, dummyAddress, 1);
                result.add(s);
            }

        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }

        return result;
    }
}