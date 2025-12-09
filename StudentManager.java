// هذا الكلاس مسؤول عن إدارة الطلاب داخل ArrayList
import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private List<Student> students = new ArrayList<>();

    // إضافة طالب للقائمة
    public void addStudent(Student s) {
        students.add(s); // إضافة بسيطة
    }

    // البحث عن طالب باستخدام رقم ID
    public Student findById(String id) {
        for (Student s : students) {
            if (s.getId().equals(id))
                return s;
        }
        return null; // لو ما لقاه
    }
    
    //تعديل أو تحديث بيانات الطالب
    public boolean updateStudentGpa(String id, double newGpa) {
        Student s = findById(id);
        if (s != null) {
            try {
                s.setGpa(newGpa);
                return true;
            }catch (Exception e){
                System.out.println("Eror apdating GPA: " + e.getMessage());
            }
        }
        return false;
    }

    // حذف طالب
    public boolean removeStudent(String id) {
        Student s = findById(id);
        if (s != null) {
            students.remove(s);
            return true;
        }
        return false;
    }

    // عرض جميع الطلاب
    public List<Student> getAllStudents() {
        return students;
    }
}