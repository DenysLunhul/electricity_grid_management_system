package services;

import billing.Bill;
import consumers.Consumer;
import meters.Meter;
import java.time.LocalDate;

public class BillingService {
    private static int idCounter = 1;

    public static Bill generateBill(Consumer consumer){
        String id = generateBillId();
        String date = LocalDate.now().toString();
        double totalConsumption = calculateTotalConsumption(consumer);
        double totalCost = calculateTotalCost(totalConsumption, consumer);
        return new Bill(consumer, id, date, totalConsumption, totalCost);
    }

    private static String generateBillId() {
        return "BILL" + String.format("%05d", idCounter++);
    }

    private static double calculateTotalConsumption(Consumer consumer){
        double totalConsumption = 0;
        for (Meter meter : consumer.getMeters()){
            totalConsumption += meter.calculateConsumption();
        }
        return totalConsumption;
    }

    private static double calculateTotalCost(double consumption, Consumer consumer){
        return consumption * consumer.getType().getPricePerKwh();
    }
}
