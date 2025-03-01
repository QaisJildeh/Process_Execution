public class Task {
    private int id;
    private int creationTime;
    private int executionTime;
    private int priority;

    public Task(int id, int creationTime, int executionTime, int priority){
        this.id = id;
        setCreationTime(creationTime);
        setExecutionTime(executionTime);
        this.priority = priority;
    }

    public void setCreationTime(int creationTime){
        this.creationTime = creationTime;
    }

    public void setExecutionTime(int executionTime){
        this.executionTime = executionTime;
    }

    public int getId(){
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

    @Override
    public String toString(){
        return "Task " + id + ": {" + creationTime + ", " + executionTime + ", " + priority + "}";
    }
}