import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulator {
    private static Simulator simulatorInstance;
    private static String filePath = "C:\\Users\\jilde\\Desktop\\Atypon\\Tasks\\Process_Execution_Task\\Process_Execution\\input.txt";
    private static List<Task> tasks;
    private static List<Processor> processors;
    private static Scheduler scheduler;

    private Simulator(){
        initializeEnvironment();
    }

    public static synchronized Simulator getSimulator(){
        if(simulatorInstance == null){
            simulatorInstance = new Simulator();
        }

        return simulatorInstance;
    }

    public void setFilePath(String filePath){
        Simulator.filePath = filePath;
    }

    public String getFilePath(){
        return filePath;
    }

    public Scheduler getScheduler(){
        return Scheduler.getScheduler();
    }

    public void initializeProcessors(){
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

    public void initializeTasks(){
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

    public void initializeEnvironment(){
        initializeProcessors();
        initializeTasks();
        scheduler = Scheduler.getScheduler();
    }
}