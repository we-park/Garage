package ekko.wepark.garage.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Location {
    private double latitude;
    private double longitude;

    @JsonCreator
    public Location(@JsonProperty("latitude") double latitude, @JsonProperty("longitude") double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
