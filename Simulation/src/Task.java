public class Task {
    private final int id;
    private final int creationTime;
    private int executionTime;
    private final int priority;

    public Task(int id, int creationTime, int executionTime, int priority){
        this.id = id;
        this.creationTime = creationTime;
        setExecutionTime(executionTime);
        this.priority = priority;
    }

    public Task(Task task){
        this.id = task.id;
        this.creationTime = task.creationTime;
        setExecutionTime(task.executionTime);
        this.priority = task.priority;
    }

    public void setExecutionTime(int executionTime){
        this.executionTime = executionTime;
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

    @Override
    public String toString(){
        return "Task " + id + ": {" + creationTime + ", " + executionTime + ", " + priority + "}";
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