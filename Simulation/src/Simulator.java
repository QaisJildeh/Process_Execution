import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

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
//            System.out.println(scheduler.isDone());
        }
        System.out.println("Total Clock Cycles = " + clock.getCurrentClockCycle());
    }

    @Override
    public String toString(){
        return "This is the simulator class";
    }
}