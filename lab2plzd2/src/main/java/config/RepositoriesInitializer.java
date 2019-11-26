package config;

import models.Role;
import models.User;
import models.Vehicle;
import models.VehicleType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import repositories.RoleRepository;
import repositories.UserRepository;
import repositories.VehicleRepository;
import repositories.VehicleTypeRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Configuration
public class RepositoriesInitializer {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    @Profile(ProfileNames.DATABASE)
    InitializingBean initializingBean(){
        return () -> {

            if(roleRepository.findAll().isEmpty() == true){
                Role roleUser = roleRepository.save(new Role(Role.Types.ROLE_USER));
                Role roleAdmin = roleRepository.save(new Role(Role.Types.ROLE_ADMIN));

                User user = new User("user", true);
                user.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                user.setPassword(passwordEncoder.encode("user"));

                User admin = new User("admin", true);
                admin.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
                admin.setPassword(passwordEncoder.encode("admin"));

                User test = new User("test", true);
                test.setRoles(new HashSet<>(Arrays.asList(roleAdmin, roleUser)));
                test.setPassword(passwordEncoder.encode("test"));

                userRepository.save(user);
                userRepository.save(admin);
                userRepository.save(test);
            }

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
                vehicle1.setPrice(12900f);
                vehicle1.setVehicleType(vehicleType);
                vehicleRepository.save(vehicle1);

                Vehicle vehicle2 = new Vehicle();
                vehicle2.setName("Jaguar");
                vehicle2.setModel("X - type");
                vehicle2.setProductionDate(new Date(106, 2, 21));
                vehicle2.setPrice(10500f);
                vehicle2.setVehicleType(vehicleType);
                vehicleRepository.save(vehicle2);

                Vehicle vehicle3 = new Vehicle();
                vehicle3.setName("Alfa Romeo");
                vehicle3.setModel("Pandion");
                vehicle3.setProductionDate(new Date(100, 8, 16));
                vehicle3.setPrice(400400f);
                vehicle3.setVehicleType(vehicleType);
                vehicleRepository.save(vehicle3);
            }
        };
    }
}
