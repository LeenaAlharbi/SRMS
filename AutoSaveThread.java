import java.util.List;
public class AutoSaveThread extends Thread{           //نعرف الكلاس كخيط يشتغل بالخلفيه مستقلا
    private StudentManager manager;                   //عشان نوصل لبيانات اللي بالذاكره
    private volatile boolean running= true;           //متغير للتحكم volatileتضمن الخيط يشوف امر الايقاف
    
    public AutoSaveThread(StudentManager manger){
        this.manager= manger;
        setDaemon(true);             //نحول الخيط لخادم مجرد ما يقفل البرنامج يموت الخيط معه
    }
 @Override
    public void run(){                               //تتنفذ اذا 
        System.out.println("=>> [[AutoSave System]]: Started..");
        
        while(running){
            try{
                Thread.sleep(30000);                //ينتظر 30 ثانية
                if(running){                        //عشان يرحع يتأكد من ان البرنامج يشتغل
                    List<Student> allStudents= manager.getAllStudents(); //جلب بيانات 
                    FileHandler.saveToCSV("autosave.csv", allStudents);  //حفظ في ملف
                    System.out.println("=>> [[AutoSave]]: Data saved successfully..");
                }
            }catch (Exception e){                  
                System.out.println("Error in AutoSave: "+ e.getMessage());
            }
        }
    } 
    public void stopService(){                      //زر ايقاف 
        running= false;
    }
}