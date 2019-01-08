package com.example.rentacar.Model.entities;

/**
 * Created by שרה ויסברגר on 11/03/2018.
 */

public class Model {
    private String modelCode;
    private String factoryName;
    private String modelName;
    private String engineCapacity;
    private Gearbox gearbox;
    private String seats;
    //..................................constructors...............................................
    public Model() {
    }
    public Model(String modelCode, String factoryName, String modelName, String engineCapacity, Gearbox gearbox, String seats) {
        this.modelCode = modelCode;
        this.factoryName = factoryName;
        this.modelName = modelName;
        this.engineCapacity = engineCapacity;
        this.gearbox = gearbox;
        this.seats = seats;
    }
    //....................................getters ans setters......................................
    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }


    public String getFactoryName() {
        return factoryName;
    }

    public Model(String modelCode) {
        this.modelCode = modelCode;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }


    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }



    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
    //............................Administration.......................................................
    @Override
    public String toString() {
        return "Model{" +
                "modelCode=" + modelCode +
                ", factoryName='" + factoryName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", engineCapacity=" + engineCapacity +
                ", gearbox=" + gearbox +
                ", seats=" + seats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Model model = (Model) o;

        if (getModelCode() != null ? !getModelCode().equals(model.getModelCode()) : model.getModelCode() != null)
            return false;
        if (getFactoryName() != null ? !getFactoryName().equals(model.getFactoryName()) : model.getFactoryName() != null)
            return false;
        if (getModelName() != null ? !getModelName().equals(model.getModelName()) : model.getModelName() != null)
            return false;
        if (getEngineCapacity() != null ? !getEngineCapacity().equals(model.getEngineCapacity()) : model.getEngineCapacity() != null)
            return false;
        if (getGearbox() != model.getGearbox()) return false;
        return getSeats() != null ? getSeats().equals(model.getSeats()) : model.getSeats() == null;

    }

    @Override
    public int hashCode() {
        int result = getModelCode() != null ? getModelCode().hashCode() : 0;
        result = 31 * result + (getFactoryName() != null ? getFactoryName().hashCode() : 0);
        result = 31 * result + (getModelName() != null ? getModelName().hashCode() : 0);
        result = 31 * result + (getEngineCapacity() != null ? getEngineCapacity().hashCode() : 0);
        result = 31 * result + (getGearbox() != null ? getGearbox().hashCode() : 0);
        result = 31 * result + (getSeats() != null ? getSeats().hashCode() : 0);
        return result;
    }
}
