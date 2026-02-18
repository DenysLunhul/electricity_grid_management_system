package billing;

import java.util.ArrayList;

public class SingleTariffReading extends Reading{
    private final double reading;
    public SingleTariffReading(String date, double reading){
        super(date);
        this.reading = reading;
    }

    public double getReading(){
        return this.reading;
    }

    @Override
    public ArrayList<Double> getTotalReading() {
        ArrayList<Double> res = new ArrayList<>();
        res.add(this.getReading());
        return res;
    }

    @Override
    public void display() {
        System.out.println("Date: " + this.getDate() + " Reading: " + this.getReading());
    }
}