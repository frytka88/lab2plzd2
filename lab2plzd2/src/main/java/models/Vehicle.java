package models;

import validators.Invalid;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

public class Vehicle {

    private long id;

    @NotBlank(message = "Name may not be null")
    @Size(min = 3, max = 15)
    @Invalid(ignore = true, myValue = {"Kia", "Ferrari"})
    private String name;

    @NotBlank(message = "Model may not be null")
    @Size(min = 3, max = 15)
    private String model;

    @Past(message = "Nie może być data z przyszłości")
    private Date productionDate;

    @NotNull
    @Min(100)
    private float price;

    private Boolean exclusive;

    @Valid
    private VehicleType vehicleType;

    public Vehicle() {
        this.vehicleType = new VehicleType();
        this.productionDate = new Date(); // ??
    }

    public Vehicle(long id, String model, String name, Date productionDate, float price, VehicleType vehicleType, Boolean exclusive) {
        this.id = id;
        this.model = model;
        this.name = name;
        this.productionDate = productionDate;
        this.price = price;
        this.vehicleType = vehicleType;
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

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Boolean getExclusive() {
        return exclusive;
    }

    public void setExclusive(Boolean exclusive) {
        this.exclusive = exclusive;
    }

}
