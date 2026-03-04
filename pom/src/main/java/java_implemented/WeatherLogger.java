package java_implemented;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;

public class WeatherLogger {
    private static final Dotenv dotenv = Dotenv.load();

    private static final String URL = Dotenv.load().get("DB_URL");
    private static final String USER = Dotenv.load().get("DB_USER");
    private static final String PASSWORD = Dotenv.load().get("DB_PASSWORD");

    private static final String INSERT_QUERY = "INSERT INTO weather_log (temperature, humidity, pressure) VALUES (?, ?, ?)";

    public static void log(float temperature, float humidity, float pressure) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Insert the new row
            String insertSQL = "INSERT INTO weather_log (temperature, humidity, pressure) VALUES (?, ?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
                insertStmt.setFloat(1, temperature);
                insertStmt.setFloat(2, humidity);
                insertStmt.setFloat(3, pressure);
                insertStmt.executeUpdate();
            }

            // Delete rows so only the latest 10 remain
            String cleanupSQL = """
            DELETE FROM weather_log
            WHERE id NOT IN (
                SELECT id FROM weather_log
                ORDER BY created_at DESC
                LIMIT 10
            );
        """;
            try (PreparedStatement cleanupStmt = conn.prepareStatement(cleanupSQL)) {
                cleanupStmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

