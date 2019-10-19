import java.sql.*;


public class SqlDatabase {

    public static void main(String[] args) {
        connectToDatabase();

    }

    public static void connectToDatabase() {
        try {
            Connection connection = startConnection();
            if(!ppa2_dbExists(connection)){
                System.out.println("ppa2_db not found \ncreating one now...");
                createPpa2_db(connection);
                createDistanceTable(connection);
                createBMITable(connection);
            }
            else System.out.println("pap2_db found");
        } catch (Exception e) {
            System.out.println(e);
            return;
        }
    }

    public static Connection startConnection() throws Exception {
        String url = "jdbc:mysql://192.168.99.100:3306/";
        String user = "root";
        String password = "pass"; // probably gotta switch to 'password'

        loadDriver();
        Connection connection = establishConnection(url, user, password);
        return connection;
//        Statement statement = connection.createStatement();
//        ResultSet result = statement.executeQuery("SELECT * FROM danny_test.people");
//
//        System.out.println("Results from Query...");
//        result.next();
//        System.out.println(result.getInt("age") + " " +
//                result.getString("name"));

//        closeConnection(connection);
    }

    public static void loadDriver() throws Exception {
        System.out.println("Attempting to load Driver...");
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded!");
    }

    public static Connection establishConnection(String url, String user, String password) throws Exception {
        System.out.println("Attempting to connect to Database...");
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("Database connected!");
        return connection;
    }

    public static boolean ppa2_dbExists(Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SHOW DATABASES;");
        Boolean dbExists = false;
        while(rs.next()){
            String db = rs.getString(1);
            if(db.equals("ppa2_db")) dbExists = true;
        }
        return dbExists;
    }

    public static void createPpa2_db(Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE DATABASE ppa2_db;");
        System.out.println("Created ppa2_db");
    }

    public static void createDistanceTable(Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        String stmt = "CREATE TABLE ppa2_db.Distance ( id INT AUTO_INCREMENT PRIMARY KEY, Timestamp VARCHAR(20), x1 DECIMAL(10,3), x2 DECIMAL(10,3), y1 DECIMAL(10,3), y2 DECIMAL(10,3), Result DECIMAL(10,3));";
        statement.executeUpdate(stmt);
        System.out.println("Created Distance table");
    }

    public static void createBMITable(Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        String stmt = "CREATE TABLE ppa2_db.Bmi ( id INT AUTO_INCREMENT PRIMARY KEY, Timestamp VARCHAR(20), Height VARCHAR(10), Weight INT, Result DECIMAL(10,3));";
        statement.executeUpdate(stmt);
        System.out.println("Created Bmi table");
    }

    public static void closeConnection(Connection connection) throws Exception {
        connection.close();
    }
}
