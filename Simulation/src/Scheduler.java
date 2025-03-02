import java.util.ArrayList;
import java.util.Comparator;
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

        return signletonScheduler;
    }

    public void setTasks(List<Task> tasks){
        Scheduler.tasks.addAll(tasks);
    }

    public void setProcessors(List<Processor> processors){
        Scheduler.processors.addAll(processors);
    }

    public void sortTaskPriority(){
        tasks.sort(Comparator.comparing(Task::getCreationTime).reversed()
                .thenComparing(Task::getPriority)
                .thenComparing(Task::getExecutionTime));
    }

    @Override
    public String toString(){
        return "This is the scheduler class.\nNumber of processors: " + processors.size() + "\nNumber of tasks: " + tasks.size();
    }
}