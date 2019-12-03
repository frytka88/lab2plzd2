package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import validators.Invalid;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@AllArgsConstructor
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

    @ManyToMany(fetch = FetchType.LAZY) //LAZY powoduje dociągnięcie tych elementów dopiero wtedy, gdy są używane
    private List<Accessory> accessories;

    public Vehicle() {
        this.vehicleType = new VehicleType();
        this.productionDate = new Date(); // ??
        this.accessories = new ArrayList<>();
    }
}
