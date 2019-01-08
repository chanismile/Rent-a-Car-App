package com.example.rentacar.Model.entities;

/**
 * Created by שרה ויסברגר on 11/03/2018.
 */

public class Car {
    private String carBranchNo;
    private String model;
    private String kilometer;
    private String carId;
    //..................................constructors...............................................
    public Car() {
    }

    public Car(String branchNo, String model, String kilometer, String carId) {
        this.carBranchNo = branchNo;
        this.model = model;
        this.kilometer = kilometer;
        this.carId = carId;
    }
    //....................................getters ans setters......................................

    public String getBranchNo() {
        return carBranchNo;
    }

    public void setBranchNo(String branchNo) {
        this.carBranchNo = branchNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getKilometer() {
        return kilometer;
    }

    public void setKilometer(String kilometer) {
        this.kilometer = kilometer;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    //............................Administration.......................................................


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (getBranchNo() != null ? !getBranchNo().equals(car.getBranchNo()) : car.getBranchNo() != null)
            return false;
        if (getModel() != null ? !getModel().equals(car.getModel()) : car.getModel() != null)
            return false;
        if (getKilometer() != null ? !getKilometer().equals(car.getKilometer()) : car.getKilometer() != null)
            return false;
        return getCarId() != null ? getCarId().equals(car.getCarId()) : car.getCarId() == null;

    }

    @Override
    public int hashCode() {
        int result = getBranchNo() != null ? getBranchNo().hashCode() : 0;
        result = 31 * result + (getModel() != null ? getModel().hashCode() : 0);
        result = 31 * result + (getKilometer() != null ? getKilometer().hashCode() : 0);
        result = 31 * result + (getCarId() != null ? getCarId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "branchNo=" + carBranchNo +
                ", model=" + model +
                ", kilometer=" + kilometer +
                ", carId=" + carId +
                '}';
    }
}
