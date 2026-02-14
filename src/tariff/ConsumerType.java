package tariff;

public enum ConsumerType {
    RESIDENTIAL(4),
    COMMERCIAL(10),
    INDUSTRIAL(8);

    private final double pricePerKwh;

    ConsumerType(double pricePerKwh) {
        this.pricePerKwh = pricePerKwh;
    }

    public double getPricePerKwh() {
        return pricePerKwh;
    }
}