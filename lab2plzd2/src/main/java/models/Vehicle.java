package models;

import java.util.Date;

public class Vehicle {

    private long id;
    private String model;
    private String name;
    private Date productionDate;
    private float price;
    private Boolean exclusive;

    public Vehicle() {

    }

    public Vehicle(long id, String model, String name, Date productionDate, float price, Boolean exclusive) {
        this.id = id;
        this.model = model;
        this.name = name;
        this.productionDate = productionDate;
        this.price = price;
        this.exclusive = exclusive;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Boolean getBroken() {
        return exclusive;
    }

    public void setBroken(Boolean exclusive) {
        exclusive = exclusive;
    }
}
