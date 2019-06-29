package ekko.wepark.garage.message.size.commands;

import ekko.wepark.garage.message.MessageBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSize extends MessageBase {
    @TargetAggregateIdentifier
    private String garageId;
    private double length;
    private double width;
    private double height;
}
