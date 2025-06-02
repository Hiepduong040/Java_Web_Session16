package data.repository;

import org.springframework.stereotype.Repository;
import data.utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class LoginRepositoryImp implements LoginRepository{
    @Override
    public int checkLogin(String username, String password) {
        String sql = "{call check_login(?,?)}";

        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("result");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int register(String username, String password, String email) {
        String sql = "{CALL register_user(?, ?, ?)}";
        try (Connection conn = ConnectionDB.openConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            // Set input parameters
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);

            boolean hasResultSet = stmt.execute();

            if (hasResultSet) {
                try (ResultSet rs = stmt.getResultSet()) {
                    if (rs.next()) {
                        String message = rs.getString("message");
                        int result = rs.getInt("result");
                        System.out.println(message);
                        return result; // 1 = thành công, 0 = lỗi
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // lỗi mặc định
    }
}
