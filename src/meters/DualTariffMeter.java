package meters;
import billing.DualTariffReading;
import java.time.LocalDate;

public class DualTariffMeter extends Meter{
    public DualTariffMeter(String id, String installationDate) {
        super(id, installationDate);
    }

    @Override
    public void updateReadings(double... values) {
        String currentDate = LocalDate.now().toString();
        DualTariffReading reading = new DualTariffReading(currentDate, values[0], values[1]);
        addReading(reading);
    }
}
