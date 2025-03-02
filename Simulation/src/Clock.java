public class Clock {
    private int currentClockCycle;

    public Clock(){
        currentClockCycle = 0;
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