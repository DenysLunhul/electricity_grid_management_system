package meters;
import billing.Reading;
import java.util.ArrayList;

public abstract class Meter {
    private final String id;
    private final String installationDate;
    private final ArrayList<Reading> readingHistory;

    public Meter(String id, String installationDate){
        this.id = id;
        this.installationDate = installationDate;
        this.readingHistory = new ArrayList<>();
    }


    public String getId(){
        return this.id;
    }

    public String getInstallationDate(){
        return this.installationDate;
    }

    public ArrayList<Reading> getReadingHistory(){
        return this.readingHistory;
    }

    protected void addReading(Reading reading) {
        this.getReadingHistory().add(reading);
    }

    public Reading getLastReading(){
        if (this.getReadingHistory().isEmpty()){
            return null;
        }
        return this.getReadingHistory().getLast();
    }

    public abstract ArrayList<Double> calculateConsumption();

    public abstract void updateReadings(double... values);
}
