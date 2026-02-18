package services;

import billing.Bill;
import consumers.Consumer;
import meters.Meter;
import java.time.LocalDate;
import java.util.ArrayList;

public class BillingService {
    private static int idCounter = 1;

    public static Bill generateBill(Consumer consumer, String meterID){
        String id = generateBillId();
        String date = LocalDate.now().toString();
        ArrayList<Double> totalConsumption = calculateTotalConsumption(consumer, meterID);
        return new Bill(consumer, id, date, meterID, totalConsumption);
    }

    private static String generateBillId() {
        return "BILL" + String.format("%05d", idCounter++);
    }

    private static ArrayList<Double> calculateTotalConsumption(Consumer consumer, String meterID){
        ArrayList<Double> totalConsumption = null;
        for (Meter meter : consumer.getMeters()){
            if (meter.getId().equals(meterID)){
                totalConsumption = meter.calculateConsumption();
            };
        }
        return totalConsumption;
    }
}
