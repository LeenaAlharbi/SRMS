import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class ReportThread extends Thread {              
    private StudentManager manager;

    public ReportThread(StudentManager manager) {
        this.manager = manager;
    }
    
    @Override 
    public void run(){
        System.out.println("=>>[[Report System]]: Generating Report in background..");
        try{
           Thread.sleep(2000);                    
           List<Student> list= manager.getAllStudents();  //نسخة من القائمه عشان نسوي عليه الحسابات
           int count= list.size();
           double totalGPA= 0;                           //نخزن كل معدلات الطلاب
           for(Student s: list){                        //نمر على كل طالب ناخذ معدله ونجمعه على المجموع الكلي
             totalGPA += s.getGpa();
            }
           double avg;
           if(count > 0){                               //نحسب المتوسط :المجموع الكلي مقسوما على عدد الطلاب
             avg= totalGPA / count;
            }else{                                      //لو مافيه طلاب ما نقسم على صفر نخلي المعدل صفر
             avg= 0.0;
            }
           PrintWriter writer= new PrintWriter(new FileWriter("final_report.txt"));//نفتح ملف جديد نكتب فيه التقرير
                   //كتابه داخل الملف
           writer.println("==Student Management System Repoort==");
           writer.println("Total student: "+ count);
           writer.println("Average GPA: "+ String.format("%.2f", avg));
           writer.println("===============================================");
           for(Student s: list){
             writer.println("- "+ s.getFirstName()+ " (GPA: "+ s.getGpa()+ ") ");
            }
           writer.close();     //نقفل الملف
           System.out.println("=>> [[Report]]: Report is ready!..Check 'report.txt' ");
    
        }catch(Exception e){
         System.out.println("Error in Report: "+ e.getMessage());    
        } 
    }
}