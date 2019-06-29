package ekko.wepark.garage.domain;

import ekko.wepark.garage.domain.dto.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {
    @Id
    private String addressId;
    private String address;
    private String city;
    private String zip;
    private Location latlon;
}
