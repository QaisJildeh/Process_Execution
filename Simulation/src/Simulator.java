import java.io.File;
import java.util.Scanner;

public class Simulator {
    private static Simulator simulatorInstance;

    private Simulator(){
        initializeEnvironment();
//        try{
//            File file = new File("./input.txt");
//            scanner = new Scanner(file);
//
//            while(scanner.hasNextLine()){
//                System.out.println(scanner.nextLine());
//            }
//
//            scanner.close();
//        } catch(Exception e){
//            System.out.println("File does not exist!");
//        }

        File file = new File("Process_Execution/Simulation/src/input.txt");

        try (Scanner scanner = new Scanner(file)) { // Try-with-resources ensures automatic closing
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println("File does not exist!");
        }
    }

    public static synchronized Simulator getInstance(){
        if(simulatorInstance == null){
            simulatorInstance = new Simulator();
        }

        return simulatorInstance;
    }

    public void initializeEnvironment(){
        try{
            Scanner scanner1 = new Scanner(System.in);
            String filePath = scanner1.nextLine();
            File file = new File(filePath);
            Scanner scanner2 = new Scanner(file);

            while(scanner2.hasNextLine()){

            }

            scanner1.close();
            scanner2.close();
        } catch (Exception e) {
            System.out.println("File does not exist!");
        }
    }
}