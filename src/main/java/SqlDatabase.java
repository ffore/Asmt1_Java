import java.sql.*;
import java.time.*;

public class SqlDatabase {

    private String url;
    private String user;
    private String password;
    private Connection connection;

    public SqlDatabase() {
        this.url = "jdbc:mysql://192.168.99.100:3306/ppa2_db";
        this.user = "root";
        this.password = "password";
    }

    public SqlDatabase(String url) {
        this.url = url;
        this.user = "root";
        this.password = "password";
    }

    public SqlDatabase(Connection c) {
        this.url = "jdbc:mysql://192.168.99.100:3306/ppa2_db";
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

    public void closeConnection() throws Exception {
        Connection connection = this.getConnection();
        connection.close();
    }

    public void writeToDistanceTable(String timestamp, double result, double[] input) throws Exception {
        Statement statement = this.createStatement();
        String query = this.createDistanceQuery(timestamp, result, input);
        ResultSet resultSet = statement.executeQuery(query);
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
        query.append("INSERT INTO distance VALUES (").append(timestamp).append(", ");

        for(int i = 0; i < 4; i++) {
            query.append(input[i]).append(", ");
        }

        query.append(result).append(")");

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
