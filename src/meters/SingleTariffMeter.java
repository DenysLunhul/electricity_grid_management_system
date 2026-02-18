package meters;
import billing.SingleTariffReading;
import java.time.LocalDate;
import java.util.ArrayList;

public class SingleTariffMeter extends Meter {
    public SingleTariffMeter(String id, String installationDate) {
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
        }
        else{
            res.add(this.getReadingHistory().getLast().getTotalReading().getFirst() - this.getReadingHistory().get(getReadingHistory().size() - 2).getTotalReading().getFirst());
        }
        return res;
    }

    @Override
    public void updateReadings(double... values) {
        String currentDate = LocalDate.now().toString();
        SingleTariffReading reading = new SingleTariffReading(currentDate, values[0]);
        addReading(reading);
    }
}
