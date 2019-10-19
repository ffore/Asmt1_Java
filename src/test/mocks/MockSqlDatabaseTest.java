import main.java.SqlDatabase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.Statement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockSqlDatabaseTest {

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

    @Test
    public void testCanWriteToTable() throws Exception {
        SqlDatabase database = new SqlDatabase(connection);
        String query = this.createTestQuery(database);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeUpdate(query)).thenReturn(1);

        String formattedTime = getFormattedTime();
        double result = 3.0;
        double[] input = {1.0, 1.0, 2.0, 2.0};

        int res = database.writeToDistanceTable(formattedTime, result, input);
        assertEquals(res, 1);
    }

    public String getFormattedTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime timestamp = LocalDateTime.of(2019, 10, 16, 3, 45, 13);
        return timestamp.format(format);
    }

    public String createTestQuery(SqlDatabase database) {
        String formattedTime = getFormattedTime();
        double result = 3.0;
        double[] input = {1.0, 1.0, 2.0, 2.0};
        return database.createDistanceQuery(formattedTime, result, input);
    }

    @Test
    public void testCanCloseConnection() throws Exception {
        SqlDatabase database = new SqlDatabase(connection);
        database.closeConnection();
        verify(connection, times(1)).close();
    }

    @Test
    public void testCanCreateDistanceTable() throws Exception {
        SqlDatabase database = new SqlDatabase(connection);
        String query = getDistanceTableCreationQuery();
        when(connection.createStatement()).thenReturn(statement);

        database.createDistanceTable();

        verify(connection, times(1)).createStatement();
        verify(statement, times(1)).executeUpdate(query);

    }

    public String getDistanceTableCreationQuery() {
        return "CREATE TABLE ppa2_db.Distance ( id INT AUTO_INCREMENT PRIMARY KEY, " +
                "Timestamp VARCHAR(20), x1 DECIMAL(10,3), y1 DECIMAL(10,3), " +
                "x2 DECIMAL(10,3), y2 DECIMAL(10,3), Result DECIMAL(10,3));";
    }

    @Test
    public void testCanCreateBMITable() throws Exception {
        SqlDatabase database = new SqlDatabase(connection);
        String query = getBMITableCreationQuery();
        when(connection.createStatement()).thenReturn(statement);

        database.createBMITable();

        verify(connection, times(1)).createStatement();
        verify(statement, times(1)).executeUpdate(query);
    }

    public String getBMITableCreationQuery() {
        return "CREATE TABLE ppa2_db.Bmi ( id INT AUTO_INCREMENT PRIMARY KEY, " +
                "Timestamp VARCHAR(20), Height VARCHAR(10), Weight INT, Result DECIMAL(10,3));";
    }

    @Test
    public void testCanCreatePPA2Database() throws Exception {
        SqlDatabase database = new SqlDatabase(connection);
        String query = "CREATE DATABASE ppa2_db;";
        when(connection.createStatement()).thenReturn(statement);

        database.createPpa2_db();

        verify(connection, times(1)).createStatement();
        verify(statement, times(1)).executeUpdate(query);
    }
    
}
