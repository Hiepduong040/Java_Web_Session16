package ra.repository;

import org.springframework.stereotype.Repository;
import ra.model.Bus;
import ra.utils.BusType;
import ra.utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BusRepositoryImp implements BusRepository{
    public int addBus(Bus bus) {
        String sql = "{CALL add_bus(?, ?, ?, ?, ?)}";
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, bus.getLicensePlate());
            stmt.setString(2, String.valueOf(bus.getBusType()));
            stmt.setInt(3, bus.getRowSeat());
            stmt.setInt(4, bus.getColSeat());
            stmt.setString(5, bus.getImage());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Bus> getAllBus() {
        List<Bus> list = new ArrayList<>();
        String sql = "{call get_all_bus()}";

        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Bus bus = new Bus(
                        rs.getInt("id"),
                        rs.getString("licensePlate"),
                        BusType.valueOf(rs.getString("busType")),
                        rs.getInt("rowSeat"),
                        rs.getInt("colSeat"),
                        rs.getInt("totalSeat"),
                        rs.getString("image")
                );
                list.add(bus);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public void updateBus(Bus bus) {
        String sql = "{call update_bus(?,?,?,?,?,?)}";

        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, bus.getId());
            cs.setString(2, bus.getLicensePlate());
            cs.setString(3, String.valueOf(bus.getBusType()));
            cs.setInt(4, bus.getRowSeat());
            cs.setInt(5, bus.getColSeat());
            cs.setString(6, bus.getImage());

            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBus(int id) {
        String sql = "{call delete_bus(?)}";

        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, id);
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Bus findById(int id) {
        Bus bus = null;
        String sql = "{call get_bus_by_id(?)}";
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement cs = conn.prepareCall(sql)) {
            cs.setInt(1, id);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                bus = new Bus(
                        rs.getInt("id"),
                        rs.getString("licensePlate"),
                        BusType.valueOf(rs.getString("busType")), // convert String -> Enum
                        rs.getInt("rowSeat"),
                        rs.getInt("colSeat"),
                        rs.getInt("totalSeat"),
                        rs.getString("image")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bus;
    }

}
