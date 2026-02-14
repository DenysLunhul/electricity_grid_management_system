package meters;
import billing.SingleTariffReading;
import java.time.LocalDate;

public class SingleTariffMeter extends Meter {
    public SingleTariffMeter(String id, String installationDate) {
        super(id, installationDate);
    }

    @Override
    public void updateReadings(double... values) {
        String currentDate = LocalDate.now().toString();
        SingleTariffReading reading = new SingleTariffReading(currentDate, values[0]);
        addReading(reading);
    }
}
