package models;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "vehicleTypes")
public class VehicleType {

    @Min(0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public VehicleType(){}

    public VehicleType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public VehicleType(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
