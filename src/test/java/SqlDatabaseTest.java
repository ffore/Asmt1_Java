import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SqlDatabaseTest {

    @Test
    public void testCanGetDatabaseUrl() {
        SqlDatabase database = new SqlDatabase();
        assertEquals(database.getUrl(), "jdbc:mysql://192.168.99.100:3306/ppa2_db");
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
        String expectedQuery = "INSERT INTO distance VALUES (" + formattedTime + ", 1.0, 1.0, 2.0, 2.0, 3.0)";
        String query = database.createDistanceQuery(formattedTime, result, input);
        assertEquals(query, expectedQuery);
    }

    public String getFormattedTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime timestamp = LocalDateTime.of(2019, 10, 16, 3, 45, 13);
        return timestamp.format(format);
    }

    /*******************************
            MOCKING / DOUBLES
     ******************************/

    @Mock
    private static Connection connection;

    @Mock
    private static Statement statement;

    @Test
    public void testCanGetConnection() {
        SqlDatabase database = new SqlDatabase(connection);
        assertEquals(database.getConnection(), connection);
    }

    @Test
    public void testCanSetConnection() {
        SqlDatabase database = new SqlDatabase();
        database.setConnection(connection);
        assertEquals(database.getConnection(), connection);
    }

    @Test
    public void testCanCreateStatement() throws Exception {
        SqlDatabase database = new SqlDatabase(connection);
        when(connection.createStatement()).thenReturn(statement);

        Statement s = database.createStatement();
        assertEquals(s, statement);
    }

}
