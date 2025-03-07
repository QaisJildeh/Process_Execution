import java.util.ArrayList;
import java.util.List;

public class Processor {
    private final int id;
    private boolean availability;
    private Task task;
    private Clock clock;
    private List<Task> finishedTasks;
    private List<Integer> startingClock;
    private List<Integer> endingClock;
    private int initialClock;

    public Processor(int id){
        this.id = id;
        availability = true;
        clock = Clock.getInstance();
        finishedTasks = new ArrayList<Task>();
        startingClock = new ArrayList<Integer>();
        endingClock = new ArrayList<Integer>();
        initialClock = 0;
    }

    void setAvailability(boolean availability){
        this.availability = availability;
    }

    public int getID(){
        return id;
    }

    public boolean isAvailable(){
        return availability;
    }

    public List<Task> getFinishedTasks(){
        return finishedTasks;
    }

    public List<Integer> getStartingClock(){
        return startingClock;
    }

    public List<Integer> getEndingClock(){
        return endingClock;
    }

    public void assignTask(Task task){
        setAvailability(false);
        this.task = task;
    }

    public void workOnTask(){
        if(isAvailable()){
            initialClock = clock.getCurrentClockCycle();
        }

        if(isAvailable() && task != null){
            setAvailability(false);
        }

        if(task != null && clock.getCurrentClockCycle() - initialClock >= task.getExecutionTime()){
            finishedTasks.add(task);
            startingClock.add(initialClock);
            endingClock.add(clock.getCurrentClockCycle());
            task = null;
            availability = true;
            initialClock = clock.getCurrentClockCycle();
        }
    }

    @Override
    public String toString(){
        return "Processor " + id + " Availability: " + availability;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) {return true;}
        if(obj == null || getClass() != obj.getClass()) {return false;}
        Processor microProcessor = (Processor) obj;
        return id == microProcessor.getID();
    }

    @Override
    public int hashCode(){
        return ((Integer) id).hashCode();
    }
}