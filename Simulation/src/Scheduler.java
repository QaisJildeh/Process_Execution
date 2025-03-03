import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Scheduler {
    private static Scheduler schedulerInstance;
    private static List<Processor> processors;
    private static List<Task> tasks;
    private static Clock clock;

    private Scheduler(){
        processors = new ArrayList<Processor>();
        tasks = new ArrayList<Task>();
        clock = Clock.getInstance();
    }

    public static synchronized Scheduler getScheduler(){
        if(schedulerInstance == null){
            schedulerInstance = new Scheduler();
        }

        return schedulerInstance;
    }

    public void setTasks(List<Task> tasks){
        Scheduler.tasks.addAll(tasks);
    }

    public void setProcessors(List<Processor> processors){
        Scheduler.processors.addAll(processors);
    }

    public void assignJob(){
        if(tasks.isEmpty()) {return;}
        for(Processor pr : processors){
            if(pr.isAvailable() && !tasks.isEmpty()){
                if(clock.getCurrentClockCycle() < tasks.getLast().getCreationTime()) {return;}
                pr.assignTask(tasks.removeLast());
            }
        }
    }

    public boolean isDone(){
        boolean tasksDone = tasks.isEmpty();
        boolean processorsAvailable = true;
        for(Processor pr : processors){
            if(!pr.isAvailable()){
                processorsAvailable = false;
                break;
            }
        }

        return tasksDone && processorsAvailable;
    }

    public void sortTaskPriority(){
        updateTasksRelativeTime();
        tasks.sort(Comparator.comparing(Task::getCreationRelativeToClock).reversed()
                .thenComparing(Task::getPriority)
                .thenComparing(Task::getExecutionTime));
    }

    public void updateTasksRelativeTime(){
        for(Task t : tasks){
            if(t.getCreationTime() <= clock.getCurrentClockCycle()){
                t.setCreationRelativeToClock(0);
            }
        }
    }

    @Override
    public String toString(){
        return "This is the scheduler class.\nNumber of processors: " + processors.size() + "\nNumber of tasks: " + tasks.size();
    }
}