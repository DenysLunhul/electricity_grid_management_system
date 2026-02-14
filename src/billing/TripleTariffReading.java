package billing;

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
    public double getTotalReading() {
        return (this.peakReading * 1.5) + this.secondPeakReading + (this.nightReading * 0.4);
    }

    @Override
    public void display() {
        System.out.println("Date: " + this.getDate() + " Reading: " +
                "Peak: " + this.getPeakReading() +
                " Second Peak: " + this.getSecondPeakReading() +
                " Night: " + this.getNightReading());
    }
}