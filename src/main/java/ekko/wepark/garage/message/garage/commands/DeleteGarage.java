package ekko.wepark.garage.message.garage.commands;

import ekko.wepark.garage.message.MessageBase;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class DeleteGarage extends MessageBase {
    @TargetAggregateIdentifier
    private String garageId;
}
