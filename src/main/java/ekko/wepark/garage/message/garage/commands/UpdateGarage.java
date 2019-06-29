package ekko.wepark.garage.message.garage.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateGarage {
    @TargetAggregateIdentifier
    private String garageId;
    private String description;
    private double price;
    private String imageUrl;
}
