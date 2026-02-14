package meters;
import billing.TripleTariffReading;
import java.time.LocalDate;

public class TripleTariffMeter extends Meter{
    public TripleTariffMeter(String id, String installationDate) {
        super(id, installationDate);
    }

    @Override
    public void updateReadings(double... values) {
        String currentDate = LocalDate.now().toString();
        TripleTariffReading reading = new TripleTariffReading(currentDate, values[0], values[1], values[2]);
        addReading(reading);
    }
}
