package dao;

import model.Measurement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MeasurementDao {

    private final String url;

    public MeasurementDao(String dbFilePath) {
        // JDBC URL for SQLite
        this.url = "jdbc:sqlite:" + dbFilePath;
        initDatabase();
    }

    private void initDatabase() {
        // creates DB file and table if not exist
        String sql = "CREATE TABLE IF NOT EXISTS measurements (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date TEXT NOT NULL," +
                "weight REAL," +
                "waist REAL," +
                "chest REAL," +
                "notes TEXT" +
                ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMeasurement(Measurement m) {
        String sql = "INSERT INTO measurements(date, weight, waist, chest, notes) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, m.getDate());
            ps.setDouble(2, m.getWeight());
            ps.setDouble(3, m.getWaist());
            ps.setDouble(4, m.getChest());
            ps.setString(5, m.getNotes());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Measurement> getAll() {
        List<Measurement> result = new ArrayList<>();

        String sql = "SELECT id, date, weight, waist, chest, notes " +
                "FROM measurements ORDER BY date DESC";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Measurement m = new Measurement(
                        rs.getInt("id"),
                        rs.getString("date"),
                        rs.getDouble("weight"),
                        rs.getDouble("waist"),
                        rs.getDouble("chest"),
                        rs.getString("notes")
                );
                result.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
