package com.epam.onlinestore.entity;

public class ReceiptLine {

    private long amount;

    public ReceiptLine(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ReceiptLine{" +
                "amount=" + amount +
                '}';
    }
}
