package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "vehicleTypes")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleType {

    @Min(0)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public VehicleType(String name) {
        this.name = name;
    }
}
