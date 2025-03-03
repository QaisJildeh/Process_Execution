import java.util.ArrayList;
import java.util.List;

public class Processor {
    private final int id;
    private boolean availability;
    private Task task;
    private Clock clock;
    private List<Task> finishedTasks;
    private int initialClock;

    public Processor(int id){
        this.id = id;
        availability = true;
        clock = Clock.getInstance();
        finishedTasks = new ArrayList<Task>();
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

    public void assignTask(Task task){
        setAvailability(false);
        this.task = task;
    }

    public void workOnTask(){
//        System.out.println(this.toString());
//        System.out.println("[1] Inital Clock = " + initialClock + " Total Clock Cycle = " + clock.getCurrentClockCycle());
//        System.out.println("Difference = " + (clock.getCurrentClockCycle() - initialClock));

        if(isAvailable()){
            initialClock = clock.getCurrentClockCycle();
            setAvailability(false);
//            System.out.println("[2] Inital Clock = " + initialClock + " Total Clock Cycle = " + clock.getCurrentClockCycle());
//            System.out.println("Difference = " + (clock.getCurrentClockCycle() - initialClock));
        }

        if(task != null && clock.getCurrentClockCycle() - initialClock >= task.getExecutionTime()){
            finishedTasks.add(task);
            System.out.println("FINISHED: " + task.toString());
            task = null;
            availability = true;
            initialClock = clock.getCurrentClockCycle();
//            System.out.println("[3] Inital Clock = " + initialClock + " Total Clock Cycle = " + clock.getCurrentClockCycle());
//            System.out.println("Difference = " + (clock.getCurrentClockCycle() - initialClock));
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