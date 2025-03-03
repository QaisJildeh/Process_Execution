import java.io.File;
import java.util.*;

public class Simulator {
    private static Simulator simulatorInstance;
    private static String filePath = "C:\\Users\\jilde\\Desktop\\Atypon\\Tasks\\Process_Execution_Task\\Process_Execution\\input.txt";
    private static List<Task> tasks;
    private static List<Processor> processors;
    private static Scheduler scheduler;
    private static Clock clock;

    private Simulator(){
        initializeEnvironment();
    }

    public static synchronized Simulator getSimulator(){
        if(simulatorInstance == null){
            simulatorInstance = new Simulator();
        }

        return simulatorInstance;
    }

    public static void setFilePath(String filePath){
        Simulator.filePath = filePath;
    }

    public static String getFilePath(){
        return filePath;
    }

    public static Scheduler getScheduler(){
        return Scheduler.getScheduler();
    }

    public static void initializeProcessors(){
        Scanner scanner = new Scanner(System.in);
        processors = new ArrayList<Processor>();
        System.out.println("Enter number of processors: ");
        int numberOfProcessors = scanner.nextInt();
        while(numberOfProcessors < 1){
            System.out.println("Invalid number of processors, Enter a number greater than zero: ");
            numberOfProcessors = scanner.nextInt();
        }
        for(int i = 1; i <= numberOfProcessors; i++){
            processors.add(new Processor(i));
        }
        scanner.close();
    }

    public static void initializeTasks(){
        tasks = new ArrayList<Task>();
        try{
            Scanner fileScanner = new Scanner(new File(filePath));
            int lines = fileScanner.nextInt();
            for(int i = 1; i <= lines; i++){
                tasks.add(new Task(i, fileScanner.nextInt(), fileScanner.nextInt(), fileScanner.nextInt()));
            }

            fileScanner.close();
        } catch(Exception e){
            System.out.println("File does not exist!");
        }
    }

    public static void initializeScheduler(){
        scheduler = Scheduler.getScheduler();
        scheduler.setTasks(tasks);
        scheduler.setProcessors(processors);
    }

    public static void initializeEnvironment(){
        initializeProcessors();
        initializeTasks();
        initializeScheduler();
        clock = Clock.getInstance();
    }

    public static void run(){
        while(!scheduler.isDone()){
            scheduler.sortTaskPriority();
            scheduler.assignJob();
            for(Processor p : processors){
                p.workOnTask();
            }

            clock.tick();
        }
    }

    public static void printReport(){
        int totalSimulationTime = 0;
        for(Processor p : processors){
            for(int end : p.getEndingClock()){
                if(end > totalSimulationTime){
                    totalSimulationTime = end;
                }
            }
        }

        if(totalSimulationTime == 0){
            totalSimulationTime = Clock.getInstance().getCurrentClockCycle() - 1;
        }

        System.out.println("Simulation Report");
        System.out.println("Total Simulation Time: " + totalSimulationTime + " cycles");
        System.out.println("----------------------------------------");

        for(Processor processor : processors){
            System.out.println("Processor " + processor.getID() + ":");
            List<Task> tasks = processor.getFinishedTasks();
            List<Integer> starts = processor.getStartingClock();
            List<Integer> ends = processor.getEndingClock();

            if(tasks.isEmpty()){
                System.out.println("  No tasks executed.");
            }
            else{
                System.out.println("  Tasks Executed:");
                for(int i = 0; i < tasks.size(); i++){
                    Task task = tasks.get(i);
                    int start = starts.get(i);
                    int end = ends.get(i);
                    System.out.println("    Task " + task.getID() + ": Start=" + start + ", End=" + end + " (Duration=" + (end - start) + ")");
                }
            }

            int busyTime = 0;
            for(int i = 0; i < ends.size(); i++){
                busyTime += ends.get(i) - starts.get(i);
            }

            int idleTime = totalSimulationTime - busyTime;
            double utilization = (busyTime/(double) totalSimulationTime) * 100;

            System.out.println("  Total Busy Time: " + busyTime + " cycles");
            System.out.println("  Total Idle Time: " + idleTime + " cycles");
            System.out.println("  Utilization: " + String.format("%.2f", utilization) + "%\n");
        }
    }

    @Override
    public String toString(){
        return "This is the simulator class";
    }
}