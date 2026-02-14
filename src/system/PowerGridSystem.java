package system;
import consumers.*;
import meters.*;
import java.util.HashMap;

public class PowerGridSystem {
    private HashMap<String, Consumer> consumers;
    private static int nextConsumerId = 1;
    private static int nextMeterId = 1;

    public PowerGridSystem(){
        this.consumers = new HashMap<>();
    }

    public void addConsumer(String name, String address, String type) {
        String id = generateConsumerId();
        Consumer consumer;
        switch (type) {
            case "RESIDENTIAL":
                consumer = new ResidentialConsumer(id, name, address);
                break;
            case "COMMERCIAL":
                consumer = new CommercialConsumer(id, name, address);
                break;
            case "INDUSTRIAL":
                consumer = new IndustrialConsumer(id, name, address);
                break;
            default:
                return;
        }
        consumers.put(id, consumer);
    }

    public void removeConsumer(String id) {
        if (consumers.containsKey(id)) {
            consumers.remove(id);
            System.out.println("Consumer with ID: " + id + " was deleted");
            return;
        } else {
            System.out.println("Consumer with ID: " + id + " not found");
        }
    }

    public Consumer findConsumer(String id){
        if (consumers.containsKey(id)) {
            return consumers.get(id);
        } else {
            System.out.println("Consumer with ID: " + id + " not found");
            return null;
        }
    }

    public void displayAllConsumers() {
        if (consumers.isEmpty()) {
            System.out.println("No registered consumers.");
            return;
        }
        System.out.println("\n═══════════════════════════════════════════════════════════");
        System.out.println("                    CONSUMERS LIST");
        System.out.println("═══════════════════════════════════════════════════════════");
        for (Consumer consumer : consumers.values()) {
            System.out.println("\nID: " + consumer.getId());
            System.out.println("Name: " + consumer.getName());
            System.out.println("Address: " + consumer.getAddress());
            System.out.println("Type: " + consumer.getType());
            System.out.println("Meters count: " + consumer.getMeters().size());
            System.out.println("───────────────────────────────────────────────────────────");
        }
        System.out.println("\nTotal consumers: " + consumers.size());
        System.out.println("═══════════════════════════════════════════════════════════\n");
    }

    public void addMeterToConsumer(String consumerId, String meterType, String installationDate) {
        Consumer consumer = findConsumer(consumerId);

        if (consumer == null) {
            System.out.println("Consumer not found!");
            return;
        }

        String meterId = generateMeterId();
        Meter meter;

        switch (meterType) {
            case "SINGLE":
                meter = new SingleTariffMeter(meterId, installationDate);
                break;
            case "DUAL":
                meter = new DualTariffMeter(meterId, installationDate);
                break;
            case "TRIPLE":
                meter = new TripleTariffMeter(meterId, installationDate);
                break;
            default:
                System.out.println("Unknown meter type!");
                return;
        }

        consumer.addMeter(meter);
    }

    public void removeMeterFromConsumer(String consumerId, String meterId) {
        Consumer consumer = findConsumer(consumerId);

        if (consumer == null) {
            System.out.println("Consumer not found!");
            return;
        }

        consumer.removeMeter(meterId);
        System.out.println("Meter removed!");
    }

    public void displayConsumerMeters(String consumerId) {
        Consumer consumer = findConsumer(consumerId);
        if (consumer == null) {
            System.out.println("Consumer not found!");
            return;
        }
        if (consumer.getMeters().isEmpty()) {
            System.out.println("This consumer has no meters.");
            return;
        }
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("  METERS FOR: " + consumer.getName());
        System.out.println("═══════════════════════════════════════");
        for (Meter meter : consumer.getMeters()) {
            System.out.println("\nMeter ID: " + meter.getId());
            System.out.println("Type: " + meter.getClass().getSimpleName());
            System.out.println("Installation Date: " + meter.getInstallationDate());
            System.out.println("Readings count: " + meter.getReadingHistory().size());
            System.out.println("───────────────────────────────────────");
        }
        System.out.println("Total meters: " + consumer.getMeters().size());
        System.out.println("═══════════════════════════════════════\n");
    }

    private static String generateConsumerId() {
        return "ACC" + String.format("%05d", nextConsumerId++);
    }

    private static String generateMeterId() {
        return "MTR" + String.format("%05d", nextMeterId++);
    }
}