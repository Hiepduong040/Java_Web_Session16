package ra.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.utils.BusType;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bus {
    private int id;

    private String licensePlate;


    private BusType busType;

    private int rowSeat;
    private int colSeat;
    private int totalSeat;
    private String image;


}
