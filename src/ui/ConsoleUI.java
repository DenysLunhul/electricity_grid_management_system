package ui;

import consumers.Consumer;
import meters.Meter;
import system.PowerGridSystem;
import services.BillingService;
import billing.Bill;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner;
    private final PowerGridSystem system;

    public ConsoleUI() {
        this.scanner = new Scanner(System.in);
        this.system = new PowerGridSystem();
    }

    public void start() {
        boolean running = true;

        while (running) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    handleAddConsumer();
                    break;
                case 2:
                    handleRemoveConsumer();
                    break;
                case 3:
                    handleDisplayConsumers();
                    break;
                case 4:
                    handleAddMeter();
                    break;
                case 5:
                    handleRemoveMeter();
                    break;
                case 6:
                    handleDisplayMeters();
                    break;
                case 7:
                    handleEnterReading();
                    break;
                case 8:
                    handleGenerateBill();
                    break;
                case 0:
                    running = false;
                    System.out.println("Program finished it's work");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }

        scanner.close();
    }

    private void displayMainMenu() {
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("    POWER GRID MANAGEMENT SYSTEM");
        System.out.println("═══════════════════════════════════════");
        System.out.println("1. Add Consumer");
        System.out.println("2. Remove Consumer");
        System.out.println("3. Display All Consumers");
        System.out.println("4. Add Meter to Consumer");
        System.out.println("5. Remove Meter from Consumer");
        System.out.println("6. Display Consumer Meters");
        System.out.println("7. Enter Meter Reading");
        System.out.println("8. Generate Bill");
        System.out.println("0. Exit");
        System.out.println("═══════════════════════════════════════");
        System.out.print("Enter choice: ");
    }

    private void handleAddConsumer() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter address: ");
        String address = scanner.nextLine();

        System.out.println("Select type:");
        System.out.println("1. RESIDENTIAL");
        System.out.println("2. COMMERCIAL");
        System.out.println("3. INDUSTRIAL");
        System.out.print("Choice: ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine();

        String type;
        switch (typeChoice) {
            case 1: type = "RESIDENTIAL"; break;
            case 2: type = "COMMERCIAL"; break;
            case 3: type = "INDUSTRIAL"; break;
            default:
                System.out.println("Invalid type!");
                return;
        }

        system.addConsumer(name, address, type);
        System.out.println("Consumer added successfully!");
    }

    private void handleRemoveConsumer() {
        System.out.print("Enter consumer ID: ");
        String id = scanner.nextLine();
        system.removeConsumer(id);
    }

    private void handleDisplayConsumers() {
        system.displayAllConsumers();
    }

    private void handleAddMeter() {
        System.out.print("Enter consumer ID: ");
        String consumerId = scanner.nextLine();

        System.out.println("Select meter type:");
        System.out.println("1. SINGLE");
        System.out.println("2. DUAL");
        System.out.println("3. TRIPLE");
        System.out.print("Choice: ");
        int typeChoice = scanner.nextInt();
        scanner.nextLine();

        String meterType;
        switch (typeChoice) {
            case 1: meterType = "SINGLE"; break;
            case 2: meterType = "DUAL"; break;
            case 3: meterType = "TRIPLE"; break;
            default:
                System.out.println("Invalid type!");
                return;
        }

        System.out.print("Enter installation date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        system.addMeterToConsumer(consumerId, meterType, date);
        System.out.println("Meter added successfully!");
    }

    private void handleRemoveMeter() {
        System.out.print("Enter consumer ID: ");
        String consumerId = scanner.nextLine();

        System.out.print("Enter meter ID: ");
        String meterId = scanner.nextLine();

        system.removeMeterFromConsumer(consumerId, meterId);
    }

    private void handleDisplayMeters() {
        System.out.print("Enter consumer ID: ");
        String consumerId = scanner.nextLine();
        system.displayConsumerMeters(consumerId);
    }

    private void handleEnterReading() {
        System.out.print("Enter consumer ID: ");
        String consumerId = scanner.nextLine();

        Consumer consumer = system.findConsumer(consumerId);
        if (consumer == null) return;

        System.out.print("Enter meter ID: ");
        String meterId = scanner.nextLine();

        Meter meter = consumer.getMeterById(meterId);
        if (meter == null) {
            System.out.println("Meter not found!");
            return;
        }

        String meterType = meter.getClass().getSimpleName();

        if (meterType.equals("SingleTariffMeter")) {
            System.out.print("Enter reading: ");
            double value = scanner.nextDouble();
            scanner.nextLine();
            meter.updateReadings(value);
        } else if (meterType.equals("DualTariffMeter")) {
            System.out.print("Enter day reading: ");
            double day = scanner.nextDouble();
            System.out.print("Enter night reading: ");
            double night = scanner.nextDouble();
            scanner.nextLine();
            meter.updateReadings(day, night);
        } else if (meterType.equals("TripleTariffMeter")) {
            System.out.print("Enter peak reading: ");
            double peak = scanner.nextDouble();
            System.out.print("Enter half-peak reading: ");
            double halfPeak = scanner.nextDouble();
            System.out.print("Enter night reading: ");
            double night = scanner.nextDouble();
            scanner.nextLine();
            meter.updateReadings(peak, halfPeak, night);
        }

        System.out.println("Reading added successfully!");
    }

    private void handleGenerateBill() {
        System.out.print("Enter consumer ID: ");
        String consumerId = scanner.nextLine();

        Consumer consumer = system.findConsumer(consumerId);
        if (consumer == null) return;

        Bill bill = BillingService.generateBill(consumer);
        bill.display();
    }
}