import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class SqlDatabaseTest {

    @Test
    public void testCanGetDatabaseUrl() {
        SqlDatabase database = new SqlDatabase();
        assertEquals(database.getUrl(), "jdbc:mysql://192.168.99.100:3306/");
    }

    @Test
    public void testUrlSpecifiedConstructor() {
        SqlDatabase database = new SqlDatabase("Hello World");
        assertEquals(database.getUrl(), "Hello World");
    }

    @Test
    public void testCanGetUser() {
        SqlDatabase database = new SqlDatabase();
        assertEquals(database.getUser(), "root");
    }

    @Test
    public void testCanGetPassword() {
        SqlDatabase database = new SqlDatabase();
        assertEquals(database.getPassword(), "password");
    }

    @Test
    public void testCreateDistanceQuery() {
        SqlDatabase database = new SqlDatabase();
        String formattedTime = getFormattedTime();
        double result = 3.0;
        double[] input = {1.0, 1.0, 2.0, 2.0};
        String expectedQuery = "INSERT INTO ppa2_db.Distance (Timestamp, x1, y1, x2, y2, Result) VALUES (\""
                + formattedTime + "\", 1.0, 1.0, 2.0, 2.0, 3.0);";
        String query = database.createDistanceQuery(formattedTime, result, input);
        assertEquals(query, expectedQuery);
    }

    public String getFormattedTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime timestamp = LocalDateTime.of(2019, 10, 16, 3, 45, 13);
        return timestamp.format(format);
    }

}
