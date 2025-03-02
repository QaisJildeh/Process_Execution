public class Clock {
    private int currentClockCycle;
    private int totalClockCycles;

    public Clock(){
        currentClockCycle = 0;
        totalClockCycles = 0;
    }

    public void setCurrentClockCycle(int clockCycleNumber){
        currentClockCycle = clockCycleNumber;
    }

    public void setTotalClockCycles(int total){
        totalClockCycles = total;
    }

    public int getCurrentClockCycle(){
        return currentClockCycle;
    }

    public int getTotalClockCycles(){
        return totalClockCycles;
    }
}