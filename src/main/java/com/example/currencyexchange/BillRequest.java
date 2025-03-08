package com.example.currencyexchange;

public class BillRequest {
    private double totalAmount;
    private boolean employee;
    private boolean affiliate;
    private int customerTenure;
    private boolean grocery;
    private String originalCurrency;

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isEmployee() {
        return employee;
    }

    public void setEmployee(boolean employee) {
        this.employee = employee;
    }

    public boolean isAffiliate() {
        return affiliate;
    }

    public void setAffiliate(boolean affiliate) {
        this.affiliate = affiliate;
    }

    public int getCustomerTenure() {
        return customerTenure;
    }

    public void setCustomerTenure(int customerTenure) {
        this.customerTenure = customerTenure;
    }

    public String getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(String originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    public boolean isGrocery() {
        return grocery;
    }

    public void setGrocery(boolean grocery) {
        this.grocery = grocery;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    private String targetCurrency;

    // Getters and setters
}