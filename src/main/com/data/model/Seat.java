package data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    private int id;

    private String nameSeat;
    private double price;
    private boolean status;

    private Bus bus;
}
