package billing;

import java.util.ArrayList;

public class DualTariffReading extends Reading{
    private final double dayReading;
    private final double nightReading;
    public DualTariffReading(String date, double dayReading, double nightReading){
        super(date);
        this.dayReading = dayReading;
        this.nightReading = nightReading;
    }

    public double getDayReading(){
        return this.dayReading;
    }

    public double getNightReading(){
        return this.nightReading;
    }

    @Override
    public ArrayList<Double> getTotalReading() {
        ArrayList<Double> res = new ArrayList<>();
        res.add(this.getDayReading());
        res.add(this.getNightReading());
        return res;
    }

    @Override
    public void display() {
        System.out.println("Date: " + this.getDate() + " Reading: "
                + "Day: " + this.getDayReading() +
                " Night: " + this.getNightReading());
    }
}