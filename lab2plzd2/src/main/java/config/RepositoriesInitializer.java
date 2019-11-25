package config;

import models.Vehicle;
import models.VehicleType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import repositories.VehicleRepository;
import repositories.VehicleTypeRepository;
import java.util.Date;

@Configuration
public class RepositoriesInitializer {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Bean
    InitializingBean initializingBean(){
        return () -> {

            if (vehicleTypeRepository.findAll().isEmpty()){

                VehicleType vehicleType = new VehicleType("SEDAN");
                vehicleTypeRepository.save(vehicleType);
                vehicleTypeRepository.save(new VehicleType("COUPE"));
                vehicleTypeRepository.save(new VehicleType("CABRIOLET"));
                vehicleTypeRepository.save(new VehicleType("SUV"));

                Vehicle vehicle1 = new Vehicle();
                vehicle1.setName("Alfa Romeo");
                vehicle1.setModel("Giulia");
                vehicle1.setProductionDate(new Date(118, 6, 1));
                vehicle1.setPrice(21500f);
                vehicle1.setVehicleType(vehicleType);
                vehicleRepository.save(vehicle1);

                Vehicle vehicle2 = new Vehicle();
                vehicle2.setName("Jaguar");
                vehicle2.setModel("X - type");
                vehicle2.setProductionDate(new Date(106, 2, 21));
                vehicle2.setPrice(28500f);
                vehicle2.setVehicleType(vehicleType);
                vehicleRepository.save(vehicle2);

                Vehicle vehicle3 = new Vehicle();
                vehicle3.setName("Alfa Romeo");
                vehicle3.setModel("Pandion");
                vehicle3.setProductionDate(new Date(100, 8, 16));
                vehicle3.setPrice(5400400f);
                vehicle3.setVehicleType(vehicleType);
                vehicleRepository.save(vehicle3);

                Vehicle vehicle4 = new Vehicle();
                vehicle4.setName("Volswagen");
                vehicle4.setModel("Golf");
                vehicle4.setProductionDate(new Date(100, 8, 16));
                vehicle4.setPrice(160000f);
                vehicle4.setVehicleType(vehicleType);
                vehicleRepository.save(vehicle4);

                Vehicle vehicle5 = new Vehicle();
                vehicle5.setName("Mercedes-Benz");
                vehicle5.setModel("A-Class");
                vehicle5.setProductionDate(new Date(100, 8, 16));
                vehicle5.setPrice(200000f);
                vehicle5.setVehicleType(vehicleType);
                vehicleRepository.save(vehicle5);

                Vehicle vehicle6 = new Vehicle();
                vehicle6.setName("Mercedes-Benz");
                vehicle6.setModel("G-Class");
                vehicle6.setProductionDate(new Date(100, 8, 16));
                vehicle6.setPrice(900000f);
                vehicle6.setVehicleType(vehicleType);
                vehicleRepository.save(vehicle6);
            }
        };
    }
}
