package meters;
import billing.DualTariffReading;
import java.time.LocalDate;
import java.util.ArrayList;

public class DualTariffMeter extends Meter{
    public DualTariffMeter(String id, String installationDate) {
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
            res.add(this.getReadingHistory().getLast().getTotalReading().getLast());
        }
        else{
            res.add(this.getReadingHistory().getLast().getTotalReading().getFirst() - this.getReadingHistory().get(getReadingHistory().size() - 2).getTotalReading().getFirst());
            res.add(this.getReadingHistory().getLast().getTotalReading().getLast() - this.getReadingHistory().get(getReadingHistory().size() - 2).getTotalReading().getLast());
        }
        return res;
    }

    @Override
    public void updateReadings(double... values) {
        String currentDate = LocalDate.now().toString();
        DualTariffReading reading = new DualTariffReading(currentDate, values[0], values[1]);
        addReading(reading);
    }
}
