package repositories;

import models.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {
}
