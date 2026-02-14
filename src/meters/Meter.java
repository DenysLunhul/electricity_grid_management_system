package meters;
import billing.Reading;
import java.util.ArrayList;

public abstract class Meter {
    private final String id;
    private final String installationDate;
    private ArrayList<Reading> readingHistory;

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
        return this.getReadingHistory().getLast();
    }

    public double calculateConsumption(){
        if (this.getReadingHistory().isEmpty()){
            return 0;
        }
        if (this.getReadingHistory().size() == 1){
            return this.getReadingHistory().getFirst().getTotalReading();
        }
        return this.getReadingHistory().getLast().getTotalReading() -
                this.getReadingHistory().get(readingHistory.size() - 2).getTotalReading();
    }

    public abstract void updateReadings(double... values);
}
