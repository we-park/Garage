package ekko.wepark.garage.message.garage.commands;

import ekko.wepark.garage.message.MessageBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGarage extends MessageBase {
    @TargetAggregateIdentifier
    private String garageId;
    private String description;
    private double price;
    private String imageUrl;
    private boolean deleted;
}
