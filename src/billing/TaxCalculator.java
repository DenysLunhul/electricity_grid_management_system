package billing;

interface TaxCalculator {
    double calculateTax(double baseCost, double taxRate);
}