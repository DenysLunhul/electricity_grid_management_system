package billing;

import java.util.ArrayList;

public class TripleTariffReading extends Reading{
    private final double peakReading;
    private final double secondPeakReading;
    private final double nightReading;
    public TripleTariffReading(String date, double peakReading, double secondPeakReading, double nightReading){
        super(date);
        this.peakReading = peakReading;
        this.secondPeakReading = secondPeakReading;
        this.nightReading = nightReading;
    }

    public double getPeakReading() {
        return peakReading;
    }

    public double getSecondPeakReading() {
        return secondPeakReading;
    }

    public double getNightReading() {
        return nightReading;
    }

    @Override
    public ArrayList<Double> getTotalReading() {
        ArrayList<Double> res = new ArrayList<>();
        res.add(this.getPeakReading());
        res.add(this.getSecondPeakReading());
        res.add(this.getNightReading());
        return res;
    }

    @Override
    public void display() {
        System.out.println("Date: " + this.getDate() + " Reading: " +
                "Peak: " + this.getPeakReading() +
                " Second Peak: " + this.getSecondPeakReading() +
                " Night: " + this.getNightReading());
    }
}