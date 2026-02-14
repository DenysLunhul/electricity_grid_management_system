package billing;

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
    public double getTotalReading() {
        return this.reading;
    }

    @Override
    public void display() {
        System.out.println("Date: " + this.getDate() + " Reading: " + this.getReading());
    }
}