public class Clock {
    private static Clock singletonClock;
    private static int currentClockCycle;

    private Clock(){
        currentClockCycle = 1;
    }

    public static synchronized Clock getInstance(){
        if(singletonClock == null){
            singletonClock = new Clock();
        }

        return singletonClock;
    }

    public void tick(){
        currentClockCycle++;
    }

    public int getCurrentClockCycle(){
        return currentClockCycle;
    }

    @Override
    public String toString(){
        return "Current Clock: " + getCurrentClockCycle() + "\n";
    }
}