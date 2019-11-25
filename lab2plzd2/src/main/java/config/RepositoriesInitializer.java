package config;

import models.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import repositories.*;
import java.util.Date;
import java.util.Set;

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

            if(roleRepository.findAll().isEmpty() == true){
                try{
//                    Role roleUser = new Role(Role.Types.ROLE_USER);
//                    roleRepository.saveAndFlush(roleUser);
//                    Role roleAdmin = new Role(Role.Types.ROLE_ADMIN);
//                    roleRepository.saveAndFlush(roleAdmin);

                    Role roleUser = roleRepository.save(new Role(Role.Types.ROLE_USER));
                    Role roleAdmin = roleRepository.save(new Role(Role.Types.ROLE_ADMIN));

                    User user = new User("user", true);
//                    user.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
                    user.setRoles((Set<Role>) roleUser);
                    user.setPassword(passwordEncoder.encode("user"));

                    User admin = new User("admin", true);
//                    admin.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
                    admin.setRoles((Set<Role>) roleAdmin);
                    admin.setPassword(passwordEncoder.encode("admin"));

                    User test = new User("test", true);
//                    test.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
                    test.setRoles((Set<Role>) roleAdmin, roleUser);
                    test.setPassword(passwordEncoder.encode("test"));

                    userRepository.save(user);
                    userRepository.save(admin);
                    userRepository.save(test);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };
    }
}
