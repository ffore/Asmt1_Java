import main.java.Server;
import main.java.ShortestDistance;
import main.java.SqlDatabase;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServerTest {

    @Mock
    private static BufferedReader distPostInput = mock(BufferedReader.class);

    @Mock
    private static SqlDatabase distanceWriteToDb = mock(SqlDatabase.class);

    @Test
    public void testCanDetectDistancePostRequest() throws Exception {
        Server server = new Server(distanceWriteToDb);
        JSONArray expectedResult = createDistanceJsonArray();
        String timestamp = getFormattedTime();
        double[] input = {1.23333, 1, 2, 2};
        double distance = 1.26;

        when(distPostInput.readLine()).thenReturn("POST /distance?x1=1.23333&y1=1&x2=2&y2=2 HTTP/1.1");

        JSONArray result = server.divertToProperRequest(distPostInput);

        assertEquals(result.toString(), expectedResult.toString());
        verify(distanceWriteToDb, times(1)).writeToDistanceTable(timestamp, distance, input);
    }

    public JSONArray createDistanceJsonArray() throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Distance", 1.26);
        return new JSONArray().put(jsonObject);
    }

    public String getFormattedTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime timestamp = LocalDateTime.now();
        return timestamp.format(format);
    }

    @Mock
    private static BufferedReader bmiPostInput = mock(BufferedReader.class);

    @Mock
    private static SqlDatabase bmiWriteToDb = mock(SqlDatabase.class);

    @Test
    public void testCanDetectBmiPostRequest() throws Exception {
        Server server = new Server(bmiWriteToDb);
        JSONArray expectedResult = createBmiJsonArray();
        String timestamp = getFormattedTime();
        double bmi = 18.7;
        String cat = "Normal weight";
        String height = "5'8\"";
        int weight = 120;

        when(bmiPostInput.readLine()).thenReturn("POST /bmi?height=5%278%22&weight=120 HTTP/1.1");

        JSONArray result = server.divertToProperRequest(bmiPostInput);

        assertEquals(result.toString(), expectedResult.toString());
        verify(bmiWriteToDb, times(1)).writeToBmiTable(timestamp, bmi, cat, height, weight);
    }

    public JSONArray createBmiJsonArray() {
        Map map = new LinkedHashMap();
        map.put("Bmi", 18.7);
        map.put("Category", "Normal weight");
        JSONObject jsonObject = new JSONObject(map);
        return new JSONArray().put(jsonObject);
    }

    @Mock
    private static BufferedReader distGetInput = mock(BufferedReader.class);

    @Mock
    private static SqlDatabase distReadDb = mock(SqlDatabase.class);

    @Mock
    private static ResultSet distTableContents = mock(ResultSet.class);

    @Test
    public void testCanDetectDistanceGetRequest() throws Exception {
        Server server = new Server(distReadDb);

        when(distGetInput.readLine()).thenReturn("GET /distance HTTP/1.1");
        when(distReadDb.readDistanceTable()).thenReturn(distTableContents);
        server.divertToProperRequest(distGetInput);

        verify(distReadDb, times(1)).readDistanceTable();
    }

    @Mock
    private static BufferedReader bmiGetInput = mock(BufferedReader.class);

    @Mock
    private static SqlDatabase bmiReadDb = mock(SqlDatabase.class);

    @Mock
    private static ResultSet bmiTableContents = mock(ResultSet.class);

    @Test
    public void testCanDetectBmiGetRequest() throws Exception {
        Server server = new Server(bmiReadDb);

        when(bmiGetInput.readLine()).thenReturn("GET /bmi HTTP/1.1");
        when(bmiReadDb.readBmiTable()).thenReturn(bmiTableContents);
        server.divertToProperRequest(bmiGetInput);

        verify(bmiReadDb, times(1)).readBmiTable();
    }
}
