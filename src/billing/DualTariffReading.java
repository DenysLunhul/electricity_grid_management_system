package billing;

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
    public double getTotalReading() {
        return this.dayReading + (this.nightReading * 0.5);
    }

    @Override
    public void display() {
        System.out.println("Date: " + this.getDate() + " Reading: "
                + "Day: " + this.getDayReading() +
                " Night: " + this.getNightReading());
    }
}