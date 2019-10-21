import main.java.BodyMassIndex;
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

    @Mock
    private static SqlDatabase verifyDatabaseCall = mock(SqlDatabase.class);

    @Test
    public void testWriteToTable() throws Exception {
        BodyMassIndex bmi = createBodyMassIndex(database);
        String timestamp = getFormattedTime();

        String height = bmi.getHeight();
        int weight = bmi.getWeight();
        double res = bmi.getBMI(height, weight);
        String cat = bmi.getBMICategory(res);

        when(database.writeToBmiTable(timestamp, res, cat, height, weight)).thenReturn(1);

        int result = bmi.writeToTable(timestamp);
        assertEquals(1, result);
    }

    public BodyMassIndex createBodyMassIndex(SqlDatabase db) {
        String height = "5\'9\"";
        int weight = 143;
        return new BodyMassIndex(db, height, weight);
    }

    public String getFormattedTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime timestamp = LocalDateTime.now();
        return timestamp.format(format);
    }

    @Mock
    private static SqlDatabase database2 = mock(SqlDatabase.class);

    @Test
    public void testWriteToDatabase() throws Exception {
        BodyMassIndex bmi = createBodyMassIndex(database2);
        String timestamp = getFormattedTime();

        String height = bmi.getHeight();
        int weight = bmi.getWeight();
        double res = bmi.getBMI(height, weight);
        String cat = bmi.getBMICategory(res);

        when(database2.writeToBmiTable(timestamp, res, cat, height, weight)).thenReturn(1);

        int result = bmi.writeToDatabase();
        assertEquals(1, result);
    }

    @Mock
    private static SqlDatabase database3 = mock(SqlDatabase.class);

    @Test
    public void testWriteToTableFails() throws Exception {
        BodyMassIndex bmi = createBodyMassIndex(database3);
        String timestamp = getFormattedTime();

        String height = bmi.getHeight();
        int weight = bmi.getWeight();
        double res = bmi.getBMI(height, weight);
        String cat = bmi.getBMICategory(res);

        when(database3.writeToBmiTable(timestamp, res, cat, height, weight)).thenThrow(new Exception("Writing Failed!"));

        try{
            bmi.writeToDatabase();
        }
        catch(Exception e){
            assertEquals(e.getMessage(), "Writing Failed!");
        }
    }

    @Test
    public void testWriteToTableActuallyWrites() throws Exception {
        BodyMassIndex bmi = createBodyMassIndexForVerification();
        String timestamp = getFormattedTime();

        String height = bmi.getHeight();
        int weight = bmi.getWeight();
        double res = bmi.getBMI(height, weight);
        String cat = bmi.getBMICategory(res);

        when(verifyDatabaseCall.writeToBmiTable(timestamp, res, cat, height, weight)).thenReturn(1);

        bmi.writeToDatabase();

        verify(verifyDatabaseCall, times(1)).writeToBmiTable(timestamp, res, cat, height, weight);

    }

    public BodyMassIndex createBodyMassIndexForVerification(){
        String height = "6\'0\"";
        int weight = 200;
        double bmi = 27.8;
        return new BodyMassIndex(verifyDatabaseCall, height, weight, bmi);
    }

}
