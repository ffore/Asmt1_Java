package main.java;

import java.sql.*;
import java.time.*;

public class SqlDatabase {


    private String url;
    private String user;
    private String password;
    private Connection connection;

    public SqlDatabase() {
        this.url = "jdbc:mysql://192.168.99.100:3306/";
        this.user = "root";
        this.password = "password";
    }

    public SqlDatabase(String url) {
        this.url = url;
        this.user = "root";
        this.password = "password";
    }

    public SqlDatabase(Connection c) {
        this.url = "jdbc:mysql://192.168.99.100:3306/";
        this.user = "root";
        this.password = "password";
        this.connection = c;
    }

//    Main only serves as a testing function
//    public static void main(String[] args) {
//        SqlDatabase db = new SqlDatabase();
//        db.connectToDatabase();
//    }

    public void connectToDatabase() {
        try {
            this.startConnection();
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
    }

    public void startConnection() throws Exception {
        Connection connection = this.establishConnection();
        this.setConnection(connection);
        this.checkIfDatabaseExists();
    }

    public void checkIfDatabaseExists() throws Exception {
        if(!this.ppa2_dbExists()) {
            System.out.println("ppa2_db not found \ncreating one now...");
            this.createDatabaseSchema();
        }
        else {
            System.out.println("ppa2_db found");
        }
    }

    public void createDatabaseSchema() throws Exception {
        this.createPpa2_db();
        this.createDistanceTable();
        this.createBMITable();
    }

    public Connection establishConnection() throws Exception {
        System.out.println("Attempting to connect to Database...");
        Connection connection = DriverManager.getConnection(this.getUrl(), this.getUser(), this.getPassword());
        System.out.println("Database connected!");
        return connection;
    }


    public boolean ppa2_dbExists() throws Exception {
        Connection connection = this.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SHOW DATABASES;");
        Boolean dbExists = false;
        while(rs.next()){
            String db = rs.getString(1);
            if(db.equals("ppa2_db")) dbExists = true;
        }
        return dbExists;
    }

    public void createPpa2_db() throws Exception {
        Connection connection = this.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE DATABASE ppa2_db;");
        System.out.println("Created ppa2_db");
    }

    public void createDistanceTable() throws Exception {
        Connection connection = this.getConnection();
        Statement statement = connection.createStatement();
        String stmt = "CREATE TABLE ppa2_db.Distance ( id INT AUTO_INCREMENT PRIMARY KEY, " +
                "Timestamp VARCHAR(20), x1 DECIMAL(10,3), y1 DECIMAL(10,3), " +
                "x2 DECIMAL(10,3), y2 DECIMAL(10,3), Result DECIMAL(10,3));";
        statement.executeUpdate(stmt);
        System.out.println("Created Distance table");
    }

    public void createBMITable() throws Exception {
        Connection connection = this.getConnection();
        Statement statement = connection.createStatement();
        String stmt = "CREATE TABLE ppa2_db.Bmi ( id INT AUTO_INCREMENT PRIMARY KEY, " +
                "Timestamp VARCHAR(20), Height VARCHAR(10), Weight INT, BmiCategory VARCHAR(20), Result DECIMAL(10,3));";
        statement.executeUpdate(stmt);
        System.out.println("Created Bmi table");
    }


    public void closeConnection() throws Exception {
        Connection connection = this.getConnection();
        connection.close();
    }

    public int writeToDistanceTable(String timestamp, double result, double[] input) throws Exception {
        Statement statement = this.createStatement();
        String query = this.createDistanceQuery(timestamp, result, input);
        int res = statement.executeUpdate(query);
        return res;
    }

    public int writeToBmiTable(String timestamp, double bmi, String bmiCategory, String height, int weight) throws Exception {
        Statement statement = this.createStatement();
        String query = this.createBmiQuery(timestamp, height, weight, bmi, bmiCategory);
//        System.out.println(query);
        int res = statement.executeUpdate(query);
        return res;
    }

    public void printDistanceTable() throws Exception{
        ResultSet resultSet = readDistanceTable();
        System.out.println("id\tTimestamp\t\t\t\t\tx1\t\ty1\t\tx2\t\ty2\t\t\tResult");
        System.out.println("---------------------------------------------------------------------------------------");
        while(resultSet.next()){
            String id = resultSet.getString("id");
            String TimeStamp = resultSet.getString("Timestamp");
            String x1 = resultSet.getString("x1");
            String x2 = resultSet.getString("x2");
            String y1 = resultSet.getString("y1");
            String y2 = resultSet.getString("y2");
            String Result = resultSet.getString("Result");
            System.out.println(id + "\t" + TimeStamp + "\t\t\t" + x1 + "\t" + y1 + "\t" + x2 + "\t" + y2 + "\t\t" + Result);
            System.out.println("---------------------------------------------------------------------------------------");
        }
    }

    public void printBmiTable() throws Exception {
        ResultSet resultSet = readBmiTable();
        System.out.println("id\tTimestamp\t\t\t\t\tHeight\t\tWeight\t\tBmiCategory\t\t\tResult");
        System.out.println("---------------------------------------------------------------------------------------");
        while(resultSet.next()){
            String id = resultSet.getString("id");
            String TimeStamp = resultSet.getString("Timestamp");
            String Height = resultSet.getString("Height");
            String Weight = resultSet.getString("Weight");
            String BmiCategory = resultSet.getString("BmiCategory");
            String Result = resultSet.getString("Result");
            if(BmiCategory.equals("Normal weight")) {
                System.out.println(id + "\t" + TimeStamp + "\t\t\t" + Height + "\t\t" + Weight + "\t\t\t" + BmiCategory + "\t\t" + Result);
            }
            else if(BmiCategory.equals("Underweight") || BmiCategory.equals("Overweight")) {
                System.out.println(id + "\t" + TimeStamp + "\t\t\t" + Height + "\t\t" + Weight + "\t\t\t" + BmiCategory + "\t\t\t" + Result);
            }
            else {
                System.out.println(id + "\t" + TimeStamp + "\t\t\t" + Height + "\t\t" + Weight + "\t\t\t" + BmiCategory + "\t\t\t\t" + Result);
            }
            System.out.println("---------------------------------------------------------------------------------------");
        }
    }

    public ResultSet readDistanceTable() throws Exception {
        Statement statement = this.createStatement();
        String query = "SELECT * FROM ppa2_db.Distance;";
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }

    public ResultSet readBmiTable() throws Exception {
        Statement statement = this.createStatement();
        String query = "SELECT * FROM ppa2_db.Bmi;";
        ResultSet rs = statement.executeQuery(query);
        return rs;
    }

//    Uncomment when createBodyMassIndexQuery() is finished
//    public void writeToBodyMassIndexTable(String timestamp, String result, String input) throws Exception {
//        Statement statement = this.createStatement();
//        String query = this.createBodyMassIndexQuery();
//        ResultSet resultSet = statement.executeQuery(query);
//    }

    public Statement createStatement() throws Exception {
        Connection c = this.getConnection();
        return c.createStatement();
    }

    public String createDistanceQuery(String timestamp, double result, double[] input) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ppa2_db.Distance (Timestamp, x1, y1, x2, y2, Result) VALUES (\"").append(timestamp).append("\", ");

        for(int i = 0; i < 4; i++) {
            query.append(input[i]).append(", ");
        }

        query.append(result).append(");");
        return query.toString();
    }

    public String createBmiQuery(String timestamp, String height, int weight, double bmi, String bmiCategory) {
        String escapedHeight = escapeHeight(height);
        String query = "INSERT INTO ppa2_db.Bmi (Timestamp, Height, Weight, Bmicategory, Result) VALUES (\"" +
                timestamp + "\", \"" + escapedHeight + "\", " + weight + ", " + "\"" + bmiCategory + "\", "  + bmi + ");";
        return query;
    }

    public String escapeHeight(String height) {
        int len = height.length();
        String escaped = height.substring(0,len-1)+"\\\"";
        return escaped;
    }

//    Populate accordingly
//    public String createBodyMassIndexQuery() {
//        StringBuilder query = new StringBuilder();
//        query.append("INSERT INTO bmi VALUES (");
//
//        return query.toString();
//    }

    public String getUrl() {
        return this.url;
    }

    public String getUser() {
        return this.user;
    }

    public String getPassword() {
        return this.password;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public void setConnection(Connection c) {
        this.connection = c;
    }


}
