import java.util.List;

public class Scheduler {
    private static Scheduler signletonScheduler;
    private List<Processor> processors;

    private Scheduler(){

    }

    public static synchronized Scheduler getScheduler(){
        if(signletonScheduler == null){
            signletonScheduler = new Scheduler();
        }

        return  signletonScheduler;
    }
}