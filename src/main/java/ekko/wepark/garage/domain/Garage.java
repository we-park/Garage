package ekko.wepark.garage.domain;

import ekko.wepark.garage.domain.dto.Location;
import ekko.wepark.garage.message.garage.commands.CreateGarage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.text.DecimalFormat;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Aggregate
public class Garage {
    @Id
    @AggregateIdentifier
    private String garageId;
    private String userId;
    private String description;
    private double price;
    private boolean deleted;

    @OneToMany
    private List<Photo> photos;

    @ManyToOne
    private Address address;

    @OneToOne
    private Size size;

//    public void setDecimalPlaces() {
//        DecimalFormat decimalFormat = new DecimalFormat();
//        decimalFormat.setMaximumFractionDigits(2);
//
//        this.price = Double.valueOf(decimalFormat.format(price));
//        this.length = Double.valueOf(decimalFormat.format(length));
//        this.width = Double.valueOf(decimalFormat.format(width));
//        this.height = Double.valueOf(decimalFormat.format(height));
//    }

    @CommandHandler
    public Garage(CreateGarage createGarage) {

    }
}
