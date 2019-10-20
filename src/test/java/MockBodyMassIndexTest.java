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
public class MockBodyMassIndexTest {

    @Mock
    private static SqlDatabase database = mock(SqlDatabase.class);

    @Test
    public void testWriteToTable() throws Exception {
        BodyMassIndex bmi = createBodyMassIndex();
        String timestamp = getFormattedTime();

        String height = bmi.getHeight();
        int weight = bmi.getWeight();
        double res = bmi.getBMI(height, weight);
        String cat = bmi.getBMICategory(res);

        when(database.writeToBmiTable(timestamp, res, cat, height, weight)).thenReturn(1);

        int result = bmi.writeToTable(timestamp);
        assertEquals(1, result);
    }

    public BodyMassIndex createBodyMassIndex() {
        String height = "5\'9\"";
        int weight = 143;
        return new BodyMassIndex(database, height, weight);
    }

    public String getFormattedTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime timestamp = LocalDateTime.now();
        return timestamp.format(format);
    }
}
