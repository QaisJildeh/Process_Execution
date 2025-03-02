import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private static Scheduler schedulerInstance;
    private static List<Processor> processors;
    private static List<Task> tasks;

    private Scheduler(){
        processors = new ArrayList<Processor>();
        tasks = new ArrayList<Task>();
    }

    public static synchronized Scheduler getScheduler(){
        if(schedulerInstance == null){
            schedulerInstance = new Scheduler();
        }

        return schedulerInstance;
    }

    public static void setTasks(List<Task> tasks){
        Scheduler.tasks.addAll(tasks);
    }

    public static void setProcessors(List<Processor> processors){
        Scheduler.processors.addAll(processors);
    }

    public static void assignJob(){
        for(Processor pr : processors){
            if(tasks.isEmpty()){
                break;
            }
            if(pr.isAvailable()){
                pr.assignTask(tasks.removeLast());
                pr.setAvailability(false);
            }
        }
    }

    @Override
    public String toString(){
        return "This is the scheduler class.\nNumber of processors: " + processors.size() + "\nNumber of tasks: " + tasks.size();
    }
}