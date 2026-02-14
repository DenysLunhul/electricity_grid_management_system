package billing;

public abstract class Reading {
    private final String date;

    public Reading(String date){
        this.date = date;
    }

    public String getDate(){
        return this.date;
    }

    public abstract double getTotalReading();
    public abstract void display();
}