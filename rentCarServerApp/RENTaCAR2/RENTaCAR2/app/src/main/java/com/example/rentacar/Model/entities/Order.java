package com.example.rentacar.Model.entities;

import java.util.Date;

/**
 * Created by שרה ויסברגר on 11/03/2018.
 */

public class Order
{
    private int clientId;
    private boolean orderKind;
    private int modelCarId;
    private Date rentalBegin;
    private Date rentalFinish;
    private float kilometerStartValue;
    private float kilometerEndValue;
    private boolean fuelFilling;
    private float amountFilling;
    private float payment;
    private int orderId;
    //..................................constructors...............................................
    public Order() {
    }
    public Order(int clientId, boolean orderKind, int carId, Date rentalBegin, Date rentalFinish, float kilometerStartValue, float kilometerEndValue, boolean fuelFilling, float amountFilling, float payment, int orderId) {
        this.clientId = clientId;
        this.orderKind = orderKind;
        this.modelCarId = carId;
        this.rentalBegin = rentalBegin;
        this.rentalFinish = rentalFinish;
        this.kilometerStartValue = kilometerStartValue;
        this.kilometerEndValue = kilometerEndValue;
        this.fuelFilling = fuelFilling;
        this.amountFilling = amountFilling;
        this.payment = payment;
        this.orderId = orderId;
    }
    //....................................getters ans setters......................................
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public boolean isOrderKind() {
        return orderKind;
    }

    public void setOrderKind(boolean orderKind) {
        this.orderKind = orderKind;
    }

    public int getCarId() {
        return modelCarId;
    }

    public void setCarId(int carId) {
        this.modelCarId = carId;
    }

    public Date getRentalBegin() {
        return rentalBegin;
    }

    public void setRentalBegin(Date rentalBegin) {
        this.rentalBegin = rentalBegin;
    }

    public Date getRentalFinish() {
        return rentalFinish;
    }

    public void setRentalFinish(Date rentalFinish) {
        this.rentalFinish = rentalFinish;
    }

    public float getKilometerStartValue() {
        return kilometerStartValue;
    }

    public void setKilometerStartValue(float kilometerStartValue) {
        this.kilometerStartValue = kilometerStartValue;
    }

    public float getKilometerEndValue() {
        return kilometerEndValue;
    }

    public void setKilometerEndValue(float kilometerEndValue) {
        this.kilometerEndValue = kilometerEndValue;
    }

    public boolean isFuelFilling() {
        return fuelFilling;
    }

    public void setFuelFilling(boolean fuelFilling) {
        this.fuelFilling = fuelFilling;
    }

    public float getAmountFilling() {
        return amountFilling;
    }

    public void setAmountFilling(float amountFilling) {
        this.amountFilling = amountFilling;
    }

    public float getPayment() {
        return payment;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    //............................Administration.......................................................
    @Override
    public String toString() {
        return "Order{" +
                "clientId=" + clientId +
                ", orderKind=" + orderKind +
                ", carId=" + modelCarId +
                ", rentalBegin=" + rentalBegin +
                ", rentalFinish=" + rentalFinish +
                ", kilometerStartValue=" + kilometerStartValue +
                ", kilometerEndValue=" + kilometerEndValue +
                ", fuelFilling=" + fuelFilling +
                ", amountFilling=" + amountFilling +
                ", payment=" + payment +
                ", orderId=" + orderId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (getClientId() != order.getClientId()) return false;
        if (isOrderKind() != order.isOrderKind()) return false;
        if (getCarId() != order.getCarId()) return false;
        if (Float.compare(order.getKilometerStartValue(), getKilometerStartValue()) != 0)
            return false;
        if (Float.compare(order.getKilometerEndValue(), getKilometerEndValue()) != 0) return false;
        if (isFuelFilling() != order.isFuelFilling()) return false;
        if (Float.compare(order.getAmountFilling(), getAmountFilling()) != 0) return false;
        if (Float.compare(order.getPayment(), getPayment()) != 0) return false;
        if (getOrderId() != order.getOrderId()) return false;
        if (getRentalBegin() != null ? !getRentalBegin().equals(order.getRentalBegin()) : order.getRentalBegin() != null)
            return false;
        return getRentalFinish() != null ? getRentalFinish().equals(order.getRentalFinish()) : order.getRentalFinish() == null;

    }

    @Override
    public int hashCode() {
        int result = getClientId();
        result = 31 * result + (isOrderKind() ? 1 : 0);
        result = 31 * result + getCarId();
        result = 31 * result + (getRentalBegin() != null ? getRentalBegin().hashCode() : 0);
        result = 31 * result + (getRentalFinish() != null ? getRentalFinish().hashCode() : 0);
        result = 31 * result + (getKilometerStartValue() != +0.0f ? Float.floatToIntBits(getKilometerStartValue()) : 0);
        result = 31 * result + (getKilometerEndValue() != +0.0f ? Float.floatToIntBits(getKilometerEndValue()) : 0);
        result = 31 * result + (isFuelFilling() ? 1 : 0);
        result = 31 * result + (getAmountFilling() != +0.0f ? Float.floatToIntBits(getAmountFilling()) : 0);
        result = 31 * result + (getPayment() != +0.0f ? Float.floatToIntBits(getPayment()) : 0);
        result = 31 * result + getOrderId();
        return result;
    }
}
