package ra.repository;

import org.springframework.stereotype.Repository;
import ra.model.Trip;
import ra.utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class TripRepositoryImp implements TripRepository{
    @Override
    public List<Trip> findAll() {
        List<Trip> trips = new ArrayList<>();
        String sql = "{CALL get_all_trips()}";

        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                Trip trip = new Trip();
                trip.setId(rs.getInt("id"));
                trip.setDeparture(rs.getString("departure"));
                trip.setDestination(rs.getString("destination"));
                trip.setDepartureTime(rs.getString("departure_time"));
                trip.setPrice(rs.getDouble("price"));
                trips.add(trip);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trips;
    }

    public List<Trip> searchTrips(String departure, String destination) {
        List<Trip> trips = new ArrayList<>();
        String sql = "{CALL search_trips(?, ?)}";

        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, departure);
            stmt.setString(2, destination);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Trip trip = new Trip();
                trip.setId(rs.getInt("id"));
                trip.setDeparture(rs.getString("departure"));
                trip.setDestination(rs.getString("destination"));
                trip.setDepartureTime(rs.getString("departure_time"));
                trip.setPrice(rs.getDouble("price"));
                trips.add(trip);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trips;
    }

}
