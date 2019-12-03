package services;

import controllers.commands.VehicleFilter;
import exceptions.VehicleNotFoundException;
import models.Accessory;
import models.Vehicle;
import models.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.AccessoryRepository;
import repositories.VehicleRepository;
import repositories.VehicleTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private AccessoryRepository accessoryRepository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Accessory> getAllAccessories() {
        return accessoryRepository.findAll();
    }

    @Override
    public List<VehicleType> getAllTypes() {
        return vehicleTypeRepository.findAll();
    }

    @Override
    public Page<Vehicle> getAllVehicles(VehicleFilter search, Pageable pageable) {
        Page page;
        if(search.isEmpty()){
            page = vehicleRepository.findAll(pageable);
        }else{
            page = vehicleRepository.findAllVehiclesUsingFilter(search.getPhraseLIKE(), search.getMinPrice(), search.getMaxPrice(), pageable);
        }

        return page;

    }

    @Transactional
    @Override
    public Vehicle getVehicle(Long id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        Vehicle vehicle = optionalVehicle.orElseThrow( () -> new VehicleNotFoundException(id) );
        vehicle.getAccessories().size();//dociągnięcie leniwych akcesoriów. Wymagana adnotacja @Transaction nad metodą lub klasą, aby findById nie zamknęło transakcji
        return vehicle;
    }

    @Override
    public void deleteVehicle(Long id) {
    // w przypadku usuwania obsługa wyjątku VehicleNotFoundException nie jest niezbędna dla bezpieczeństwa systemu
        if(vehicleRepository.existsById(id) == true){
            vehicleRepository.deleteById(id);
        }else{
            throw new VehicleNotFoundException(id);
        }
    }

    @Override
    public void saveVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }
}
