package models;

import validators.Invalid;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name may not be null")
    @Size(min = 3, max = 15)
    @Invalid(ignore = true, myValue = {"Kia", "Ferrari"})
    private String name;

    @Column(name = "model", nullable = false)
    @NotBlank(message = "Model may not be null")
    @Size(min = 3, max = 15)
    private String model;

    @Column(name = "production_Date")
    @Past(message = "Nie może być data z przyszłości")
    private Date productionDate;

    @Column(name = "price")
    @NotNull
    @Min(100)
    private Float price;

    @Column(name = "exclusive")
    private Boolean exclusive;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", nullable = false)
    private VehicleType vehicleType;

    public Vehicle() {
        this.vehicleType = new VehicleType();
        this.productionDate = new Date(); // ??
    }

    public Vehicle(long id, String model, String name, Date productionDate, Float price, VehicleType vehicleType, Boolean exclusive) {
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
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
