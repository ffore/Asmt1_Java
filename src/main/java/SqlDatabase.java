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

//    Main only serves as a testing function
//    public static void main(String[] args) {
//        SqlDatabase db = new SqlDatabase();
//        db.connectToDatabase();
//    }

    public void connectToDatabase() {
        try {
            this.startConnection(); // does it need to be this.startConnection()??
            Connection connection = getConnection();
            if(!this.ppa2_dbExists(connection)){
                System.out.println("ppa2_db not found \ncreating one now...");
                this.createPpa2_db(connection);
                this.createDistanceTable(connection);
                this.createBMITable(connection);
            }
            else System.out.println("pap2_db found");
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
    }

    public void startConnection() throws Exception {
        loadDriver();
        Connection connection = this.establishConnection();
        this.setConnection(connection);
    }

    public static void loadDriver() throws Exception {
        System.out.println("Attempting to load Driver...");
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded!");
    }

    public Connection establishConnection() throws Exception {
        System.out.println("Attempting to connect to Database...");
        Connection connection = DriverManager.getConnection(this.getUrl(), this.getUser(), this.getPassword());
        System.out.println("Database connected!");
        return connection;
    }


    public boolean ppa2_dbExists(Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SHOW DATABASES;");
        Boolean dbExists = false;
        while(rs.next()){
            String db = rs.getString(1);
            if(db.equals("ppa2_db")) dbExists = true;
        }
        return dbExists;
    }

    public void createPpa2_db(Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE DATABASE ppa2_db;");
        System.out.println("Created ppa2_db");
    }

    public void createDistanceTable(Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        String stmt = "CREATE TABLE ppa2_db.Distance ( id INT AUTO_INCREMENT PRIMARY KEY, Timestamp VARCHAR(20), x1 DECIMAL(10,3), x2 DECIMAL(10,3), y1 DECIMAL(10,3), y2 DECIMAL(10,3), Result DECIMAL(10,3));";
        statement.executeUpdate(stmt);
        System.out.println("Created Distance table");
    }

    public void createBMITable(Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        String stmt = "CREATE TABLE ppa2_db.Bmi ( id INT AUTO_INCREMENT PRIMARY KEY, Timestamp VARCHAR(20), Height VARCHAR(10), Weight INT, Result DECIMAL(10,3));";
        statement.executeUpdate(stmt);
        System.out.println("Created Bmi table");
    }


    public void closeConnection() throws Exception {
        Connection connection = this.getConnection();
        connection.close();
    }

    public void writeToDistanceTable(String timestamp, double result, double[] input) throws Exception {
        Statement statement = this.createStatement();
        String query = this.createDistanceQuery(timestamp, result, input);
        int res = statement.executeUpdate(query);
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
        query.append("INSERT INTO ppa2_db.Distance (Timestamp, x1, x2, y1, y2, Result) VALUES (\"").append(timestamp).append("\" , ");

        for(int i = 0; i < 4; i++) {
            query.append(input[i]).append(", ");
        }

        query.append(result).append(");");
        System.out.println(query.toString());
        return query.toString();
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
