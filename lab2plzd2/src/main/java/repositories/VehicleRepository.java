package repositories;

import models.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("SELECT v FROM Vehicle v WHERE " +
            "(" +
            ":phrase is null OR :phrase = '' OR "+
            "upper(v.name) LIKE upper(:phrase) OR " +
            "upper(v.model) LIKE upper(:phrase) OR " +
            "upper(v.vehicleType.name) LIKE upper(:phrase)" +
            ") " +
            "AND " +
            "(:min is null OR :min <= v.price) " +
            "AND (:max is null OR :max >= v.price)")
    Page<Vehicle> findAllVehiclesUsingFilter(@Param("phrase") String p, @Param("min") Float priceMin, @Param("max") Float priceMax, Pageable pageable);
}
