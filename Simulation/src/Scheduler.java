import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private static Scheduler signletonScheduler;
    private static List<Processor> processors;
    private static List<Task> tasks;

    private Scheduler(){
        processors = new ArrayList<Processor>();
        tasks = new ArrayList<Task>();
    }

    public static synchronized Scheduler getScheduler(){
        if(signletonScheduler == null){
            signletonScheduler = new Scheduler();
        }

        return  signletonScheduler;
    }

    public void setTasks(List<Task> tasks){

    }
}