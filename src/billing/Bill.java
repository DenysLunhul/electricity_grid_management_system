package billing;

import consumers.Consumer;

import java.util.ArrayList;

public class Bill implements Displayable{
    private Consumer consumer;
    private String id;
    private String date;
    private String meterId;
    private ArrayList<Double> consumption;

    public Bill() {
    }

    public Bill(Consumer consumer, String id, String date, String meterId, ArrayList<Double> consumption){
        this.consumer = consumer;
        this.id = id;
        this.date = date;
        this.meterId = meterId;
        this.consumption = consumption;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    public void setConsumption(ArrayList<Double> consumption) {
        this.consumption = consumption;
    }

    private static class HeaderItem implements Displayable {
        private final String label;
        private final String value;

        private HeaderItem(String label, String value) {
            this.label = label;
            this.value = value;
        }

        @Override
        public void display() {
            System.out.println(label + ": " + value);
        }
    }

    public static class LineItem implements Displayable{
        private final String label;
        private final double amount;

        public LineItem(String label, double amount) {
            this.label = label;
            this.amount = amount;
        }

        @Override
        public void display() {
            System.out.printf("%-20s: %.2f%n", label, amount);
        }
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<Double> getConsumption() {
        return consumption;
    }

    public String getMeterId() {
        return meterId;
    }

    public double getTotalCost(double taxRate) {
        BillHelper helper = new BillHelper(this.consumer.getType().getPricePerKwh(), this.consumption, taxRate);
        return helper.getTotalCost();
    }

    public static class BillHelper {
        private final ArrayList<LineItem> lines;
        private final ArrayList<Double> consumption;
        private final double pricePerKwh;
        private final double taxRate;
        private double totalConsumption;
        private double totalCost;

        BillHelper(double pricePerKwh, ArrayList<Double> consumption, double taxRate) {
            this.pricePerKwh = pricePerKwh;
            this.consumption = consumption;
            this.taxRate = taxRate;
            this.lines = new ArrayList<>();
            recalculate();
        }

        public void recalculate() {
            totalConsumption = 0.0;
            int size = consumption == null ? 0 : consumption.size();

            lines.clear();

            if (size == 0) {
                lines.add(new LineItem("No Readings", 0.0));
            } else if (size == 1) {
                Double v0 = consumption.getFirst();
                double value = v0 == null ? 0.0 : v0;
                totalConsumption = value;
                lines.add(new LineItem("Reading", value));
            } else if (size == 2) {
                Double v0 = consumption.get(0);
                Double v1 = consumption.get(1);
                double day = v0 == null ? 0.0 : v0;
                double night = v1 == null ? 0.0 : v1;
                totalConsumption = day + (night * 0.5);
                lines.add(new LineItem("Day Reading", day));
                lines.add(new LineItem("Night Reading", night));
            } else if (size == 3) {
                Double v0 = consumption.get(0);
                Double v1 = consumption.get(1);
                Double v2 = consumption.get(2);
                double peak = v0 == null ? 0.0 : v0;
                double semiPeak = v1 == null ? 0.0 : v1;
                double night = v2 == null ? 0.0 : v2;
                totalConsumption = (peak * 1.6) + (semiPeak) + (night * 0.4);
                lines.add(new LineItem("Peak Reading", peak));
                lines.add(new LineItem("Semi-Peak Reading", semiPeak));
                lines.add(new LineItem("Night Reading", night));
            } else {
                for (Double value : consumption) {
                    if (value != null) {
                        totalConsumption += value;
                    }
                }
                lines.add(new LineItem("Total Readings", totalConsumption));
            }


            double baseCost = totalConsumption * pricePerKwh;
            TaxCalculator taxCalc = new TaxCalculator() {
                @Override
                public double calculateTax(double baseCost, double taxRate) {
                    return baseCost * (taxRate / 100);
                }
            };

            double taxAmount = taxCalc.calculateTax(baseCost, taxRate);
            totalCost = baseCost + taxAmount;

            lines.add(new LineItem("Base Cost", baseCost));
            lines.add(new LineItem("Tax", taxAmount));
            lines.add(new LineItem("Total", totalCost));
        }

        public ArrayList<LineItem> getLines() {
            return this.lines;
        }

        public double getTotalConsumption() {
            return totalConsumption;
        }

        public double getTotalCost() {
            return totalCost;
        }
    }

    @Override
    public void display() {
        System.out.println("═══════════════════════════════════════");
        System.out.println("        ELECTRICITY BILL");
        System.out.println("═══════════════════════════════════════");
        new HeaderItem("Bill Number", this.getId()).display();
        new HeaderItem("Date", this.getDate()).display();
        System.out.println("───────────────────────────────────────");
        new HeaderItem("Consumer", this.consumer.getName()).display();
        new HeaderItem("Address", this.consumer.getAddress()).display();
        new HeaderItem("Consumer Type", this.consumer.getType().toString()).display();
        new HeaderItem("Meter ID", this.meterId).display();
        new HeaderItem("Tariff", this.consumer.getType().getPricePerKwh() + " UAH/kWh").display();
        System.out.println("───────────────────────────────────────");
        BillHelper helper = new BillHelper(this.consumer.getType().getPricePerKwh(), this.consumption, 5);
        for (LineItem line : helper.getLines()) {
            line.display();
        }
        System.out.println("───────────────────────────────────────");
        System.out.printf("TOTAL AMOUNT: %.2f UAH%n", helper.getTotalCost());
        System.out.println("═══════════════════════════════════════");
    }
}
