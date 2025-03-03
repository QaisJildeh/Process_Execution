public class Task {
    private final int id;
    private final int creationTime;
    private final int executionTime;
    private final int priority;
    private int creationRelativeToClock;

    public Task(int id, int creationTime, int executionTime, int priority){
        this.id = id;
        this.creationTime = creationTime;
        this.executionTime = executionTime;
        this.priority = priority;
        creationRelativeToClock = creationTime;
    }

    public Task(Task task){
        this.id = task.id;
        this.creationTime = task.creationTime;
        this.executionTime = task.executionTime;
        this.priority = task.priority;
        this.creationRelativeToClock = task.creationRelativeToClock;
    }

    public void setCreationRelativeToClock(int time){
        this.creationRelativeToClock = time;
    }

    public int getID(){
        return id;
    }

    public int getCreationTime(){
        return creationTime;
    }

    public int getExecutionTime(){
        return executionTime;
    }

    public int getPriority(){
        return priority;
    }

    public int getCreationRelativeToClock(){
        return creationRelativeToClock;
    }

    @Override
    public String toString(){
        return "Task " + id + ": {" + creationTime + "(" + creationRelativeToClock + "), " + executionTime + ", " + priority + "}";
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) {return true;}
        if(obj == null || getClass() != obj.getClass()) {return false;}
        Task task = (Task) obj;
        return id == task.getID();
    }

    @Override
    public int hashCode(){
        return ((Integer) id).hashCode();
    }
}