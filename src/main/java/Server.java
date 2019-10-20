import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import main.java.SqlDatabase;

import org.json.*;

public class Server {

    private ServerSocket server;
    private SqlDatabase database;

    public Server(SqlDatabase database) {
        this.database = database;
        try{
            this.server = createServerSocket();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }

    public void startServer() throws Exception{
        ServerSocket server = this.getServer();
        this.acceptRequests(server);
    }

    public void getDistanceTable() {
        System.out.println("server about to print distance table");
        try {
            this.database.printDistanceTable();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public void acceptRequests(ServerSocket server) throws Exception {
        while (true){
            try (Socket socket = server.accept()) {
                System.out.println("Client found");
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                JSONArray result = this.divertToProperRequest(in);
                this.sendMessage(socket, result);
            } catch (SocketException e) {
                System.out.println("Closing server socket");
                break;
            }
        }
    }

    public JSONArray divertToProperRequest(BufferedReader in) throws Exception {
        String originalRequest = in.readLine();

        if(originalRequest == null)
            return new JSONArray();

        System.out.println("Request is:" + originalRequest);
        String[] requestInfo = originalRequest.split(" ");
        JSONArray result = new JSONArray();

        if(requestInfo[0].equals("GET")) {
            System.out.println("Received Get Request!");
            result = this.getTableInfo(requestInfo[1]);
        } else {
            System.out.println("Received Post Request!");
//            postToTable(requestInfo[1]);
        }

        return result;
    }

    public JSONArray getTableInfo(String tableName) {
        if(tableName.contains("distance")) {
            System.out.println("User wants Distance Table!");
            return this.getDistanceTableInfo();
        } else if (tableName.contains("bmi")) {
            System.out.println("User wants BMI Table!");
            return this.getBMITableInfo();
        } else {
            System.out.println("User wants something not supported!");
            return new JSONArray();
        }
    }

    public JSONArray getDistanceTableInfo() {
        SqlDatabase database = this.getDatabase();
        JSONArray json = new JSONArray();
        try {
            ResultSet resultSet = database.readDistanceTable();
            json = this.convertToDistanceJson(resultSet);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return json;
    }

    public JSONArray getBMITableInfo() {
        SqlDatabase database = this.getDatabase();
        JSONArray json = new JSONArray();
        try {
            ResultSet resultSet = database.readBmiTable();
            json = this.convertToBmiJson(resultSet);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return json;
    }

    public JSONArray convertToDistanceJson(ResultSet resultSet) throws Exception{
//        https://stackoverflow.com/questions/3948206/json-order-mixed-up
        JSONArray array = new JSONArray();
        while(resultSet.next()) {
            Map map = new LinkedHashMap();
            map.put("ID", resultSet.getString("id"));
            map.put("TimeStamp", resultSet.getString("Timestamp"));
            map.put("X1", resultSet.getString("x1"));
            map.put("Y1", resultSet.getString("y1"));
            map.put("X2", resultSet.getString("x2"));
            map.put("Y2", resultSet.getString("y2"));
            map.put("Result", resultSet.getString("Result"));
            JSONObject jsonObject = new JSONObject(map);
            array.put(jsonObject);
        }
        System.out.println(array.toString());
        return array;
    }

    public JSONArray convertToBmiJson(ResultSet resultSet) throws Exception{
//        https://stackoverflow.com/questions/3948206/json-order-mixed-up
        JSONArray array = new JSONArray();
        while(resultSet.next()) {
            Map map = new LinkedHashMap();
            map.put("ID", resultSet.getString("id"));
            map.put("TimeStamp", resultSet.getString("Timestamp"));
            map.put("Height", resultSet.getString("Height"));
            map.put("Weight", resultSet.getString("Weight"));
            map.put("BmiCategory", resultSet.getString("BmiCategory"));
            map.put("Result", resultSet.getString("Result"));
            JSONObject jsonObject = new JSONObject(map);
            array.put(jsonObject);
        }
        System.out.println(array.toString());
        return array;
    }

    public void sendMessage(Socket socket, JSONArray result) throws IOException {
        String httpResponse = "HTTP/1.1 200 OK\r\n\r\n" + result.toString();
        socket.getOutputStream().write(httpResponse.getBytes("UTF-8"));
    }

    public ServerSocket createServerSocket() throws IOException {
        System.out.println("Listening for connection on port 5000 ....");
        return new ServerSocket(5000);
    }

    public ServerSocket getServer() {
        return this.server;
    }

    public SqlDatabase getDatabase() {
        return this.database;
    }

    public void closeConnection() throws Exception {
        this.server.close();
    }
}
