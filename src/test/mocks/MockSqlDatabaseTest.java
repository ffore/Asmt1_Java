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
import static org.mockito.Mockito.when;

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

}
