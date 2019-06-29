package ekko.wepark.garage.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Size {
    @Id
    private String sizeId;
    private double length;
    private double width;
    private double height;
}
