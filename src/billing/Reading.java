package billing;

import java.util.ArrayList;

public abstract class Reading {
    private final String date;

    public Reading(String date){
        this.date = date;
    }

    public String getDate(){
        return this.date;
    }

    public abstract ArrayList<Double> getTotalReading();
    public abstract void display();
}