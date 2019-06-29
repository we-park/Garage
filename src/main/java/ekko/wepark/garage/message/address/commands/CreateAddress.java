package ekko.wepark.garage.message.address.commands;

import ekko.wepark.garage.domain.dto.Location;
import ekko.wepark.garage.message.MessageBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddress extends MessageBase {
    @TargetAggregateIdentifier
    private String garageId;
    private String addressId;
    private String address;
    private String city;
    private String zip;
}
