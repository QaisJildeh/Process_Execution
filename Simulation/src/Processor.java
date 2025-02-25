public class Processor {
    private final int id;
    private boolean availability;

    public Processor(int id){
        this.id = id;
        availability = true;
    }

    void setAvailability(boolean availability){
        this.availability = availability;
    }

    public int getID(){
        return id;
    }

    public boolean isAvailable(){
        return availability;
    }
}