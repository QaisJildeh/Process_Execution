public class Processor {
    private final int id;
    private boolean availability;
    private Task task;
    private Clock clock;

    private int initialClock;

    public Processor(int id){
        this.id = id;
        availability = true;
        clock = new Clock();
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
        this.task = task;
    }

    public void workOnTask(){
        try{
            if(isAvailable()){
                initialClock = clock.getCurrentClockCycle();
            }

            if(clock.getCurrentClockCycle() - initialClock < task.getExecutionTime()){
                clock.tick();
            }
            else if(clock.getCurrentClockCycle() - initialClock == task.getExecutionTime()){
                task = null;
                setAvailability(true);
            }
        } catch(Exception e){
            System.out.println("Trying to work on task that does not exist is invalid!!");
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