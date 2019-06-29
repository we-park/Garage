package ekko.wepark.garage.repository;

import ekko.wepark.garage.domain.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarageRepository extends JpaRepository<Garage, String> {
    @Query("SELECT garage FROM Garage WHERE garage.userId = :userId")
    List<Garage> findAllByUserId(@Param("userId") String userId);

    @Query("SELECT garage FROM Garage WHERE garage.address.zip = :zip")
    List<Garage> findByZip(@Param("zip") String zip);
}
