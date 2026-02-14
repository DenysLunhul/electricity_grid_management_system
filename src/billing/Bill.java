package billing;

import consumers.Consumer;

public class Bill {
    private final Consumer consumer;
    private final String id;
    private final String date;
    private final double consumption;
    private final double payCost;

    public Bill(Consumer consumer, String id, String date, double consumption, double payCost){
        this.consumer = consumer;
        this.id = id;
        this.date = date;
        this.consumption = consumption;
        this.payCost = payCost;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public double getConsumption() {
        return consumption;
    }

    public void display() {
        System.out.println("═══════════════════════════════════════");
        System.out.println("        ELECTRICITY BILL");
        System.out.println("═══════════════════════════════════════");
        System.out.println("Bill Number: " + this.id);
        System.out.println("Date: " + this.date);
        System.out.println("───────────────────────────────────────");
        System.out.println("Consumer: " + this.consumer.getName());
        System.out.println("Address: " + this.consumer.getAddress());
        System.out.println("Consumer Type: " + this.consumer.getType());
        System.out.println("───────────────────────────────────────");
        System.out.println("Consumption: " + String.format("%.2f", this.consumption) + " kWh");
        System.out.println("Tariff: " + this.consumer.getType().getPricePerKwh() + " UAH/kWh");
        System.out.println("───────────────────────────────────────");
        System.out.println("TOTAL AMOUNT: " + String.format("%.2f", this.payCost) + " UAH");
        System.out.println("═══════════════════════════════════════");
    }
}
