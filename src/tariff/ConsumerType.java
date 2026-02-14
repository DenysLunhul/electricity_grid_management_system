package tariff;

public enum ConsumerType {
    RESIDENTIAL(4.3),
    COMMERCIAL(11.6),
    INDUSTRIAL(14.1);

    private final double pricePerKwh;

    ConsumerType(double pricePerKwh) {
        this.pricePerKwh = pricePerKwh;
    }

    public double getPricePerKwh() {
        return pricePerKwh;
    }
}