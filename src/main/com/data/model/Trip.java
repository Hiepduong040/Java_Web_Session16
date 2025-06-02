package data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Trip {
    private int id;
    private String departure;
    private String destination;
    private String departureTime;
    private double price;
}
