package meters;
import billing.TripleTariffReading;
import java.time.LocalDate;
import java.util.ArrayList;

public class TripleTariffMeter extends Meter{
    public TripleTariffMeter(String id, String installationDate) {
        super(id, installationDate);
    }

    @Override
    public ArrayList<Double> calculateConsumption() {
        if (this.getReadingHistory().isEmpty()){
            return null;
        }
        ArrayList<Double> res = new ArrayList<>();
        if (this.getReadingHistory().size() == 1){
            res.add(this.getReadingHistory().getLast().getTotalReading().getFirst());
            res.add(this.getReadingHistory().getLast().getTotalReading().get(1));
            res.add(this.getReadingHistory().getLast().getTotalReading().getLast());
        }
        else{
            res.add(this.getReadingHistory().getLast().getTotalReading().getFirst() - this.getReadingHistory().get(getReadingHistory().size() - 2).getTotalReading().getFirst());
            res.add(this.getReadingHistory().getLast().getTotalReading().get(1) - this.getReadingHistory().get(getReadingHistory().size() - 2).getTotalReading().get(1));
            res.add(this.getReadingHistory().getLast().getTotalReading().getLast() - this.getReadingHistory().get(getReadingHistory().size() - 2).getTotalReading().getLast());
        }
        return res;
    }

    @Override
    public void updateReadings(double... values) {
        String currentDate = LocalDate.now().toString();
        TripleTariffReading reading = new TripleTariffReading(currentDate, values[0], values[1], values[2]);
        addReading(reading);
    }
}
