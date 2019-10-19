import main.java.SqlDatabase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockShortestDistanceTest {

    @Mock
    private static SqlDatabase database = mock(SqlDatabase.class);

    @Mock
    private static SqlDatabase verifyDatabaseCall = mock(SqlDatabase.class);

    @Test
    public void testWriteToTable() throws Exception {
        ShortestDistance distance = createShortestDistance();
        String timestamp = getFormattedTime();

        when(database.writeToDistanceTable(timestamp,
                distance.getDistance(),
                distance.getCoordinates()))
                .thenReturn(1);

        int result = distance.writeToTable(timestamp);
        assertEquals(result, 1);
    }

    public ShortestDistance createShortestDistance() {
        double[] input = {1, 1, 2, 2};
        double result = 1.414;
        return new ShortestDistance(database, input, result);
    }

    public String getFormattedTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime timestamp = LocalDateTime.now();
        return timestamp.format(format);
    }

    @Test
    public void testWriteToDatabase() throws Exception {
        ShortestDistance distance = createShortestDistance();
        String timestamp = getFormattedTime();

        when(database.writeToDistanceTable(timestamp,
                distance.getDistance(),
                distance.getCoordinates()))
                .thenReturn(1);

        int result = distance.writeToDatabase();
        assertEquals(result, 1);
    }

    @Test
    public void testWriteToTableFails() throws Exception {
        ShortestDistance distance = createShortestDistance();
        String timestamp = getFormattedTime();

        when(database.writeToDistanceTable(timestamp,
                distance.getDistance(),
                distance.getCoordinates()))
                .thenThrow(new Exception("Writing Failed!"));

        try {
            distance.writeToDatabase();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "Writing Failed!");
        }
    }

    @Test
    public void testWriteToTableActuallyWrites() throws Exception {
        ShortestDistance distance = createShortestDistanceForVerification();

        String timestamp = getFormattedTime();
        double dist = distance.getDistance();
        double[] input = distance.getCoordinates();

        when(verifyDatabaseCall.writeToDistanceTable(timestamp, dist, input)).thenReturn(1);

        distance.writeToDatabase();

        verify(verifyDatabaseCall, times(1)).writeToDistanceTable(timestamp, dist, input);
    }

    public ShortestDistance createShortestDistanceForVerification() {
        double[] input = {1, 1, 2, 2};
        double result = 1.414;
        return new ShortestDistance(verifyDatabaseCall, input, result);
    }

}
