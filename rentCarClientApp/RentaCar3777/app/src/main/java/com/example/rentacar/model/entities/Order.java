package com.example.rentacar.model.entities;

import java.util.Date;

/**
 * Created by שרה ויסברגר on 11/03/2018.
 */

public class Order
{
    private String orderClientId;
    private boolean orderKind;
    private String orderCarId;
    private Date rentalBegin;
    private Date rentalFinish;
    private float kilometerStartValue;
    private float kilometerEndValue;
    private boolean fuelFilling;
    private float amountFilling;
    private float orderPayment;
    private int orderId;
    //..................................constructors...............................................
    public Order() {
    }
    public Order(String clientId, boolean orderKind, String carId, Date rentalBegin, Date rentalFinish, float kilometerStartValue, float kilometerEndValue, boolean fuelFilling, float amountFilling, float payment, int orderId) {
        this.orderClientId = clientId;
        this.orderKind = orderKind;
        this.orderCarId = carId;
        this.rentalBegin = rentalBegin;
        this.rentalFinish = rentalFinish;
        this.kilometerStartValue = kilometerStartValue;
        this.kilometerEndValue = kilometerEndValue;
        this.fuelFilling = fuelFilling;
        this.amountFilling = amountFilling;
        this.orderPayment = payment;
        this.orderId = orderId;
    }
    //....................................getters ans setters......................................

    public String getOrderClientId() {
        return orderClientId;
    }

    public void setOrderClientId(String orderClientId) {
        this.orderClientId = orderClientId;
    }

    public boolean isOrderKind() {
        return orderKind;
    }

    public void setOrderKind(boolean orderKind) {
        this.orderKind = orderKind;
    }

    public String getOrderCarId() {
        return orderCarId;
    }

    public void setOrderCarId(String orderCarId) {
        this.orderCarId = orderCarId;
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

    public float getOrderPayment() {
        return orderPayment;
    }

    public void setOrderPayment(float orderPayment) {
        this.orderPayment = orderPayment;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }


    //............................Administration.......................................................


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (isOrderKind() != order.isOrderKind()) return false;
        if (Float.compare(order.getKilometerStartValue(), getKilometerStartValue()) != 0)
            return false;
        if (Float.compare(order.getKilometerEndValue(), getKilometerEndValue()) != 0) return false;
        if (isFuelFilling() != order.isFuelFilling()) return false;
        if (Float.compare(order.getAmountFilling(), getAmountFilling()) != 0) return false;
        if (Float.compare(order.getOrderPayment(), getOrderPayment()) != 0) return false;
        if (getOrderId() != order.getOrderId()) return false;
        if (getOrderClientId() != null ? !getOrderClientId().equals(order.getOrderClientId()) : order.getOrderClientId() != null)
            return false;
        if (getOrderCarId() != null ? !getOrderCarId().equals(order.getOrderCarId()) : order.getOrderCarId() != null)
            return false;
        if (getRentalBegin() != null ? !getRentalBegin().equals(order.getRentalBegin()) : order.getRentalBegin() != null)
            return false;
        return getRentalFinish() != null ? getRentalFinish().equals(order.getRentalFinish()) : order.getRentalFinish() == null;

    }

    @Override
    public int hashCode() {
        int result = getOrderClientId() != null ? getOrderClientId().hashCode() : 0;
        result = 31 * result + (isOrderKind() ? 1 : 0);
        result = 31 * result + (getOrderCarId() != null ? getOrderCarId().hashCode() : 0);
        result = 31 * result + (getRentalBegin() != null ? getRentalBegin().hashCode() : 0);
        result = 31 * result + (getRentalFinish() != null ? getRentalFinish().hashCode() : 0);
        result = 31 * result + (getKilometerStartValue() != +0.0f ? Float.floatToIntBits(getKilometerStartValue()) : 0);
        result = 31 * result + (getKilometerEndValue() != +0.0f ? Float.floatToIntBits(getKilometerEndValue()) : 0);
        result = 31 * result + (isFuelFilling() ? 1 : 0);
        result = 31 * result + (getAmountFilling() != +0.0f ? Float.floatToIntBits(getAmountFilling()) : 0);
        result = 31 * result + (getOrderPayment() != +0.0f ? Float.floatToIntBits(getOrderPayment()) : 0);
        result = 31 * result + getOrderId();
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderClientId='" + orderClientId + '\'' +
                ", orderKind=" + orderKind +
                ", orderCarId='" + orderCarId + '\'' +
                ", rentalBegin=" + rentalBegin +
                ", rentalFinish=" + rentalFinish +
                ", kilometerStartValue=" + kilometerStartValue +
                ", kilometerEndValue=" + kilometerEndValue +
                ", fuelFilling=" + fuelFilling +
                ", amountFilling=" + amountFilling +
                ", orderPayment=" + orderPayment +
                ", orderId=" + orderId +
                '}';
    }
}
